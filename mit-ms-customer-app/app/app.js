(function () {
	'use strict';
})();

var msCusApp = angular.module('app', [   'ui.router',
	'ngStorage',
	'ngAnimate',
	'ngMaterial',
	'msCusApp.controllers',
	'msCusApp.providers',
	'msCusApp.services',
	'msCusApp.directives',
	'msCusApp.filters',
	'msCusApp.constant',
	'mdPickers',
	'ipCookie',
	'ui.bootstrap',
	'angularUtils.directives.dirPagination'
	]);

angular.module('msCusApp.providers', ['ngMaterial']);

angular.module('msCusApp.controllers', ['ngMaterial']);

angular.module('msCusApp.factories', ['ngMaterial']);

angular.module('msCusApp.directives', ['ngMaterial']);

angular.module('msCusApp.services', ['ngMaterial']);

angular.module('msCusApp.filters', []);

angular.module('msCusApp.constant', []);
