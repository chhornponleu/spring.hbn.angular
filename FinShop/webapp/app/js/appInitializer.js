(function(app) {
	var defineHeaders = function($httpProvider) {
		var mainLink = getLocation(window.location.href);
		var baseApiUrl = 'http://localhost:8080/restServer';
		
		// Enable CORS! (see e.g. http://enable-cors.org/)
        //$httpProvider.defaults.useXDomain = true;
        //delete $httpProvider.defaults.headers.common['X-Requested-With'];

        //Set headers
        $httpProvider.defaults.headers.common['Content-Type'] = 'application/json; charset=utf-8';
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		
	};
	
	app.ng.application.config(defineHeaders).run(function($log) {
		$log.info("Initial tasks are done!");
	});
	
	
	function getLocation(href) {
	    var l = document.createElement("a");
	    l.href = href;
	    return l;
	};
}(app || {}));