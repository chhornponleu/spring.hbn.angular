(function(module) {

app.controllers = $.extend(module, {
	NewAttributePopupController : function ($scope, $rootScope, $uibModalInstance, restServiceProvider) { 
		
		$scope.data = {};
		
		$scope.events = {
			submit : function (valid) {
				if(valid) {
					restServiceProvider.attributes.hasName($scope.data, function (resp) {
						if(resp && resp.header && resp.header.result == false) {
							restServiceProvider.attributes.save($scope.data, function (resp) {
								$scope.frmNewAttribute.$setPristine();
								closePopup(resp.body);
							}, function (err) {
								console.log(err)
							});
						}
						else {
							alert('Attribute name has already been taken.');
						}
					});
				}
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

app.ng.application.controller('NewAttributePopupController', ['$scope', '$rootScope', '$uibModalInstance', 'restServiceProvider', app.controllers.NewAttributePopupController]).run(function () {
	console.info('New Attribute Popup Controller has been initialized');
});

}(app.controllers || {}));
