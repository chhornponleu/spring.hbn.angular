(function() {
	require.config({
		map : {

		},
		paths : {
			'angular' : '../bower_components/angular/angular',
			'angular-resource' : '../bower_components/angular-resource/angular-resource',
			'angular-route' : '../bower_components/angular-route/angular-route',
			'angular-bootstrap' : '../bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
			'angular-animate' : '../bower_components/angular-animate/angular-animate.min',
			'jquery' : '../bower_components/jquery/dist/jquery.min'
		},
		shim : {
			'angular' : { exports : 'angular' },
			'angular-resource' : { deps : [ 'angular' ] },
			'angular-route' : { deps : [ 'angular' ] },
			'angular-bootstrap' : { deps : ['angular'] },
			'angular-animate' : { deps : ['angular']},
			'app' : {
				deps : [ 'angular', 'jquery', 'angular-resource', 'angular-route', 'angular-bootstrap', 'angular-animate' ],
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