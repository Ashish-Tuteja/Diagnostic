dashboard.factory('controllerservice', [ '$resource', function($resource) {
	return $resource(':url', {},{
		save : {
			url : '/navresearch/ecuController/add',
			method : 'POST',
			isArray : false
		}

	});
} ]);

	
dashboard.factory('controllerdeleteservice', [ '$resource', function($resource) {
	return $resource('/navresearch/ecuController/delete/:id',{id: "@id"},{
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
			url : '/navresearch/ecuController/list?page=:page',
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
			url : '/navresearch/ecuController/get/:controller',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('carrierListService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/navresearch/ecuController/carrier/list',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('controllertypeListService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/navresearch/ecuController/controllertype/list',
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
			url : '/navresearch/ecuController/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('getMakesByYearService', [ '$resource', function($resource) {
	return $resource(':url', {year: "@year"}, {
		getMakes : {
			url : '/navresearch/ecuController/makes/list/:year',
			method : 'GET',
			isArray : true
		}
	});
} ]);
