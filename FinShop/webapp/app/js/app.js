var app = (function (modules) {
	modules.ng = {
	    config: angular.module('config', []),
	    services: angular.module('services', ['ngResource']),
	    components : angular.module('components', []),
		application : angular.module('app', ['services', 'components', 'config', 
		                                     'ngRoute', 'ngSanitize', 'ui.bootstrap', 
		                                     'ui.select', 'pascalprecht.translate', 'angularGrid'])
	};
	
	modules.ng.config.value('pagingConfig', {
		page : 1,
		pageSize : 9,
		search : {}
	});
	
	return modules;
}(app || {}));