/**
 * 
 */
dashboard.controller('UserCtrl', function($scope, $location, $http, $rootScope,
		userservice, userListService, getUserService, userRoleService,
		getUsersBySerach, findaccountname, $routeParams, ctrlOptions,
		breadcrumbs) {
	$scope.showModal = false;
	$scope.breadcrumbs = breadcrumbs;
	$rootScope.pageNumber = 1;
	$scope.save = {};
	$scope.accounts = {};
	$scope.user = {};
	$scope.spinnerToggle=true;
	if ($rootScope.userDetail != null) {
		$scope.buttonName = "Edit";
		$scope.useraccount = true;
		$scope.save.user = $rootScope.userDetail;
	} else {
		$scope.buttonName = "Save";
	}
	if (ctrlOptions.showusers) {
		loading("Fetching Users...");
		$scope.buttonName = "Save";
		userListService.getList({}, {
			page : 0
		}, function(response) {
			$rootScope.pages = response.totalPages;
			$rootScope.responseList = response;
			$scope.users = response.content;
			$scope.spinnerToggle=false;
		});
	}
	if (ctrlOptions.edituser) {

		$scope.buttonName = "Edit";
		$scope.showModal = !$scope.showModal;

	}
	userRoleService.getList(function(response) {
		$rootScope.roles = JSON.parse(JSON.stringify(response));
	}), $scope.addUser = function() {
		$rootScope.userDetail = null;
		$scope.save = {};
		$scope.useraccount = false;
		$location.path("/user/add");

	}
	$scope.edituser = function(obj) {

		$rootScope.userDetail = obj; 
		$location.path("/user/add");
		$scope.buttonName = "Edit";
	},
	// User Save
	$scope.register = function() {
		userservice.save(angular.toJson($scope.save.user), function(response) {
			if (response.id) {
				if ($scope.save.user.id != null) {
					angular.forEach($scope.users, function(user, key) {
						if (user.id == $scope.save.user.id) {
							$scope.users[key] = response;
						}
					});
					$location.path("/user");
					showmessage("Success!", "User update successfully",
							"success");
				} else {
					// $scope.users.push(response);
					showmessage("Success!", "User create Successfully ",
							"success");
					$location.path("/user");

				}

			} else {
				showmessage("Error!",
						"User not create !! Please check all credentials",
						"error");
			}
		});
	}, findaccountname.getAccountName(function(response) {
		$scope.accounts = response;

	}), $scope.range = function(n) {
		return new Array(n);
	}, $scope.getUserINRange = function(range) {
		$rootScope.pageNumber=range;
		userListService.getList({}, {
			page : range
		}, function(response) {
			$scope.users = response.content;
			$rootScope.pages = response.totalPages;
		});
	}, $scope.$watch('searchUser', function(newValue, oldValue) {
		if (String(newValue).length > 1 && newValue) {
			getUsersBySerach.getList({}, {
				searchterm : newValue
			}, function(response) {
				$rootScope.pages = response.length;
				$scope.users = response;
			});
		} else if (String(newValue).length <= 1 && newValue) {
			$scope.users = $rootScope.responseList.content;
			$rootScope.pages = $rootScope.responseList.totalPages;
		}
	});

	$scope.close = function() {
		$location.path("/user");
	};
	$scope.previous = function() {
		if ($rootScope.pageNumber > 1) {
			$rootScope.pageNumber = $rootScope.pageNumber - 1;
			$scope.getUserINRange($rootScope.pageNumber);
		}
	};
	$scope.next = function() {
		if ($rootScope.responseList.totalPages > $rootScope.pageNumber) {
			$rootScope.pageNumber = $rootScope.pageNumber + 1;
			$scope.getUserINRange($rootScope.pageNumber);
		}
	};
});
