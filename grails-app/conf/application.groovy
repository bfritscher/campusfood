// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.fii.rejectPublicInvocations = false
grails.plugin.springsecurity.userLookup.userDomainClassName = 'ch.fritscher.campusfood.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'ch.fritscher.campusfood.UserRole'
grails.plugin.springsecurity.authority.className = 'ch.fritscher.campusfood.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/aclclass/**', access: ['ROLE_ADMIN']],
        [pattern: '/aclentry/**', access: ['ROLE_ADMIN']],
        [pattern: '/aclobjectidentity/**', access: ['ROLE_ADMIN']],
        [pattern: '/aclsid/**', access: ['ROLE_ADMIN']],
        [pattern: '/registrationcode/**', access: ['ROLE_ADMIN']],
        [pattern: '/persistentlogin/**', access: ['ROLE_ADMIN']],
        [pattern: '/requestmap/**', access: ['ROLE_ADMIN']],
        [pattern: '/role/**', access: ['ROLE_ADMIN']],
        [pattern: '/securityinfo/**', access: ['ROLE_ADMIN']],
        [pattern: '/user/**', access: ['ROLE_ADMIN']]
 ]
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/'

grails.plugin.springsecurity.errors.login.disabled = "Désolé, votre compte est désactivé."
grails.plugin.springsecurity.errors.login.expired = "Désolé, votre compte a expiré."
grails.plugin.springsecurity.errors.login.passwordExpired = "Désolé, votre mot de passe a expiré."
grails.plugin.springsecurity.errors.login.locked = "Désolé, votre compte est verrouillé."
grails.plugin.springsecurity.errors.login.fail = "Désolé, nous n'avons pas été en mesure de trouver un utilisateur avec ce pseudo et mot de passe."

grails.plugin.springsecurity.ui.register.emailBody = '''\
Salut $user.username, <br/>
<br/>
Vous (ou quelqu'un se faisant passer pour vous) a créé un compte avec cette adresse e-mail. <br/>
<br/>
Si vous avez fait la demande, s'il vous plaît  <a href="$url">cliquez ici</ a> pour terminer l'enregistrement.
'''

grails.plugin.springsecurity.ui.register.emailFrom = 'do.not.reply@localhost'
grails.plugin.springsecurity.ui.register.emailSubject = 'Nouveau compte'
grails.plugin.springsecurity.ui.register.defaultRoleNames = ['ROLE_USER']
grails.plugin.springsecurity.ui.register.postRegisterUrl = null // use defaultTargetUrl if not set

grails.plugin.springsecurity.ui.forgotPassword.emailBody = '''\
Salut $user.username, <br/>
<br/>
Vous (ou quelqu'un se faisant passer pour vous) a demandé que votre mot de passe soit réinitialisé. <br/>
<br/>
Si vous n'avez pas fait cette demande alors ignorer l'e-mail, pas de changements ont été aeffectuées <br/>.
<br/>
Si vous avez fait la demande, alros cliquez <a href="$url">ici</ a> pour réinitialiser votre mot de passe.
'''

grails.plugin.springsecurity.ui.forgotPassword.emailFrom = 'do.not.reply@localhost'
grails.plugin.springsecurity.ui.forgotPassword.emailSubject = 'Réinitialiser mot de passe'
grails.plugin.springsecurity.ui.forgotPassword.postResetUrl = null // use defaultTargetUrl if not set