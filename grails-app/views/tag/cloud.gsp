<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="campusfood" />
        <title>Tag Cloud</title>
    </head>
    <body>
        <div class="box">
            <h2>Tag Cloud</h2>
            <div class="content">
           	 <tc:tagCloud tags="${tags}" 
           	 		size="${[start: 15, end: 40, unit: 'px']}"
                    color="${[start: '#f90', end: '#00f']}"
                    controller="tag" action="show" />
             </div>
        </div>
	</body>
</html>
