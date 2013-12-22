package ch.fritscher.campusfood

class Comment {

	String content
	Date dateCreated
	Date lastUpdated
	ItemStatus status = ItemStatus.UNAPPROVED
	
	static belongsTo = [user: User, meal: Meal]
	
    static constraints = {
		content blank: false, maxSize: 1000
    }
}
