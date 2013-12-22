import pl.burningice.plugins.image.engines.scale.ScaleType
import pl.burningice.plugins.image.engines.RenderingEngine
// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "https://campusfood"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
		//grails.mail.port = com.icegreen.greenmail.util.ServerSetupTest.SMTP.port
		grails.mail.host = "smtp.unil.ch"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'ch.fritscher.campusfood.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'ch.fritscher.campusfood.UserRole'
grails.plugins.springsecurity.authority.className = 'ch.fritscher.campusfood.Role'
grails.plugins.springsecurity.controllerAnnotations.staticRules = [
'/aclclass/**': ['ROLE_ADMIN'],
'/aclentry/**': ['ROLE_ADMIN'],
'/aclobjectidentity/**': ['ROLE_ADMIN'],
'/aclsid/**': ['ROLE_ADMIN'],
'/registrationcode/**': ['ROLE_ADMIN'],
'/persistentlogin/**': ['ROLE_ADMIN'],
'/requestmap/**': ['ROLE_ADMIN'],
'/role/**': ['ROLE_ADMIN'],
'/securityinfo/**': ['ROLE_ADMIN'],
'/user/**': ['ROLE_ADMIN']
 ]
grails.plugins.springsecurity.successHandler.defaultTargetUrl = '/'

grails.plugins.springsecurity.errors.login.disabled = "Désolé, votre compte est désactivé."
grails.plugins.springsecurity.errors.login.expired = "Désolé, votre compte a expiré."
grails.plugins.springsecurity.errors.login.passwordExpired = "Désolé, votre mot de passe a expiré."
grails.plugins.springsecurity.errors.login.locked = "Désolé, votre compte est verrouillé."
grails.plugins.springsecurity.errors.login.fail = "Désolé, nous n'avons pas été en mesure de trouver un utilisateur avec ce pseudo et mot de passe."

grails.plugins.springsecurity.ui.register.emailBody = '''\
Salut $user.username, <br/>
<br/>
Vous (ou quelqu'un se faisant passer pour vous) a créé un compte avec cette adresse e-mail. <br/>
<br/>
Si vous avez fait la demande, s'il vous plaît  <a href="$url">cliquez ici</ a> pour terminer l'enregistrement.
'''

grails.plugins.springsecurity.ui.register.emailFrom = 'do.not.reply@localhost'
grails.plugins.springsecurity.ui.register.emailSubject = 'Nouveau compte'
grails.plugins.springsecurity.ui.register.defaultRoleNames = ['ROLE_USER']
grails.plugins.springsecurity.ui.register.postRegisterUrl = null // use defaultTargetUrl if not set

grails.plugins.springsecurity.ui.forgotPassword.emailBody = '''\
Salut $user.username, <br/>
<br/>
Vous (ou quelqu'un se faisant passer pour vous) a demandé que votre mot de passe soit réinitialisé. <br/>
<br/>
Si vous n'avez pas fait cette demande alors ignorer l'e-mail, pas de changements ont été aeffectuées <br/>.
<br/>
Si vous avez fait la demande, alros cliquez <a href="$url">ici</ a> pour réinitialiser votre mot de passe.
'''

grails.plugins.springsecurity.ui.forgotPassword.emailFrom = 'do.not.reply@localhost'
grails.plugins.springsecurity.ui.forgotPassword.emailSubject = 'Réinitialiser mot de passe'
grails.plugins.springsecurity.ui.forgotPassword.postResetUrl = null // use defaultTargetUrl if not set

//image burning plugin
//http://code.google.com/p/burningimage/wiki/Images_upload_handling
// not working :( bi.renderingEngine = RenderingEngine.IMAGE_MAGICK
bi.Photo = [
	outputDir:  ['path':'/var/www/campusfood/photos/', 'alias':'/photos/'],
	prefix: 'meal',
	images: ['large':[scale:[width:800, height:600, type:ScaleType.APPROXIMATE]],
			 'small':[scale:[width:150, height:150, type:ScaleType.ACCURATE]]],
	constraints:[
		nullable:true,
		maxSize:5000,
		contentType:['image/gif', 'image/png']
	]
]
