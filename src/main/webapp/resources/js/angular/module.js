var app = angular.module('app', [ 'ngRoute', 'ngResource' ]);
app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/new/user', {
		controller : 'AdminCtrl',
		templateUrl : 'views/signup.html'
	}).when('/login', {
		controller : 'AdminCtrl',
		templateUrl : 'views/login.html'
	}).otherwise({
		redirectTo : '/login'
	});
});
var dashboard = angular
		.module(
				'dashboard',
				[ 'ngRoute', 'ngResource', 'ngMaterial', 'ng-breadcrumbs',
						'ngCookies' ]).constant("IMPORT_OPTION", [ {
			'value' : '0',
			'option' : 'Global Parameters'
		} ]);
dashboard.config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/', {
		controller : "DashboardCtrl",
		templateUrl : 'views/dashboard.html',
		label : 'Dashboard'
	}).when('/user', {
		controller : 'UserCtrl',
		templateUrl : 'views/user.html',
		label : 'User',
		resolve : {
			ctrlOptions : function() {
				return {
					showusers : true,
				};
			}
		}
	}).when('/account', {
		controller : 'AccountCtrl',
		templateUrl : 'views/account.html',
		label : 'Account',
		resolve : {
			ctrlOptions : function() {
				return {
					showaccounts : true,
				};
			}
		}
	}).when('/device', {
		controller : 'DeviceCtrl',
		templateUrl : 'views/device.html',
		label : 'Device',
		resolve : {
			ctrlOptions : function() {
				return {
					showdevices : true,
				};
			}
		}
	}).when('/device/add', {
		controller : 'DeviceCtrl',
		templateUrl : 'views/addDevice.html',
		label : 'Add',
		resolve : {
			ctrlOptions : function() {
				return {
					showdevices : false,
				};
			}
		}
	}).when('/controller/parameter/add', {
		controller : 'ParameterCtrl',
		templateUrl : 'views/addParameter.html',
		label : 'Add',
		resolve : {
			ctrlOptions : function() {
				return {
					showparameters : false,
				};
			}
		}
	}).when('/controller', {
		controller : 'EcuControllerCtrl',
		templateUrl : 'views/ecuController.html',
		label : 'EcuController',
		resolve : {
			ctrlOptions : function() {
				return {
					showcontrollers : true,
				};
			}
		}
	}).when('/controller/add', {
		controller : 'EcuControllerCtrl',
		templateUrl : 'views/addEcuController.html',
		label : 'Add',
		resolve : {
			ctrlOptions : function() {
				return {
					showcontrollers : false,
				};
			}
		}
	}).when('/controller/parameter', {
		controller : 'ParameterCtrl',
		templateUrl : 'views/parameter.html',
		label : 'Parameters',
		resolve : {
			ctrlOptions : function() {
				return {
					showparameters : true,
				};
			}
		}
	}).when('/vehicle', {
		controller : 'VehicleCtrl',
		templateUrl : 'views/vehicle.html',
		label : 'Vehicle',
		resolve : {
			ctrlOptions : function() {
				return {
					showvehicles : true,
				};
			}
		}
	}).when('/vehicle/reportinfo/vehiclereport', {
		controller : 'VehicleCtrl',
		templateUrl : 'views/vehiclereport.html',
		label : 'ReportInfo',
		resolve : {
			ctrlOptions : function() {
				return {
					showvehicles : false,
				};
			}
		}
	}).when('/vehicle/add', {
		controller : 'VehicleCtrl',
		templateUrl : 'views/addVehicle.html',
		label : 'Add',
		resolve : {
			ctrlOptions : function() {
				return {
					showvehicles : false,
				};
			}
		}
	})

	.when('/vehicle/reportinfo', {
		controller : 'VehicleCtrl',
		templateUrl : 'views/vehiclereportinfo.html',
		label : 'Reports',
		resolve : {
			ctrlOptions : function() {
				return {
					showvehicles : false,
				};
			}
		}
	})

	.when('/account/add', {
		controller : 'AccountCtrl',
		templateUrl : 'views/addAccount.html',
		label : 'Add',
		resolve : {
			ctrlOptions : function() {
				return {
					showaccounts : false,
				};
			}
		}

	}).when('/user/add', {
		controller : 'UserCtrl',
		templateUrl : 'views/addUser.html',
		label : 'Add',
		resolve : {
			ctrlOptions : function() {
				return {
					showusers : false,
				};
			}
		}
	}).when('/useraccount', {
		controller : 'UserAccountCtrl',
		templateUrl : 'views/accountsetting.html',
		label : 'UserAccount',
		resolve : {
			ctrlOptions : function() {
				return {
					useraccount : true,
				};
			}
		}
	})

	.when('/import', {
		controller : 'ImportCtrl',
		templateUrl : 'views/import.html',
		label : 'UserAccount',
		resolve : {
			ctrlOptions : function() {
				return {
					useraccount : true,
				};
			}
		}
	})

	.when('/useraccount/changepassword', {
		controller : 'UserAccountCtrl',
		templateUrl : 'views/changePassword.html',
		label : 'Change Password',
		resolve : {
			ctrlOptions : function() {
				return {
					useraccount : false,
				};
			}
		}
	}).otherwise({
		redirectTo : '/'
	});
});
dashboard.run(function($rootScope) {

	var data = JSON.parse($("#permissionValue").val());
	var permissions = [];
	var permissionurl = [];
	for ( var i in data) {
		permissions.push(data[i]);
	}
	for ( var i in permissions) {
		permissionurl.push(permissions[i].toLowerCase());
	}
	$rootScope.accountID = "";
	$rootScope.parentAccount = "";
	$rootScope.permissions = permissions;
	$rootScope.permissionurl = permissionurl;
});

dashboard
		.directive(
				'modal',
				function() {
					return {
						template : '<div class="modal fade">'
								+ '<div class="modal-dialog">'
								+ '<div class="modal-content">'
								+ '<div class="modal-header">'
								+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
								+ '<h4 class="modal-title">{{ title }}</h4>'
								+ '</div>'
								+ '<div class="modal-body" ng-transclude></div>'
								+ '</div>' + '</div>' + '</div>',
						restrict : 'E',
						transclude : true,
						replace : true,
						scope : true,
						link : function postLink(scope, element, attrs) {
							scope.title = attrs.title;

							scope.$watch(attrs.visible, function(value) {
								if (value == true) {
									$(element).modal('show');
								} else {
									$(element).modal('hide');
								}
							});

							$(element).on('shown.bs.modal', function() {
								scope.$apply(function() {
									scope.$parent[attrs.visible] = true;
								});
							});

							$(element).on('hidden.bs.modal', function() {
								scope.$apply(function() {
									scope.$parent[attrs.visible] = false;
								});
							});
						}
					};
				});
dashboard.directive('hboTabs', function() {
	return {
		restrict : 'A',
		link : function(scope, elm, attrs) {
			console.log(scope + " " + elm + " " + attrs);
			var jqueryElm = $(elm[0]);
			$(jqueryElm).tabs()
		}
	};
})