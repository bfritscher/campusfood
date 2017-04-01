package ch.fritscher.campusfood

class Alert {
	User user
	Tag tag
	Date dateCreated
	
    static constraints = {
    }

	static mapping = {
		id generator: 'sequence', params: [sequence: 'alert_seq']
	}

	String toString(){
		return "Alert for ${tag} by ${user}"
	}
}
