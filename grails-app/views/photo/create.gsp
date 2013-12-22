

<%@ page import="ch.fritscher.campusfood.Photo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${photoInstance}">
            <div class="errors">
                <g:renderErrors bean="${photoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="photo.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'description', 'errors')}">
                                    <g:textArea name="description" cols="40" rows="5" value="${photoInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="imageExtension"><g:message code="photo.imageExtension.label" default="Image Extension" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'imageExtension', 'errors')}">
                                    <g:textField name="imageExtension" value="${photoInstance?.imageExtension}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="meal"><g:message code="photo.meal.label" default="Meal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'meal', 'errors')}">
                                    <g:select name="meal.id" from="${ch.fritscher.campusfood.Meal.list()}" optionKey="id" value="${photoInstance?.meal?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status"><g:message code="photo.status.label" default="Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'status', 'errors')}">
                                    <g:select name="status" from="${ch.fritscher.campusfood.ItemStatus?.values()}" keys="${ch.fritscher.campusfood.ItemStatus?.values()*.name()}" value="${photoInstance?.status?.name()}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user"><g:message code="photo.user.label" default="User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'user', 'errors')}">
                                    <g:select name="user.id" from="${ch.fritscher.campusfood.User.list()}" optionKey="id" value="${photoInstance?.user?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
