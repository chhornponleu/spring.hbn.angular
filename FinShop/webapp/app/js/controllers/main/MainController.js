(function(module) {

app.controllers = $.extend(module, {
	MainController : function (scope, rootScope) { 
		
		scope.collapsed = false;
		scope.toggleSidebarCollapsed = function () {
			scope.collapsed = !scope.collapsed;
			console.log(scope.collapsed);
		}
		
		
	}
});

app.ng.application.controller('MainController', ['$scope', '$rootScope', app.controllers.MainController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
