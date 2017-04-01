<div id="menu-${meal?.menu?.id}" class="meal ${meal?.menu?.location}">
	<g:if test="${meal.menu}"><h3><g:link controller="meal" action="show" id="${meal.id}">${meal.menu}</g:link></h3></g:if>
	<div class="meal-content">${raw(meal.content?.replaceAll(/(.*)\n/, '<p>$1</p>'))}</div>
</div>