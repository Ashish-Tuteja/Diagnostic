dashboard.factory('UserLog', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		get : {
			url : '/NavResearch/dashboard/getUserLogs?page=:page&size=11',
			method : 'GET',
			isArray : false
		}

	});
} ]);
dashboard.factory('getChildAccounts', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		get : {
			url : '/NavResearch/dashboard/getAccounts',
			method : 'GET',
			isArray : true
		}

	});
} ]);
dashboard.factory('accountsession', [ '$resource', function($resource) {
	return $resource(':url', {
		accountID : "@accountID"
	}, {
		setaccountsession : {
			url : '/NavResearch/dashboard/accountsession/:accountID',
			method : 'GET',
			isArray : false
		}

	});
} ]);
