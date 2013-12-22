

<%@ page import="ch.fritscher.campusfood.Meal" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'meal.label', default: 'Meal')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${mealInstance}">
            <div class="errors">
                <g:renderErrors bean="${mealInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${mealInstance?.id}" />
                <g:hiddenField name="version" value="${mealInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="menu"><g:message code="meal.menu.label" default="Menu" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'menu', 'errors')}">
                                    <g:select name="menu.id" from="${ch.fritscher.campusfood.Menu.list()}" optionKey="id" value="${mealInstance?.menu?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateServed"><g:message code="meal.dateServed.label" default="Date Served" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'dateServed', 'errors')}">
                                    <g:datePicker name="dateServed" precision="day" value="${mealInstance?.dateServed}" default="none" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="content"><g:message code="meal.content.label" default="Content" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'content', 'errors')}">
                                    <g:textArea name="content" cols="40" rows="5" value="${mealInstance?.content}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="rawContent"><g:message code="meal.rawContent.label" default="Raw Content" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'rawContent', 'errors')}">
                                    <g:textArea name="rawContent" cols="40" rows="5" value="${mealInstance?.rawContent}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comments"><g:message code="meal.comments.label" default="Comments" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'comments', 'errors')}">
                                    
<ul>
<g:each in="${mealInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="comment" action="create" params="['meal.id': mealInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="photos"><g:message code="meal.photos.label" default="Photos" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'photos', 'errors')}">
                                    
<ul>
<g:each in="${mealInstance?.photos?}" var="p">
    <li><g:link controller="photo" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="photo" action="create" params="['meal.id': mealInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'photo.label', default: 'Photo')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tags"><g:message code="meal.tags.label" default="Tags" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'tags', 'errors')}">
                                    <g:select name="tags" from="${ch.fritscher.campusfood.Tag.list()}" multiple="yes" optionKey="id" size="5" value="${mealInstance?.tags*.id}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
