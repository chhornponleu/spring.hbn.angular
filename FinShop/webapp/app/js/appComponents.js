define([ 'jquery', 'app' ], function() {
	var components = {
		services : [
		     'RestServiceProvider'
		],
		controllers : [
		    'main/MainController',
		    'main/LoginController',
		    'main/DashboardController',
		    
		    'orders/OrderListController',
		    
		    'products/ProductListController',
		    'products/NewProductController',
		    
		    'categories/NewCategoryPopupController'
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