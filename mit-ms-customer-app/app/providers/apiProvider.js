angular.module('msCusApp.providers').provider('api', function ApiProvider() {

	var _apiUrl = null;
	this.setApiUrl = function (url) {
		_apiUrl = url;
	};

	var _apiHeaders = {};
	this.setApiHeaders = function (headers) {
		_apiHeaders = headers;
	};

	var setApiHeadersss = function (headers) {
		_apiHeaders = headers;
	};

	function getCookie(name)
	{
		var re = new RegExp(name + "=([^;]+)");
		var value = re.exec(document.cookie);
		return (value != null) ? unescape(value[1]) : null;
	}


	var http = null;
	var httpRequest = function (method, path, data, callback) {
		if (http === null) callback({
			error: true,
			errorCode: "HTTP_NULL"
		}, null);

		_apiHeaders['Content-Type'] = 'application/json';
		_apiHeaders['ADPFID'] = getCookie('AD_PFID');
		http({
			method: method,
			url: _apiUrl + path,
			headers: _apiHeaders,
			data: data
		})
		.success(function (data, status, headers, config) {
			if (data.error) {
				callback(data, null);
			} else {
				callback(null, data);
			}
		})
		.error(function (data, status, headers, config) {
			callback({
				error: true,
				errorCode: "CONNECTION_ERROR"
			}, null);
		});
	};

	this.$get = ['$http', function ($http, ipCookie) {
		http = $http;

		var apiClass = {};

		apiClass.httpRequest = function (_method, _relative_url, data, callback) {
			httpRequest(_method, _relative_url, data, function (err, data) {
				if (err) {
					callback(null, err);
				} else {
					callback(data, null);
				}
			});
		};

		apiClass.Auth = {

		};

		apiClass.Auth.login = function (user, callback) {
			httpRequest("POST", "mit-ms-customer-service/customer/login", user, function (err, data) {
				if (err) {
					callback(null, err);
				} else {
					callback(data, null);
				}
			});
		};
		
		apiClass.Auth.register = function (user, callback) {
			httpRequest("POST", "mit-ms-customer-service/customer/registration", user, function (err, data) {
				if (err) {
					callback(null, err);
				} else {
					callback(data, null);
				}
			});
		};

		apiClass.Product = {

		};

		apiClass.Product.addProduct = function (product, callback) {
			httpRequest("POST", "mit-ms-product-service/product", product, function (err, data) {
				if (err) {
					callback(null, err);
				} else {
					callback(data, null);
				}
			});
		};
		
		apiClass.Product.getAllProducts = function (callback) {
			httpRequest("GET", "mit-ms-product-service/product", null, function (err, data) {
				if (err) {
					callback(null, err);
				} else {
					callback(data, null);
				}
			});
		};
		
		apiClass.Order = {

		};

		apiClass.Order.buyProduct = function (order, callback) {
			httpRequest("POST", "mit-ms-order-service/order", order, function (err, data) {
				if (err) {
					callback(null, err);
				} else {
					callback(data, null);
				}
			});
		};
		
		apiClass.Order.getAllOrders = function (id, callback) {
			httpRequest("GET", "mit-ms-order-service/order/customer/"+id, null, function (err, data) {
				if (err) {
					callback(null, err);
				} else {
					callback(data, null);
				}
			});
		};

		return apiClass;
	}];
});
