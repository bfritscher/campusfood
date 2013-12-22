<html>
    <head>
        <title>CampusFood</title>
        <meta name="layout" content="campusfood" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'jdpicker.css')}" type="text/css"/>
        <g:javascript src="jquery.jdpicker.js" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'fg.menu.css')}" type="text/css"/>
		<link rel="stylesheet" href="${resource(dir:'css/custom-theme',file:'jquery-ui-1.8.10.custom.css')}" type="text/css"/>
        <g:javascript src="fg.menu.js" />
        <script type="text/javascript">
        $(document).ready(function(){
			var menu = $('#hierarchybreadcrumb').menu({
				content: $('#meal-items').html(),
				width: 220,
				maxHeight: 400,
				backLinkText: 'Retour',
				backLink: true,
				crumbDefaultText: ''
			});
        });
        </script>        
    </head>
    <body>
<div id="meal-items" class="hidden">
<ul>
 	<g:each in="${campus}" var="c">
 	<li><a href="#">${c.name.encodeAsHTML()}</a>
		<ul>
		<g:each in="${c.locations}" var="location">
			<li><a href="#">${location.name.encodeAsHTML()}</a>
				<ul>
					<g:each in="${menus.grep{it.location == location}}" var="menu">
						<li><a href="#">${menu.name.encodeAsHTML()}</a>
							<ul>
								<g:each in="${meals.grep{it.menu == menu}}" var="meal">
									<li><g:link controller="meal" action="show" id="${meal.id}">${meal.content.encodeAsHTML()}</g:link></li>
								</g:each>	
								<li class="meal-add"><g:link controller="meal" action="create" id="${menu.id}">Ajouter un élément</g:link></li>
							</ul>
						</li>
					</g:each>
					<li class="meal-add"><g:link controller="menu" action="create" id="${location.id}">Ajouter une catégorie</g:link></li>
				</ul>
			</li>
		</g:each>
			<li class="meal-add"><g:link controller="location" action="create">Ajouter un lieu</g:link></li>
		</ul>
	</li>
	</g:each>
	<li class="meal-add"><g:link controller="campus" action="create">Ajouter un campus</g:link></li>
</ul>
</div>
 		<input type="text" id="meal-date"  class="jdpicker" value="${new Date().format('dd-MM-yyyy')}"/>
 		<div id="menu"><a href="#meal-items" id="hierarchybreadcrumb" class="fg-button fg-button-icon-right">consommer<span class="ui-icon ui-icon-triangle-1-s"></span></a>
 			<g:link controller="tag" action="cloud">tag cloud</g:link>
 		</div>
 		<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
		</g:if>
 		<div id="meals"></div>
    </body>
</html>
