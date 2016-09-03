(function(module) {

app.controllers = $.extend(module, {
	OrderDetailController : function ($scope, $rootScope, $routeParams, $timeout, restServiceProvider, angularGridInstance) { 
		var orderId = $routeParams.orderId;
		
		$scope.events = {
			print : function () {
				$rootScope.isPrinting = true;
				$timeout(function () {
					window.print();
					$rootScope.isPrinting = false;
				},300);
			},
			orderPaid : function () {
				restServiceProvider.orders.setPaid({id : $scope.order.id}, function (resp) {
					if(resp && resp.header && resp.header.result) {
						$scope.order.status = 'SA';
						$scope.order.paidAmount = $scope.order.totalAmount - $scope.order.discountAmount;
					}
					else {
						alert('Something wrong happened. Please contact System Administrator.');
					}
					
				}, function () {
					alert('Ooop....');
				});
			},
			refreshGrid : function(){
	            angularGridInstance.gallery.refresh();
	        }
		};
		
		restServiceProvider.orders.get({orderId:orderId}, function (resp) {
			if(resp && resp.header && resp.header.result) {
				var order = resp.body;
				$scope.order = prepareOrderForView(order);
			}
		});
		
		function prepareOrderForView(order) {
			if(!order) return order;
			var ods = order.orderDetails;
			var newOds = [];
			if(angular.isArray(ods)) {
				angular.forEach(ods, function (od, ind) {
					var p = undefined;
					$.each(newOds, function (index, item) {
						if(od.id.product.id == item.id) {
							p = item;
							return false;
						}
					});
					if(p === undefined) {
						p = od.id.product;
						p.attributes = [];
						newOds.push(p);
					}
					p.attributes.push({
						id : {
							attribute : od.id.attribute
						},
						quantity : od.attributeQty,
						unitPrice : od.attributePrice
					});
				});
			}
			order.orderDetails = newOds;
			console.log(order);
			return order;
		}
	}
});

app.ng.application.controller('OrderDetailController', ['$scope', '$rootScope', '$routeParams', '$timeout', 
                                                        'restServiceProvider', 'angularGridInstance', app.controllers.OrderDetailController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
