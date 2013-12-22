package ch.fritscher.campusfood

import com.ocpsoft.pretty.time.PrettyTime;

class MyTagLib {
	static namespace = "my"
	
	def prettyTime = { attrs ->
		if(attrs.date){
			PrettyTime p = new PrettyTime()
			out << p.format(attrs.date)
		}
	}
}
