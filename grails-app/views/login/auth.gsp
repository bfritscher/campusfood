<head>
<title><g:message code='spring.security.ui.login.title'/></title>
<meta name='layout' content='campusfood'/>
</head>

<body>
<div class="box">
	<h2><g:message code="spring.security.ui.login.title" /></h2>
	<form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>
	<table>
		<tr>
			<td><label for="username"><g:message code='spring.security.ui.login.username'/></label></td>
			<td><input name="j_username" id="username" size="20" /></td>
		</tr>
		<tr>
			<td><label for="password"><g:message code='spring.security.ui.login.password'/></label></td>
			<td><input type="password" name="j_password" id="password" size="20" /></td>
		</tr>
		<tr>
			<td colspan='2'>
				<input type="checkbox" class="checkbox" name="${rememberMeParameter}" id="remember_me" checked="checked" />
				<label for='remember_me'><g:message code='spring.security.ui.login.rememberme'/></label> |
				<span class="forgot-link">
					<g:link controller='register' action='forgotPassword'><g:message code='spring.security.ui.login.forgotPassword'/></g:link>
				</span>
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<g:link action='index' controller='register' ><g:message code='spring.security.ui.login.register'/></g:link>
				<g:submitButton name="submit" value="${message(code:'spring.security.ui.login.login')}"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
