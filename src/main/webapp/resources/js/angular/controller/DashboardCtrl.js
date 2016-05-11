dashboard.controller('DashboardCtrl', function($scope, $location, $rootScope,
		$window, getChildAccounts, accountsession, UserLog) {
	$rootScope.accountID = "";
	$rootScope.pageNumber = 1;
	$scope.spinnerToggle = true;
	loading("Fetching User Logs...");
	UserLog.get({}, {
		page : 1
	}, function(response) {
		document.cookie = "JSESSION=" + readCookie("JSESSIONID")
				+ "; expires=Fri, 31 Dec 9999 23:59:59 GMT";
		$scope.userlogs = response.content;
		$rootScope.responseList = response;
		$rootScope.pages = response.totalPages;
		$scope.spinnerToggle = false;

	});

	if ($window.sessionStorage.parentAccount) {
		$rootScope.parentAccount = $window.sessionStorage.parentAccount;
	}
	getChildAccounts.get({}, function(response) {
		$rootScope.parentaccounts = response;
	});
	$scope.accountinScope = function(getaccountid) {
		angular.forEach($scope.parentaccounts, function(account, key) {
			if (String(getaccountid) == String(account.id)) {
				$window.sessionStorage.parentAccount = getaccountid;
				$location.path("/");
				accountsession.setaccountsession({}, {
					accountID : $window.sessionStorage.parentAccount
				}, function(response) {
				});

			}

		});
	};
	$scope.checkPermission = function(permissionName) {
		var data = JSON.parse($("#permissionValue").val());
		var permissions = [];
		var permissionurl = [];
		for ( var i in data) {
			permissions.push(data[i]);
		}
		return $.inArray(permissionName, permissions);
	};
	$scope.range = function(n) {
		return new Array(n);
	};
	$scope.getUserLogINRange = function(range) {
		$rootScope.pageNumber = range;
		UserLog.get({}, {
			page : range
		}, function(response) {
			$scope.userlogs = response.content;
		});
	};
	$scope.close = function() {
		$location.path("/useraccount");
	};
	$scope.previous = function() {
		if ($rootScope.pageNumber > 1) {
			$rootScope.pageNumber = $rootScope.pageNumber - 1;
			$scope.getUserLogINRange($rootScope.pageNumber);
		}
	};
	$scope.next = function() {
		if ($rootScope.pages > $rootScope.pageNumber) {
			$rootScope.pageNumber = $rootScope.pageNumber + 1;
			$scope.getUserLogINRange($rootScope.pageNumber);
		}
	};

});
/*
 * Check after every 2 seconds user login
 */
var interval = window
		.setInterval(
				function() {
					var actual = String(readCookie("JSESSION"));
					var expected = String(readCookie("JSESSIONID"));
					if (actual != expected) {
						bootbox
								.alert("<a href='javascript:void(0)'>Your Session has been Expired! <br>Please Login to continue</br></a>");
						clearInterval(interval);
					}
				}, 5000);
