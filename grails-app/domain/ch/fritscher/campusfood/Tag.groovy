package ch.fritscher.campusfood

class Tag {

	String name
	
	static hasMany = [meals:Meal]
	static belongsTo = Meal
	
    static constraints = {
		name blank: false, unique: true
    }
	
	String toString(){
		return name
	}
}
