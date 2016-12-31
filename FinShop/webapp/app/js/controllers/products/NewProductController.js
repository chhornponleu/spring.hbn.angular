(function(module) {

app.controllers = $.extend(module, {
	NewProductController : function ($scope, $rootScope, $uibModal, restServiceProvider, $location, $translate) { 
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
		
		/**
		 * {
		 * 		attribute : { },
		 * 		stockAmount : 1,
		 * 		quantity : 1
		 * }
		 */
		$scope.tmpAttribute = { 
			
		};
		
		$scope.newAttributeTemplateUrl = 'newAttributeTemplate.html';
		
		$scope.events = {
			addCategory : function () {
				openAddCategoryPopup();
			},
			openNewAttributePopup : function () {
				openAddAttributePopup();
			},
			addAttribute : function () {
				if(!$scope.tmpAttribute.attribute) {
					return;
				}
				var index = $scope.data.config.attributes.indexOf($scope.tmpAttribute.attribute);
				$scope.product.attributes.push(angular.extend($scope.tmpAttribute,{}));
				$scope.data.config.attributes.splice(index, 1)[0];
				$scope.tmpAttribute = {};

				console.log($scope.product.attributes);
			},
			removeAttribute : function (index) {
				console.log('Removed attribute at index:' + index);
				var attribute = $scope.product.attributes.splice(index, 1)[0];
				console.log(attribute);
				$scope.data.config.attributes.push(attribute.attribute);
			},
			submitSave : function ($valid) {
				if($valid) {
					if(!$scope.product.imageName || !$scope.product.imageName){
						alert('Please select an image');
						return;
					}
					
					var product = angular.extend($scope.product,{});
					var attributes = [];
					angular.forEach(product.attributes, function (item, index) {
						var attr = {
							id : {
								attribute : {
									id : item.attribute.id
								}
							},
							quantity : item.quantity,
							unitPrice : item.unitPrice
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
				}
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
					console.log($scope.data.config.attributes);
				}
			});
		}
		
		function loadCategories () {
			restServiceProvider.categories.query(function (resp) {
				if(resp.header && resp.header.result == true) {
					$scope.data.config.categories = resp.body;
					console.log($scope.data.config.categories);
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
