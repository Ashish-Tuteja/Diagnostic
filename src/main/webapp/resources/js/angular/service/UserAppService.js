//ngResource
app.factory('userservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/NavResearch/user/add/',
			method : 'POST',
			isArray : false
		}
	});
} ]);
dashboard.factory('userservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/NavResearch/user/add/',
			method : 'POST',
			isArray : false
		}
	});
} ]);

dashboard.factory('useraccountservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getUserAccount : {
			url : '/NavResearch/user/getuseraccount/',
			method : 'GET',
			isArray : false
		}
	});
} ]);
dashboard.factory('sendlinktochangecredential', [ '$resource',
		function($resource) {
			return $resource(':url', {}, {
				sendCode : {
					url : '/NavResearch/user/accountsetting',
					method : 'GET',
					isArray : false
				}
			});
		} ]);

dashboard.factory('changepassword', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		changepassword : {
			url : '/NavResearch/user/changepassword',
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
			url : '/NavResearch/user/get/:user',
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
			url : '/NavResearch/account/find/',
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
			url : '/NavResearch/user/list/?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);
dashboard.factory('userRoleService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/NavResearch/user/roles/list',
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
			url : '/NavResearch/user/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);
