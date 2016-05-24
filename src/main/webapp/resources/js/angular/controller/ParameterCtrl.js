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
		parameterListService.getList({}, {
			page : 1
		}, function(response) {	
			$scope.parameters = response.content;
			$rootScope.responseList = response;
			$rootScope.pages = response.totalPages;
			$scope.spinnerToggle=false;

		});
	}

	// parametertypes
	/*parametertypeListService.getList(function(response) {
		$scope.parametertypes = response;

	});

	// carriers
	carrierListService.getList(function(response) {
		$scope.carriers = response;
	});*/

	$scope.addParameter = function() {
		$rootScope.parameterDetail = null;
		// $scope.showModal = !$scope.showModal;
		$location.path("/parameter/add");
	};

	$scope.editparameter = function(obj) {
		$rootScope.parameterDetail = obj;
		$location.path("/parameter/add");
	}, $scope.getReports = function(obj) {
		$cookieStore.put("parameterip", obj.ip);
		$location.path("/vehicle/reportinfo");
	}

	
	//Display parameters for each parameter
	$scope.displayParameters = function(obj) {
		$rootScope.parameterDetail = obj;
		$location.path("/parameter");
	};
	
	$scope.deleteparameter = function(id) {

		console.log(id);
//		parameterDeleteService.deleteEcu({},{id:id},function(
//				response) {
////			if (response.id) {
//					$location.path("/parameter");
//					showmessage("Success!", "parameter deleted successfully",
//							"success");
//
////			} else {
////				showmessage("Error!", "parameter already exists", "error");
////			}
//		});
		
		parameterDeleteService.deleteEcu({id:id},function(response) {
			console.log(response);
//			$location.path("/parameter");
			$route.reload();
			showmessage("Success!", "parameter deleted successfully",
					"success");

		});
	
	}
	
	
	
	$scope.clickCheck = function(id){
		if(checkList.indexOf(id) < 0)
			{
			checkList.push(id);
			}
		else
			{
			checkList.pop(id);
			}
		console.log(checkList);
		
	 }
	 $scope.uploadFile = function(files) {
		 $scope.generateObJ = "Global Parameters";
		 /*
		 var parameterIds = [];
		 angular.forEach($scope.parameters, function(parameter) {
			 if($scope.check[parameter.parameterId]){
				 this.push(parameter.parameterId);
				 
			 }
		 }, parameterIds);*/
		 
		 
         var formData = new FormData();
         if (files.length == 0) {
           showmessage("Info!", "Please select a file", "info");
           return;
         }
         var uploadUrl = "";
         formData.append("file", files[0]);
         if (String($scope.generateObJ) == "Global Parameter") {
           
           uploadUrl = "/NavResearch/vehicle/upload/globaldata?data="
                   + String($scope.generateObJ);
           vehicleupload.uploadGlobalData(uploadUrl, formData);
           $scope.browse = true;
         } else if (String($scope.generateObJ) == "Vehicle ECU") {
           
           uploadUrl = "/NavResearch/vehicle/upload/vehicleecu?data="
                   + String($scope.generateObJ);
           vehicleupload.uploadVehicleECU(uploadUrl, formData);
           $scope.browse = true;

         } else if (String($scope.generateObJ) == "Global ECU") {
          
           uploadUrl = "/NavResearch/vehicle/upload/globalecu?data="
                   + String($scope.generateObJ);
           vehicleupload.uploadGlobalECU(uploadUrl, formData);
           $scope.browse = true;

         } else if (String($scope.generateObJ) == "Global Parameters") {
             
             uploadUrl = "/NavResearch/vehicle/upload/globalparameters?data="
                     + String($scope.generateObJ);
             vehicleupload.uploadGlobalParameters(uploadUrl, formData,files[0].name,checkList);
             $scope.browse = true;

         }

       }
	$scope.register = function() {
		delete $scope.save.parameter.$$hashKey;
		if(!$scope.save.parameter.id){
		var newField = {"id": "","parameterId":""};
		  angular.extend($scope.save.parameter, newField);
		}
		  console.log(JSON.stringify($scope.save.parameter));
		  parameterService.save(JSON.stringify($scope.save.parameter), function(
					response) {
				console.log(response);
				if (response.id) {
					if ($scope.save.parameter.id !="") {
						angular.forEach($scope.parameters, function(parameter, key) {
							if (parameter.id == $scope.save.parameter.id) {
								$scope.parameters[key] = response;

							}
						});
						$location.path("/parameter");
						showmessage("Success!", "parameter update successfully",
								"success");
					} else {
						$location.path("/parameter");
						showmessage("Success!", "parameter create successfully",
								"success");
					}

				} else {
					showmessage("Error!", "parameter already exists", "error");
				}
			});


	}, $scope.range = function(n) {
		return new Array(n);
	}, $scope.getparameterINRange = function(range) {
		$rootScope.pageNumber = range;
		parameterListService.getList({}, {
			page : range
		}, function(response) {
			$scope.parameters = response.content;
			$rootScope.pages = response.totalPages;
		});
	}, $scope.$watch('searchparameter', function(newValue, oldValue) {
		if (String(newValue).length > 1 && newValue) {
			getParametersBySerach.getList({}, {
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
		$location.path("/parameter");
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
