dashboard.factory('parameterService', [ '$resource', function($resource) {
	return $resource('/NavResearch/parameters/add/:id',{id: "@id"},{
		save : {
			method : 'POST',
			isArray : false,
		}

	});
} ]);
dashboard.factory('parameterListService', [ '$resource', function($resource) {
	return $resource(':url', {id : "@id",page : "@page"}, {
		getList : {
			url : '/NavResearch/parameters/list/:id?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);
	
dashboard.factory('parameterDeleteService', [ '$resource', function($resource) {
	return $resource('/NavResearch/parameters/delete/:id',{id: "@id"},{
		deleteParameter : {
			method : 'DELETE',
			isArray : false
		}

	});
} ]);



dashboard.factory('parametersDeleteService', [ '$resource', function($resource) {
	return $resource('/NavResearch/parameters/deleteParamsForCtrl/:id',{id: "@id"},{
		deleteParameters : {
			method : 'DELETE',
			isArray : false
		}

	});
} ]);

/*dashboard.factory('parameterListService', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		getList : {
			url : '/NavResearch/parameters/list?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);*/

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
	return $resource(':url', {searchterm : "@searchterm", contId : "@contId"}, {
		getList : {
			url : '/NavResearch/parameters/search/:searchterm/:contId',
			method : 'GET',
			isArray : true
		}
	});
} ]);
