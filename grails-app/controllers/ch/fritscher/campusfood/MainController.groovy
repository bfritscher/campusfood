package ch.fritscher.campusfood

class MainController {

    def index = {

		[campus:Campus.list(),
		 menus: Menu.findAll("from Menu as m WHERE m.menuType = :menuType ORDER BY m.location.name ASC, m.name ASC", [menuType:MenuType.FIXED]),
		 meals:Meal.findAll("from Meal as m WHERE m.menu.menuType = :menuType ORDER BY m.menu.location.name ASC, m.menu.name ASC, m.content ASC", [menuType:MenuType.FIXED])]
	}
	
	def privacy = {
		
	}
}
