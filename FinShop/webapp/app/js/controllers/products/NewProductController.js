(function(module) {

app.controllers = $.extend(module, {
	NewProductController : function ($scope, $rootScope, $uibModal, restServiceProvider, $location) { 
		$scope.data = {
			config : {
				attributes : [],
				categories : []
			}
		};
		$scope.product = {
			attributes : [],
			category : {}
		};
		
		$scope.events = {
			addCategory : function () {
				openAddCategoryPopup();
			},
			addAttribute : function () {
				openAddAttributePopup();
			},
			submitSave : function ($valid) {
				var product = angular.extend($scope.product,{});
				var attributes = [];
				angular.forEach(product.attributes, function (item, index) {
					var attr = {
						id : {
							attribute : {
								id : item.id
							}
						}
					};
					attributes.push(attr);
				});
				product.attributes = attributes;
				restServiceProvider.products.save(product, function (resp) {
					if(resp && resp.header && resp.header.result == true) {
						$scope.events.routeToProduct();
					}
					else {
						alert("Cannot save product...");
					}
				});
				//console.log($scope.product, $scope.frmNewProduct);
			},
			routeToProduct : function () {
				$location.path('/products');
			}
		};
		
		function openAddCategoryPopup() {
			var instance = $uibModal.open({
				backdrop : false,
				templateUrl : 'app/views/categories/category.new.popup.html',
				controller : 'NewCategoryPopupController',
				resolve: {
				}
			});
			instance.result.then(function (data) {
				$scope.data.config.categories.unshift(data);
		    }, function () {
		    	console.info('Modal dismissed at: ' + new Date());
		    });
		}
		
		function openAddAttributePopup() {
			var instance = $uibModal.open({
				backdrop : false,
				templateUrl : 'app/views/attributes/attribute.new.popup.html',
				controller : 'NewAttributePopupController',
				resolve: {
				}
			});
			instance.result.then(function (data) {
				$scope.data.config.attributes.unshift(data);
		    }, function () {
		    	console.info('Modal dismissed at: ' + new Date());
		    });
		}
		
		function loadAttributes () {
			restServiceProvider.attributes.query(function (resp) {
				if(resp.header && resp.header.result == true) {
					$scope.data.config.attributes = resp.body;
				}
			});
		}
		
		function loadCategories () {
			restServiceProvider.categories.query(function (resp) {
				if(resp.header && resp.header.result == true) {
					$scope.data.config.categories = resp.body;
				}
			});
		}
		
		(function init() {
			loadAttributes();
			loadCategories();
		})();
	}
});

app.ng.application.controller('NewProductController', ['$scope', '$rootScope', '$uibModal', 'restServiceProvider', '$location', app.controllers.NewProductController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
