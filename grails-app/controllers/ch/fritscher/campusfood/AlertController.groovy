package ch.fritscher.campusfood

import grails.plugin.springsecurity.annotation.Secured

class AlertController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def springSecurityService

	//springSecurityService.currentUser
	//TODO: check security to limit to useronly
	
	@Secured(['ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_USER'])
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [alertInstanceList: Alert.findAllByUser(springSecurityService.currentUser, params), alertInstanceTotal: Alert.countByUser(springSecurityService.currentUser)]
    }

	@Secured(['ROLE_USER'])
    def create() {
        def alertInstance = new Alert()
        alertInstance.properties = params
		alertInstance.user = springSecurityService.currentUser
        return [alertInstance: alertInstance]
    }

	@Secured(['ROLE_USER'])
    def save() {
        def alertInstance = new Alert(params)
		alertInstance.user = springSecurityService.currentUser
        if (alertInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'alert.label', default: 'Alert'), alertInstance.id])}"
            redirect(action: "show", id: alertInstance.id)
        }
        else {
            render(view: "create", model: [alertInstance: alertInstance])
        }
    }

	@Secured(['ROLE_USER'])
    def show() {
        def alertInstance = Alert.get(params.id)
        if (!alertInstance || alertInstance.user != springSecurityService.currentUser) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), params.id])}"
            redirect(action: "list")
        }
        else {
            [alertInstance: alertInstance]
        }
    }

	@Secured(['ROLE_USER'])
    def edit() {
        def alertInstance = Alert.get(params.id)
        if (!alertInstance || alertInstance.user != springSecurityService.currentUser) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [alertInstance: alertInstance]
        }
    }

	@Secured(['ROLE_USER'])
    def update() {
        def alertInstance = Alert.get(params.id)
        if (alertInstance  && alertInstance.user == springSecurityService.currentUser) {
            if (params.version) {
                def version = params.version.toLong()
                if (alertInstance.version > version) {
                    
                    alertInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'alert.label', default: 'Alert')] as Object[], "Another user has updated this Alert while you were editing")
                    render(view: "edit", model: [alertInstance: alertInstance])
                    return
                }
            }
            alertInstance.properties = params
            if (!alertInstance.hasErrors() && alertInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'alert.label', default: 'Alert'), alertInstance.id])}"
                redirect(action: "show", id: alertInstance.id)
            }
            else {
                render(view: "edit", model: [alertInstance: alertInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), params.id])}"
            redirect(action: "list")
        }
    }
	
	@Secured(['ROLE_USER'])
    def delete() {
        def alertInstance = Alert.get(params.id)
        if (alertInstance && alertInstance.user == springSecurityService.currentUser) {
            try {
                alertInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'alert.label', default: 'Alert'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'alert.label', default: 'Alert'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), params.id])}"
            redirect(action: "list")
        }
    }
}
