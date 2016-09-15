dashboard
		.controller(
				'VehicleCtrl',
				function($scope, $location, vehicleservice, vehicleListService,
						applyScale, getVehicleService,
						vehicleDeviceListService, getVehiclesBySerach,
						getReportByGroupId, getReportsbyID, getReportsbyIP,
						translationServiceDynamicStaticTest,
						translationServiceMode6,
						translationServiceDynamicStatic, ctrlOptions,
						$rootScope, $routeParams, breadcrumbs, $location,$modal,
						$cookieStore, $http) {
					$scope.dynamicReports = [];
					$scope.dynamicReportsTimestamp = [];
					$scope.dynamicReportpacketType = [];
					$scope.staticReports = [];
					$scope.staticReportsTimestamp = [];
					$scope.staticReportpacketType = [];
					$scope.troubleReports = [];
					$scope.troubleReportsTimestamp = [];
					$scope.troubleReportpacketType = [];
					$scope.mode6Reports = [];
					$scope.mode6ReportsTimestamp = [];
					$scope.mode6reportpacketType = [];
					$scope.mode7Reports = [];
					$scope.mode7ReportsTimestamp = [];
					$scope.mode7reportpacketType = [];
					$scope.mode8Reports = [];
					$scope.mode8ReportsTimestamp = [];
					$scope.mode8reportpacketType = [];
					$scope.systemReports = [];
					$scope.systemReportsTimestamp = [];
					$scope.spinnerToggle = true;
					$scope.showModal = false;
					$rootScope.pageNumber = 1;
					$scope.sortCriteria = "timestamp DESC";

					$scope.sortClasses = {
						"make" : "fa fa-sort",
						"model" : "fa fa-sort",
						"year" : "fa fa-sort",
						"timestamp" : "fa fa-sort-desc"
					};
					if (String($location.path()).indexOf("vehiclereport") != -1) {
						breadcrumbs.options = {
							"ReportInfo" : $cookieStore.get('groupid')
						}
					}
					$scope.breadcrumbs = breadcrumbs;
					$scope.save = {};
					$scope.vehicletypes = {};
					$scope.devices = {};

					$scope.noDevice = false;
					if ($rootScope.vehicleDetail != null) {
						$scope.buttonName = "Edit";
						$scope.devices = $rootScope.devices;
						$scope.save.vehicle = $rootScope.vehicleDetail;
						$scope.vinstats = false;
					} else {
						$scope.buttonName = "Save";
						vehicleDeviceListService.getList(function(response) {
							$rootScope.devices = response;
							$scope.save = {};
							$scope.vinstats = true;
							$scope.devices = $rootScope.devices;

						});

					}
					if (String($location.path()).indexOf("/vehicle/reportinfo") != -1
							&& String($location.path()).indexOf(
									"/vehiclereport") == -1) {
						$rootScope.reportinfo = "";
						loading("Fetching Tests...");
						if (angular.isUndefined($cookieStore.get("deviceip"))) {
							getReportsbyID.get({
								vin : $cookieStore.get('vin')
							}, function(response) {
								console.log(response);
								$rootScope.reportinfo = response;
								$scope.spinnerToggle = false;
							});
						} else {
							getReportsbyIP.get({
								ip : $cookieStore.get('deviceip')
							}, function(response) {
								$rootScope.reportinfo = response;
								$scope.spinnerToggle = false;
							});
						}

					}
					$scope.getreport = function(obj) {
						$cookieStore.put("groupid", obj.reportgroupId);
						$location.path("/vehicle/reportinfo/vehiclereport");

					},

					$scope.getReportsinfo = function(obj) {
						$cookieStore.remove("deviceip");
						$cookieStore.put("vin", obj.vin);
						$location.path("/vehicle/reportinfo");
					}

					if (ctrlOptions.showvehicles) {
						loading("Fetching Vehicles...");
						$scope.buttonName = "Save";
						if (String($location.path()).indexOf("vehiclereport") == -1) {
							vehicleListService.getList({}, {
								page : 1,
								sortCriteria : $scope.sortCriteria
							}, function(response) {
								$scope.vehicles = response.content;
								$rootScope.responseList = response;
								$rootScope.pages = response.totalPages;
								$scope.spinnerToggle = false;
							});
						}
					}

					$scope.toggleSortDirection = function(sortField, direction) {
						$scope.sortClasses = {
							"make" : "fa fa-sort",
							"model" : "fa fa-sort",
							"year" : "fa fa-sort",
							"timestamp" : "fa fa-sort"
						};
						if (direction.indexOf("desc") != -1) {
							$scope.sortCriteria = sortField + " ASC";
							$scope.sortClasses[sortField] = "fa fa-sort-asc";
						} else if (direction.indexOf("asc") != -1) {
							$scope.sortCriteria = sortField + " DESC";
							$scope.sortClasses[sortField] = "fa fa-sort-desc";
						} else {
							$scope.sortCriteria = sortField + " " + "ASC";
							$scope.sortClasses[sortField] = "fa fa-sort-asc";
						}

						vehicleListService.getList({}, {
							page : $rootScope.pageNumber,
							sortCriteria : $scope.sortCriteria
						}, function(response) {
							$scope.vehicles = response.content;
							$rootScope.responseList = response;
							$rootScope.pages = response.totalPages;
							$scope.spinnerToggle = false;
						});
					}
					$scope.updateSort = function(sortField) {
						$scope.toggleSortDirection(sortField,
								$scope.sortClasses[sortField]);
					}

					// Generate Dynamic Reports
					$scope.setDynamicReports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
/*							var check = system_array[i].split(":");
							if (check[1] != 0 || check[2] != 0 || check[3] != 0
									|| check[4] != 0) {
							}*/
							temp.push(system_array[i].split(":"));

						}
						$scope.dynamicReports.push(temp);

					}
					// Generate Static Reports
					$scope.setStaticReports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							/*var check = system_array[i].split(":");
							if (check[1] != 0 || check[2] != 0) {
							}*/
							temp.push(system_array[i].split(":"));

						}
						$scope.staticReports.push(temp);
					}

					// Generate Trouble Reports
					$scope.setTroubleReports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							/*var check = system_array[i].split(":");
							if (check[1] != 0 || check[2] != 0 || check[3] != 0
									|| check[4] != 0) {
							}*/
							temp.push(system_array[i].split(":"));

						}
						$scope.troubleReports.push(temp);
					}

					// Generate Mode 6 Reports
					$scope.setMode6Reports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							/*var check = system_array[i].split(":");
							if (check[1] != 0 || check[2] != 0 || check[3] != 0
									|| check[4] != 0 || check[5] != 0
									|| check[6] != 0) {
							}*/
							temp.push(system_array[i].split(":"));

						}
						$scope.mode6Reports.push(temp);
					}
					// Generate Mode 7 Reports
					$scope.setMode7Reports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							/*var check = system_array[i].split(":");
							if (check[1] != 0 || check[2] != 0 || check[3] != 0
									|| check[4] != 0 || check[5] != 0
									|| check[6] != 0) {
							}*/
							temp.push(system_array[i].split(":"));

						}
						$scope.mode7Reports.push(temp);
					}

					// Generate Mode 8 Reports
					$scope.setMode8Reports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							/*var check = system_array[i].split(":");
							if (check[1] != 0 || check[2] != 0 || check[3] != 0
									|| check[4] != 0 || check[5] != 0
									|| check[6] != 0) {
							}*/
							temp.push(system_array[i].split(":"));

						}
						$scope.mode8Reports.push(temp);
					}

					$scope.hi = function(obj) {
						console.log(obj);
					}

					// Generate System Reports
					$scope.setSystemReports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							/*var check = system_array[i].split(":");
							if (check[1] != 0 || check[2] != 0 || check[3] != 0
									|| check[4] != 0) {
							}*/
							temp.push(system_array[i].split(":"));

						}
						$scope.systemReports.push(temp);
					}

					if (String($location.path()).indexOf("vehiclereport") != -1) {
						/*
						 * Reintialize variables
						 */
						$scope.dynamicReports = [];
						$scope.staticReports = [];
						$scope.troubleReports = [];
						$scope.mode6Reports = [];
						$scope.mode7Reports = [];
						$scope.mode8Reports = [];
						$scope.systemReports = [];
						loading("Fetching Reports...");
						translationServiceDynamicStaticTest.success(function(
								data) {
							$scope.testtranslation = data['description'];
							$scope.testunits = data['units'];
							
						});
						translationServiceMode6.success(function(data) {
							$scope.monitorId = data['monitorId'];
							$scope.testId = data['testId'];
							$scope.UsIdunits = data['units'];
						});

						translationServiceDynamicStatic.success(function(data) {
							$scope.translation = data['description'];
							$scope.units = data['units'];
							$scope.minMax = data['min_max'];
						});
						getReportByGroupId
								.get(
										{
											groupid : $cookieStore
													.get('groupid')
										},
										function(response) {

											$scope.reportslen = JSON.parse(JSON
													.stringify(response))["reports"].length;

											if ($scope.reportslen == 0) {
												// print message no reports
												return;
											}
											var timestamp = JSON.parse(JSON
													.stringify(response))["timestamp"];

											var packetType = JSON.parse(JSON
													.stringify(response))["packetType"];

											$scope.descDetails = JSON
													.parse(JSON
															.stringify(response))["json"];
											$scope.canDetails = JSON.parse(JSON
													.stringify(response))["canDetails"];

											$scope.rawData = JSON.parse(JSON
													.stringify(response))["reports"];

											$scope.vehicleMake =  JSON.parse(JSON
													.stringify(response))["make"];

											for (k = 0; k < timestamp.length; k++) {

												if (timestamp[k]
														.indexOf("Dynamic") != -1) {
													$scope.dynamicReportsTimestamp
															.push(timestamp[k]
																	.split(',')[1]);
													$scope.dynamicReportpacketType
															.push(packetType[k]);

													var system_array = JSON
															.parse(JSON
																	.stringify(response))["reports"][k]["Dynamic Report"]
															.split(';');

													$scope
															.setDynamicReports(system_array);

												} else if (timestamp[k]
														.indexOf("Static") != -1) {

													$scope.staticReportsTimestamp
															.push(timestamp[k]
																	.split(',')[1]);
													$scope.staticReportpacketType
															.push(packetType[k]);

													var system_array = JSON
															.parse(JSON
																	.stringify(response))["reports"][k]["Static Report"]
															.split(';');

													$scope
															.setStaticReports(system_array);

												} else if (timestamp[k]
														.indexOf("Trouble") != -1) {
													$scope.troubleReportsTimestamp
															.push(timestamp[k]
																	.split(',')[1]);
													$scope.troubleReportpacketType
													.push(packetType[k]);
													var system_array = JSON
															.parse(JSON
																	.stringify(response))["reports"][k]["Trouble Code"]
															.split(';');
													$scope
															.setTroubleReports(system_array);

												} else if (timestamp[k]
														.indexOf("System") != -1) {
													$scope.systemReportsTimestamp
															.push(timestamp[k]
																	.split(',')[1]);

													var system_array = JSON
															.parse(JSON
																	.stringify(response))["reports"][k]["System Report"]
															.split(';');

													$scope
															.setSystemReports(system_array);

												} else if (timestamp[k]
														.indexOf("Mode 6 Report") != -1) {
													$scope.mode6ReportsTimestamp
															.push(timestamp[k]
																	.split(',')[1]);
													$scope.mode6reportpacketType
															.push(packetType[k]);

													var system_array = JSON
															.parse(JSON
																	.stringify(response))["reports"][k]["Mode 6 Report"]
															.split(';');

													$scope
															.setMode6Reports(system_array)

												} else if (timestamp[k]
														.indexOf("Mode 7 Report") != -1) {
													$scope.mode7ReportsTimestamp
															.push(timestamp[k]
																	.split(',')[1]);
													$scope.mode7reportpacketType
															.push(packetType[k]);

													var system_array = JSON
															.parse(JSON
																	.stringify(response))["reports"][k]["Mode 7 Report"]
															.split(';');

													$scope
															.setMode7Reports(system_array);

												} else if (timestamp[k]
														.indexOf("Mode 8 Report") != -1) {
													$scope.mode8ReportsTimestamp
															.push(timestamp[k]
																	.split(',')[1]);
													$scope.mode8reportpacketType
															.push(packetType[k]);

													var system_array = JSON
															.parse(JSON
																	.stringify(response))["reports"][k]["Mode 8 Report"]
															.split(';');

													$scope
															.setMode8Reports(system_array);

												}
											}
											$scope.spinnerToggle = false;
										});

					}

							$scope.addVehicle = function() {
								// device
								$rootScope.vehicleDetail = null;
								if ($rootScope.devices.length != 0) {
									$scope.save = {};
									$scope.vinstats = false;
									$scope.devices = $rootScope.devices;
									$location.path("/vehicle/add");
								} else {
									showmessage(
											"Error!",
											"Please add device before add vehicle",
											"error");
								}

							},
							$scope.editvehicle = function(obj) {
								$rootScope.vehicleDetail = obj;
								$location.path("/vehicle/add");
							},

							$scope.register = function() {
								delete $scope.save.vehicle.$$hashKey;
								vehicleservice
										.save(
												JSON
														.stringify($scope.save.vehicle),
												function(res) {
													var response = JSON
															.parse(JSON
																	.stringify(res));
													if (response.id != null) {
														$scope.showModal = false;
														if ($scope.save.vehicle.id != null) {
															angular
																	.forEach(
																			$scope.vehicles,
																			function(
																					vehicle,
																					key) {
																				if (vehicle.id == $scope.save.vehicle.id) {
																					$scope.vehicles[key] = response;

																				}

																			});
															$location
																	.path("/vehicle");
															showmessage(
																	"Success!",
																	"Vehicle update successfully",
																	"success");
														} else {
															$location
																	.path("/vehicle");
															showmessage(
																	"Success!",
																	"Vehicle save successfully",
																	"success");
														}

													} else {
														showmessage(
																"Error!",
																"Vehicle already exists",
																"error");
													}
												});
							},
							$scope.range = function(n) {
								return new Array(n);
							},
							$scope.getVehicleINRange = function(range) {
								$rootScope.pageNumber = range;
								vehicleListService.getList({}, {
									page : range,
									sortCriteria : $scope.sortCriteria
								}, function(response) {
									$scope.vehicles = response.content;
									$rootScope.pages = response.totalPages;
								});
							},
							$scope
									.$watch(
											'searchVehicle',
											function(newValue, oldValue) {
												if (String(newValue).length > 1
														&& newValue) {
													getVehiclesBySerach
															.getList(
																	{},
																	{
																		searchterm : newValue
																	},
																	function(
																			response) {
																		$rootScope.pages = response.length;
																		$scope.vehicles = response;
																	});
												} else if (String(newValue).length <= 1
														&& newValue) {
													$scope.vehicles = $rootScope.responseList.content;
													$rootScope.pages = $rootScope.responseList.totalPages;
												}
											});

					$scope.close = function() {
						$location.path("/vehicle");
					};

					$scope.previous = function() {
						if ($rootScope.pageNumber > 1) {
							$rootScope.pageNumber = $rootScope.pageNumber - 1;
							$scope.getVehicleINRange($rootScope.pageNumber);
						}
					};
					$scope.next = function() {
						if ($rootScope.pages > $rootScope.pageNumber) {
							$rootScope.pageNumber = $rootScope.pageNumber + 1;
							$scope.getVehicleINRange($rootScope.pageNumber);
						}
					};
					/* Report parsing */
					$scope.translateParametersDesc = function(parameterId,
							$index, reportName) {
						var getStaticPacketType = parseInt($scope.staticReportpacketType[$index]);
						/*
						 * // var getDynamicPacketType =
						 * parseInt($scope.dynamicReportpacketType[$index]); if ( //
						 * (getDynamicPacketType == 4 && String(reportName) ==
						 * "dynamic") ||
						 */
						var getDynamicPacketType = parseInt($scope.dynamicReportpacketType[$index]);
						if ((getDynamicPacketType == 4 && String(reportName) == "dynamic")
								|| (getStaticPacketType == 3 && String(reportName) == "static")) {
							if ($scope.translation != null
									&& $scope.translation[parameterId] != null) {
								return $scope.translation[parameterId];
							} else {
								return parameterId;
							}
						} else {
							if ($scope.testtranslation != null
									&& $scope.testtranslation[parameterId] != null) {

								return $scope.testtranslation[parameterId];
							} else {
								return parameterId;
							}
						}

					}
					$scope.translateUnit = function(parameterId, $index,
							reportName) {
						var getStaticPacketType = parseInt($scope.staticReportpacketType[$index]);
						var getDynamicPacketType = parseInt($scope.dynamicReportpacketType[$index]);
						if ((getDynamicPacketType == 4 && String(reportName) == "dynamic")
								|| (getStaticPacketType == 3 && String(reportName) == "static")) {
							if ($scope.units != null
									&& $scope.units[parameterId] != null
									&& $scope.units[parameterId].length > 0) {
								return " " + $scope.units[parameterId];
							} else {
								return "";
							}
						} else {
							if ($scope.testunits != null
									&& $scope.testunits[parameterId] != null
									&& $scope.testunits[parameterId].length > 0) {

								return " " + $scope.testunits[parameterId];
							} else {
								return "";
							}
						}

					}
					$scope.translateMonitorId = function(report) {
						if ($scope.monitorId[report.split('x')[1].toUpperCase()] != null) {
							return $scope.monitorId[report.split('x')[1].toUpperCase()];
						} else {
							return report;
						}


					}

					$scope.translateTestId = function(report) {
						if ($scope.testId[report.split('x')[1].toUpperCase()] != null) {
							return $scope.testId[report.split('x')[1].toUpperCase()];
						} else {
							return report;
						}


					}

					$scope.getUnitByUsId = function(UsId){
						if ($scope.UsIdunits[UsId.split('x')[1].toUpperCase()] != null) {
							return $scope.UsIdunits[UsId.split('x')[1].toUpperCase()];
						} else {
							return;
						}
					}

					$scope.getParameterIdfromDB = function(report) {
						if (!angular.isUndefined($scope.descDetails[report])) {
							var desc = $scope.descDetails[report][1];
							return desc;
						} else {
							return report + "  No Id from DB";
						}
					}

					$scope.getParameterDescfromDB = function(report) {
						if (!angular.isUndefined($scope.descDetails[report])) {
							var desc = $scope.descDetails[report][0];
							return desc;
						} else {
							return report+" No Description from DB";
						}
					}
					
					$scope.validateDynamicPacketType6 = function(parameterDescId,rawValue,testCount){
						var scaled = $scope.scaleValue(parameterDescId,rawValue);
						
						if(testCount > 0){
							
						
							if (!angular.isUndefined($scope.descDetails[parameterDescId])) {
							
									if(scaled >= $scope.descDetails[parameterDescId][4] &&
											scaled<= $scope.descDetails[parameterDescId][5]){
										return 'P';
									} else{
										return 'F';
									}
							} else {
								return " No result from DB";
							}
						}else{
							return 'F';
						} 
							
					}
					
					$scope.validateDynamicPacketType4 = function(parameterDescId,scaledValue,testCount){
						
						if(testCount > 0){
							
							if (!angular.isUndefined($scope.minMax[parameterDescId])) {
							
									if(scaledValue >= parseInt($scope.minMax[parameterDescId][0], 10) &&
											scaledValue<= parseInt($scope.minMax[parameterDescId][1], 10)){
										return 'P';
									} else{
										return 'F';
									}
							} else {
								return " No result from DB";
							}
						}else{
							return 'F';
						} 
							
						
					}
					$scope.scaleValue=function(parameterDescId,rawValue){
						if (!angular.isUndefined($scope.descDetails[parameterDescId])) {
							var scaledValue="";
							var formula=$scope.descDetails[parameterDescId][2];

							try{
								var found=formula.search("rawValue")>=0;
								if(found){
									formula.replace("rawValue",rawValue);

									var index=formula.indexOf('+');
									if(index!=-1){
										formula=formula.slice(0,index)+" - 0 + "+formula.slice(index+1,formula.length);
									}
									scaledValue=eval(formula);
									try{
										scaledValue= typeof(scaledValue)==='number' && Math.round(scaledValue)!=scaledValue?scaledValue.toFixed(2):scaledValue;
									}catch (err) {
										 console.log('error scaling rawValue');
									}
								}else{
									throw "Formula not compatible with given format";
								}
							}catch(e){
								scaledValue=rawValue;
							}
							return scaledValue;
						} else {
							return rawValue;
						}
					}
					
					$scope.applyScalingFromDB = function(parameterDescId,rawValue) {
						if (!angular.isUndefined($scope.descDetails[parameterDescId])) {
							var scaledValue="";
							var formula=$scope.descDetails[parameterDescId][2];

							try{
								var found=formula.search("rawValue")>=0;
								if(found){
									formula.replace("rawValue",rawValue);

									var index=formula.indexOf('+');
									if(index!=-1){
										formula=formula.slice(0,index)+" - 0 + "+formula.slice(index+1,formula.length);
									}
									scaledValue=eval(formula);
									try{
										scaledValue= typeof(scaledValue)==='number' && Math.round(scaledValue)!=scaledValue?scaledValue.toFixed(2):scaledValue;
									}catch (err) {
										 console.log('error scaling rawValue');
									}
								}else{
									throw "Formula not compatible with given format";
								}
							}catch(e){
								scaledValue=rawValue;
							}


							var formulaUnit = scaledValue + " "+$scope.descDetails[parameterDescId][3];
							return formulaUnit;
						} else {
							return rawValue;
						}
					}

					$scope.getCanDescfromDB = function(report) {
						if (!angular.isUndefined($scope.canDetails[report])) {
							var desc = $scope.canDetails[report][1];
							return desc;
						} else {
							return "No desc returned";
						}
					}
					$scope.getCanMessagefromDB = function(report) {
						if (!angular.isUndefined($scope.canDetails[report])) {
							var message = $scope.canDetails[report][0];
							return message;
						} else {
							return "No messageId returned";
						}
					}

					$scope.applyScalingMode1 = function(rawValue, parameterId) {
						if(rawValue =='0'){
							return 0;
						}
						

						return applyScale.scaleForMode1(rawValue, parameterId);

					}

					
					$scope.showMonitorStatus = function(rawValue, parameterId){
						
						$scope.previewModal(rawValue, parameterId);
						
						
					}
					$scope.scalingCanStatic = function(rawValue, param, scaledValue) {
						if($scope.getCanDescfromDB(param) == 'Odometer') {
							// each manufacturer uses different methods to calculate milage
							if ( $scope.vehicleMake == 'GMC' ||
										$scope.vehicleMake == 'Chevrolet' ||
										$scope.vehicleMake == 'Buick' ||
										$scope.vehicleMake == 'Cadillac' ||
										$scope.vehicleMake == 'GM' ) {
								//GM reports odometer in 1/64 of a Kilometers.
								return ((rawValue*0.015625)*0.62137).toFixed(1);
							} else if ($scope.vehicleMake == 'Hyundai' ||
										$scope.vehicleMake == 'Kia' ||
										$scope.vehicleMake == 'Chrysler' ||
										$scope.vehicleMake == 'Dodge' ||
										$scope.vehicleMake == 'Plymouth' ||
										$scope.vehicleMake == 'Jeep' ) {
								// Hyundai/Kia uses a different scale
								return (rawValue*0.062137).toFixed(1);
							} else if ($scope.vehicleMake == 'Honda' ||
										$scope.vehicleMake == 'Acura') {
								// Hyundai/Kia uses a different scale
								return (rawValue*0.621371192237334).toFixed(1);
							} else {
								return (rawValue*0.62137).toFixed(1);
							}
						}
						return rawValue;
					}

					$scope.changeToHex = function(report) {

						return parseInt(report, 10).toString(16).toUpperCase();
					}

					$scope.applyScaling = function(rawValue, parameterId) {

						if(rawValue =='0'){
							return 0;
						}
						switch (parameterId) {
						case '5':
							return rawValue - 40;
							break;
						case '10':
							return rawValue * 3;
							break;
						case '12':
							return rawValue / 4;
							break;
						case '15':
							return rawValue - 40;
							break;
						case '31':
							return 'Formula :(A*256)+B //A & B values ?';
							break;
						case '66':
							return rawValue / 1000;
							break;
						case '67':
							return rawValue * 100 / 255;
							break;
						case '71':
							return rawValue * 100 / 255;
							break;
						case '72':
							return rawValue * 100 / 255;
							break;
						case '70':
							return rawValue - 40;
							break;
						default:
							return rawValue;
						}

					}

					var ModalDialogController=function($scope,$modalInstance,data){
						
						
						var rawValue = data[0];
						var rawValueSize = rawValue.toString().split('').length;
						switch(rawValueSize)
						{
						case 1:
							 rawValue = '000'+rawValue.toString()
							break;
						case 2:
							 rawValue = '00'+rawValue.toString()
							break;
						case 3:
							 rawValue = '0'+rawValue.toString()
							break;
						}

//						console.log("rawValueSize: "+rawValue)

						var A = rawValue & 0xff;
						var B = (rawValue >> 8) & 0xff;
						var C = (rawValue >> 16) & 0xff;
						var D = (rawValue >> 24)  & 0xff;
						
						
						$scope.A=A;
						$scope.B=B;
						$scope.C=C;
						$scope.D=D;
						
						$scope.close=function(){
							$modalInstance.close();
						}
						
						
						
					};
					$scope.previewModal = function(rawValue, parameterId) {

									var data = [rawValue, parameterId] ;
					                                var modalInstance =  $modal.open({
					                          	templateUrl: 'views/modalPid01.html',
					                                    controller: ModalDialogController,
										resolve:   {data : function() {
                                              							      return data;
                                            							    }	
										}
					                                   });
					                            }
					
				});
