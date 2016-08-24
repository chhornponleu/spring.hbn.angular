(function(module) {

app.controllers = $.extend(module, {
	NewCategoryPopupController : function ($scope, $rootScope, $uibModalInstance, restServiceProvider) { 
		
		$scope.data = {};
		
		$scope.events = {
			submit : function (valid) {
				restServiceProvider.categories.hasName($scope.data, function (resp) {
					if(resp && resp.header && resp.header.result == false) {
						restServiceProvider.categories.save($scope.data, function (resp) {
							$scope.frmNewCategory.$setPristine();
							closePopup(resp.body);
						}, function (err) {
							console.log(err)
						});
					}
					else {
						alert('Category name has already been taken.');
					}
				});
				
			},
			cancel : function () {
				cancel();
			}
		};
		
		
		function closePopup(data) {
			$uibModalInstance.close(data);
		}
		
		function cancel () {
			$uibModalInstance.dismiss('cancel');
		}
	}
});

app.ng.application.controller('NewCategoryPopupController', ['$scope', '$rootScope', '$uibModalInstance', 'restServiceProvider', app.controllers.NewCategoryPopupController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
