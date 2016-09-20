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
            .when('/orders/:orderId/detail', {
            	templateUrl : 'app/views/orders/order.detail.html'
            })
            .when('/orders/cart', {
            	templateUrl : 'app/views/orders/order.cart.html'
            })
            .when('/orders/checkout/customer', {
            	templateUrl : 'app/views/orders/order.checkout.customer.html'
            })
            .when('/orders/checkout', {
            	templateUrl : 'app/views/orders/order.checkout.html'
            })
            
            
            // Products
            .when('/products', {
            	templateUrl : 'app/views/products/product.list.html'
            })
            .when('/products/addNew', {
            	templateUrl : 'app/views/products/product.addNew.html'
            })
            
            // Users
            .when('/users/profile', {
            	templateUrl : 'app/views/users/profile.html'
            })
            
            // Setting
            .when('/setting', {
            	templateUrl : 'app/views/main/setting.html'
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
