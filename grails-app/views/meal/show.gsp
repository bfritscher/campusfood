
<%@page import="ch.fritscher.campusfood.ItemStatus"%>
<%@ page import="ch.fritscher.campusfood.Meal" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="campusfood" />
        <g:set var="entityName" value="${message(code: 'meal.label', default: 'Meal')}" />
        <title>${mealInstance}</title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'contentflow.css')}" type="text/css"/>
        <g:javascript src="contentflow.js" load="lightbox gallery white" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'jquery.ui.stars.min.css')}" type="text/css"/>
        <g:javascript src="jquery-ui-1.8.10.custom.min.js"  />
        <g:javascript src="jquery.ui.stars.min.js"  />
        <script type="text/javascript">
        	$(document).ready(function(){
				$('.toggleNext').click(function(){
					$(this).next().slideToggle();
				});
				$("#stars-wrapper").stars({
						 captionEl: $("#stars-cap"),
						 inputType: "select"
        			});
        	});
        </script>
    </head>
    <body>
        <div class="box">
            <h2>${mealInstance}</h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
<div class="content">            		
			<h3>Informations</h3>
			<g:if test="${mealInstance.dateServed}">
            	<g:render template="date" bean="${[content:mealInstance.content]}" var="meal"></g:render>
            </g:if>	

			<div>
			<g:form controller="meal" action="consume">
				<g:hiddenField name="meal.id" value="${mealInstance?.id}" />
				Rating: <span id="stars-cap"></span>
				<div id="stars-wrapper"><g:select name="rating" optionKey="key" optionValue="value" from="${[[key:1, value:'Very poor'], [key:2, value:'Not that bad'], [key:3, value:'Average'], [key:4, value:'Good'], [key:5, value:'Perfect']]}"></g:select></div>
				<input type="submit" value="consommer"/>    
			</g:form>
			</div>
			<sec:ifLoggedIn>
			<p>historique
				<ul>
					<g:each in="${userMeals}" var="um">
						<li>${um.dateCreated}: ${um.rating?:''}</li>
					</g:each>
				</ul>
			</p>
			</sec:ifLoggedIn>
			<p>Rating global: todo</p>
			<p>Nombre de fois consommé global: todo</p>
			<p>Related?</p>

	<h3>Photos</h3>
	<div class="ContentFlow" id="mealContentFlow">
        <div class="loadIndicator"><div class="indicator"></div></div>
        <div class="flow">
        	<g:each in="${mealInstance.photos.grep{it.status in [ItemStatus.APPROVED, ItemStatus.UNAPPROVED, ItemStatus.FLAGGED]}.sort{it.dateCreated}}" var="photo">
			<bi:hasImage bean="${photo}">
				<div class="item">
            		<bi:img class="content" href="${bi.resource(size:'large', bean:photo)}" size="small" bean="${photo}" />
            		<div class="caption">${photo?.description?.encodeAsHTML()}</div>
            		<div class="label">${photo?.user?.username?.encodeAsHTML()}</div>
        		</div>
			</bi:hasImage>
		</g:each>
        </div>
        <div class="globalCaption"></div>
		<div class="scrollbar">
             <div class="slider"><div class="position"></div></div>
         </div>
    </div>
    <a href="#" class="toggleNext">ajouter une image</a>
  	   <g:form class="hidden" action="savePhoto" method="post" enctype="multipart/form-data">
	    	<p><label for="image">Image:</label>  <input type="file" name="image" /></p>
	    	<p><label for="description">Description:</label> <input type="text" name="description" /></p>
	    	<p><input type="submit" name="submit" value="envoyer" /></p>
	    	<g:hiddenField name="meal.id" value="${mealInstance?.id}" />
		</g:form>
  
  <h3>Commentaires</h3>
        <g:each in="${mealInstance.comments.grep{it.status in [ItemStatus.APPROVED, ItemStatus.UNAPPROVED, ItemStatus.FLAGGED]}.sort{ it.dateCreated}}" var="c" status="i">
           <div class="c-content">
           	<sec:ifAnyGranted roles="ROLE_ADMIN"><g:link class="c-admin" controller="comment" action="edit" id="${c.id}">&Pi;</g:link></sec:ifAnyGranted>
			${c.content.encodeAsHTML().replaceAll(/(.*)\n/, '<p>$1</p>')}
			</div>
           <div class="c-footer"><my:prettyTime date="${c.lastUpdated}" /><g:link class="c-user" controller="profile" action="view" id="${c.user.id}">${c.user.username}</g:link></div>
        </g:each>
			
			<a href="#" class="toggleNext">ajouter un commentaire</a>
           	<g:form class="hidden" controller="meal" action="saveComment" >
           		<g:hiddenField name="meal.id" value="${mealInstance.id}" />
            	<p><g:textArea name="content" /></p>
            	<p><g:submitButton name="create" value="Envoyer" /></p>
			</g:form>
 

			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<h3>Admin</h3>
                <g:form>
                    <g:hiddenField name="id" value="${mealInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
			</sec:ifAnyGranted>
			</div>
        </div> 
       <script type="text/javascript">
       	 var myNewFlow = new ContentFlow('mealContentFlow', { startItem:'last' } ) ;
        </script>  
        
    </body>
</html>
