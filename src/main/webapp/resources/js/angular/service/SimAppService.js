dashboard.factory('simService', [ '$resource', function($resource) {
	return $resource('/navresearch/sim/getByIdOrMsisdnOrsimNo?id=:id',{id: "@id"},{
		getByIdOrMsisdnOrsimNo : {
			method : 'GET',
			isArray : false,
		}

	});
} ]);


dashboard.factory('simClientService', [ '$resource', function($resource) {
	return $resource(':url',{},{
		inquireSim: {
			url: '/navresearch/sim/inquireSim',
			method : 'GET',
			isArray : false,
		},
		queryHLR: {
			url: '/navresearch/sim/queryHLR',
			method : 'GET',
			isArray : false,
		},
		getNetworkStatus:{
			url: '/navresearch/sim/getNetworkStatus',
			method : 'GET',
			isArray : false,
		},
		cancelLocation:{
			url: '/navresearch/sim/cancelLocation',
			method : 'GET',
			isArray : false,
		},
		activateSim:{
			url: '/navresearch/sim/activateSim',
			method : 'GET',
			isArray : false,
		},
		suspendSim:{
			url: '/navresearch/sim/suspendSim',
			method : 'GET',
			isArray : false,
		},
		resumeSim:{
			url: '/navresearch/sim/resumeSim',
			method : 'GET',
			isArray : false,
		}
		
		
	});
} ]);