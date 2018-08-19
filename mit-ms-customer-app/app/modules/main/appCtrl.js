angular.module('msCusApp.controllers')
.controller("appCtrl", function($scope, $rootScope, $state, $rootScope, myConfig, ipCookie, $mdDialog, $localStorage) {

	$scope.stateName = $state.current.name;
	$scope.params = $state.params;

	$scope.logout = logout;
	$scope.logoutcheck = logoutcheck;

	function logout(){
		debugger
		ipCookie('AD_PFID', null);
		$state.go('auth');
	}

	if(!ipCookie('AD_PFID')){
		$state.go('auth');
	}


	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		if(!ipCookie('AD_PFID') && toState.name != 'auth'){
			$state.go('auth');
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
