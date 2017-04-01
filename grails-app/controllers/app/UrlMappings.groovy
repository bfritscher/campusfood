package app

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:'main', action: 'index')
		"/privacy"(controller:'main', action: 'privacy')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
