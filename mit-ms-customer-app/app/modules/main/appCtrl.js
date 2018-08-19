angular.module('msCusApp.controllers')
.controller("appCtrl", function($scope, $rootScope, $state, $rootScope, User, myConfig, ipCookie, $mdDialog, $localStorage) {

	$scope.stateName = $state.current.name;
	$scope.params = $state.params;
	
	$scope.logoutcheck = logoutcheck;

	if(!ipCookie('AD_PFID')){
		$state.go('auth');
	}


	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		if(!ipCookie('AD_PFID') && toState.name != 'auth'){
			$state.go('auth');
		}
		if(toState.data){
			$scope.currentState = toState.data.currentState;
			$scope.currentModule = toState.data.currentModule;
			$scope.rootState = toState.data.rootState;
		}
		$scope.stateName = toState.name;
		$scope.params = $state.params;
	});
	
	
	function logoutcheck(){
		$mdDialog.show({
			templateUrl: 'app/modules/modals/logoutConfirm.html',
			clickOutsideToClose:true,
			 
			controller: function($scope, api, $state, $mdDialog, $rootScope){
				$scope.logout = logout;
				
				$scope.logout = function(){
					logout();
					$mdDialog.cancel();
				};
				$scope.cancel = function(){
					$mdDialog.cancel();
				};
			}
		});
	}


});
