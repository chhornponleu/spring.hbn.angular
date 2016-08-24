(function(module) {

	app.services = $.extend(module, {
		RestServiceProvider : function () { 
			
			this.$get = ['$resource', '$rootScope', function (resource, $rootScope) {

				var baseUrl = '/FinShop/apis'
				var defineResource = function (url, paramDefaults, actions) {
	                return resource(baseUrl + url, paramDefaults, 
                		angular.extend({ query : { method : 'GET', isArray:false}}, actions) 
	                );
	            };
	            return {
	                products: defineResource('/products/:productId', {productId: '@id'}, {
	                    getPaging: {method: 'POST', url : baseUrl+'/products/paging'}
	                }),
	                attributes : defineResource('/attributes/:attributeId', {attributeId: '@id'}, {
	                	hasName : {
                			method : 'GET', 
                			url : baseUrl+'/attributes/hasName/:attributeName', 
                			params : {attributeName : '@attributeName'}
            			}
	                }),
                	categories : defineResource('/categories/:categoryId', {categoryId: '@id'}, {
                		hasName : {
                			method : 'GET', 
                			url : baseUrl+'/categories/hasName/:categoryName', 
                			params : {categoryName : '@categoryName'}
            			}
	                }),
	                customers : defineResource('/customers/:customerId', {categoryId: '@id'}, {
                		
	                }),
                	orders : defineResource('/orders/:orderId', {orderId: '@id'}, {
                		
	                })
	            }
			}];
			
			
		}
	});
	
	app.ng.services.config(function ($provide) {
		$provide.provider('restServiceProvider', app.services.RestServiceProvider);
	}).run(function ($log) {
		$log.info('Welcome controller has been initialized');
	});

}(app.services || {}));
