dashboard.factory('vehiclesHistoryListService', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		getList : {
			url : '/cimble/vehicleHistory/list?page=:1&size=2',
			method : 'GET',
			isArray : true
		}
	});
} ]);

dashboard.factory('getVehiclesHistoryBySerach', [ '$resource', function($resource) {
	return $resource(':url', {
		searchterm : "@searchterm"
	}, {
		getList : {
			url : '/cimble/vehicleHistory/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);


