<head>
<title><g:message code='spring.security.ui.resetPassword.title'/></title>
<meta name='layout' content='campusfood'/>
</head>

<body>
<div class="box">
	<h2><g:message code="spring.security.ui.resetPassword.title"/></h2>
	<g:form action='resetPassword' name='resetPasswordForm' autocomplete='off'>
		<g:hiddenField name='t' value='${token}'/>
			<h4><g:message code='spring.security.ui.resetPassword.description'/></h4>

	<table>
		<s2ui:passwordFieldRow name='password' labelCode='resetPasswordCommand.password.label' bean="${command}"
                             labelCodeDefault='Password' value="${command?.password}"/>
	</table>

	<g:submitButton name="submit" value="${message(code:'spring.security.ui.resetPassword.submit')}"/>
	</g:form>
</div>
</body>
