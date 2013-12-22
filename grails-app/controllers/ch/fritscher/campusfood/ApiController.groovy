package ch.fritscher.campusfood
import grails.converters.*

import grails.plugins.springsecurity.Secured
class ApiController {
	def apiBase = '/campusfood/api/v1'
	def springSecurityService
	def googleAnalyticsService
		
	def index = {
	}
	
    def locations = {
		def locations = Location.list()
		def results = locations.collect{ location ->
			[id: location.id,
			 name: location.name]
		}
		render results as JSON
	}
	
	def menuForLocation = {
		def menus = Menu.findAll("from Menu as m WHERE m.menuType = :menuType AND m.location.id = :locationId ORDER BY m.location.name ASC, m.name ASC",
			[menuType:MenuType.FIXED, locationId: params.long('id')])
		def results = menus.collect{ menu ->
			[id: menu.id,
			 name: menu.name]
		}
		render results as JSON
	}
	
	def mealForMenu = {
		def meals = Meal.findAll("from Meal as m WHERE m.menu.menuType = :menuType AND m.menu.id = :menuId ORDER BY m.menu.location.name ASC, m.menu.name ASC, m.content ASC",
			[menuType:MenuType.FIXED, menuId: params.long('id')])
		def results = meals.collect{ meal ->
			[id: meal.id,
			 content: meal.content]
		}
		render results as JSON
	}
	
	def daily = {
		def day
		if(params.id){
		   day = Date.parse("yyyy-MM-dd", params.id)
		}else{
			day = new Date()
		}
		
		def meals = Meal.getDaily(day)
		
		def results = meals.collect{ meal ->
				[id: meal.id,
				 content: meal.content,
				 menu: [id: meal.menu.id,
					    name: meal.menu.name,
					    location: [id: meal.menu.location.id, 
							       name: meal.menu.location.name]]]
			}
		render results as JSON
	}
	
	def mealTree = {
		def locations = Location.list()
		def menus =  Menu.findAll("from Menu as m WHERE m.menuType = :menuType ORDER BY m.location.name ASC, m.name ASC", [menuType:MenuType.FIXED])
		def meals = Meal.findAll("from Meal as m WHERE m.menu.menuType = :menuType ORDER BY m.menu.location.name ASC, m.menu.name ASC, m.content ASC", [menuType:MenuType.FIXED])
		def results = locations.collect{ location ->
			[id: location.id,
			 name: location.name,
			 menus: menus.grep{it.location == location}.collect{ menu ->
				 [id: menu.id,
				  name: menu.name,
				  meals: meals.grep{it.menu == menu}.collect{ meal ->
					  [id: meal.id,
					   content: meal.content]
				  }]
			 }]
			
		}
		
		render results as JSON
	}
		
	@Secured(['ROLE_USER'])
	def profileLastMeals = {
		
		def eatenMeals = UserMeal.findAll("from UserMeal where user.id=:userId ORDER BY dateCreated DESC", [userId: springSecurityService.currentUser.id], [max:10])
		def results = eatenMeals.collect{ em ->
			[id: em.meal.id,
			 menu: em.meal.menu.name,
			 location: em.meal.menu.location.name,
			 content: em.meal.content,
			 dateCreated: em.dateCreated,
			 rating: em.rating]
		}
		
		render results as JSON
	}
	
	//new api
	
	def menus = {
		googleAnalyticsService.pageView("$apiBase/menus", "APIv1 menus" )
		def menus =  Menu.findAll("from Menu as m WHERE m.menuType != :menuType ORDER BY m.location.campus.id ASC, m.location.name ASC, m.name ASC", [menuType:MenuType.FIXED])
		def locations = menus.collect{it.location} as SortedSet
		def campus = locations.collect{it.campus} as SortedSet
		def results = campus.sort{it.id}.collect{ c ->
			[id: c.id,
			 name: c.name,
			 locations: locations.grep{it.campus == c }.collect{ location ->
				 [id: location.id,
				  name: location.name,
				  menus: menus.grep{it.location == location}.collect{ menu ->
				   [id: menu.id,
					name: menu.name]
				  }]
			}]
		}
		render results as JSON
	}
	
	def meals = {
		googleAnalyticsService.pageView("$apiBase/meals", "APIv1 meals" )
		def day
		if(params.id){
		   day = Date.parse("yyyy-MM-dd", params.id)
		}else{
			day = new Date()
		}
		
		def meals = Meal.getDaily(day)
		def filteredMeals = []
		if(params.m){
			def items = []
			params.m.split(/,/).each{ id ->
				def meal = meals.find{ it.menu.id.toString() == id	}
				if(meal == null){
					meal = [content:"?", menu: [id:id.toInteger()]]
				}else{
					items << [id:id, name:meal.menu.name, cat: meal.menu.location.name]
				}
				filteredMeals << meal
			}
			googleAnalyticsService.eCommerceTransaction(items) //TODO: affiliation base on parameter
		}else{
			filteredMeals = meals
		}
		
		def results = filteredMeals.collect{ meal ->
				[id: meal.id,
				 mid: meal.menu.id,
				 content: meal.content]
			}
		render results as JSON
	}

}
