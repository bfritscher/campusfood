package ch.fritscher.campusfood


import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import ch.fritscher.campusfood.User;

class RegisterController extends grails.plugins.springsecurity.ui.RegisterController {
	
	def mailService
	def saltSource
	
	def index = {
		[command: new RegisterCommand()]
	}

	def register = { RegisterCommand command ->

		if (command.hasErrors()) {
			render view: 'index', model: [command: command]
			return
		}

		String salt = saltSource instanceof NullSaltSource ? null : command.username
		String password = springSecurityService.encodePassword(command.password, salt)
		def user = lookupUserClass().newInstance(email: command.email, username: command.username,
				password: password, accountLocked: true, enabled: true)
		if (!user.validate() || !user.save()) {
			// TODO
		}

		def registrationCode = new RegistrationCode(username: user.username).save()
		String url = generateLink('verifyRegistration', [t: registrationCode.token])

		def conf = SpringSecurityUtils.securityConfig
		def body = conf.ui.register.emailBody
		if (body.contains('$')) {
			body = evaluate(body, [user: user, url: url])
		}
		mailService.sendMail {
			to command.email
			from conf.ui.register.emailFrom
			subject conf.ui.register.emailSubject
			html body.toString()
		}

		render view: 'index', model: [emailSent: true]
	}
	
	def resetPassword = { ResetPasswordCommand command ->
		
		String token = params.t

		def registrationCode = token ? RegistrationCode.findByToken(token) : null
		if (!registrationCode) {
			flash.error = message(code: 'spring.security.ui.resetPassword.badCode')
			redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
			return
		}

		if (!request.post) {
			return [token: token, command: new ResetPasswordCommand()]
		}

		command.username = registrationCode.username
		command.validate()

		if (command.hasErrors()) {
			return [token: token, command: command]
		}

		String salt = saltSource instanceof NullSaltSource ? null : registrationCode.username
		RegistrationCode.withTransaction { status ->
			def user = lookupUserClass().findByUsername(registrationCode.username)
			user.password = springSecurityService.encodePassword(command.password, salt)
			user.save()
			registrationCode.delete()
		}

		springSecurityService.reauthenticate registrationCode.username

		flash.message = message(code: 'spring.security.ui.resetPassword.success')

		def conf = SpringSecurityUtils.securityConfig
		String postResetUrl = conf.ui.register.postResetUrl ?: conf.successHandler.defaultTargetUrl
		redirect uri: postResetUrl
	}
	
	@Override
	protected String generateLink(String action, linkParams) {
		createLink(controller: 'register', action: action,
				params: linkParams,
				absolute: true)

	}
		
}
class RegisterCommand {
	
		String username
		String email
		String password
	
		static constraints = {
			username blank: false, validator: { value, command ->
				if (value) {
					if (User.findByUsername(value)) {
						return 'registerCommand.username.unique'
					}
				}
			}
			email blank: false, email: true, validator: { value, command ->
				if (value) {
					if (User.findByEmail(value)) {
						return 'registerCommand.email.unique'
					}
				}
			}
			password blank: false
		}
	}
	
	class ResetPasswordCommand {
		String username
		String password
	
		static constraints = {
			password blank: false
		}
	}