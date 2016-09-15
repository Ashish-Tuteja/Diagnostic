dashboard.controller('SimCtrl', function($scope, $location, $rootScope,
		  $routeParams, $window, breadcrumbs, $route,
		$cookieStore,simService,simClientService) {
	$scope.simQuery={
		msisdn: '',
		sim: ''
	};
	$scope.networkResult='';
	$scope.simResult='';
	$scope.queryHLRResult='';
	$scope.breadcrumbs = breadcrumbs;

	$scope.inquireSim=function(){
		//setting this empty so that this isn't available for changes
		$scope.spinnerToggle=true;
		$scope.networkResult='';
		$scope.queryHLRResult='';
		if($scope.simQuery.msisdn=='' && $scope.simQuery.sim=='' || $scope.simQuery.msisdn==undefined && $scope.simQuery.sim==undefined){
			showmessage("Error!"," Please insert SIM or MSISDN! ","error");
			$scope.spinnerToggle=false;
		}else{
			simClientService.inquireSim({msisdn: $scope.simQuery.msisdn,sim: $scope.simQuery.sim},function(response){
				$scope.simResult=response.singleSIMInquiryResult;
				$scope.spinnerToggle=false;
			});
		}
	}
	
	$scope.queryHLR=function(){
		$scope.spinnerToggle=true;
		$scope.networkResult='';
		if($scope.simQuery.msisdn=='' && $scope.simQuery.sim=='' || $scope.simQuery.msisdn==undefined && $scope.simQuery.sim==undefined){
			$scope.spinnerToggle=false;
			showmessage("Error!"," Please insert SIM or MSISDN! ","error");
		}else{
			simClientService.queryHLR({msisdn: $scope.simQuery.msisdn,sim: $scope.simQuery.sim},function(response){
				//how to show values of this
				$scope.queryHLRResult=response.singleQueryHLRResult;
				if($scope.queryHLRResult!='' || $scope.queryHLRResult!=undefined){
					$scope.convertQueryHlrToSimInquiryResult($scope.queryHLRResult);
				}
				$scope.spinnerToggle=false;				
			});
		}
	}
	
	$scope.getNetworkStatus=function(){
		$scope.spinnerToggle=true;
		$scope.queryHLRResult='';
		//NONE with SIM value
			simClientService.getNetworkStatus({msisdn: $scope.simQuery.msisdn,sim: $scope.simQuery.sim},function(response){
				$scope.networkResult=response.getNetworkConnectionOnlineStatusResult;
				$scope.spinnerToggle=false;
				
			});
	}

	$scope.cancelLocation=function(){
		$scope.queryHLRResult='';
		$scope.spinnerToggle=true;
		if($scope.simQuery.msisdn=='' && $scope.simQuery.sim=='' || $scope.simQuery.msisdn==undefined && $scope.simQuery.sim==undefined){
			$scope.spinnerToggle=false;
			showmessage("Error!"," Please insert SIM or MSISDN! ","error");
		}else{
			simClientService.cancelLocation({msisdn: $scope.simQuery.msisdn,sim: $scope.simQuery.sim},function(response){
			//how to show response of this
			$scope.cancelLocationResult=response.singleCancelDeviceLocationResult;
				if($scope.cancelLocationResult.result=='Failed'||$scope.cancelLocationResult.result=='Error'){
					showmessage("Error!",$scope.resumeSimResult.description,"error");
				}
				$scope.spinnerToggle=false;
				
			});
		}
	}

	$scope.activateSim=function(){
		$scope.queryHLRResult='';
		if($scope.simQuery.sim=='' || $scope.simQuery.sim==undefined){
			showmessage("Error!"," SIM missing! ","error");
			$scope.spinnerToggle=false;
		}else{
			var result=confirm("Do you really want to Activate SIM ?");
				if(result){
					$scope.spinnerToggle=true;
					simClientService.activateSim({msisdn: $scope.simQuery.msisdn,sim: $scope.simQuery.sim},function(response){
						//how to show response of this
						$scope.activateSimResult=response.singleActivateSIMResult;				
						if($scope.activateSimResult.result=='Failed'||$scope.activateSimResult.result=='Error'){
							showmessage("Error!",$scope.activateSimResult.description,"error");
						}
						$scope.spinnerToggle=false;
					});
				}
		}
	}

	$scope.suspendSim=function(){
		$scope.queryHLRResult='';
		if($scope.simQuery.msisdn=='' && $scope.simQuery.sim=='' || $scope.simQuery.msisdn==undefined && $scope.simQuery.sim==undefined){
			showmessage("Error!"," Please insert SIM or MSISDN! ","error");
		}else{
			var result=confirm("Do you really want to suspend SIM ?");
			if(result){
			$scope.spinnerToggle=true;
			simClientService.suspendSim({msisdn: $scope.simQuery.msisdn,sim: $scope.simQuery.sim},function(response){
				//how to show response of this
				$scope.suspendSimResult=response.singleSuspendSIMResult;
				if($scope.suspendSimResult.result=='Failed'||$scope.suspendSimResult.result=='Error'){
					showmessage("Error!",$scope.suspendSimResult.description,"error");
				}
				$scope.spinnerToggle=false;
				
			});
			}
		}
	}


	$scope.resumeSim=function(){
		$scope.queryHLRResult='';
		if($scope.simQuery.msisdn=='' && $scope.simQuery.sim=='' || $scope.simQuery.msisdn==undefined && $scope.simQuery.sim==undefined){
			showmessage("Error!"," Please insert SIM or MSISDN! ","error");
			$scope.spinnerToggle=false;
			
		}else{
			var result=confirm("Do you really want to Resume SIM ?");
			if(result){
				$scope.spinnerToggle=true;
				simClientService.resumeSim({msisdn: $scope.simQuery.msisdn,sim: $scope.simQuery.sim},function(response){
					//how to show response of this
					$scope.resumeSimResult=response.singleRestoreSIMResult;
					if($scope.resumeSimResult.result=='Failed'||$scope.resumeSimResult.result=='Error'){
						showmessage("Error!",$scope.resumeSimResult.description,"error");
					}
					$scope.spinnerToggle=false;
					
				});
			}
		}
	}
	$scope.findSim=function(lookUpId){
		$scope.queryHLRResult='';
		$scope.networkResult='';
		$scope.spinnerToggle=true;
		
		if(lookUpId == '' || lookUpId == undefined){
			showmessage("Error!","Empty lookup field","error");
			$scope.spinnerToggle=false;
			
		}else{
			simService.getByIdOrMsisdnOrsimNo({id:lookUpId},function(response){
				if(response!=null){
					$scope.simQuery.msisdn=response.msisdn;
					$scope.simQuery.sim=response.sim;
					if($scope.simQuery.msisdn=='' && $scope.simQuery.sim=='' || $scope.simQuery.msisdn==undefined && $scope.simQuery.sim==undefined){
						showmessage("Error!"," No device Found! ","error");
						$scope.simResult='';
						$scope.spinnerToggle=false;
					}else{
						simClientService.inquireSim({msisdn: $scope.simQuery.msisdn,sim: $scope.simQuery.sim},function(response){
							$scope.simResult=response.singleSIMInquiryResult;
							$scope.spinnerToggle=false;
								
						});
					}
				}
			});
		}
	
	}

	$scope.convertQueryHlrToSimInquiryResult=function(queryHlr){
		$scope.simResult={
			'simno' : queryHlr.simno,
			'msisdn' : queryHlr.msisdn,
			'code' : queryHlr.code,
			'description' : queryHlr.description,
		 	'ipaddress' : queryHlr.ipaddress,
			'status' : queryHlr.status,
			'pricePlanId' : queryHlr.pricePlanId,
			'result' : queryHlr.result,
			'suspendMRC' : queryHlr.suspendMRC,
			'transactionId' : queryHlr.transactionId,
			'time' : queryHlr.time
		}
	}
				
});