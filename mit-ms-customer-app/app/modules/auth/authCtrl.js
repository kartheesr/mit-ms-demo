angular.module('msCusApp.controllers')
.controller("authCtrl", function($scope, api, $rootScope, $state, CONSTANT, $timeout, User, ipCookie) {

	$scope.auth = {};
	$scope.response = {}
	
	$scope.login = login;
	
	function login(){
		api.Auth.login($scope.auth, function(result, status){
			if(result != null && result.status == CONSTANT.STATUS.SUCCESS){
				ipCookie('AD_PFID', result.data.id);
				User.setUserInfo(result.data);
				$state.go("app.products");
			}else if(result != null && result.status == CONSTANT.STATUS.FAILURE){
				$scope.response.status = CONSTANT.STATUS.FAILURE;
				$scope.response.message = result.message;
				$timeout(function(){
					$scope.response ={};
				},3000)
			}
		})
	}
	
});