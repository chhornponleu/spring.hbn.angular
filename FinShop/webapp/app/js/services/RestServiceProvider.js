(function(module) {

	app.services = $.extend(module, {
		RestServiceProvider : function () { 
			
			this.$get = ['$resource', '$rootScope', function (resource, $rootScope) {

				var baseUrl = '/FinShop/apis'
				var defineResource = function (url, paramDefaults, actions) {
	                return resource(baseUrl + url, paramDefaults, actions);
	            };
	            return {
	                products: defineResource('/products/:productId', {productId: '@productId'}, {
	                    getPaging: {method: 'POST', url : baseUrl+'/products/paging'}
	                }),
	                attribuites : defineResource('/attributes/:attributeId', {productId: '@attributeId'}, {
	                }),
                	categories : defineResource('/attributes/:categoryId', {productId: '@categoryId'}, {
	                }),
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
