(function(module) {

app.controllers = $.extend(module, {
	SettingController : function ($scope, $rootScope, $translate) {
		
		
	}
});

app.ng.application.controller('SettingController', ['$scope', '$rootScope', '$translate', app.controllers.SettingController]).run(function () {
	console.info('DashboardController controller has been initialized');
});

}(app.controllers || {}));
