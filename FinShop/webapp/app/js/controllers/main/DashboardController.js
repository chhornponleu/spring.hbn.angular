(function(module) {

app.controllers = $.extend(module, {
	DashboardController : function (scope, rootScope) {
		
	}
});

app.ng.application.controller('DashboardController', ['$scope', '$rootScope', app.controllers.WelcomeController]).run(function () {
	console.info('DashboardController controller has been initialized');
});

}(app.controllers || {}));
