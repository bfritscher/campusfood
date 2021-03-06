package ch.fritscher.campusfood

class Menu {
	String name
	Location location
	MenuType menuType = MenuType.FIXED
	
	static hasMany = [meals:Meal]
	
    static constraints = {
		name unique: 'location'
    }

	static mapping = {
		id generator: 'sequence', params: [sequence: 'menu_seq']
	}
	
	String toString(){
		return "${location} - ${name}"		
	}
}
