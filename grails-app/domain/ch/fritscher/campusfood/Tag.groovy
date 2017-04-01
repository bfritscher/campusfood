package ch.fritscher.campusfood

class Tag {

	String name
	
	static hasMany = [meals:Meal]
	static belongsTo = Meal
	
    static constraints = {
		name blank: false, unique: true
    }

	static mapping = {
		id generator: 'sequence', params: [sequence: 'tag_seq']
	}
	
	String toString(){
		return name
	}
}
