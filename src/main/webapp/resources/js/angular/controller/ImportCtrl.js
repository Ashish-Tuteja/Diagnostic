dashboard.controller("ImportCtrl",
        function($scope, $location, ctrlOptions, $rootScope, $routeParams,
                breadcrumbs, $location, $cookieStore,$window, $http, IMPORT_OPTION,
                vehicleupload) {
          
          $scope.importoptions = IMPORT_OPTION;
          $scope.importField = function(obj) {
            $scope.generateObJ = JSON.parse(JSON
                    .stringify($scope.importoptions[obj]))['option'];
            $scope.browse = true;
            $scope.browse = false;
            
          }

          $scope.uploadFile = function(files) {
        	  
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
                vehicleupload.uploadGlobalParameters(uploadUrl, formData);
                $scope.browse = true;

            }

          }

        });
