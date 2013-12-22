<%@ page import="ch.fritscher.campusfood.Meal" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="campusfood" />
        <g:set var="entityName" value="${message(code: 'meal.label', default: 'Meal')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="box">
            <h2><g:message code="default.create.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${mealInstance}">
            <div class="errors">
                <g:renderErrors bean="${mealInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" id="${params.id}">
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="menu"><g:message code="meal.menu.label" default="Category" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'menu', 'errors')}">
                                    ${ch.fritscher.campusfood.Menu.get(params.id)}
                                    <g:hiddenField name="menu.id" value="${params.id}"></g:hiddenField>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="content"><g:message code="meal.content.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mealInstance, field: 'content', 'errors')}">
                                    <g:textArea name="content" cols="40" rows="5" value="${mealInstance?.content}" />
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
