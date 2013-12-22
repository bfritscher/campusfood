<head>
<title><g:message code='spring.security.ui.forgotPassword.title'/></title>
<meta name='layout' content='campusfood'/>
</head>

<body>
<div class="box">
	<h2><g:message code="spring.security.ui.forgotPassword.title" /></h2>
	<g:form action='forgotPassword' name="forgotPasswordForm" autocomplete='off'>
	<g:if test='${emailSent}'>
		<g:message code='spring.security.ui.forgotPassword.sent'/>
	</g:if>
	<g:else>
		<h4><g:message code='spring.security.ui.forgotPassword.description'/></h4>
		<g:if test="${flash.error}">
   			<div class="message errors">${flash.error}</div>
   		</g:if>
		<table>
			<tr>
				<td><label for="username"><g:message code='spring.security.ui.forgotPassword.username'/></label></td>
				<td><g:textField name="username" size="25" /></td>
			</tr>
		</table>
		<g:submitButton name="submit" value="${message(code:'spring.security.ui.forgotPassword.submit')}"/>
	</g:else>
	</g:form>
</div>
</body>
