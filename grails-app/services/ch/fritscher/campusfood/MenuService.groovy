package ch.fritscher.campusfood

class MenuService {

    static transactional = true
	def alertService
	def grailsApplication

	def parse(){
		def rss =  new XmlSlurper().parse( "https://www2.unil.ch/menus/rss/menus-du-jour/" )
		def logtxt = ""
		rss.channel.item.eachWithIndex{ item, i ->
			Date dateServed = new Date(item.pubDate.text())
			
		    def titlesrc = item.title.text().split("-")		
		    
			String locationName = titlesrc[0].trim()
			String menuName = titlesrc[1].trim()
			String content = item.description.text()
			logtxt += this.createMeal(dateServed, locationName, menuName, content, "UNIL")			
		}
		return logtxt
	}
	
	
	def parseEpfl(Date dateServed){
		def url = "https://menus.epfl.ch/cgi-bin/rssMenus?date=${dateServed.format('dd/MM/yyyy')}"
		def rss =  new XmlSlurper().parse(url)
		def logtxt = ""
		rss.channel.item.eachWithIndex{ item, i ->			
			def titlesrc = item.title.text().split(':')
			
			String locationName = titlesrc[0].trim()
			String menuName = titlesrc[1].trim()
			String content = item.description.text()
			logtxt += this.createMeal(dateServed, locationName, menuName, content, "EPFL")
		}
		return logtxt
	}
	
	def parseGeo(week=0){
		def logtxt = ""
		(0..4).each{ n ->
			def url =  "http://geopolis.sv-restaurant.ch/fr/plan-des-menus.html?addGP%5Bshift%5D=$n"
			def html = url.toURL().getText()
			def match = html =~ /(?s)html-menu"\>(.*)\<\/div\>\<!--/
			def menu = match[0][1]
			menu = (menu =~ /(?is)target=".*?"|onclick=".*?"|onfocus=".*?"|<input.*?>|\<g:plusone.*?\<\/g:plusone\>/).replaceAll("")
			menu = (menu =~ /&([^;]+(?!(?:\\w|;)))/).replaceAll("&amp;${1}")
			menu = (menu =~ /(?is)<div class="social-network">.*?<\/div>/).replaceAll("")
			menu = (menu =~ /(?is)<div class="specials-logo-right">.*?<\/div>/).replaceAll("")
			menu = (menu =~ /(?is)<img.*?>/).replaceAll("")
			def rss =  new XmlSlurper().parseText(menu)
			def mdate = rss.div.findAll{ it.@class == 'date' }.toString() =~ /\d{1,2}.\d{1,2}.\d{4}/
			Date dateServed = new Date().parse("dd.MM.yyyy", mdate[0])
			String locationName = "Geopolis"
			rss.div.findAll{ it.@class == 'offer'}.each{ o ->
				String menuName = o.div.findAll{ it.@class == 'offer-description'}.toString().trim()
				String content = o.div.depthFirst().findAll{ it.@class == 'title'}[0].toString().trim()
				content += "\n" + o.div.depthFirst().findAll{ it.@class == 'trimmings'}[0].toString().trim()
				content += "\n" + o.div.depthFirst().findAll{ it.@class == 'sidedish'}[0].toString().trim()
				logtxt += this.createMeal(dateServed, locationName, menuName, content, "UNIL")       
			}
		}
		return logtxt
	}
	
	def parseCSV(){
		def servletContext = grailsApplication.getParentContext().getServletContext()
		int i = 0
		def logtxt = ""
		servletContext.getResourceAsStream("/WEB-INF/menus.csv").eachCsvLine { tokens ->
				//0 content
				//1 date 2009-05-27T06:00:00
				//2 Location
				//3 id
				//4 Menu
				if(i>0){
					logtxt += this.createMeal(Date.parse("yyyy-MM-dd'T'HH:mm:ss", tokens[1]), tokens[2], tokens[4], tokens[0])
				}
				i++
		}
		return logtxt
	}
	
	private createMeal(Date dateServed, String locationName, String menuName, String content, String campusName){
		def logtxt = ""
		Location location = Location.findByName(locationName)
		if(!location){
			Campus campus = Campus.findByName(campusName)
			location = new Location(name: locationName, campus:campus).save(flush:true)
		}
		Menu menu = Menu.findByNameAndLocation(menuName, location)
		if(!menu){
			menu = new Menu(name: menuName, location: location, menuType: MenuType.DAILY).save(flush:true)
		}
		Meal meal = Meal.findByMenuAndDateServed(menu, dateServed)

		logtxt += "#______________________<br />${dateServed}: ${menu}\n"
		if(meal){
			logtxt += "already in store!"
		}else{
			def cleanContent = content.replaceAll("<(.|\n)*?>", '').replaceAll("\t",'').trim()
			meal = new Meal(menu:menu, dateServed: dateServed, content: cleanContent, rawContent: content).save(flush:true)
			logtxt += "saved"
			makeTags(meal.id)
		}
		return logtxt
	}
	
	def makeTags(long mealId){
		Meal meal = Meal.get(mealId)
		if(meal){
			def matches = meal?.rawContent =~ /(?:(?:.*?)\<em\>)?(?:(.*?)(?:(<br \/>|<\/em>)))/
			def tags = []
			matches.each{
				def menuLine = it[1].trim()
				if (menuLine != "" && menuLine.count('*') < 3 && menuLine != '-ou-') { //we dont want empty lines of *** separation lines
					menuLine.tokenize(' ,()').each{ t ->
						if(t.size() > 2){
							if(t[-1] == 's') t = t[0..-2]
							if(t[1] == "'") t = t[2..-1]
							//create a tag
							Tag tag = Tag.findByName(t.toLowerCase())
							if(!tag){
								tag = new Tag(name: t.toLowerCase()).save(flush:true)	
							}else{
								alertService.notifyTag(tag, meal)
							}
							try{
								meal.addToTags(tag)
							}catch(Exception e){
								println e.printStackTrace()
							}
						}
					}
				}
			}
			meal.save()
		}
	}
}
