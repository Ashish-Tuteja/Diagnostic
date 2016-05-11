dashboard.factory('deviceservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/NavResearch/device/add',
			method : 'POST',
			isArray : false
		}

	});
} ]);
dashboard.factory('deviceListService', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		getList : {
			url : '/NavResearch/device/list?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('getDeviceService', [ '$resource', function($resource) {
	return $resource(':url', {
		device : "@device"
	}, {
		getDeviceForEdit : {
			url : '/NavResearch/device/get/:device',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('carrierListService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/NavResearch/device/carrier/list',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('devicetypeListService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/NavResearch/device/devicetype/list',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('getDevicesBySerach', [ '$resource', function($resource) {
	return $resource(':url', {
		searchterm : "@searchterm"
	}, {
		getList : {
			url : '/NavResearch/device/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);
