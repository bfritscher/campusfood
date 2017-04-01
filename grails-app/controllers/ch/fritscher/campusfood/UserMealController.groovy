package ch.fritscher.campusfood

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class UserMealController {
	static scaffold = UserMeal
}
