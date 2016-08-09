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
            
            // Orders
            .when('/orders', {
            	templateUrl : 'app/views/orders/order.list.html'
            })
            
            
            // Products
            .when('/products', {
            	templateUrl : 'app/views/products/product.list.html'
            })
            .when('/products/addNew', {
            	templateUrl : 'app/views/products/product.addNew.html'
            })
            
            .when('/carts', {
            	templateUrl : 'app/views/carts.html'
            })
            
            .when('/checkout', {
            	templateUrl : 'app/views/checkout.html'
            })
            
            // Others
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
