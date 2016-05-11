//ngResource
app.factory('userservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/cimble/user/add/',
			method : 'POST',
			isArray : false
		}
	});
} ]);
dashboard.factory('userservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/cimble/user/add/',
			method : 'POST',
			isArray : false
		}
	});
} ]);

dashboard.factory('useraccountservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getUserAccount : {
			url : '/cimble/user/getuseraccount/',
			method : 'GET',
			isArray : false
		}
	});
} ]);
dashboard.factory('sendlinktochangecredential', [ '$resource',
		function($resource) {
			return $resource(':url', {}, {
				sendCode : {
					url : '/cimble/user/accountsetting',
					method : 'GET',
					isArray : false
				}
			});
		} ]);

dashboard.factory('changepassword', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		changepassword : {
			url : '/cimble/user/changepassword',
			method : 'POST',
			isArray : false
		}
	});
} ]);

dashboard.factory('getUserService', [ '$resource', function($resource) {
	return $resource(':url', {
		user : "@user"
	}, {
		getUserForEdit : {
			url : '/cimble/user/get/:user',
			method : 'GET',
			isArray : false
		}
	});
} ]);
dashboard.factory('findaccountname', [ '$resource', function($resource) {
	return $resource(':url', {
		user : "@user"
	}, {
		getAccountName : {
			url : '/cimble/account/find/',
			method : 'GET',
			isArray : true
		}
	});
} ]);

dashboard.factory('userListService', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		getList : {
			url : '/cimble/user/list/?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);
dashboard.factory('userRoleService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/cimble/user/roles/list',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('getUsersBySerach', [ '$resource', function($resource) {
	return $resource(':url', {
		searchterm : "@searchterm"
	}, {
		getList : {
			url : '/cimble/user/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);
