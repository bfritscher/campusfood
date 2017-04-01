package ch.fritscher.campusfood

class Location implements Comparable {

	String name
	Campus campus
	
	static hasMany = [menus:Menu]
	
    static constraints = {
    	name unique: true
	}
	
	static mapping = {
		id generator: 'sequence', params: [sequence: 'location_seq']
		sort 'name'
	}
	
	String toString(){
		return name
	}
	
	int compareTo(obj) {
		name.compareTo(obj.name)
	}
}
