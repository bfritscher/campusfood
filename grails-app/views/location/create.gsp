<%@ page import="ch.fritscher.campusfood.Location" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="campusfood" />
        <g:set var="entityName" value="${message(code: 'location.label', default: 'Location')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="box">
            <h2><g:message code="default.create.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${locationInstance}">
            <div class="errors">
                <g:renderErrors bean="${locationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save">
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="location.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: locationInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${locationInstance?.name}" />
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
