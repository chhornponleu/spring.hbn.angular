(function(module) {

app.controllers = $.extend(module, {
	ProductListController : function ($scope, $rootScope, $uibModal, restServiceProvider, pagingConfig, myCartService) { 
		var searchTimeout = undefined;
		
		$scope.data = {
			products : []	
		};
		$scope.pagingConfig = pagingConfig;
		
		
		$scope.events = {
			pageChanged	: function () {
				reloadProductList();
			},
			viewProductDetail : function (product) {
				restServiceProvider.products.get({productId:product.id}, function (resp) {
					if(resp && resp.header && resp.header.result) {
						showProductDetail(resp.body);
					}
				});
			},
			reloadProduct : function () {
				searchTimeout && clearTimeout(searchTimeout) ;
				searchTimeout = setTimeout(function () {
					console.log($scope.data)
					reloadProductList();
				}, 800);
			},
			addToCart : function () {
				myCartService.add($scope.product);
			}
		};
		
		function reloadProductList() {
			var search = $scope.data.search;
			if(search) {
				var product = {};
				if(!isNaN(Number(search))) {
					product.id = search
				}
				product.productName = search;
				product.productDescription = search;
				$scope.pagingConfig.search = product;
			}
			else {
				$scope.pagingConfig.search = {};
			}
			restServiceProvider.products.getPaging($scope.pagingConfig, function(resp) {
				$scope.data.products = resp.body.data;
				$scope.pagingConfig.total = resp.body.total;
			});
		}
		reloadProductList();
		
		function showProductDetail(product) {
			var instance = $uibModal.open({
				size : 'lg',
				templateUrl : 'app/views/products/product.detail.popup.html',
				controller : 'ProductDetailPopupController',
				resolve: {
					product : function () {
						return product;
					}
				}
			});
			instance.result.then(function (data) {
				$scope.data.config.attributes.unshift(data);
		    }, function () {
		    	console.info('Modal dismissed at: ' + new Date());
		    });
		}
	}
});

app.ng.application.controller('ProductListController', ['$scope', '$rootScope', '$uibModal', 'restServiceProvider', 'pagingConfig', 'myCartService', app.controllers.ProductListController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
