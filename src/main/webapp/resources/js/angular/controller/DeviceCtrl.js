/**
 * 
 */
dashboard.controller('DeviceCtrl', function($scope, $location, $rootScope,
		deviceservice, deviceListService, carrierListService,
		devicetypeListService, getDeviceService, getDevicesBySerach,
		 ctrlOptions, $routeParams, $window, breadcrumbs,
		$cookieStore) {
	$scope.showModal = false;
	$rootScope.pageNumber = 1;
	$scope.breadcrumbs = breadcrumbs;
	$scope.save = {};
	$scope.devicetypes = {};
	$scope.carriers = {};
	$scope.spinnerToggle=true;
	if ($rootScope.deviceDetail != null) {
		$scope.buttonName = "Edit";
		$scope.save.device = $rootScope.deviceDetail;
	} else {
		$scope.buttonName = "Save";
	}
	if (ctrlOptions.showdevices) {
		loading("Fetching Devices...");
		$scope.buttonName = "Save";
		deviceListService.getList({}, {
			page : 1
		}, function(response) {
			$scope.devices = response.content;
			$rootScope.responseList = response;
			$rootScope.pages = response.totalPages;
			$scope.spinnerToggle=false;

		});
	}

	// devicetypes
	devicetypeListService.getList(function(response) {
		$scope.devicetypes = response;

	});

	// carriers
	carrierListService.getList(function(response) {
		$scope.carriers = response;
	});

	$scope.addDevice = function() {
		$rootScope.deviceDetail = null;
		// $scope.showModal = !$scope.showModal;
		$location.path("/device/add");
	};

	$scope.editdevice = function(obj) {
		$rootScope.deviceDetail = obj;
		$location.path("/device/add");
	}, $scope.getReports = function(obj) {
		$cookieStore.put("deviceip", obj.ip);
		$location.path("/vehicle/reportinfo");
	}

	$scope.register = function() {
		delete $scope.save.device.$$hashKey;
		if ($scope.save.device.password) {
			deviceservice.save(JSON.stringify($scope.save.device), function(
					response) {
				if (response.id) {
					if ($scope.save.device.id != null) {
						angular.forEach($scope.devices, function(device, key) {
							if (device.id == $scope.save.device.id) {
								$scope.devices[key] = response;

							}
						});
						$location.path("/device");
						showmessage("Success!", "Device update successfully",
								"success");
					} else {
						$location.path("/device");
						showmessage("Success!", "Device create successfully",
								"success");
					}

				} else {
					showmessage("Error!", "Device already exists", "error");
				}
			});

		} else {
			showmessage("Error!", "Password can't blank", "error");
		}

	}, $scope.range = function(n) {
		return new Array(n);
	}, $scope.getDeviceINRange = function(range) {
		$rootScope.pageNumber = range;
		deviceListService.getList({}, {
			page : range
		}, function(response) {
			$scope.devices = response.content;
			$rootScope.pages = response.totalPages;
		});
	}, $scope.$watch('searchDevice', function(newValue, oldValue) {
		if (String(newValue).length > 1 && newValue) {
			getDevicesBySerach.getList({}, {
				searchterm : newValue
			}, function(response) {
				$rootScope.pages = response.length;
				$scope.devices = response;
			});
		} else if (String(newValue).length <= 1 && newValue) {
			$scope.devices = $rootScope.responseList.content;
			$rootScope.pages = $rootScope.responseList.totalPages;
		}
	});

	$scope.close = function() {
		$location.path("/device");
	}
	$scope.previous = function() {
		if ($rootScope.pageNumber > 1) {
			$rootScope.pageNumber = $rootScope.pageNumber - 1;
			$scope.getDeviceINRange($rootScope.pageNumber);
		}
	}
	$scope.next = function() {
		if ($rootScope.pages > $rootScope.pageNumber) {
			$rootScope.pageNumber = $rootScope.pageNumber + 1;
			$scope.getDeviceINRange($rootScope.pageNumber);
		}
	}
});
