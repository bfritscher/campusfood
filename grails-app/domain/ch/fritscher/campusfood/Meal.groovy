package ch.fritscher.campusfood

class Meal {
	Menu menu
	Date dateServed
	String content
	String rawContent
	
	static hasMany = [tags:Tag, photos:Photo, comments:Comment]
	
    static constraints = {
		menu()
		dateServed(nullable:true)
		content(nullable:true, maxSize: 1000, validator: { val, obj -> 
			if (obj.dateServed == null && val == null) return ['Content cannot be null'] })
		rawContent(nullable: true, maxSize: 1000)
    }
	
	static mapping = {
		dateServed type:'date'
	}
	
	String toString(){
		if(dateServed){
			return "${menu} - ${dateServed}"
		}else{
			return "${menu} - ${content}"
		}
	}
	
	static def getDaily(Date day){
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("Europe/Zurich"))
		today.time = day
		today.set(Calendar.HOUR, 1)
		today.set(Calendar.AM_PM, Calendar.AM)
		today.set(Calendar.MINUTE, 0)
		today.set(Calendar.SECOND, 0)
		today.set(Calendar.MILLISECOND, 0)
		// some menus are set monday for the whole week...
		// calculate last monday relative to today
		def monday = today.time
		if(today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			monday -= 6
		}else{
			monday -= today.get(Calendar.DAY_OF_WEEK) - 2
		}
		// fetch todays menus
	
		return Meal.findAll("FROM Meal as m WHERE (m.dateServed = :today AND m.menu.menuType = :daily) OR (m.dateServed = :monday AND m.menu.menuType = :weekly)  ORDER BY m.menu.location.campus.id ASC, m.menu.location.name ASC, m.menu.name ASC",
							 [today: today.time, daily: MenuType.DAILY, monday: monday, weekly: MenuType.WEEKLY],
							 [cache: true])
	}
}
