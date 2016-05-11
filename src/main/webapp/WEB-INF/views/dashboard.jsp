
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en-us" id="extr-page" ng-app="dashboard">
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
<!-- Spinner-->
<script
	src="<c:url value='/resources/js/cimble.js'></c:url>"></script>
<!-- AngulR Libraries -->
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.8/angular-route.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.8/angular-resource.min.js"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/ng-breadcrumbs.js'></c:url>"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.15/angular-cookies.min.js"></script>
	
<script src="<c:url value='/resources/js/libs/angular/truncate.js'></c:url>"></script>
	
<!-- Switch Buttons JS and CSS-->
<link rel="stylesheet"
	href="<c:url value='/resources/css/angular-material.css'></c:url>">
<script src="<c:url value='/resources/js/hammer.min.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular-animate.min.js'></c:url>"></script>
<script src="<c:url value='/resources/js/angular-aria.min.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular-material.min.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/bootbox.min.js'></c:url>"></script>


<script src="<c:url value='/resources/js/angular/module.js'></c:url>"></script>
<!--Dashboard -->
<script
	src="<c:url value='/resources/js/angular/controller/DashboardCtrl.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/service/DashboardAppService.js'></c:url>"></script>

<!-- Account -->
<script
	src="<c:url value='/resources/js/angular/controller/AccountCtrl.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/service/AccountAppService.js'></c:url>"></script>


<!-- User -->
<script
	src="<c:url value='/resources/js/angular/controller/UserCtrl.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/controller/UserAccountCtrl.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/service/UserAppService.js'></c:url>"></script>

<!-- Device -->
<script
	src="<c:url value='/resources/js/angular/controller/DeviceCtrl.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/service/DeviceAppService.js'></c:url>"></script>

<!-- Vehicle -->
<script
	src="<c:url value='/resources/js/angular/controller/VehicleCtrl.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/controller/ImportCtrl.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/angular/service/VehicleAppService.js'></c:url>"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/font-awesome.min.css'></c:url>">

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/smartadmin-rtl.min.css'></c:url>">

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
<script
	src="<c:url value='/resources/js/plugin/pace/pace.min.js'></c:url>"></script>

<script
	src="<c:url value='/resources/js/libs/jquery-ui-1.10.3.min.js'></c:url>"></script>
<script>
	if (!window.jQuery.ui) {
		document
				.write('<script src="<c:url value='/resources/js/libs/jquery-ui-1.10.3.min.js'></c:url>"><\/script>');
	}
</script>

<!-- IMPORTANT: APP CONFIG -->
<script src="<c:url value='/resources/js/app.config.js'></c:url>"></script>
<!--custom.css -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/datepicker.min.css'></c:url>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/datepicker3.min.css'></c:url>">
<script
	src="<c:url value='/resources/js/plugin/datepicker/bootstrap-datepicker.min.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/plugin/datepicker/moment.min.js'></c:url>"></script>
<!-- full calendar view  -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/fullcalendar.min.css'></c:url>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-switch.min.css'></c:url>">
<script
	src="<c:url value='/resources/js/plugin/fullcalendar/fullcalendar.js'></c:url>"></script>
<script
	src="<c:url value='/resources/js/jquery.pnotify.min.js'></c:url>"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/pnotify.custom.min.css'></c:url>">

<link rel="stylesheet"
	href="http://ironsummitmedia.github.io/startbootstrap-simple-sidebar/css/simple-sidebar.css">
<link rel="stylesheet"
	href="http://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0-rc2/css/bootstrap-glyphicons.css">
<script src="<c:url value='/resources/js/bootstrap-switch.js'></c:url>"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css'></c:url>">

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/custom.css'></c:url>">

</head>
<body ng-controller="DashboardCtrl">
	<input type="hidden" value='${permissions}' id="permissionValue">
	<input type="hidden" name="accountSession"
		value='<%=session.getAttribute("accountId")%>'
		ng-model="accountSession">
	<sec:authentication var="principal" property="principal" />
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#/"> <img class="defaultlogo"
					alt="cimble"></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">

				<ul class="nav navbar-nav">
					<!--select class="form-control" style="margin-top:10px;" ng-change="accountinScope(parentAccount)" ng-model="parentAccount">
	                      <option ng-repeat="parentAccount in parentaccounts"  > {{parentAccount.accountName}}</option>
	                </select-->
					<select class="form-control" style="margin-top: 10px;"
						ng-change="accountinScope(parentAccount)" ng-model="parentAccount"
						ng-options="obj.id as obj.accountName for obj in parentaccounts">
						<option value="">--Select an account--</option>
					</select>
				</ul>
				</li>
				</ul-->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#useraccount" title="User Account" class="user">${principal.emailId}
					</a></li>

					<li><a href="/cimble/logout" title="Sign Out"><span
							class="glyphicon glyphicon-log-out"></span></a></li>

				</ul>

			</div>
		</div>
	</nav>
	<!--/.nav-collapse -->

	<!-- Sidebar Content -->
	<div class="row">

		<div class="container col-lg-1 col-xs-2 col-md-2"
			ng-include="'views/sidepanel.html'" style="z-index: 100"></div>
		<div class="container col-lg-12 col-xs-12 col-md-12" ng-view
			style="padding-top: 63px; padding-left: 43px; padding-right: 0px;"></div>


	</div>

	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>
</html>
