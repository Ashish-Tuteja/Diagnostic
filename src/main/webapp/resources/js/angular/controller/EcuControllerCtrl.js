/**
 *
 */
dashboard.controller('EcuControllerCtrl', function($scope, $location, $rootScope,
		controllerservice, controllerListService, carrierListService,
		controllertypeListService, getControllerService, getControllersBySerach,controllerdeleteservice,parametersDeleteService,
		 ctrlOptions, $routeParams, $window, breadcrumbs, $route,getMakesByYearService,$filter,
		$cookieStore,vehicleupload) {
	$scope.showModal = false;
	$rootScope.pageNumber = 1;
	$scope.breadcrumbs = breadcrumbs;
	$scope.save = {};
	 $scope.pageSize = 20;
	 $scope.currentPage = 0;
	$scope.controllertypes = {};
	$scope.carriers = {};
	$scope.spinnerToggle=true;
	var checkList = [];
	var controllerList = [];
	 var fileNameOne ="";
	 var oneFormData = new FormData();
	 $scope.propertyName = 'make';
	  $scope.reverse = false;
//	  $scope.makes=["Accura","Audi","BMW","Buick","Cadillac","Chevrolet","Chrysler","Dodge","Fiat","Ford","GMC",
//	                "Honda","Hummer","Hyundai","Infiniti","Jeep","Kia","Lexus","Lincoln","Mazda","Mercedes-Benz",
//	                "Mercury","Mini","Mitsubishi","Nissan","Porsche","Ram","Saab","Scion","Subaru",
//	                "Toyota","Volkswagen","Volvo"];



	  $scope.controllerNames=["ABS - EBCM","Chassis","Enhanced Powertrain","Power Steering","SRS","Suspension","TPMS","Traction Control - Differential","Transmission"];
	  $scope.sortBy = function(propertyName) {
	    $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
	    $scope.propertyName = propertyName;
	  };
	if ($rootScope.controllerDetail != null) {
//		$scope.showSingleYear = true;
		$scope.buttonName = "Edit";
		getMakesByYearService.getMakes({year:$rootScope.controllerDetail.year},function(response) {
			console.log(response);
			$scope.makes=angular.copy(response);
			$scope.getModels($rootScope.controllerDetail.make);
		});
		$scope.save.controller = $rootScope.controllerDetail;
	} else {
//		$scope.showSingleYear = false;
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
			$rootScope.pages = Math.ceil(response.numberOfElements/$scope.pageSize);
			$scope.spinnerToggle=false;

		});
	}



	//Display parameters for each controller
	$scope.displayParameters = function(obj) {
		$rootScope.contDetails = obj;
		$location.path("/controller/parameter");
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

	$scope.findMakes = function(year){
		getMakesByYearService.getMakes({year:year},function(response) {
			$scope.makes=response;
			$scope.save.controller.make ="";
			$scope.save.controller.model="";
		});
	}
	$scope.getModels = function(makeName){
		var make = $filter("filter")($scope.makes, {
			name: makeName
		});
		$scope.models = make[0].models;
	}

	$scope.editcontroller = function(obj) {
		$rootScope.controllerDetail = obj;
		$location.path("/controller/add");
	}, $scope.getReports = function(obj) {
		$cookieStore.put("controllerip", obj.ip);
		$location.path("/vehicle/reportinfo");
	}

	$scope.deleteController = function(id,controllerId) {

		console.log(id);
		//deleting the controller
		controllerdeleteservice.deleteEcu({id:id},function(response) {

			//deleting parameters for the controller
			parametersDeleteService.deleteParameters({id:controllerId},function(response) {
				console.log(response);

			});

			console.log(response);
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

         var formData = new FormData();
         if (files.length == 0) {
           showmessage("Info!", "Please select a file", "info");
           return;
         }
         var uploadUrl = "";
         formData.append("file", files[0]);
         if (String($scope.generateObJ) == "Global Parameters") {

             uploadUrl = "/navresearch/vehicle/upload/globalparameters?data="
                     + String($scope.generateObJ);
             vehicleupload.uploadGlobalParameters(uploadUrl, formData,files[0].name,checkList);
             $scope.browse = true;

         }

       }
	 $scope.uploadFileForOne = function(files) {


         if (files.length == 0) {
           showmessage("Info!", "Please select a file", "info");
           return;
         }
         oneFormData.append("file", files[0]);
             fileNameOne = files[0].name;

             $scope.browse = true;
             showmessage("File Uploaded!", "Click save",
				"success");


       }

	 $scope.generateUUID = function(){
		    var d = new Date().getTime();
		    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		        var r = (d + Math.random()*16)%16 | 0;
		        d = Math.floor(d/16);
		        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
		    });
		    return uuid;
		};

	$scope.register = function() {
		delete $scope.save.controller.$$hashKey;
		if(!$scope.save.controller.id){

		  var controllerUUID = $scope.generateUUID();
		  console.log(controllerUUID);
		  controllerList.push(controllerUUID);


		var newField = {"id": "","controllerId":controllerList.toString()};
		angular.extend($scope.save.controller, newField);
		}
		$scope.save.controller.year = $scope.save.controller.year.toString();
		  console.log(JSON.stringify($scope.save.controller));
			controllerservice.save(JSON.stringify($scope.save.controller), function(
					response) {
				console.log(response);
				if (response.id) {
					if ($scope.save.controller.id != "") {
						angular.forEach($scope.controllers, function(controller, key) {
							if (controller.id == $scope.save.controller.id) {
								$scope.controllers[key] = response;

							}
						});
						$location.path("/controller");
						showmessage("Success!", "Controller update successfully",
								"success");
					} else {
						  var OneUploadUrl = "/navresearch/vehicle/upload/globalparameters?data=Global Parameters";
						vehicleupload.uploadGlobalParameters(OneUploadUrl, oneFormData,fileNameOne,controllerList);
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
	}/*, $scope.getcontrollerINRange = function(range) {
		$rootScope.pageNumber = range;
		controllerListService.getList({}, {
			page : range
		}, function(response) {
			$scope.controllers = response.content;
			$rootScope.pages = response.totalPages;
		});
	}*/, $scope.$watch('searchController', function(newValue, oldValue) {
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
/*	$scope.previous = function() {
		if ($rootScope.pageNumber > 1) {
			$rootScope.pageNumber = $rootScope.pageNumber - 1;
			$scope.getcontrollerINRange($rootScope.pageNumber);
		}
	}
	$scope.next = function() {
		if ($rootScope.pages > $rootScope.pageNumber) {
			$rootScope.pageNumber = $rootScope.pageNumber + 1;
			$scope.getcontrollerINRange($rootScope.pageNumber);
		}
	}*/
});

dashboard.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        if(input==undefined){
			return;
	}
        return input.slice(start);
    }
});
