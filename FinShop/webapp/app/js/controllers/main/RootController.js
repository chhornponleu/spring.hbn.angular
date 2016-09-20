(function(module) {

app.controllers = $.extend(module, {
	RootController : function (scope, rootScope, translate, myCartService) { 
		var menuCollapsedStateName = 'menu_collapsed';
		var languageName = 'language';
		
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
			},
			setLanguage : function (lang) {
				setLanguage(lang);
			}
		}
		
		rootScope.collapsed = getMenuCollapsedState();
		rootScope.toggleSidebarCollapsed = function () {
			rootScope.collapsed = !rootScope.collapsed;
			setMenuCollapsedState(rootScope.collapsed);
		}
		
		rootScope.getLanguage = getLanguage;
		setLanguage(rootScope.getLanguage());
		
		
		function getMenuCollapsedState() {
			return localStorage.getItem(menuCollapsedStateName) == 'true';
		}
		function setMenuCollapsedState(state) {
			localStorage.setItem(menuCollapsedStateName, state);
		}
		
		function setLanguage(lang) {
			translate.use(lang);
			rootScope.currentLanguage = lang;
			localStorage.setItem(languageName, lang);
		}
		function getLanguage() {
			return localStorage.getItem(languageName);
		}
	}
});

app.ng.application.controller('RootController', ['$scope', '$rootScope', '$translate', 'myCartService', app.controllers.RootController]).run(function () {
	console.info('Welcome controller has been initialized');
});

}(app.controllers || {}));
