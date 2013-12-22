
<%@ page import="ch.fritscher.campusfood.Meal" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'meal.label', default: 'Meal')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'meal.id.label', default: 'Id')}" />
                        
                            <th><g:message code="meal.menu.label" default="Menu" /></th>
                        
                            <g:sortableColumn property="dateServed" title="${message(code: 'meal.dateServed.label', default: 'Date Served')}" />
                        
                            <g:sortableColumn property="content" title="${message(code: 'meal.content.label', default: 'Content')}" />
                        
                            <g:sortableColumn property="rawContent" title="${message(code: 'meal.rawContent.label', default: 'Raw Content')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${mealInstanceList}" status="i" var="mealInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${mealInstance.id}">${fieldValue(bean: mealInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: mealInstance, field: "menu")}</td>
                        
                            <td><g:formatDate date="${mealInstance.dateServed}" /></td>
                        
                            <td>${fieldValue(bean: mealInstance, field: "content")}</td>
                        
                            <td>${fieldValue(bean: mealInstance, field: "rawContent")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${mealInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
