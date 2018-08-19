angular.module('app')
.config(function ($stateProvider, $urlRouterProvider, $httpProvider, apiProvider, myConfig){

	$urlRouterProvider.otherwise('/auth');
	$stateProvider

	.state('auth', {
		url: '/auth',
		templateUrl: 'app/modules/auth/auth.html',
		controller: 'authCtrl'
	})

	.state('app', {
		abstract: true,
		templateUrl: 'app/modules/main/app.html',
		controller: 'appCtrl'
	})

	.state('app.products', {
		url: '/products',
		templateUrl: 'app/modules/products/productList.html',
		controller:'productsCtrl'
	});

	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.headers.common = 'Content-Type: application/json';
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
	apiProvider.setApiUrl(myConfig.backend);
});






