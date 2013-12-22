package ch.fritscher.campusfood


class User {

	String username
	String password
	String email
	String description
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Date dateCreated
	
	static hasMany = [photos:Photo, comments:Comment]
	
	static constraints = {
		username blank: false, unique: true
		password blank: false
		email email: true, unique: true
		description nullable: true, blank: true, maxSize: 1000
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
	
	String toString(){
		return username
	}
}
