define([ 'jquery', 'app' ], function() {
	var components = {
		services : [

		],
		controllers : [
		    'main/MainController',
		    'main/LoginController',
		    'main/DashboardController'
	    ],
	    filters : [],
	    directives : []
	};
	
	return function () {
		var def = $.Deferred();
		
		var deps = [
		    'routes',
		    'appInitializer'
		];
		
		$.each(components, function (pkg, items) {
			
			$.each(items, function (index, path) {
				deps.push(pkg + '/' + path);
			})
			
		});
		
		require(deps, function () {
			def.resolve();
		});
		
		return def.promise();
	};
});