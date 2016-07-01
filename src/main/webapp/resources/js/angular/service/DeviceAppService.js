dashboard.factory('deviceservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/navresearch/device/add',
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
			url : '/navresearch/device/list?page=:page&size=20',
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
			url : '/navresearch/device/get/:device',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('carrierListService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/navresearch/device/carrier/list',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('devicetypeListService', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		getList : {
			url : '/navresearch/device/devicetype/list',
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
			url : '/navresearch/device/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);
