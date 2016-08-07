(function() {
	require.config({
		map : {

		},
		paths : {
			'angular' : '../bower_components/angular/angular.min',
			'angular-resource' : '../bower_components/angular-resource/angular-resource.min',
			'angular-route' : '../bower_components/angular-route/angular-route.min',
			'jquery' : '../bower_components/jquery/dist/jquery.min',
		},
		shim : {
			'angular' : {
				exports : 'angular'
			},
			'angular-resource' : {
				deps : [ 'angular' ]
			},
			'angular-route' : {
				deps : [ 'angular' ]
			},
			'app' : {
				deps : [ 'angular', 'jquery', 'angular-resource', 'angular-route' ],
				exports : 'app'
			}
		}
	});
	require([ 'appComponents' ], function(appComponents) {
		appComponents().then(function() {
			console.info('Components has been loaded...');
			angular.bootstrap(document, [ 'app' ]);
		});
	});
}());