(function(module) {

app.controllers = $.extend(module, {
	OrderDetailController : function ($scope, $rootScope, $routeParams, restServiceProvider) { 
		var orderId = $routeParams.orderId;
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

app.ng.application.controller('OrderDetailController', ['$scope', '$rootScope', '$routeParams', 'restServiceProvider', app.controllers.OrderDetailController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
