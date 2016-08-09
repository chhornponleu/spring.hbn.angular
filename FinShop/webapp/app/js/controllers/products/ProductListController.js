(function(module) {

app.controllers = $.extend(module, {
	ProductListController : function ($scope, $rootScope, restServiceProvider, pagingConfig) { 
		alert(1);
		$scope.data = {
			products : []	
		};
		$scope.pagingConfig = pagingConfig;
		
		$scope.events = {
			pageChanged	: function () {
				reloadProductList();
			}
		};
		
		function reloadProductList() {
			restServiceProvider.products.getPaging($scope.pagingConfig, function(resp) {
				console.log(resp.body);
				$scope.data.products = resp.body.data;
				$scope.pagingConfig.total = resp.body.total;
			});
		}
		reloadProductList();
	}
});

app.ng.application.controller('ProductListController', ['$scope', '$rootScope', 'restServiceProvider', 'pagingConfig', app.controllers.ProductListController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
