package ch.fritscher.campusfood

class Campus implements Comparable {

	String name
	
	static hasMany = [locations:Location]
	
    static constraints = {
    	name unique: true
	}
	
	static mapping = {
		sort 'name'
	}
	
	String toString(){
		return name
	}
	
	int compareTo(obj) {
		name.compareTo(obj.name)
	}
}
