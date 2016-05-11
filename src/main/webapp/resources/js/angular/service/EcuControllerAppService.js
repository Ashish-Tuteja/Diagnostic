dashboard.factory('controllerservice', [ '$resource', function($resource) {
	return $resource(':url', {},{
		save : {
			url : '/NavResearch/ecuController/add',
			method : 'POST',
			isArray : false
		}

	});
} ]);

	
dashboard.factory('controllerdeleteservice', [ '$resource', function($resource) {
	return $resource('/NavResearch/ecuController/delete/:id',{id: "@id"},{
		deleteEcu : {
			method : 'DELETE',
			isArray : false
		}

	});
} ]);


dashboard.factory('controllerListService', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		getList : {
			url : '/NavResearch/ecuController/list?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('getControllerService', [ '$resource', function($resource) {
	return $resource(':url', {
		controller : "@controller"
	}, {
		getControllerForEdit : {
			url : '/NavResearch/ecuController/get/:controller',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('carrierListService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/NavResearch/ecuController/carrier/list',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('controllertypeListService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/NavResearch/ecuController/controllertype/list',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('getControllersBySerach', [ '$resource', function($resource) {
	return $resource(':url', {
		searchterm : "@searchterm"
	}, {
		getList : {
			url : '/NavResearch/ecuController/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);
