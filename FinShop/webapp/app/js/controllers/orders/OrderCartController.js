(function(module) {

app.controllers = $.extend(module, {
	OrderCartController : function ($scope, $rootScope, myCartService) { 
		$scope.products = myCartService.getAll();
		
		$scope.events = {
			removeProduct : function (index) {
				var result = myCartService.remove($scope.products[index].id);
				if(result > 0) {
					$scope.products.splice(index, 1);
				}
			}
		};
	}
});

app.ng.application.controller('OrderCartController', ['$scope', '$rootScope', 'myCartService', app.controllers.OrderCartController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
