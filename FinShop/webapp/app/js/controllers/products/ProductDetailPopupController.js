(function(module) {

app.controllers = $.extend(module, {
	ProductDetailPopupController : function ($scope, $rootScope, $uibModalInstance, restServiceProvider, myCartService, product) { 
		$scope.product = product;
		
		$scope.isInCart = myCartService.exists(product.id);
		
		$scope.events = {
			addToCart : function () {
				var added = myCartService.add($scope.product);
				if(added > 0) {
					$scope.isInCart = true;
				}
			},
			removeFromCart : function () {
				var removed  = myCartService.remove($scope.product.id);
				if(removed > 0 ) {
					$scope.isInCart = false;
				}
			},
			dismiss : function () {
				$uibModalInstance.dismiss('cancel');
			},
			close : function () {
				$uibModalInstance.close(data);
			}
		};
	}
});

app.ng.application.controller('ProductDetailPopupController', ['$scope', '$rootScope', '$uibModalInstance', 'restServiceProvider', 'myCartService', 'product', app.controllers.ProductDetailPopupController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
