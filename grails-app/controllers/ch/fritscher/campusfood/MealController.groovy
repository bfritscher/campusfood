package ch.fritscher.campusfood

import grails.plugin.springsecurity.annotation.Secured

class MealController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	 
	 def springSecurityService
	 def menuService
	 def imageUploadService
	 
	 def index() {
		 redirect(action: "list", params: params)
	 }
 
	 def list() {
		 params.max = Math.min(params.max ? params.int('max') : 10, 100)
		 [mealInstanceList: Meal.list(params), mealInstanceTotal: Meal.count()]
	 }
 
	 @Secured(['ROLE_USER'])
	 def create() {
		 if(params.id){
			 def mealInstance = new Meal()
			 mealInstance.properties = params
			 return [mealInstance: mealInstance]
		 }else{
		 	redirect(controller: "main", action: "index")
		 }
	 }
 
	 @Secured(['ROLE_USER'])
	 def save() {
		 def mealInstance = new Meal(params)
		 if (mealInstance.save(flush: true)) {
			 flash.message = "${message(code: 'default.created.message', args: [message(code: 'meal.label', default: 'Meal'), mealInstance])}"
			 redirect(controller: "main", action: "index")
		 }
		 else {
			 render(view: "create", model: [mealInstance: mealInstance])
		 }
	 }
 
	 def show() {
		 def mealInstance = Meal.get(params.id)
		 if (!mealInstance) {
			 flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'meal.label', default: 'Meal'), params.id])}"
			 redirect(action: "list")
		 }
		 else {
			 def userMeals
			 if(springSecurityService.currentUser){
				 userMeals = UserMeal.get(springSecurityService.currentUser.id , mealInstance.id)
			 }
			 [mealInstance: mealInstance, userMeals:userMeals]
		 }
	 }
 
	 @Secured(['ROLE_ADMIN'])
	 def edit() {
		 def mealInstance = Meal.get(params.id)
		 if (!mealInstance) {
			 flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'meal.label', default: 'Meal'), params.id])}"
			 redirect(action: "list")
		 }
		 else {
			 return [mealInstance: mealInstance]
		 }
	 }
 
	 @Secured(['ROLE_ADMIN'])
	 def update() {
		 def mealInstance = Meal.get(params.id)
		 if (mealInstance) {
			 if (params.version) {
				 def version = params.version.toLong()
				 if (mealInstance.version > version) {
					 
					 mealInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'meal.label', default: 'Meal')] as Object[], "Another user has updated this Meal while you were editing")
					 render(view: "edit", model: [mealInstance: mealInstance])
					 return
				 }
			 }
			 mealInstance.properties = params
			 if (!mealInstance.hasErrors() && mealInstance.save(flush: true)) {
				 flash.message = "${message(code: 'default.updated.message', args: [message(code: 'meal.label', default: 'Meal'), mealInstance.id])}"
				 redirect(action: "show", id: mealInstance.id)
			 }
			 else {
				 render(view: "edit", model: [mealInstance: mealInstance])
			 }
		 }
		 else {
			 flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'meal.label', default: 'Meal'), params.id])}"
			 redirect(action: "list")
		 }
	 }
 
	 @Secured(['ROLE_ADMIN'])
	 def delete() {
		 def mealInstance = Meal.get(params.id)
		 if (mealInstance) {
			 try {
				 mealInstance.delete(flush: true)
				 flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'meal.label', default: 'Meal'), params.id])}"
				 redirect(action: "list")
			 }
			 catch (org.springframework.dao.DataIntegrityViolationException e) {
				 flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'meal.label', default: 'Meal'), params.id])}"
				 redirect(action: "show", id: params.id)
			 }
		 }
		 else {
			 flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'meal.label', default: 'Meal'), params.id])}"
			 redirect(action: "list")
		 }
	 }
	 
	 @Secured(['ROLE_USER'])
	 def consume() {
		 println params
		 UserMeal userMeal = new UserMeal()
		 userMeal.properties = params
		 userMeal.user = springSecurityService.currentUser
		 userMeal.save(flush:true)
		 redirect(action:'show', id:params.meal.id)
	 }
	 
	 @Secured(['ROLE_USER'])
	 def savePhoto() {
		 Photo.withTransaction { status ->
			 Photo photo = new Photo()
			 photo.properties = params
			 photo.user = springSecurityService.currentUser

			 if(photo.save()) {
				 try{
					 imageUploadService.save(photo, true)
					 redirect(action:'show', id:params.meal.id)
				 }catch(Exception ex){
					 flash.message = "erreur aucune image trouvé ou image trop grande"
					 status.setRollbackOnly()
					 redirect(action:'show', id:params.meal.id)
				 }
			 }else{
				 photo.errors.each {
					 println it
				 }
				 status.setRollbackOnly()
			 }
		 }
	 }
	 
	 @Secured(['ROLE_USER'])
	 def saveComment() {
		 def commentInstance = new Comment(params)
		 commentInstance.user = springSecurityService.currentUser
		 //TODO: error handling?
		 commentInstance.save(flush: true)
		 redirect(action: "show", id: params.meal.id)
	 }
	 
	 def date() {
		 def day
		 if(params.id){
			day = Date.parse("dd-MM-yyyy", params.id)
		 }else{
		 	day = new Date()
		 }
		 
		 def meals = Meal.getDaily(day)
		 
		 if(meals){
			 render template: 'date', collection: meals, var: 'meal'
		 }else{
			 render template: 'date', model:[meal:[content: 'Aucun menu disponible pour ce jour!']]
		 }
	 }
	 
}
