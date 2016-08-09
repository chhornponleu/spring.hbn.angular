(function(module) {

app.controllers = $.extend(module, {
	NewProductController : function ($scope, $rootScope, $uibModal, restServiceProvider) { 
		var data = {
			config : {
				attributes : [],
				categories : []
			}	
		};
		
		$scope.events = {
			addCategory : function () {
				openAddCategoryPopup();
			},
			addAttribute : function () {
				console.log('addAttribute');
			}
		};
		
		function openAddCategoryPopup() {
			var instance = $uibModal.open({
				backdrop : false,
				templateUrl : 'app/views/categories/category.new.popup.html',
				//controller : 'NewProductController',
				resolve: {
				}
			});
			instance.result.then(function (selectedItem) {
				$scope.selected = selectedItem;
		    }, function () {
		    	$log.info('Modal dismissed at: ' + new Date());
		    });
		}
	}
});

app.ng.application.controller('NewProductController', ['$scope', '$rootScope', '$uibModal', 'restServiceProvider', 'pagingConfig', app.controllers.NewProductController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
