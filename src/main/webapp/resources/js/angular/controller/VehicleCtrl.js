dashboard
		.controller(
				'VehicleCtrl',
				function($scope, $location, vehicleservice, vehicleListService,
						getVehicleService, vehicleDeviceListService,
						getVehiclesBySerach, getReportByGroupId,
						getReportsbyID, getReportsbyIP,
						translationServiceDynamicStaticTest,
						translationServiceDynamicStatic, ctrlOptions,
						$rootScope, $routeParams, breadcrumbs, $location,
						$cookieStore, $http) {
					$scope.dynamicReports = [];
					$scope.dynamicReportsTimestamp = [];
					$scope.dynamicReportpacketType = [];
					$scope.staticReports = [];
					$scope.staticReportsTimestamp = [];
					$scope.staticReportpacketType = [];
					$scope.troubleReports = [];
					$scope.troubleReportsTimestamp = [];
					$scope.systemReports = [];
					$scope.systemReportsTimestamp = [];
					$scope.spinnerToggle = true;
					$scope.showModal = false;
					$rootScope.pageNumber = 1;
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
					if (String($location.path()).indexOf("/vehicle/reportinfo") != -1) {
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
								page : 1
							}, function(response) {
								$scope.vehicles = response.content;
								$rootScope.responseList = response;
								$rootScope.pages = response.totalPages;
								$scope.spinnerToggle = false;
							});
						}
					}

					// Generate Dynamic Reports
					$scope.setDynamicReports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							var check = system_array[i].split(":");
							if(check[1] != 0 || check[2] != 0 || check[3] != 0|| check[4] != 0 ){
							temp.push(system_array[i].split(":"));
							}
							
						}
						$scope.dynamicReports.push(temp);
						console.log("dynamic reports "+$scope.dynamicReports);

					}
					// Generate Static Reports
					$scope.setStaticReports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							var check = system_array[i].split(":");
							if(check[1] != 0 || check[2] != 0 ){
							temp.push(system_array[i].split(":"));
							}
							
						}
						$scope.staticReports.push(temp);
						console.log("static reports "+$scope.staticReports);
					}

					// Generate Trouble Reports
					$scope.setTroubleReports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							var check = system_array[i].split(":");
							if(check[1] != 0 || check[2] != 0 || check[3] != 0|| check[4] != 0 ){
							temp.push(system_array[i].split(":"));
							}
							
						}
						$scope.troubleReports.push(temp);
					}
					$scope.hi = function(obj) {
						console.log(obj);
					}

					// Generate System Reports
					$scope.setSystemReports = function(system_array) {
						var temp = new Array();
						for (var i = 0; i < system_array.length; i++) {
							var check = system_array[i].split(":");
							if(check[1] != 0 || check[2] != 0 || check[3] != 0|| check[4] != 0 ){
							temp.push(system_array[i].split(":"));
							}
							
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
						$scope.systemReports = [];
						loading("Fetching Reports...");
						translationServiceDynamicStaticTest.success(function(
								data) {
							$scope.testtranslation = data['description'];
							$scope.testunits = data['units'];
						});

						translationServiceDynamicStatic.success(function(data) {
							$scope.translation = data['description'];
							$scope.units = data['units'];
						});
						getReportByGroupId
								.get(
										{
											groupid : $cookieStore
													.get('groupid')
										},
										function(response) {
											console.log(JSON
													.stringify(response));
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
															.setSystemReports(system_array)
												}
											}
											console
													.log($scope.dynamicReportpacketType);
											console
													.log($scope.staticReportpacketType);
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
									page : range
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
						var getDynamicPacketType = parseInt($scope.dynamicReportpacketType[$index]);
						console.log(String(reportName) == "dynamic");
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
								return "(" + $scope.units[parameterId] + ")";
							} else {
								return "";
							}
						} else {
							if ($scope.testunits != null
									&& $scope.testunits[parameterId] != null
									&& $scope.testunits[parameterId].length > 0) {

								return "(" + $scope.testunits[parameterId]
										+ ")";
							} else {
								return "";
							}
						}

					}

				});
