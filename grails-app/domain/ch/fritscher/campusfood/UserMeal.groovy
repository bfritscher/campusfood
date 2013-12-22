package ch.fritscher.campusfood

class UserMeal {

	User user
	Meal meal
	Date dateCreated
	Integer rating
	
    static constraints = {
		rating nullable: true
    }
	
	static def get(long userId, long mealId) {
		findAll 'from UserMeal where user.id=:userId and meal.id=:mealId order by dateCreated desc',
			[userId: userId, mealId: mealId]
	}
	
	String toString(){
		return "${dateCreated} - ${user.username} - ${meal} - ${rating?:''}" 
	}
	
}
