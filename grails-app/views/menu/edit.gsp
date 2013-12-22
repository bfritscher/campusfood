

<%@ page import="ch.fritscher.campusfood.Menu" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'menu.label', default: 'Menu')}" />
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
            <g:hasErrors bean="${menuInstance}">
            <div class="errors">
                <g:renderErrors bean="${menuInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${menuInstance?.id}" />
                <g:hiddenField name="version" value="${menuInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="menu.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: menuInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${menuInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="location"><g:message code="menu.location.label" default="Location" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: menuInstance, field: 'location', 'errors')}">
                                    <g:select name="location.id" from="${ch.fritscher.campusfood.Location.list()}" optionKey="id" value="${menuInstance?.location?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="meals"><g:message code="menu.meals.label" default="Meals" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: menuInstance, field: 'meals', 'errors')}">
                                    
<ul>
<g:each in="${menuInstance?.meals?}" var="m">
    <li><g:link controller="meal" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="meal" action="create" params="['menu.id': menuInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'meal.label', default: 'Meal')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="menuType"><g:message code="menu.menuType.label" default="Menu Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: menuInstance, field: 'menuType', 'errors')}">
                                    <g:select name="menuType" from="${ch.fritscher.campusfood.MenuType?.values()}" keys="${ch.fritscher.campusfood.MenuType?.values()*.name()}" value="${menuInstance?.menuType?.name()}"  />
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
