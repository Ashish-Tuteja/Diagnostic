/**
 * 
 */
dashboard.factory('vehicleservice', [ '$resource', function($resource) {
	return $resource(':url', {}, {
		save : {
			url : '/NavResearch/vehicle/add',
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
			url : '/NavResearch/vehicle/list?page=:page&size=20',
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
			url : '/NavResearch/vehicle/get/:vehicle',
			method : 'GET',
			isArray : false
		}
	});
} ]);

dashboard.factory('vehicleDeviceListService', [ '$resource',
		function($resource) {
			return $resource(':url', {}, {
				getList : {
					url : '/NavResearch/vehicle/device/list',
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
			url : '/NavResearch/vehicle/search/:searchterm',
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
			url : '/NavResearch/vehicle/findreportbygroupid/:groupid',
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
			url : '/NavResearch/vehicle/findreportsbyvin/:vin',
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
			url : '/NavResearch/vehicle/findreportsbyip/:ip',
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
							showmessage("Success!", "Upload file successfully",
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
							showmessage("Success!", "Upload file successfully",
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
							showmessage("Success!", "Upload file successfully",
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
		
		//Uploading Global parameters List for 1 file
		uploadGlobalParameters : function(uploadUrl, formData,fileName,controllerIds) {
			$http.post(uploadUrl, formData,  {
				headers : {
					'Content-Type' : undefined,
					'ids' : controllerIds
				},
				transformRequest : angular.identity
			}).success(
					function(data) {
						if (data == "0") {
							showmessage("Success!", fileName+" uploaded !!",
									"success");
							$location.path("/controller");
						} else {
							showmessage("Error!",
									"File not upload please check file format",
									"error");
							$location.path("/controller");
						}

					}).error(function(error) {
				showmessage("", "Please upload the file", "success");
			});
		}
//end for uploading global parameters
	}
});
dashboard
		.factory(
				'translationServiceDynamicStaticTest',
				function($http) {
					return $http
							.get('/NavResearch/resources/js/angular/i18n/parameter_description.json');
				});
dashboard
		.factory(
				'translationServiceDynamicStatic',
				function($http) {
					return $http
							.get('/NavResearch/resources/js/angular/i18n/parameter_description_d_s.json');
				});
