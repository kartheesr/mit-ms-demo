angular.module('msCusApp.controllers')
.controller("productsCtrl", function($scope, $stateParams, $state, CONSTANT, $mdDialog, $location, $rootScope, ipCookie, myConfig, $localStorage, api, $filter, appService, $timeout, $http) {

	$scope.products = {loading:false, data:[]};
	
	$scope.getAllProducts = getAllProducts;
	$scope.addProduct = addProduct;
	$scope.buyProduct = buyProduct;

	function getAllProducts(){
		$scope.products.loading = true;
		api.Product.getAllProducts(function(result, status){
			$scope.products.loading = false;
			if(result != null && result.status == CONSTANT.STATUS.SUCCESS){ 
				$scope.products.data = result.data;
				$scope.products.status = CONSTANT.STATUS.SUCCESS;
			}else if(result != null && result.status == CONSTANT.STATUS.FAILURE){
				$scope.products.status = CONSTANT.STATUS.FAILURE;
				$scope.products.message = result.message;
			}else{
				$scope.products.status = CONSTANT.STATUS.FAILURE;
				$scope.products.message = CONSTANT.ERROR_CODE.E001;
			}
		})
	};
	
	$rootScope.$on("product_added", function(){
		getAllProducts();
	})

	function addProduct(ev){
		$mdDialog.show({
			templateUrl: 'app/modules/modals/addProduct.html',
			targetEvent: ev,
			clickOutsideToClose:true,
			controller: function($scope, $state, $mdDialog, $rootScope, appService, CONSTANT){
				
				$scope.products = {};
				$scope.productDt = {};
				$scope.loader = false;
				
				$scope.addProduct = addProduct;
				
				function addProduct(){
					$scope.loader = true;
					api.Product.addProduct($scope.productDt, function(result, status){
						$scope.loader = false;
						if(result != null && result.status == CONSTANT.STATUS.SUCCESS){ 
							$mdDialog.cancel();
							$rootScope.$broadcast('product_added');
						}else if(result != null && result.status == CONSTANT.STATUS.FAILURE){
							$scope.products.status = CONSTANT.STATUS.FAILURE;
							$scope.products.message = result.message;
							$timeout(function(){
								$scope.products ={};
							},5000);
						}else{
							$scope.products.status = CONSTANT.STATUS.FAILURE;
							$scope.products.message = CONSTANT.ERROR_CODE.E001;
							$timeout(function(){
								$scope.products ={};
							},5000);
						}
					})
				}

				$scope.cancel = function(){
					$mdDialog.cancel();
				};


			}
		});
	}
	
	
	function buyProduct(ev, product){
		$mdDialog.show({
			templateUrl: 'app/modules/modals/buyProduct.html',
			targetEvent: ev,
			clickOutsideToClose:true,
			controller: function($scope, $state, $mdDialog, $rootScope, appService, CONSTANT){
				
				$scope.products = {};
				$scope.orderDt = {customerId:ipCookie('AD_PFID'), productId:product.id,productName:product.name,unitPrice:product.price,qty:1};
				$scope.loader = false;
				
				$scope.buyProduct = buyProduct;
				
				function buyProduct(){
					$scope.loader = true;
					api.Order.buyProduct($scope.orderDt, function(result, status){
						$scope.loader = false;
						if(result != null && result.status == CONSTANT.STATUS.SUCCESS){ 
							$mdDialog.cancel();
						}else if(result != null && result.status == CONSTANT.STATUS.FAILURE){
							$scope.products.status = CONSTANT.STATUS.FAILURE;
							$scope.products.message = result.message;
							$timeout(function(){
								$scope.products ={};
							},5000);
						}else{
							$scope.products.status = CONSTANT.STATUS.FAILURE;
							$scope.products.message = CONSTANT.ERROR_CODE.E001;
							$timeout(function(){
								$scope.products ={};
							},5000);
						}
					})
				}

				$scope.cancel = function(){
					$mdDialog.cancel();
				};


			}
		});
	}
	
	getAllProducts();

});