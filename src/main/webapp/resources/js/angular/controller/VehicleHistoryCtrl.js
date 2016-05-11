dashboard.controller('VehicleHistoryCtrl', function($scope, $location,
        vehiclesHistoryListService, getVehiclesHistoryBySerach, ctrlOptions,
        $rootScope, $routeParams, breadcrumbs) {
  $scope.showModal = false;
  $rootScope.pageNumber = 1;
  $scope.breadcrumbs = breadcrumbs;
  $scope.save = {};

  if (ctrlOptions.showvehicleshistory) {
    // $scope.buttonName = "Save";
    vehiclesHistoryListService.getList({}, {
      page: 1
    }, function(response) {
      $scope.vehicleshistories = response.content;
      $rootScope.responseList = response;
      $rootScope.pages = response.totalPages;
    });
  }

  $scope.range = function(n) {
    return new Array(n);
  }, $scope.getVehicleHistoryINRange = function(range) {
    $rootScope.pageNumber = range;
    vehicleHistoryListService.getList({}, {
      page: range
    }, function(response) {
      $scope.vehicleshistories = response.content;
      $rootScope.pages = response.totalPages;
    });
  }, $scope.$watch('searchVehicleHistory', function(newValue, oldValue) {
    if (String(newValue).length > 1 && newValue) {
      getVehicleshistoryBySerach.getList({}, {
        searchterm: newValue
      }, function(response) {
        $rootScope.pages = response.length;
        $scope.vehicleshistories = response;
      });
    } else if (String(newValue).length <= 1 && newValue) {
      $scope.vehicleshistories = $rootScope.responseList.content;
      $rootScope.pages = $rootScope.responseList.totalPages;
    }
  });

  $scope.close = function() {
    $location.path("/vehicleHistory");
  };
  $scope.previous = function() {
    if ($rootScope.pageNumber > 1) {
      $rootScope.pageNumber = $rootScope.pageNumber - 1;
      $scope.getVehicleHistoryINRange($rootScope.pageNumber);
    }
  };
  $scope.next = function() {
    if ($rootScope.pages > $rootScope.pageNumber) {
      $rootScope.pageNumber = $rootScope.pageNumber + 1;
      $scope.getVehicleHistoryINRange($rootScope.pageNumber);
    }
  };
});