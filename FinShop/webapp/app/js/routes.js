(function (app) {
    var defineRoutes = function ($routeProvider, $locationProvider) {
    	$locationProvider.hashPrefix('!');
    	$locationProvider.html5Mode(false);
    	
        $routeProvider
            .when('/', {
                templateUrl: 'app/views/dashboard.html'
            })
            .when('/login', {
                templateUrl: 'app/views/login.html',
                controller : function ($scope) {
                	console.log('login....');
                }
            })
            .when('/404', {
            	templateUrl : 'app/views/404.html'
            })
            .otherwise('/404');
            ;
          
    };
    app.ng.application.config(defineRoutes).run(function ($log) {
        $log.info("Routes definition completed");
    });
}(app || {}));
