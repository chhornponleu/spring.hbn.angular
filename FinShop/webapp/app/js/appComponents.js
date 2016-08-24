define([ 'jquery', 'app' ], function() {
	var components = {
		services : [
		     'RestServiceProvider',
		     'MyCartService'
		],
		controllers : [
		    'main/RootController',
		    'main/LoginController',
		    'main/DashboardController',
		    
		    'orders/OrderListController',
		    'orders/OrderCartController',
		    'orders/OrderCheckoutController',
		    'orders/OrderCheckoutCustomerController',
		    'orders/OrderDetailController',
		    
		    'products/ProductListController',
		    'products/NewProductController',
		    'products/ProductDetailPopupController',
		    
		    'categories/NewCategoryPopupController',
		    'attributes/NewAttributePopupController'
	    ],
	    filters : [],
	    directives : [
	        'inputImgPreview'
	    ]
	};
	
	return function () {
		var def = $.Deferred();
		
		var deps = ['routes','appInitializer'];
		
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