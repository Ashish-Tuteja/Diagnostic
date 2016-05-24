dashboard.factory('parameterService', [ '$resource', function($resource) {
	return $resource(':url', {},{
		save : {
			url : '/NavResearch/Parameter/add',
			method : 'POST',
			isArray : false
		}

	});
} ]);

	
dashboard.factory('parameterDeleteService', [ '$resource', function($resource) {
	return $resource('/NavResearch/Parameter/delete/:id',{id: "@id"},{
		deleteParameter : {
			method : 'DELETE',
			isArray : false
		}

	});
} ]);


dashboard.factory('parameterListService', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		getList : {
			url : '/NavResearch/Parameter/list?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('getParameterService', [ '$resource', function($resource) {
	return $resource(':url', {
		parameter : "@parameter"
	}, {
		getParameterForEdit : {
			url : '/NavResearch/Parameter/get/:parameter',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('getParametersBySerach', [ '$resource', function($resource) {
	return $resource(':url', {
		searchterm : "@searchterm"
	}, {
		getList : {
			url : '/NavResearch/Parameter/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);
