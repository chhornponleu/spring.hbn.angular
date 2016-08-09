(function(module) {

app.controllers = $.extend(module, {
	NewCategoryPopupController : function ($scope, $rootScope, $uibModalInstance, restServiceProvider) { 
		
		$scope.data = {};
		
		$scope.events = {
			ok : function () {
				
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
