/**
 * 
 */
dashboard.controller('UserAccountCtrl', function($scope, $location,
        ctrlOptions, useraccountservice, sendlinktochangecredential,
        changepassword) {
  $scope.user = {};
  if (ctrlOptions.useraccount) {
    useraccountservice.getUserAccount({}, function(response) {
      $scope.user = JSON.parse(JSON.stringify(response));
    });
  }
  $scope.sendCode = function() {
    sendlinktochangecredential.sendCode({}, function(response) {
      $location.path("/useraccount/changepassword");
      showmessage("", "Confirmation Code send your register Email Id",
              "success");
    });
  }
  $scope.changePassword = function() {
    if (angular.equals($scope.user.password, $scope.user.cnfPassword)) {
      changepassword.changepassword(JSON.stringify($scope.user), function(
              response) {

        if (JSON.parse(JSON.stringify(response)).id) {
          $location.path("/useraccount");
          showmessage("Success!", "Password successfully Update ", "success");

        } else {
          $location.path("/useraccount");
          showmessage("Error!", "Password not update ", "error");

        }
      });
    } else {
      showmessage("Error!", "Password Error ", "error");
    }
  }

});
