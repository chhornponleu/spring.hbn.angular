<%@include file="../taglibs.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge">
  		<title></title>
  		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

		<link rel="stylesheet" href="${baseUrl}app/bower_components/bootstrap/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/Ionicons/css/ionicons.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/AdminLTE/dist/css/AdminLTE.min.css">
		<link rel="stylesheet" href="${baseUrl}app/bower_components/AdminLTE/dist/css/skins/skin-black-light.min.css">
		
		<link rel="stylesheet" href="${baseUrl}app/css/common.css">
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
	</head>
	<body class="skin-black login-page">
		<div class="login-box">
			<div class="login-logo">
				<a href="#"><b>Shop </b>Commerce</a>
			</div>
			<!-- /.login-logo -->
			<div class="login-box-body">
				<p class="login-box-msg">Sign in to start your session</p>
		
				<h3 class="form-title">Login to your account</h3>
				<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
					<div class="alert alert-danger">
					   <span>${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</span>
					</div>
				</c:if>
				<form action="<c:url value="/user/authenticate"/>" method="POST">
					<input type="hidden" name="_csrf" value="${_csrf.token}">
			      	<div class="form-group">
						<label class="control-label visible-ie8 visible-ie9">Username</label>
						<div class="input-icon">
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username" autofocus="autofocus" required="required"/> 
						</div>
			      	</div>
			      	<div class="form-group">
						<label class="control-label visible-ie8 visible-ie9">Password</label>
			         	<div class="input-icon">
			 				<input class="form-control" type="password" autocomplete="off" 
			 					placeholder="Password" name="password" required="required"/> 
						</div>
			   		</div>
			   		<div class="form-actions">
					    <button type="submit" class="btn btn-success btn-flat btn-block btn-login"><i class="fa fa-lock"></i> Login </button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>