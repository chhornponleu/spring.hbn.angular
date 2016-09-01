(function(module) {

	app.directives = $.extend(module, {
		inputImgPreview : function ($rootScope, $parse) { 
			return {
				restrict : 'A',
				link : function (scope, el, attr, model) {
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
						
						// model for image name
						if(attr.ngModel) {
							$parse(attr.ngModel).assign(scope, el.val())
						}
						
						// model data for image data base 64
						if(attr.ngModelData) {
							$parse(attr.ngModelData).assign(scope, e.target.result);
						}
						
					}
					function onError() {
						
					}
				}
			}
		}
	});
	
	app.ng.application.directive('inputImgPreview', ['$rootScope', '$parse', app.directives.inputImgPreview]).run(function ($log) {
		$log.info('input-img-preview has been initialized');
	});

}(app.directives || {}));
