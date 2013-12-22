package ch.fritscher.campusfood

class Alert {
	User user
	Tag tag
	Date dateCreated
	
    static constraints = {
    }
	String toString(){
		return "Alert for ${tag} by ${user}"
	}
}
