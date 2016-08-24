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
			'angular-sanitize' : '../bower_components/angular-sanitize/angular-sanitize.min',
			'angular-ui-select' : '../bower_components/angular-ui-select/dist/select.min',
			//'angular-select2' : '../bower_components/angular-ui-select2/src/select2',
			
			'jquery' : '../bower_components/jquery/dist/jquery.min',
			//'select2' : '../bower_components/select2/dist/js/select2'
		},
		shim : {
			'angular' : { exports : 'angular' },
			'angular-resource' : { deps : [ 'angular' ] },
			'angular-route' : { deps : [ 'angular' ] },
			'angular-bootstrap' : { deps : ['angular'] },
			'angular-animate' : { deps : ['angular']},
			'angular-sanitize' : { deps : ['angular']},
			'angular-ui-select' : { deps : ['angular']},
			//'angular-select2' : { deps : ['angular', 'jquery', 'select2']},
			//'select2' : { deps : ['jquery']},
			'app' : {
				deps : [ 'angular', 'jquery', 'angular-resource', 'angular-route', 
				         'angular-bootstrap', 'angular-animate', 'angular-sanitize', 'angular-ui-select'],
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