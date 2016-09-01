<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html ng-controller="RootController">
	<head>
		<meta charset="utf-8">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge">
  		<title></title>
  		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

		<%-- <link rel="stylesheet" href="${baseUrl}app/bower_components/select2/dist/css/select2.css"> --%>
		<link rel="stylesheet" href="${baseUrl}app/bower_components/bootstrap/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/Ionicons/css/ionicons.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/AdminLTE/dist/css/AdminLTE.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/AdminLTE/dist/css/skins/skin-black-light.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/angular-ui-select/dist/select.css">
		
		<link rel="stylesheet" href="${baseUrl}app/css/common.css">
		<link rel="stylesheet" href="${baseUrl}app/css/print.css">
		
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
	</head>
	<body class="sidebar-mini skin-black fixed" ng-class="{'sidebar-collapse' : collapsed, 'print' : isPrinting}" ng-cloak>
		<div class="wrapper">
			<header class="main-header">
				<!-- Logo -->
				<a href="#!/" class="logo bg-yellow">
					<span class="logo-mini"><b>F</b>IN</span>
					<span class="logo-lg" translate="app_name"></span>
				</a>
				<nav class="navbar navbar-static-top" style="background-color: rgba(255, 255, 255, 0.9);">
					<!-- Sidebar toggle button-->
					<a ng-click="toggleSidebarCollapsed()" href="javascript:void(0);" class="sidebar-toggle"> 
						<span class="sr-only">Toggle navigation</span> 
						<span class="icon-bar"></span> <span class="icon-bar"></span> 
						<span class="icon-bar"></span>
					</a>
		
					<div class="navbar-custom-menu">
						<ul class="nav navbar-nav">
							<li class="dropdown notifications-menu">
								<a href="#!/orders/cart" class="dropdown-toggle" data-toggle="dropdown">
									<i class="fa fa-shopping-cart"></i> 
									<span translate="menu.cart"></span>
									<span class="label label-info" ng-bind="cart.count"></span>
								</a>
							</li>
							<li class="dropdown user user-menu">
				            	<a href="#!/user/profile" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				              		<i class="fa fa-user"></i> 
					              	<span class="hidden-xs" translate="menu.profile"></span>
				            	</a>
					   		</li>
					   		<li> 
								<form action="<c:url value="/user/logout"/>" method="POST">
									<input type="hidden" name="_csrf" value="${_csrf.token}">
									<button type="submit" class="btn btn-link" style="margin-top: 9px;"> <i class="fa fa-sign-out"></i></button>
								</form>
							</li>
						</ul>
					</div>
				</nav>
			</header>

			<aside class="main-sidebar hidden-print">
				<section class="sidebar">
					<ul class="sidebar-menu">
						<li class="treeview">
							<a href="#!/"><i class="fa fa-dashboard"></i><span translate="menu.dashboard"></span></a>
						</li>
						<li class="treeview">
							<a href="#!/products"><i class="fa fa-list"></i><span translate="menu.product"></span></a>
						</li>
						<li class="treeview">
							<a href="#!/orders"><i class="fa fa-get-pocket"></i><span translate="menu.order"></span></a>
						</li>
					</ul>
				</section>
			</aside>


			<div class="content-wrapper" style="min-height:700px;">
				<div ng-view></div>
			</div>
		</div>
		<script type="text/javascript" data-main="${baseUrl}app/js/loader" src="${baseUrl}app/bower_components/requirejs/require.js"></script>
	</body>
</html>