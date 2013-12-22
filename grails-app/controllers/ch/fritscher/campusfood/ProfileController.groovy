package ch.fritscher.campusfood

import grails.plugins.springsecurity.Secured
class ProfileController {

	def springSecurityService
	
	 @Secured(['ROLE_USER'])
     def index = { 
		 [user: springSecurityService.currentUser] 
		 
	 }
	 
	 def view = {
		 render 'nothing yet'		 
	 }
}
