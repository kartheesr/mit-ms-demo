angular.module('msCusApp.controllers')
.controller("appCtrl", function($scope, $rootScope, $state, $rootScope, User, myConfig, ipCookie, $mdDialog, $localStorage) {
	$rootScope.showLoader = false;
	$scope.currentState = $state.current.data.currentState;
	$scope.currentModule = $state.current.data.currentModule;

	$scope.changeState = changeState;
	$scope.changeModule = changeModule;
	$scope.stateName = $state.current.name;
	$scope.userDetails = User.UserModel;
	$scope.backendUrl = myConfig.backend;
	$scope.params = $state.params;
	$scope.isWhiteLabel = myConfig.isWhiteLabel;
	
	$scope.gotoState = gotoState;
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

	function changeState(state){
		$scope.currentState = state;
		if(state=="placeDetails"){
			$state.go("app.settings.placeDetails",$scope.params);
		}else if(state=="spaceType"){
			$state.go("app.settings.spaceType",$scope.params);
		}else if(state=="availabilityAndPrice"){
			$state.go("app.settings.availabilityAndPrice",$scope.params);
		}else if(state=="additionalOption"){
			$state.go("app.settings.additionalOption",$scope.params);
		}else if(state=="placeExperience"){
			$state.go("app.settings.addExperience",$scope.params);
		}else if(state=="promocode"){
			$state.go("app.settings.promocode",$scope.params);
		}else if(state=="referrer"){
			$state.go("app.settings.placeReferrer",$scope.params);
		}
	}

	function changeModule(module){
		 
		$scope.currentModule = module;
		if(module == 'restaurant'){
			$scope.currentState = 'newRestaurant';	
		}else if(module == 'places'){
			$localStorage.businessId = undefined;
		}
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
	
	
	
	

	function gotoState(state){
		if(state == 'basic'){
			$state.go("app.settings.restaurant",$scope.params);
		}else if(state == 'advanced'){
			$state.go("app.settings.advancedSettings",$scope.params);
		}else if(state == 'general'){
			$state.go("app.settings.generalSettings",$scope.params);
		}else if(state == 'foodItem'){
			$state.go("app.settings.foodItemSettings",$scope.params);
		}else if(state == 'table'){
			$state.go("app.settings.tableSettings",$scope.params);
		}else if(state == 'experience'){
			$state.go("app.settings.addRestaurantExperience",{'restaurantId': $scope.params.restaurantId, 'mode':'update','expMode':'add'});
		}else if(state == 'promo'){
			$state.go("app.settings.restpromocode",$scope.params);
		}else if(state == 'waitlist'){
			$state.go("app.settings.waitList",$scope.params);
		}else if(state=="referrer"){
			$state.go("app.settings.referrer",$scope.params);
		}
	}


});
