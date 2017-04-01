package app

import ch.fritscher.campusfood.Campus
import ch.fritscher.campusfood.Role
import ch.fritscher.campusfood.User
import ch.fritscher.campusfood.UserRole
import ch.fritscher.campusfood.Location
import ch.fritscher.campusfood.Menu
import ch.fritscher.campusfood.MenuType
import ch.fritscher.campusfood.Meal
import ch.fritscher.campusfood.Tag

class BootStrap {

	def springSecurityService

    def init = { servletContext ->
		if(Campus.count()< 2){
			new Campus(name: 'UNIL').save(flush: true)
			new Campus(name: 'EPFL').save(flush: true)
		}

		if( Role.count() < 2 ||  User.count() < 1  ) {
			def unil = Campus.findByName('UNIL')

			def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
			def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

			def tag = new ch.fritscher.campusfood.Tag(name:'pomme')
			tag.save(flush:true)

			String password = springSecurityService.encodePassword('admin')
			def adminUser = new User(username: 'admin', enabled: true, password: password, email: 'admin@exemple.com')
			adminUser.save(flush: true)
			UserRole.create adminUser, adminRole, true
			UserRole.create adminUser, userRole, true

			def demoUser = new User(username:'demo', enabled: true, password: 'demo', email: 'demo@exemple.com')
			demoUser.save(flush:true)
			UserRole.create demoUser, userRole, true

			def anthropole = new Location(name: 'Anthropole', campus:unil).save(flush: true)

			def internef = new Location(name: 'Internef', campus:unil).save(flush: true)
			def nef_boissons =new Menu(name: "Boissons", location: internef, menuType: MenuType.FIXED).save(flush:true)
			def nef_croissant =new Menu(name: "Pâtisserie & Viennoiserie", location: internef, menuType: MenuType.FIXED).save(flush:true)
			new Meal(menu: nef_boissons, content: "Café").save(flush: true)
			new Meal(menu: nef_boissons, content: "Jus d'orange").save(flush: true)
			new Meal(menu: nef_croissant, content: "Croissant").save(flush: true)

			def amphimax = new Location(name: 'Amphimax', campus:unil).save(flush: true)
			def amphi1 = new Menu(name: "Assiette 1", location: amphimax, menuType: MenuType.DAILY).save(flush:true)
			new Menu(name: "Assiette 2", location: amphimax, menuType: MenuType.DAILY).save(flush:true)

			def unitheque = new Location(name: 'Unithèque', campus:unil).save(flush: true)
			new Menu(name: "Assiette 1", location: unitheque, menuType: MenuType.DAILY).save(flush:true)
			new Menu(name: "Assiette 2", location: unitheque, menuType: MenuType.DAILY).save(flush:true)
			new Menu(name: "Menu 1", location: unitheque, menuType: MenuType.DAILY).save(flush:true)
			new Menu(name: "Menu 2", location: unitheque, menuType: MenuType.DAILY).save(flush:true)
			new Menu(name: "Fourchette verte", location: unitheque, menuType: MenuType.DAILY).save(flush:true)
			new Menu(name: "Festival de pâtes", location: unitheque, menuType: MenuType.WEEKLY).save(flush:true)
			new Menu(name: "Menu du Monde", location: unitheque, menuType: MenuType.WEEKLY).save(flush:true)
			new Menu(name: "Pizza", location: unitheque, menuType: MenuType.FIXED).save(flush:true)

			def dorigny = new Location(name: "Restaurant de Dorigny", campus:unil).save(flush: true)
			new Menu(name: "Menu du jour", location: dorigny, menuType: MenuType.DAILY).save(flush:true)

		}
    }
    def destroy = {
    }
}
