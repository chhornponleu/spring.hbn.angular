(function(module) {

app.controllers = $.extend(module, {
	OrderCheckoutController : function ($scope, $rootScope, $location, myCartService, restServiceProvider) {
		var orderTpl = {
			pickupDate : '',
			totalAmount : '',
			paidAmount : '',
			discountAmount : '',
			taxAmount : '',
			customer : {},
			orderDetails : [
			]
		};
		var orderDetailTpl = {
			id : {
				product : { id : '' },
				attribute : { id : '' }
			},
			attributePrice : 0
		};
		
		var tmpCustomerName = '_tmpCustomer';
		$scope.order = {
			customer : JSON.parse(localStorage.getItem(tmpCustomerName))
		};
		$scope.products = myCartService.getAll();
		console.log($scope.products)
		$scope.events  = {
			saveOrder : function (valid) {
				if(valid) {
					var order = buildOrderObject();
					restServiceProvider.orders.save(order, function (resp) {
						console.log(resp);
						if(resp && resp.header && resp.header.result) {
							myCartService.removeAll();
							localStorage.removeItem(tmpCustomerName);
							var productId = resp.body;
							$location.path('/orders/'+productId+'/detail');
						}
						else {
							alert('Cannot save order. Please try again')
						}
					});
				}
			}
		};
		
		$scope.$watch('products', function (old, new_) {
			$scope.order.totalAmount = getTotalAmount();
		}, true)
		
		function buildOrderObject() {
			var order = orderTpl;
			
			order.pickupDate = new Date();
			order.totalAmount = getTotalAmount();
			order.paidAmount = $scope.order.paidAmount,
			order.discountAmount = $scope.order.discountAmount || 0;
			order.taxAmount = 0;
			order.customer = $scope.order.customer;
			order.orderDetails = [];
			angular.forEach($scope.products, function (pItem, pIndex) {
				if(pItem.attributes) {
					var attributes = pItem.attributes;
					angular.forEach(attributes, function (oItem, oIndex) {
						var od = angular.copy(orderDetailTpl);
						od.id.product.id= pItem.id;
						od.id.attribute.id=oItem.id.attribute.id;
						od.attributePrice=oItem.unitPrice;
						od.attributeQty=oItem.quantity;
						order.orderDetails.push(od); 
						console.log(oItem);
					});
				}
			});
			
			return order;	
		}
		
		function getTotalAmount () {
			var total = 0;
			if($scope.products && $scope.products.length >0) {
				angular.forEach($scope.products, function (pItem, pIndex) {
					if(pItem.attributes) {
						var attributes = pItem.attributes;
						angular.forEach(attributes, function (oItem, oIndex) {
							var t = oItem.quantity * oItem.unitPrice;
							if(!isNaN(t)) {
								total +=t;
							}
							
						});
					}
				});
			}
			return total;
		}
		
		
	}
});

app.ng.application.controller('OrderCheckoutController', ['$scope', '$rootScope', '$location', 'myCartService', 'restServiceProvider', app.controllers.OrderCheckoutController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
