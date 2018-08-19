angular.module('msCusApp.controllers')
.controller("authCtrl", function($scope, api, $rootScope, $state, CONSTANT, $timeout, ipCookie) {

	$scope.auth = {};
	$scope.regDt = {};
	$scope.response = {}
	
	$scope.login = login;
	$scope.register = register;
	
	function login(){
		api.Auth.login($scope.auth, function(result, status){
			if(result != null && result.status == CONSTANT.STATUS.SUCCESS){
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
	
	function register(){
		api.Auth.register($scope.regDt, function(result, status){
			if(result != null && result.status == CONSTANT.STATUS.SUCCESS){
				$scope.regDt = {};
				$scope.response.status = CONSTANT.STATUS.SUCCESS;
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