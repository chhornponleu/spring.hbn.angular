(function(module) {

	app.directives = $.extend(module, {
		inputImgPreview : function ($rootScope) { 
			return {
				restrict : 'A',
				link : function (scope, el, attr) {
					//console.log(arguments);
					el.bind('change', function (e) {
						var reader = new FileReader();
			            reader.onload = onLoad;
			            reader.onerror = onError;
			            reader.readAsDataURL((e.srcElement || e.target).files[0]);
					});
					function onLoad(e) {
						var imgId = attr['inputImgPreview'];
						if(!imgId) {
							return;
						}
						var img = document.getElementById(imgId);
						img.src = e.target.result;
					}
					function onError() {
						
					}
				}
			}
		}
	});
	
	app.ng.application.directive('inputImgPreview', ['$rootScope', app.directives.inputImgPreview]).run(function ($log) {
		$log.info('input-img-preview has been initialized');
	});

}(app.directives || {}));
