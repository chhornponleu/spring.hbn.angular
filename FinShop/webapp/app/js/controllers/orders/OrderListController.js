(function(module) {

app.controllers = $.extend(module, {
	OrderListController : function ($scope, $rootScope, $uibModal, $location, restServiceProvider, pagingConfig) { 
		
		var searchTimeout = undefined;
		
		$scope.data = {
			orders : []	
		};
		$scope.pagingConfig = pagingConfig;
		
		$scope.events = {
			pageChanged	: function () {
				reloadOrderList();
			},
			reloadOrders : function () {
				searchTimeout && clearTimeout(searchTimeout) ;
				searchTimeout = setTimeout(function () {
					console.log($scope.data)
					reloadOrderList();
				}, 800);
			},
			showOrderDetail : function (orderId) {
				$location.path('orders/'+orderId+'/detail');
			}
		};
		
		function reloadOrderList () {
			var search = $scope.data.search;
			if(search) {
				var order = {};
				
				if(angular.isString(search) && search.trim().indexOf('#') == 0) {
					order.id = search.replace('#', '');
				}
				else {
					if(!isNaN(Number(search))) {
						order.id = search;
					}
					order.customer = {};
					order.customer.customerName = search;
					order.customer.contact = search;
					order.customer.address = search;
				}
				$scope.pagingConfig.search = order;
			}
			else {
				$scope.pagingConfig.search = {};
			}
			restServiceProvider.orders.getPaging($scope.pagingConfig, function (resp) {
				$scope.data.orders = resp.body.data;
				$scope.pagingConfig.total = resp.body.total;
			});
		}
		reloadOrderList();
		
	}
});

app.ng.application.controller('OrderListController', ['$scope', '$rootScope', '$uibModal', '$location', 'restServiceProvider', 'pagingConfig', app.controllers.OrderListController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
