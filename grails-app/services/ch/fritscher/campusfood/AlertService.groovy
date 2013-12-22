package ch.fritscher.campusfood

class AlertService {

    static transactional = true
	def mailService

    def notifyTag(tag, meal) {
		def results = Alert.findAllByTag(tag)
		results.each{ alert ->
			mailService.sendMail {
			   to alert.user.email
			   from "noreply@isisvn.unil.ch"
			   subject "CampusFood Alert ${alert.tag.name}"
			   body "A menu with a tag you subscribed to has been added http://isisvn.unil.ch/campusfood/meal/show/${meal.id} ${meal.toString()}"
			}
		}
    }
}
