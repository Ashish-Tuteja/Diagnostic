/**
 * 
 */
dashboard.controller('EcuControllerCtrl', function($scope, $location, $rootScope,
		controllerservice, controllerListService, carrierListService,
		controllertypeListService, getControllerService, getControllersBySerach,controllerdeleteservice,
		 ctrlOptions, $routeParams, $window, breadcrumbs, $route,
		$cookieStore,vehicleupload) {
	$scope.showModal = false;
	$rootScope.pageNumber = 1;
	$scope.breadcrumbs = breadcrumbs;
	$scope.save = {};
	$scope.controllertypes = {};
	$scope.carriers = {};
	$scope.spinnerToggle=true;
	var checkList = [];
	if ($rootScope.controllerDetail != null) {
		$scope.buttonName = "Edit";
		$scope.save.controller = $rootScope.controllerDetail;
	} else {
		$scope.buttonName = "Save";
	}
	if (ctrlOptions.showcontrollers) {
		loading("Fetching Controllers...");
		$scope.buttonName = "Save";
		controllerListService.getList({}, {
			page : 1
		}, function(response) {
			$scope.controllers = response.content;
			$rootScope.responseList = response;
			$rootScope.pages = response.totalPages;
			$scope.spinnerToggle=false;

		});
	}

	//Display parameters for each controller
	$scope.displayParameters = function(obj) {
		$rootScope.parameterControllerId = obj;
		$location.path("/parameter");    
	};
	
	// controllertypes
	/*controllertypeListService.getList(function(response) {
		$scope.controllertypes = response;

	});

	// carriers
	carrierListService.getList(function(response) {
		$scope.carriers = response;
	});*/

	$scope.addController = function() {
		$rootScope.controllerDetail = null;
		// $scope.showModal = !$scope.showModal;
		$location.path("/controller/add");
	};

	$scope.editcontroller = function(obj) {
		$rootScope.controllerDetail = obj;
		$location.path("/controller/add");
	}, $scope.getReports = function(obj) {
		$cookieStore.put("controllerip", obj.ip);
		$location.path("/vehicle/reportinfo");
	}

	$scope.deleteController = function(id) {

		console.log(id);
//		controllerdeleteservice.deleteEcu({},{id:id},function(
//				response) {
////			if (response.id) {
//					$location.path("/controller");
//					showmessage("Success!", "Controller deleted successfully",
//							"success");
//
////			} else {
////				showmessage("Error!", "Controller already exists", "error");
////			}
//		});
		
		controllerdeleteservice.deleteEcu({id:id},function(response) {
			console.log(response);
//			$location.path("/controller");
			$route.reload();
			showmessage("Success!", "Controller deleted successfully",
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
		 var controllerIds = [];
		 angular.forEach($scope.controllers, function(controller) {
			 if($scope.check[controller.controllerId]){
				 this.push(controller.controllerId);
				 
			 }
		 }, controllerIds);*/
		 
		 
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
		delete $scope.save.controller.$$hashKey;
		if(!$scope.save.controller.id){
		var newField = {"id": "","controllerId":""};
		  angular.extend($scope.save.controller, newField);
		}
		  console.log(JSON.stringify($scope.save.controller));
			controllerservice.save(JSON.stringify($scope.save.controller), function(
					response) {
				console.log(response);
				if (response.id) {
					if ($scope.save.controller.id !="") {
						angular.forEach($scope.controllers, function(controller, key) {
							if (controller.id == $scope.save.controller.id) {
								$scope.controllers[key] = response;

							}
						});
						$location.path("/controller");
						showmessage("Success!", "Controller update successfully",
								"success");
					} else {
						$location.path("/controller");
						showmessage("Success!", "Controller create successfully",
								"success");
					}

				} else {
					showmessage("Error!", "Controller already exists", "error");
				}
			});


	}, $scope.range = function(n) {
		return new Array(n);
	}, $scope.getControllerINRange = function(range) {
		$rootScope.pageNumber = range;
		controllerListService.getList({}, {
			page : range
		}, function(response) {
			$scope.controllers = response.content;
			$rootScope.pages = response.totalPages;
		});
	}, $scope.$watch('searchController', function(newValue, oldValue) {
		if (String(newValue).length > 1 && newValue) {
			getControllersBySerach.getList({}, {
				searchterm : newValue
			}, function(response) {
				$rootScope.pages = response.length;
				$scope.controllers = response;
			});
		} else if (String(newValue).length <= 1 && newValue) {
			$scope.controllers = $rootScope.responseList.content;
			$rootScope.pages = $rootScope.responseList.totalPages;
		}
	});

	$scope.close = function() {
		$location.path("/controller");
	}
	$scope.previous = function() {
		if ($rootScope.pageNumber > 1) {
			$rootScope.pageNumber = $rootScope.pageNumber - 1;
			$scope.getControllerINRange($rootScope.pageNumber);
		}
	}
	$scope.next = function() {
		if ($rootScope.pages > $rootScope.pageNumber) {
			$rootScope.pageNumber = $rootScope.pageNumber + 1;
			$scope.getControllerINRange($rootScope.pageNumber);
		}
	}
});