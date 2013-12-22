
<%@ page import="ch.fritscher.campusfood.Photo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'photo.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'photo.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="imageExtension" title="${message(code: 'photo.imageExtension.label', default: 'Image Extension')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'photo.dateCreated.label', default: 'Date Created')}" />
                        
                            <th><g:message code="photo.meal.label" default="Meal" /></th>
                        
                            <g:sortableColumn property="status" title="${message(code: 'photo.status.label', default: 'Status')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${photoInstanceList}" status="i" var="photoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${photoInstance.id}">${fieldValue(bean: photoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: photoInstance, field: "description")}</td>
                        
                            <td>${fieldValue(bean: photoInstance, field: "imageExtension")}</td>
                        
                            <td><g:formatDate date="${photoInstance.dateCreated}" /></td>
                        
                            <td>${fieldValue(bean: photoInstance, field: "meal")}</td>
                        
                            <td>${fieldValue(bean: photoInstance, field: "status")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${photoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
