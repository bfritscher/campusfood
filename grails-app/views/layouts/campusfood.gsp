<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <title><g:layoutTitle default="Campus Food" /></title>
		<asset:stylesheet src="campusfood.css"/>
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <script type="text/javascript">
			var baseURL = '/';
        </script>
		<asset:javascript src="jquery-2.2.0.min.js"/>
		<asset:javascript src="jquery-migrate-1.4.1.min.js"/>
		<asset:javascript src="campusfood.js"/>
        <!--[if !IE 7]>
		<style type="text/css">
			#wrap {display:table;height:100%}
		</style>
		<![endif]-->

		<g:layoutHead />
        <script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36525343-2']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-36525343-3', 'isisvn.unil.ch');
  ga('send', 'pageview');

</script>
    </head>
    <body>
		<div id="wrap">
			<div id="main">
				<div id="header">
					<div id="login_box">
					<sec:ifLoggedIn>
						<sec:ifAnyGranted roles="ROLE_ADMIN">
						<g:link controller="admin" action="parse">Parse</g:link>
						<g:link controller="user">Springsecurity UI</g:link>
						<g:link controller="admin">controllers</g:link>
						</sec:ifAnyGranted>
						<g:link controller="profile"><sec:username/></g:link>
						<g:link controller="logoff">Déconnecter</g:link>
					</sec:ifLoggedIn>
					<sec:ifNotLoggedIn>
						<g:link action='index' controller='register' ><g:message code='spring.security.ui.login.register'/></g:link>
						<g:link controller="login" action="auth">Se connecter</g:link>
					</sec:ifNotLoggedIn>
						<br class="clear"/>
					</div>
					<h1><g:link controller="main" action="index">Campus Food</g:link></h1>
					<div class="nav">
						<g:link controller="android" action="index">Application Android</g:link>
						<g:link controller="api" action="index">API</g:link>
					</div>
				</div>
				<g:layoutBody />

			</div>
		</div>
		<div id="footer">
			<div id="footer-content">alpha <g:meta name="app.version"></g:meta></div>
		</div>
    </body>
</html>