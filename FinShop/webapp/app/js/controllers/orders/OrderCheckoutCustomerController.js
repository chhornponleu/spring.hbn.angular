(function(module) {

app.controllers = $.extend(module, {
	OrderCheckoutCustomerController : function ($scope, $rootScope, $location, restServiceProvider) { 
		var tmpCustomerStorageName = '_tmpCustomer';
		$scope.data = {
			config : {
				customers : [],
				customersMaxLength : 10
			}
		};
		$scope.customer = {
			customerName : "",
			contact : "",
			address : "",
			isReturned : false
		};
		
		$scope.events = {
			nextStep : function (valid) {
				if(valid) {
					saveAndNext();
				}
			},
			searchCustomer : function (search) {
				if(search) {
					restServiceProvider.customers.query({search:search, limit : $scope.data.config.customersMaxLength}, function (resp) {
						console.log(resp);
						if(resp && resp.header && resp.header.result) {
							$scope.data.config.customers = resp.body;
						}
					});
				}
			},
			returnedCustChanged : function () {
				if($scope.customer.isReturned == false) {
					$scope.events.clearForm(false);
				}
				
			},
			returnCustomerSelected : function (customer, customerId) {
				$scope.customer = angular.extend(customer, {
					isReturned : true
				});
			},
			clearForm : function (includeCache) {
				if(includeCache) {
					localStorage.removeItem(tmpCustomerStorageName);
				}
				$scope.customer = {
					customerName : "",
					contact : "",
					address : "",
					isReturned : false
				};
			}
		};
		
		function init() {
			var tmpCustomer = localStorage.getItem(tmpCustomerStorageName)
			if(tmpCustomer) {
				$scope.customer = JSON.parse(tmpCustomer);
				$scope.data.config.customers.push($scope.customer);
			}
		}
		
		function saveAndNext() {
			localStorage.setItem(tmpCustomerStorageName, JSON.stringify($scope.customer));
			$location.path('/orders/checkout');
		}
		init();
	}
});

app.ng.application.controller('OrderCheckoutCustomerController', ['$scope', '$rootScope', '$location', 'restServiceProvider', app.controllers.OrderCheckoutCustomerController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
