(function(module) {

app.controllers = $.extend(module, {
	WelcomeController : function (scope, rootScope) {
		console.log(localStorage);
		
		scope.collapsed = false;
		scope.toggleSidebarCollapsed = function () {
			scope.collapsed = !scope.collapsed;
			console.log(scope.collapsed);
		}
		
		
		
		
	}
});

app.ng.application.controller('MainController', ['$scope', '$rootScope', app.controllers.WelcomeController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
