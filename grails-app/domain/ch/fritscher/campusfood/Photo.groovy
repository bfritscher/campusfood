package ch.fritscher.campusfood

class Photo {

	User user
	Meal meal
	String description
	Date dateCreated
	ItemStatus status = ItemStatus.UNAPPROVED

	static mapping = {
		id generator: 'sequence', params: [sequence: 'photo_seq']
	}

    static constraints = {
		description nullable:true, maxSize: 1000
		image nullable: true
		
    }

}
