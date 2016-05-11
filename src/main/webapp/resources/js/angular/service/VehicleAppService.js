/**
 * 
 */
dashboard.factory('vehicleservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/cimble/vehicle/add',
			method : 'POST',
			isArray : false
		}

	});
} ]);
dashboard.factory('vehicleListService', [ '$resource', function($resource) {
	return $resource(':url', {
		page : "@page"
	}, {
		getList : {
			url : '/cimble/vehicle/list?page=:page&size=20',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('getVehicleService', [ '$resource', function($resource) {
	return $resource(':url', {
		vehicle : "@vehicle"
	}, {
		getVehicleForEdit : {
			url : '/cimble/vehicle/get/:vehicle',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('vehicleDeviceListService', [ '$resource',
		function($resource) {
			return $resource(':url', {}, {
				getList : {
					url : '/cimble/vehicle/device/list',
					method : 'GET',
					isArray : true
				}
			});
		} ]);
dashboard.factory('getVehiclesBySerach', [ '$resource', function($resource) {
	return $resource(':url', {
		searchterm : "@searchterm"
	}, {
		getList : {
			url : '/autoficio/vehicle/search/:searchterm',
			method : 'GET',
			isArray : true
		}
	});
} ]);

dashboard.factory('getReportByGroupId', [ '$resource', function($resource) {
	return $resource(':url', {
		groupid : "@groupid"
	}, {
		get : {
			url : '/cimble/vehicle/findreportbygroupid/:groupid',
			method : 'GET',
			isArray : false
		}
	});
} ]);
dashboard.factory('getReportsbyID', [ '$resource', function($resource) {
	return $resource(':url', {
		vin : "@vin"
	}, {
		get : {
			url : '/cimble/vehicle/findreportsbyvin/:vin',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('getReportsbyIP', [ '$resource', function($resource) {
	return $resource(':url', {
		ip : "@ip"
	}, {
		get : {
			url : '/cimble/vehicle/findreportsbyip/:ip',
			method : 'GET',
			isArray : true
		}
	});
} ]);
dashboard.factory('vehicleupload', function($http, $location) {
	return {
		uploadGlobalData : function(uploadUrl, formData) {
			$http.post(uploadUrl, formData, {
				headers : {
					'Content-Type' : undefined
				},
				transformRequest : angular.identity
			}).success(
					function(data) {
						if (data == "0") {
							showmessage("Sucsses!", "Upload file successfully",
									"success");
							$location.path("/import");
						} else {
							showmessage("Error!",
									"File not upload please check file format",
									"error");
							$location.path("/import");
						}

					}).error(function(error) {
				showmessage("Error!", "File not upload !! Try again", "error");
			});
		},
		uploadVehicleECU : function(uploadUrl, formData) {
			$http.post(uploadUrl, formData, {
				headers : {
					'Content-Type' : undefined
				},
				transformRequest : angular.identity
			}).success(
					function(data) {
						if (data == "0") {
							showmessage("Sucsses!", "Upload file successfully",
									"success");
							$location.path("/import");
						} else {
							showmessage("Error!",
									"File not upload please check file format",
									"error");
							$location.path("/import");
						}

					}).error(function(error) {
				showmessage("Error!", "File not upload !! Try again", "error");
			});
		},
		uploadGlobalECU : function(uploadUrl, formData) {
			$http.post(uploadUrl, formData, {
				headers : {
					'Content-Type' : undefined
				},
				transformRequest : angular.identity
			}).success(
					function(data) {
						if (data == "0") {
							showmessage("Sucsses!", "Upload file successfully",
									"success");
							$location.path("/import");
						} else {
							showmessage("Error!",
									"File not upload please check file format",
									"error");
							$location.path("/import");
						}

					}).error(function(error) {
				showmessage("Error!", "File not upload !! Try again", "error");
			});
		}

	}
});
dashboard
		.factory(
				'translationServiceDynamicStaticTest',
				function($http) {
					return $http
							.get('/cimble/resources/js/angular/i18n/parameter_description.json');
				});
dashboard
		.factory(
				'translationServiceDynamicStatic',
				function($http) {
					return $http
							.get('/cimble/resources/js/angular/i18n/parameter_description_d_s.json');
				});
