package ch.fritscher.campusfood

class GoogleAnalyticsService {

    static transactional = true

	def urlString = "http://www.google-analytics.com/collect"
	def tracker = 'UA-36525343-3'
	
	def pageView(page, title, referrer = ""){
	    page = java.net.URLEncoder.encode(page)
	    title = java.net.URLEncoder.encode(title)
	    referrer = java.net.URLEncoder.encode(referrer)
	    def queryString = "v=1&tid=$tracker&cid=555&t=pageview&dh=isisvn.unil.ch&dp=$page&dt=$title&dr=$referrer"
	    post(queryString)
	}
	
	def eCommerceTransaction(items, affiliation=""){
	    def tid = java.util.UUID.randomUUID().toString()
	    def queryString = "v=1&tid=$tracker&cid=555&t=transaction&ti=$tid&ta=$affiliation"
	    post(queryString)
	    items.each{ item ->
	        eCommerceItem(tid, item.id, item.name, item.cat)
	    }
	
	
	}
	
	def eCommerceItem(tid, itemId, itemName, itemCategory){
	    itemName = java.net.URLEncoder.encode(itemName)
	    itemCategory = java.net.URLEncoder.encode(itemCategory)
	    def queryString = "v=1&tid=$tracker&cid=555&t=item&ti=$tid&iq=1&ic=$itemId&in=$itemName&ic=$itemCategory"
	    post(queryString)    
	}
	
	
	def post(queryString){
	    new Thread(){
			public void run(){
			    def url = new URL(urlString)
			    def connection = url.openConnection()
			    connection.setRequestMethod("POST")
			    connection.doOutput = true
			    def writer = new OutputStreamWriter(connection.outputStream)
			    writer.write(queryString)
			    writer.flush()
			    writer.close()
			    connection.connect()
			    connection.content.doFetch()
			}
		}.start()
	}
}
