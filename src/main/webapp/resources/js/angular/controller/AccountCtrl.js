dashboard.controller('AccountCtrl', function($scope, $location, $rootScope,
        accountservice, accountListService, getAccountService, breadcrumbs,
        getAccountsBySerach, ctrlOptions, $routeParams, $window) {
  $scope.showModal = false;
  $rootScope.pageNumber = 1;
  $scope.breadcrumbs = breadcrumbs;
  $scope.save = {};
  $scope.spinnerToggle=true;
  if ($rootScope.accountDetail != null) {
    $scope.buttonName = "Edit";
    $scope.save.account = $rootScope.accountDetail;
  } else {
    $scope.buttonName = "Add";
  }
  if (ctrlOptions.showaccounts) {
    loading("Fetching Accounts...");
    $scope.buttonName = "Save";
    accountListService.getList({}, {
      page: 1
    }, function(response) {

      $scope.accounts = response.content;
      $rootScope.responseList = response;
      $rootScope.pages = response.totalPages;
      $scope.spinnerToggle=false;
    }, function(error) {
      if (error.status === 404 || error.status === 401) {
        $window.location.reload();
      }
    });
  }
  $scope.addAccount = function() {
    $scope.save = {};
    $rootScope.accountDetail = null;
    $location.path("/account/add");
  }, $scope.editaccount = function(obj) {
    $rootScope.accountDetail = obj;
    $location.path("/account/add");
    $scope.buttonName = "Edit";

  }, $scope.range = function(n) {
    return new Array(n);
  };
  $scope.register = function() {

    accountservice.save(angular.toJson($scope.save.account),
            function(response) {
              if (response.id != "") {
                $scope.showModal = false;

                if ($scope.save.account.id != null) {

                  angular.forEach($scope.accounts, function(account, key) {
                    if (account.id == $scope.save.account.id) {
                      $scope.accounts[key] = response;

                    }
                  });
                  $location.path("/account");
                  showmessage("Success!", "Account update successfully",
                          "success");
                } else {
                  $rootScope.parentaccounts.push(response);
                  $location.path("/account");
                  showmessage("Success!", "Account create successfully",
                          "success");
                }

              } else {
                showmessage("Error!", "Account not create", "error");
              }
            });

  }, $scope.getAccountINRange = function(range) {
    $rootScope.pageNumber = range;
    accountListService.getList({}, {
      page: range
    }, function(response) {
      $scope.accounts = response.content;
      $rootScope.pages = response.totalPages;
    });
  }, $scope.accountinScope = function(getaccount) {
    angular.forEach($scope.parentaccounts, function(account, key) {
      if (String(getaccount).trim() == String(account.accountName).trim()) {
        $rootScope.accountID = account.id;
      }

    });
    angular.forEach($scope.accounts, function(accounts, key) {

    });

  }, $scope.$watch('searchAccount', function(newValue, oldValue) {
    if (String(newValue).length > 1 && newValue) {
      getAccountsBySerach.getList({}, {
        searchterm: newValue
      }, function(response) {
        $rootScope.pages = response.length;
        $scope.accounts = response;
      });
    } else if (String(newValue).length <= 1 && newValue) {
      $scope.accounts = $rootScope.responseList.content;
      $rootScope.pages = $rootScope.responseList.totalPages;
    }
  });

  $scope.close = function() {
    $location.path("/account");
  };
  $scope.previous = function() {
    if ($rootScope.pageNumber > 1) {
      $rootScope.pageNumber = $rootScope.pageNumber - 1;
      $scope.getAccountINRange($rootScope.pageNumber);
    }
  };
  $scope.next = function() {
    if ($rootScope.pages > $rootScope.pageNumber) {
      $rootScope.pageNumber = $rootScope.pageNumber + 1;
      $scope.getAccountINRange($rootScope.pageNumber);
    }
  };
});