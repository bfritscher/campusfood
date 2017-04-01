package ch.fritscher.campusfood

import grails.plugin.springsecurity.annotation.Secured
class ProfileController {

	def springSecurityService
	
	 @Secured(['ROLE_USER'])
     def index() {
		 [user: springSecurityService.currentUser] 
		 
	 }
	 
	 def view() {
		 render 'nothing yet'		 
	 }
}
