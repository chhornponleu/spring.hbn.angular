(function(module) {
	
	app.controllers = $.extend(module, {
		LoginController : function (scope, $rootScope) {
			scope.welcome = 'Welcome to hello world';
		}
	});
	
	app.ng.application.controller('LoginController', ['$scope', '$rootScope', app.controllers.LoginController]).run(function () {
		console.info('Welcome controller has been initialized');
	});

}(app.controllers || {}));
