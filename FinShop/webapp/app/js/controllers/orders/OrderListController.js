(function(module) {

app.controllers = $.extend(module, {
	OrderListController : function (scope, rootScope) { 
		
		scope.collapsed = false;
		scope.toggleSidebarCollapsed = function () {
			scope.collapsed = !scope.collapsed;
			console.log(scope.collapsed);
		}
		
		
	}
});

app.ng.application.controller('OrderListController', ['$scope', '$rootScope', app.controllers.OrderListController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
