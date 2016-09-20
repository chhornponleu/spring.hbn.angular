(function(module) {

app.controllers = $.extend(module, {
	UserProfileController : function ($scope, $rootScope, $uibModal, restServiceProvider, $location) { 
		$scope.user = {};
		
		$scope.events = {
			submitSave : function (valid) {
				if(valid) {
					restServiceProvider.users.save($scope.user, function (resp) {
						if(resp && resp.header && resp.header.result) {
							alert("User has been saved.")
						}
						else {
							alert("User could not be saved.")
						}
					});
				}
			}
		}
		
		restServiceProvider.users.getProfile(function (resp) {
			if(resp && resp.header && resp.header.result) {
				$scope.user = resp.body;
			}
		});
	
	}
});

app.ng.application.controller('UserProfileController', ['$scope', '$rootScope', '$uibModal', 'restServiceProvider', '$location', app.controllers.UserProfileController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
