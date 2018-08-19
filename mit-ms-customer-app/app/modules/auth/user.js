'use strict';

msCusApp.factory('User', function ($http, $q, api, ipCookie, $log) {
	var dfd = $q.defer();

	var UserModel = {
			initialized: dfd.promise,
			firstName: undefined,
			lastName: undefined,
			picture: undefined,
			email: undefined,
			profileId:undefined,
			phone: undefined,
			contactPerson: undefined,
			userRole: undefined
	};

	if(ipCookie("AD_PFID")!= null && ipCookie("AD_PFID") != undefined){
		api.Auth.getUserInfo(ipCookie("AD_PFID"), function(result, error){
			if(result != null && result.status == "SUCCESS") {
				setUserInfo(result.data);
				dfd.resolve(UserModel);
			}else {
				$log.log('Error', result, error);
			}
		});
	}

	var setUserInfo = function(user) {
		UserModel.userName = user.userName;
		if(user.contactDetails){
			UserModel.firstName = user.contactDetails.firstName;
			UserModel.lastName = user.contactDetails.lastName;
			UserModel.email = user.contactDetails.emailAddress;
			UserModel.phone = user.contactDetails.mobilePhone;
		}
		UserModel.profileId = user.profileId;
		UserModel.picture = user.profileImageUrl;
		UserModel.AD_PFID = user.id;
		UserModel.userRole = user.role;
	};

	return {
		UserModel:UserModel,
		setUserInfo: setUserInfo
	}

});
