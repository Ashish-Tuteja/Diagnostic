/**
 * 
 */
dashboard.controller('ParameterCtrl', function($scope, $location, $rootScope,
		parameterService, parameterListService, getParameterService, getParametersBySerach,parameterDeleteService,
		 ctrlOptions, $routeParams, $window, breadcrumbs, $route,
		$cookieStore,vehicleupload) {
	$scope.showModal = false;
	$rootScope.pageNumber = 1;
	$scope.breadcrumbs = breadcrumbs;
	$scope.save = {};
	$scope.parametertypes = {};
	$scope.carriers = {};
	
	$scope.spinnerToggle=true;
	var checkList = [];
	if ($rootScope.parameterDetail != null) {
		$scope.buttonName = "Edit";
		$scope.save.parameter = $rootScope.parameterDetail;
	} else {
		$scope.buttonName = "Save";
	}
	if (ctrlOptions.showparameters) {
		loading("Fetching Parameters...");
		$scope.buttonName = "Save";
		parameterListService.getList({id:$rootScope.contDetails.controllerId}, {page : 1}, function(response) {	
			$scope.parameters = response.content;
			$rootScope.responseList = response;
			$rootScope.pages = response.totalPages;
			$scope.spinnerToggle=false;

		});
	}

	$scope.addParameter = function() {
		$rootScope.parameterDetail = null;
		// $scope.showModal = !$scope.showModal;
		$location.path("/controller/parameter/add");
	};

	$scope.editparameter = function(obj) {
		$rootScope.parameterDetail = obj;
		$location.path("/controller/parameter/add");
	}, $scope.getReports = function(obj) {
		$cookieStore.put("parameterip", obj.ip);
		$location.path("/vehicle/reportinfo");
	}

	
	//deleting parameter by Id
	$scope.deleteParameter = function(id) {
		console.log(id);
		parameterDeleteService.deleteParameter({id:id},function(response) {
			console.log(response);
			$route.reload();
			showmessage("Success!", "parameter deleted successfully",
					"success");

		});
	
	}
	
	
	//Saving parameters for a particular controller by controller Id
	$scope.register = function() {
		delete $scope.save.parameter.$$hashKey;
		  console.log(JSON.stringify($scope.save.parameter));
		  parameterService.save({id:$rootScope.contDetails.controllerId},JSON.stringify($scope.save.parameter), function(
					response) {
				console.log(response);
				if (response.id) {
					if ($scope.save.parameter.id != null) {
						angular.forEach($scope.parameters, function(parameter, key) {
							if (parameter.id == $scope.save.parameter.id) {
								$scope.parameters[key] = response;

							}
						});
						$location.path("/controller/parameter");
						showmessage("Success!", "parameter updated successfully",
								"success");
					} else {
						$location.path("/controller/parameter");
						showmessage("Success!", "parameter created successfully",
								"success");
					}

				} else {
					showmessage("Error!", "parameter already exists", "error");
				}
			});


	}, $scope.range = function(n) {
		return new Array(n);
	}, $scope.getParameterINRange = function(range) {
		$rootScope.pageNumber = range;
		parameterListService.getList({}, {
			page : range
		}, function(response) {
			$scope.parameters = response.content;
			$rootScope.pages = response.totalPages;
		});
	}, $scope.$watch('searchParameter', function(newValue, oldValue) {
		if (String(newValue).length > 1 && newValue) {
			getParametersBySerach.getList({contId : $rootScope.contDetails.controllerId}, {
				searchterm : newValue
			}, function(response) {
				$rootScope.pages = response.length;
				$scope.parameters = response;
			});
		} else if (String(newValue).length <= 1 && newValue) {
			$scope.parameters = $rootScope.responseList.content;
			$rootScope.pages = $rootScope.responseList.totalPages;
		}
	});

	$scope.close = function() {
		$location.path("/controller/parameter");
	}
	$scope.previous = function() {
		if ($rootScope.pageNumber > 1) {
			$rootScope.pageNumber = $rootScope.pageNumber - 1;
			$scope.getparameterINRange($rootScope.pageNumber);
		}
	}
	$scope.next = function() {
		if ($rootScope.pages > $rootScope.pageNumber) {
			$rootScope.pageNumber = $rootScope.pageNumber + 1;
			$scope.getparameterINRange($rootScope.pageNumber);
		}
	}
});
