/**
 * 
 */
app.controller('AdminCtrl', [
		'$scope',
		'$http',
		'$location',
		'$routeParams',
		'userservice',
		function($scope, $http, $location, routeParams, userservice) {

			if (String($location.absUrl()).indexOf("error") != -1) {
				$scope.error = true;
				$(".input").addClass("error form-group has-error");

			}
			$scope.register = function() {

				if ($scope.form.$valid) {
					if (!angular.equals($scope.user.password,
							$scope.user.cnfPassword)) {
						$scope.passwordmatch = true;
						$scope.registerd = true;
					} else {
						console.log($scope.user);
						userservice.save(JSON.stringify($scope.user), function(
								response) {
							if (JSON.parse(JSON.stringify(response)).id) {
								showmessage("Success!",
										"Register Successfully Activation link send to "
												+ $scope.user.emailId,
										"success");
								$scope.user = {};
								$scope.registerd = true;
								$location.path("/");
							} else {
								$scope.registerd = true;
								showmessage("Error!", "User already exists",
										"error");
							}

						});
					}
				} else {
					$scope.error = true;
					return;
				}

			}

			$scope.close = function() {
				$location.path("/useraccount");
			}
		}

]);
