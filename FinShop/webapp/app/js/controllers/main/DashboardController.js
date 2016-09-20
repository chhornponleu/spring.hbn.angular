(function(module) {

app.controllers = $.extend(module, {
	DashboardController : function ($scope, $rootScope, $location, restServiceProvider) {
		$scope.orders = [];
		restServiceProvider.orders.getUnpaid(function (resp) {
			console.log(resp);
			if(resp && resp.header && resp.header.result) {
				$scope.orders = resp.body;
			}
		});
		
		$scope.events = {
			showOrderDetail : function (orderId) {
				$location.path('orders/'+orderId+'/detail');
			}
		};
	}
});

app.ng.application.controller('DashboardController', ['$scope', '$rootScope', '$location', 'restServiceProvider', app.controllers.DashboardController]).run(function () {
	console.info('DashboardController controller has been initialized');
});

}(app.controllers || {}));
