package ch.fritscher.campusfood

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class UserMealController {
	static scaffold = true
}
