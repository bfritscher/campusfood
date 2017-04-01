package ch.fritscher.campusfood

import grails.plugin.springsecurity.annotation.Secured

class AdminController {
	
	static allowedMethods = [savePhoto:'POST']
	
	def menuService
	
	@Secured(['ROLE_ADMIN'])
    def index() {

	}

	@Secured(['ROLE_ADMIN'])
    def debug() {
       def logtxt = ""
        String property = System.getProperty("java.library.path")
        StringTokenizer parser = new StringTokenizer(property, ":")
        while (parser.hasMoreTokens()) {
            logtxt += parser.nextToken() + "\n"
        }
        render logtxt

    }
	
	def parse() {
		render menuService.parse()
	}
	
	def parseGeo() {
		render menuService.parseGeo(params.week ?: 0)
	}
	
	def parseEpfl() {
		render menuService.parseEpfl(params.date ? Date.parse('yyyy-MM-dd', params.date) : new Date())
	}
	
	def parseEpflWeek() {
		(0..4).each{
			render menuService.parseEpfl(new Date() + it)
		}
	}
	
	def parseCSV() {
		render menuService.parseCSV()
	}
	
	@Secured(['ROLE_ADMIN'])
	def db() {
		org.hsqldb.util.DatabaseManagerSwing.main(new String[0])
		Thread.sleep 100000
	}	
}
