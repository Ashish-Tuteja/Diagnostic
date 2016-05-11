<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-us" id="extr-page" ng-app="app">
<head>
<meta charset="utf-8">
<title>Cimble's AuctionPro</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- #CSS Links -->
<!-- Basic Styles -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'></c:url>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/popup.css'></c:url>">

<!-- jQuery library -->
<script src="<c:url value='/resources/js/libs/jquery.min.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/bootstrap/bootstrap.min.js'></c:url>"></script>
<!-- AngulR Libraries -->
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.8/angular-route.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.8/angular-resource.min.js"></script>
<script src="<c:url value='/resources/js/angular/module.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/service/UserAppService.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/controller/AdminCtrl.js'></c:url>"></script>
<script 
	src="<c:url value='/resources/js/libs/angular/truncate.js'></c:url>"></script>
<!-- End -->
<!--End-->

<!-- <link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/font-awesome.min.css'></c:url>">

<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/smartadmin-production-plugins.min.css'></c:url>">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/smartadmin-production.min.css'></c:url>">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/smartadmin-skins.min.css'></c:url>">



<!-- SmartAdmin RTL Support -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/smartadmin-rtl.min.css'></c:url>">

<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/demo.min.css'></c:url>">

<!-- #FAVICONS -->
<link rel="shortcut icon"
	href="<c:url value='/resources/img/favicon/favicon.ico'></c:url>"
	type="image/x-icon">
<link rel="icon"
	href="<c:url value='/resources/img/favicon/favicon.ico'></c:url>"
	type="image/x-icon">

<!-- #GOOGLE FONT -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

<!-- #APP SCREEN / ICONS -->
<!-- Specifying a Webpage Icon for Web Clip 
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
<link rel="apple-touch-icon"
	href="<c:url value='/resources/img/splash/sptouch-icon-iphone.png'></c:url>">
<link rel="apple-touch-icon" sizes="76x76"
	href="<c:url value='/resources/img/splash/touch-icon-ipad.png'></c:url>">
<link rel="apple-touch-icon" sizes="120x120"
	href="<c:url value='/resources/img/splash/touch-icon-iphone-retina.png'></c:url>">
<link rel="apple-touch-icon" sizes="152x152"
	href="<c:url value='/resources/img/splash/touch-icon-ipad-retina.png'></c:url>">

<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- Startup image for web apps -->
<link rel="apple-touch-startup-image"
	href="<c:url value='/resources/img/splash/ipad-landscape.png'></c:url>"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
<link rel="apple-touch-startup-image"
	href="<c:url value='/resources/img/splash/ipad-portrait.png'></c:url>"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
<link rel="apple-touch-startup-image"
	href="<c:url value='/resources/img/splash/iphone.png'></c:url>"
	media="screen and (max-device-width: 320px)">
<!--================================================== -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<script
	src="<c:url value='/resources/js/plugin/pace/pace.min.js'></c:url>"></script>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script> if (!window.jQuery) { document.write('<script src="<c:url value='/resources/js/libs/jquery-2.1.1.min.js'></c:url>"><\/script>');} </script> -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script>
	if (!window.jQuery.ui) {
		document
				.write('<script src="<c:url value='/resources/js/libs/jquery-ui-1.10.3.min.js'></c:url>"><\/script>');
	}
</script>

<!-- IMPORTANT: APP CONFIG -->
<script src="<c:url value='/resources/js/app.config.js'></c:url>"></script>

<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->


<!-- JQUERY VALIDATE -->
<script
	src="<c:url value='/resources/js/plugin/jquery-validate/jquery.validate.min.js'></c:url>"></script>

<!-- JQUERY MASKED INPUT -->
<script
	src="<c:url value='/resources/js/plugin/masked-input/jquery.maskedinput.min.js'></c:url>"></script>

<!--[if IE 8]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->

<!-- MAIN APP JS FILE -->
<script src="<c:url value='/resources/js/app.min.js'></c:url>"></script>

<script type="text/javascript">
	//runAllForms();

	$(function() {
		// Validation
		$("#login-form").validate({
			// Rules for form validation
			rules : {
				email : {
					required : true,
					email : true
				},
				password : {
					required : true,
					minlength : 3,
					maxlength : 20
				}
			},

			// Messages for form validation
			messages : {
				email : {
					required : 'Please enter your email address',
					email : 'Please enter a VALID email address'
				},
				password : {
					required : 'Please enter your password'
				}
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	});
</script>
<script
	src="<c:url value='/resources/js/jquery.pnotify.min.js'></c:url>"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/jquery.loader.css'></c:url>">
<script src="<c:url value='/resources/js/jquery-loader.js'></c:url>"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/pnotify.custom.min.css'></c:url>">
<script src="<c:url value='/resources/js/cimble.js'></c:url>"></script>
</head>

<body>
	<div ng-view></div>


</body>
</html>
