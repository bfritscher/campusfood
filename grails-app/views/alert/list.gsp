
<%@ page import="ch.fritscher.campusfood.Alert" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'alert.label', default: 'Alert')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'alert.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'alert.dateCreated.label', default: 'Date Created')}" />
                        
                            <th><g:message code="alert.tag.label" default="Tag" /></th>
                        
                            <th>Action</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${alertInstanceList}" status="i" var="alertInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${alertInstance.id}">${fieldValue(bean: alertInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${alertInstance.dateCreated}" /></td>
                        
                            <td>${fieldValue(bean: alertInstance, field: "tag")}</td>
                        
                            <td><g:form>
                    				<g:hiddenField name="id" value="${alertInstance?.id}" />
                    				<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    			</g:form></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${alertInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
