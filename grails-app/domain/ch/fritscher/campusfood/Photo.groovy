package ch.fritscher.campusfood
import pl.burningice.plugins.image.ast.FileImageContainer

@FileImageContainer(field = 'image')
class Photo {

	User user
	Meal meal
	String description
	Date dateCreated
	ItemStatus status = ItemStatus.UNAPPROVED
	
    static constraints = {
		description nullable:true, maxSize: 1000
		image nullable: true
		
    }

}
