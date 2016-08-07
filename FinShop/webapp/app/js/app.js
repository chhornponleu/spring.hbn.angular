var app = (function (modules) {
	modules.ng = {
	    config: angular.module('config', []),
	    services: angular.module('services', ['ngResource']),
		application : angular.module('app', ['ngRoute', 'services', 'config'])
	};
	return modules;
}(app || {}));