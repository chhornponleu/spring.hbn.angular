(function(module) {

	app.services = $.extend(module, {
		MyCartServiceProvider : function () { 
			
			this.$get = ['$resource', '$rootScope', function (resource, $rootScope) {
				var storageName = "_cart";
				function save(products) {
					localStorage.setItem(storageName, encodeURIComponent(JSON.stringify(products)));
					$(document).trigger('cart.updated');
				}
				
				function updateCartCount() {
					if($rootScope.cart) {
						$rootScope.cart.count = cart.count();
					}
				}
				
				var cart = {
					getAll : function () {
						var products = localStorage.getItem(storageName);
						if(!products) {
							products = "[]";
						}
						products = decodeURIComponent(products);
						return JSON.parse(products);
					},
					removeAll : function () {
						save([]);
						updateCartCount();
					},
					remove : function (productId) {
						var products = cart.getAll();
						var affected = 0;
						$.each(products, function (index, product) {
							if(productId == product.id) {
								products.splice(index, 1);
								affected++;
								return false;
							}
						});
						if(affected > 0) {
							save(products);
							updateCartCount();
						}
						return affected;
					},
					add : function (product) {
						var added = 0;
						if(!cart.exists(product.id)) {
							var products = cart.getAll();
							products.push(product);
							save(products);
							updateCartCount();
							added++;
						}
						return added;
					},
					get : function (id) {
						var products = cart.getAll(),
						product = undefined;
						$.each(products, function (index, prod) {
							if(prod.id == id) {
								product = prod;
								return false;
							}
						});
						return product;
					},
					exists : function (id) {
						return cart.get(id) != undefined;
					},
					count : function () {
						return cart.getAll().length;
					}
				};
				window.cart = cart;
				return cart;
			}];
			
			
		}
	});
	
	app.ng.services.config(function ($provide) {
		$provide.provider('myCartService', app.services.MyCartServiceProvider);
	}).run(function ($log) {
		$log.info('Welcome controller has been initialized');
	});

}(app.services || {}));
