dashboard.factory('accountservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/NavResearch/account/add',
			method : 'POST',
			isArray : false
		}

	});
} ]);
dashboard.factory('accountListService', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		getList : {
			url : '/NavResearch/account/list?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('getAccountService', [ '$resource', function($resource) {
	return $resource(':url', {
		account : "@account"
	}, {
		getAccountForEdit : {
			url : '/NavResearch/account/get/:account',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('getAccountsBySerach', [ '$resource', function($resource) {
	return $resource(':url', {
		searchterm : "@searchterm"
	}, {
		getList : {
			url : '/NavResearch/account/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);
