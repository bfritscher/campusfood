
<%@ page import="ch.fritscher.campusfood.Comment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'comment.label', default: 'Comment')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'comment.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="content" title="${message(code: 'comment.content.label', default: 'Content')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'comment.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'comment.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <th><g:message code="comment.meal.label" default="Meal" /></th>
                        
                            <g:sortableColumn property="status" title="${message(code: 'comment.status.label', default: 'Status')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${commentInstanceList}" status="i" var="commentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${commentInstance.id}">${fieldValue(bean: commentInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: commentInstance, field: "content")}</td>
                        
                            <td><g:formatDate date="${commentInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${commentInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: commentInstance, field: "meal")}</td>
                        
                            <td>${fieldValue(bean: commentInstance, field: "status")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${commentInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
