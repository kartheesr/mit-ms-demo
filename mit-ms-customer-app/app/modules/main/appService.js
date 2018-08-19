msCusApp.service('appService', function($q, $filter, ipCookie, $mdDialog, $location) {

	var self = this;
	this.today = function(){
		return new Date();
	}

	this.imgCvt = function(imdt){
		obj = {};
		obj.imageBase64 = imdt.split(",")[1];
		obj.imageType = imdt ? (imdt.substring(11).split(";")[0]) : "";
		return obj;
	}

});
