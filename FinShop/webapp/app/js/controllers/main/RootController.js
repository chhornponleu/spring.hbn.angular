(function(module) {

app.controllers = $.extend(module, {
	RootController : function (scope, rootScope, myCartService) { 
		var menuCollapsedStateName = 'menu_collapsed';
		
		rootScope.cart = {
			count : myCartService.count()
		}
		
		rootScope.events = {
			clearCart : function () {
				if(confirm("Are you sure to clear cart?")) {
					myCartService.removeAll();
					rootScope.cart.count = myCartService.count();
					return true;
				}
				else {
					return false;
				}
			}
		}
		
		rootScope.collapsed = getMenuCollapsedState();
		rootScope.toggleSidebarCollapsed = function () {
			rootScope.collapsed = !rootScope.collapsed;
			setMenuCollapsedState(rootScope.collapsed);
		}
		
		function getMenuCollapsedState() {
			return localStorage.getItem(menuCollapsedStateName) == 'true';
		}
		function setMenuCollapsedState(state) {
			localStorage.setItem(menuCollapsedStateName, state);
		}
	}
});

app.ng.application.controller('RootController', ['$scope', '$rootScope', 'myCartService', app.controllers.RootController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
