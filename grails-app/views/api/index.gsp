<html>
    <head>
        <title>Campus Food API</title>
        <meta name="layout" content="campusfood" />
    </head>
    <body>

<div class="box"><h2>API</h2>
<div class="content">
<h2>List of Menus</h2>
<h3>Description</h3>    
<p>Graph of List&lt;Campus&gt; -&gt; List&lt;Location&gt; -&gt; List&lt;Menu&gt;</p>
<p>path: <g:link controller="api" action="menus">/api/menus</g:link></p>

    
<h3>Sample</h3>    
<pre>[{
 "id" : 1,
 "name" : "UNIL",
 "locations" : [{
	"id" : 3,
	"name" : "Amphimax",
		"menus" : [{
			"id" : 3,
			"name" : "Assiette 1"
			},
			...
			]
		},
		...
	]
},
...
]</pre>
    
<h2>List of Meals</h2>
<h3>Description</h3>    
<p>Meals for a given date and menu selection</p>
<p>Path: <g:link controller="api" action="meals" id="2012-11-20" params="[m:'1,2,3']">/api/meals/[date]/?m=x,y,z API</g:link></p>
<p>[date]: in the format yyyy-mm-dd</p>
<p>x,y,z: List of menu ids (mid) separated by commas</p>
<p>Output in the same order as the list of mid</p>
    
<h3>Sample</h3>    
<pre>[{id: 1,
mid: 5,
content: "Test meal 1\nLine2" }
]</pre>
</div>
</div>
    </body>
</html>