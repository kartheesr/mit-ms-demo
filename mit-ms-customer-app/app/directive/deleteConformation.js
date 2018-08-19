angular.module('app')
.directive('deleteConformation', ['$mdDialog',
                                  function($mdDialog){
	return {
		link: function (scope, element, attr) {
			var msg = attr.deleteConformation || "Are you sure?";
			var clickAction = attr.confirmedClick;
			element.bind('click',function (event) {
				event.stopPropagation();

				$mdDialog.show({
					templateUrl: 'app/modules/modals/confirmDialog.html',
					parent: angular.element(document.body),
					clickOutsideToClose:true,
					controller: function($scope,$rootScope,$mdDialog){
						$scope.msg = msg;
						$scope.confirmButton = "Please do it!"
						
						$scope.confirm = function(){
							scope.$eval(clickAction)
							$mdDialog.cancel();
						}
						
						$scope.cancel = function(){
							$mdDialog.cancel();
						}
					}
				});
			});
		}
	};
}]);
