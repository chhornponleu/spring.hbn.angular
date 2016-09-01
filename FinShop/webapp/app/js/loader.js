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
			'jquery' : '../bower_components/jquery/dist/jquery.min',
			'angular-translate' : '../bower_components/angular-translate/angular-translate.min',
			'angular-translate-file' : '../bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.min',
		},
		shim : {
			'angular' : { exports : 'angular' },
			'angular-resource' : { deps : [ 'angular' ] },
			'angular-route' : { deps : [ 'angular' ] },
			'angular-bootstrap' : { deps : ['angular'] },
			'angular-animate' : { deps : ['angular']},
			'angular-sanitize' : { deps : ['angular']},
			'angular-ui-select' : { deps : ['angular']},
			'angular-translate' : { deps : ['angular']},
			'angular-translate-file' : { deps : ['angular', 'angular-translate']},
			'app' : {
				deps : [ 'angular', 'jquery', 'angular-resource', 'angular-route', 
				         'angular-bootstrap', 'angular-animate', 'angular-sanitize', 'angular-ui-select',
				         'angular-translate', 'angular-translate-file'],
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