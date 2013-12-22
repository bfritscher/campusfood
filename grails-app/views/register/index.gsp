<head>
	<meta name='layout' content='campusfood'/>
	<title><g:message code='spring.security.ui.register.title'/></title>
</head>

<body>
<div class="box">
	<h2><g:message code='spring.security.ui.register.description'/></h2>
	<g:form action='register' name='registerForm'>

	<g:if test='${emailSent}'>
		<g:message code='spring.security.ui.register.sent'/>
	</g:if>
	<g:else>
		<table>
			<tbody>
				<s2ui:textFieldRow name='username' labelCode='user.username.label' bean="${command}"
                         size='40' labelCodeDefault='Username' value="${command.username}"/>

				<s2ui:textFieldRow name='email' bean="${command}" value="${command.email}"
		                   size='40' labelCode='user.email.label' labelCodeDefault='E-mail'/>

				<s2ui:passwordFieldRow name='password' labelCode='user.password.label' bean="${command}"
                             size='40' labelCodeDefault='Password' value="${command.password}"/>

				</tbody>
			</table>
		<g:submitButton name="submit" value="${message(code:'spring.security.ui.register.submit')}"/>
	</g:else>
</g:form>
</div>
</body>
