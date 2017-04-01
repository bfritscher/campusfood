package ch.fritscher.campusfood


enum MenuType {
	DAILY('Daily'),
	WEEKLY('Weekly'),
	FIXED('Fixed')
	
	String name
	
	MenuType (String name) {
		this.name = name
	}
	
	static list() {
		[DAILY, WEEKLY, FIXED]
	}
	
}