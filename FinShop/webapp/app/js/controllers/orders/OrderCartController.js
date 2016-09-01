(function(module) {

app.controllers = $.extend(module, {
	OrderCartController : function ($scope, $rootScope, $location, myCartService) { 
		$scope.products = myCartService.getAll();
		
		$scope.events = {
			removeProduct : function (index) {
				var result = myCartService.remove($scope.products[index].id);
				if(result > 0) {
					$scope.products.splice(index, 1);
					noProductInCart();
				}
			},
			clearCart : function () {
				var result = $scope.$parent.events.clearCart();
				if(result) {
					$location.path('/products');
				}
			}
		};
		
		function noProductInCart() {
			if($scope.products.length == 0) {
				$location.path('/products');
			}
		}
		noProductInCart();
	}
});

app.ng.application.controller('OrderCartController', ['$scope', '$rootScope', '$location', 'myCartService', app.controllers.OrderCartController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
