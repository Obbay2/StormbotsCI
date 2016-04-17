var thunderstormCollectionControllers = angular.module('thunderstormCollectionControllers', ['googlechart']);

thunderstormCollectionControllers.controller('User', function ($scope) {
    $scope.username = "kylekyle12";
});

thunderstormCollectionControllers.controller('MyCtrl', function ($scope, $location) {
    $scope.isActive = function (route) {
        return route === $location.path();
    }
});

thunderstormCollectionApp.filter('startFrom', function () {
    return function (input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});

thunderstormCollectionControllers.controller('collectHomeController', function ($scope, $rootScope) {
	$rootScope.startSaving = false;
	
	$rootScope.newRobotId = null;
   
});

thunderstormCollectionControllers.controller('collectTeamController', function ($scope, $http, $rootScope) {
	$rootScope.newRobotId = null;
    $rootScope.startSaving = false;
    $scope.submitTeam = function() {
		$http({method: 'POST', url: '../../rest/team/', params: Math.random().toString(36).substring(2), data: {teamNumber: $scope.teamNumberEnter, teamName: $scope.teamName, school: $scope.schoolName, city: $scope.city, state: $scope.state, leader													: $scope.leaderFullName, yearFounded: $scope.yearFounded, notes: $scope.notes, }, cache: false}).
			  success(function(data, status) {
				if(data) {
					alertm("Team Added Successfully", 5, "success");
					$scope.teamNumberEnter = null;
					$scope.teamName = null;
					$scope.schoolName = null;
					$scope.city = null;
					$scope.state = null;
					$scope.leaderFullName = null;
					$scope.yearFounded = null;
					$scope.notes = null;
					
				} else {
					alertm("Server error. Please retry", 6, "error");
				}
			  }).
			  error(function(data, status) {
				if(status==409) {
					alertm('That team already exists! <br /> Edit it on the <a href="#/teamSearch">search page</a>', 10, "error");
				}else {
					alertm("Error saving data. Please retry", 6, "error");
				}
			  });
	}
   
});

thunderstormCollectionControllers.controller('searchTeamController', function ($scope, $rootScope) {
	$rootScope.newRobotId = null;
	
	$scope.teamOrder = '-teamNumber';
});

thunderstormCollectionControllers.controller('TeamRowCtrl', function ($scope, $http, $rootScope) {
    // Function to get Round data on page load
	$http({method: 'GET', url: '../../rest/team/', params: Math.random().toString(36).substring(2), cache: false}).
	  success(function(data, status) {
		$scope.teams = data;
	  }).
	  error(function(data, status) {
		alertm("Error loading data: "+status);
	  });

    $scope.strict = false;

    $scope.toggleStrict = function () {
        if ($scope.strict === false) {
            $scope.strict = true;
        } else {
            $scope.strict = false;
        }
    }

    // START PAGINATION

    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $scope.numberOfPages = function () {
        return Math.ceil($scope.teams.length / $scope.pageSize);
    }

    $scope.changePage = function (direction) {
        if (direction == "next") {
            if ($scope.currentPage <= $scope.teams.length / $scope.pageSize - 1) {
                $scope.currentPage = $scope.currentPage + 1;
            }
        } else if (direction == "prev") {
            if ($scope.currentPage !== 0) {
                $scope.currentPage = $scope.currentPage - 1;
            }
        }
    }

    $scope.$watch('teamSearch', function () {
        $scope.currentPage = 0;
    });
	
    $scope.$watch('teamSearch.teamNumber', function () {
        $scope.currentPage = 0;
        if ($scope.teamSearch.teamNumber !== "") {
            $scope.numberOfPages = function () {
                return 1;
            }
        } else {
            $scope.numberOfPages = function () {
                return Math.ceil($scope.teams.length / $scope.pageSize);
            }
        }
    });
    $scope.$watch('strict', function () {
        if ($scope.strict === false) {
            $scope.teamSearch.teamNumber = "";
        }
    });
	
	
    // END PAGINATION

	// Check data before 
    $scope.checkName = function (data) {
        if (data.match(/^[0-9]+$/) == null) {
			alertm ("Number should be integer");
            return "Number should be integer";
        }
    };

	// upload/save data
    $scope.saveTeam = function (data, id) {
        angular.extend(data, {
            id: id
        });
        alertm("Team Edited", 5, "info");
		if(data['teamId'] == undefined || data['teamId'] == "") {
        	return $http.post('../../rest/team/', data);
		} else {
			return $http.put('../../rest/team/', data);
		}
    };

    // remove data - NOT USED RIGHT NOW ON THIS PAGE
    $scope.removeTeam = function (index, opposite) {
        if ($scope.teamOrder == 'teamNumber') {
            $scope.teams.splice(opposite, 1);
        } else {
            $scope.teams.splice(index, 1);
        }
        alertm("Data Removed", 5, "success");
    };

    // add data - NOT USED RIGHT NOW ON THIS PAGE
    $scope.addTeam = function () {
		if ($scope.teamOrder == '-teamNumber') {
			$scope.inserted = {
				name: '',
			};
			$scope.teams.push($scope.inserted);
			alertm("Data Added", 5, "success");
		} else {
			alertm("Sort from newest to add data.");
		}
    };
});

thunderstormCollectionControllers.controller('collectRobotController', function ($scope, $http, $rootScope) {
	$rootScope.startSaving = false;
	
	$scope.submitRobot = function() {
		$http({method: 'POST', url: '../../rest/robot/', params: Math.random().toString(36).substring(2), data: {teamNumber: $scope.teamNumberRobotEnter, driveTrain: $scope.driveTrain, driveTrainMotorsNumber: $scope.numDriveTrainMotors, driveTrainMotorsTypes: $scope.typeDriveTrainMotors, otherMotorsNumber: $scope.numOtherMotors, otherMotorsTypes: $scope.typeOtherMotors, weight: $scope.weight, programLanguage: $scope.programmingLanguage, autonomous: $scope.robotEnterAutonomous, notes: $scope.robotEnterNotes}, cache: false}).
			  success(function(data, status) {
				if(data) {
					alertm("Robot Added Successfully", 5, "success");
					$scope.teamNumberRobotEnter = null;
					$scope.driveTrain = null;
					$scope.numDriveTrainMotors = null;
					$scope.typeDriveTrainMotors = null;
					$scope.numOtherMotors = null;
					$scope.typeOtherMotors = null;
					$scope.weight = null;
					$scope.programmingLanguage = null;
					$scope.robotEnterAutonomous = null;
					$scope.robotEnterNotes = null;
					
					$rootScope.newRobotId = data;
					
					window.location = "#/robotDetails";
					
					alertm("Now enter Robot Details", 10, "info");
					
				} else {
					alertm("Server error. Please retry", 6, "error");
				}
			  }).
			  error(function(data, status) {
				alertm("Error saving data. Please retry", 6, "error");
			  });
	}
	
	$scope.updateRobot = function() {
		$http({method: 'PUT', url: '../../rest/robot/', params: Math.random().toString(36).substring(2), data: {id: $scope.newRobotId, driveTrain: $scope.driveTrain, driveTrainMotorsNumber: $scope.numDriveTrainMotors, driveTrainMotorsTypes: $scope.typeDriveTrainMotors, otherMotorsNumber: $scope.numOtherMotors, otherMotorsTypes: $scope.typeOtherMotors, weight: $scope.weight, programLanguage: $scope.programmingLanguage, autonomous: $scope.robotEnterAutonomous, notes: $scope.robotEnterNotes}, cache: false}).
			  success(function(data, status) {
				if(data) {
					alertm("Robot Updated Successfully", 5, "success");
					$scope.teamNumberRobotEnter = null;
					$scope.driveTrain = null;
					$scope.numDriveTrainMotors = null;
					$scope.typeDriveTrainMotors = null;
					$scope.numOtherMotors = null;
					$scope.typeOtherMotors = null;
					$scope.weight = null;
					$scope.programmingLanguage = null;
					$scope.robotEnterAutonomous = null;
					$scope.robotEnterNotes = null;
					
					$rootScope.newRobotId = data;
					
					//window.location = "#/robotDetails";
					
					//alertm("Now enter Robot Details", 10, "info");
					
				} else {
					alertm("Server error. Please retry", 6, "error");
				}
			  }).
			  error(function(data, status) {
				alertm("Error saving data. Please retry", 6, "error");
			  });
	}
	
	$scope.getRobot = function() {
		$http({method: 'GET', url: '../../rest/robot/'+$scope.teamNumberRobotEnter, params: Math.random().toString(36).substring(2), cache: false}).
			  success(function(data, status) {
				if(data) {
					$scope.newRobotId = null;
					$scope.driveTrain = null;
					$scope.numDriveTrainMotors = null;
					$scope.typeDriveTrainMotors = null;
					$scope.numOtherMotors = null;
					$scope.typeOtherMotors = null;
					$scope.weight = null;
					$scope.programmingLanguage = null;
					$scope.robotEnterAutonomous = null;
					$scope.robotEnterNotes = null;
					
					$scope.newRobotId = data[0]["robotId"];
					$scope.teamNumberRobotEnter = data[0]["teamNumber"];
					$scope.driveTrain = data[0]["driveTrain"];
					$scope.numDriveTrainMotors = data[0]["driveTrainMotorsNumber"];
					$scope.typeDriveTrainMotors = data[0]["driveTrainMotorsTypes"];
					$scope.numOtherMotors = data[0]["otherMotorsNumber"];
					$scope.typeOtherMotors = data[0]["otherMotorsTypes"];
					$scope.weight = data[0]["weight"];
					$scope.programmingLanguage = data[0]["programLanguage"];
					$scope.robotEnterAutonomous = data[0]["autonomous"];
					$scope.robotEnterNotes = data[0]["notes"];
					
					
					alertm("Robot Loaded Successfully", 5, "success");
				} else {
					alertm("Server error. Please retry", 6, "error");
				}
			  }).
			  error(function(data, status) {
				alertm("Error getting data. Please retry", 6, "error");
			  });
	}
	
});

thunderstormCollectionControllers.controller('collectRobotDetailsController', function ($scope, $http, $rootScope) {
	$rootScope.startSaving = false;
	
	$scope.submitRobotDetails = function() {
		$http({method: 'POST', url: '../../rest/robotdetails/', params: Math.random().toString(36).substring(2), data: {robotId: $rootScope.newRobotId, autoPickUpTote: $scope.autoPickUpTote, autoPickUpContainer: $scope.autoPickUpContainer, autoCanStackTote: $scope.autoCanStackTote, autoCanStackContainer: $scope.autoCanStackContainer, autoMoveToAutozone: $scope.autoMoveToAutozone, pickUpTote: $scope.pickUpTote, pickUpContainer: $scope.pickUpContainer, pickUpLitter: $scope.pickUpLitter, toteStorage: $scope.toteStorage, containerStorage: $scope.containerStorage, litterStorage: $scope.litterStorage, canChuteTote: $scope.canChuteTote, canChuteLitter: $scope.canChuteLitter, stackTote: $scope.stackTote, canStackContainer: $scope.canStackContainer, canStackLitter: $scope.canStackLitter, stackCoop: $scope.stackCoop, notes: $scope.robotDetailsNotes}, cache: false}).
			  success(function(data, status) {
				if(data) {
					alertm("Robot Details Added Successfully", 5, "success");
					$scope.autoPickUpTote = null;
					$scope.autoPickUpContainer = null;
					$scope.autoCanStackTote = null;
					$scope.autoCanStackContainer = null;
					$scope.autoMoveToAutozone = null;
					$scope.pickUpTote = null;
					$scope.pickUpContainer = null;
					$scope.pickUpLitter = null;
					$scope.toteStorage = null;
					$scope.containerStorage = null;
					$scope.litterStorage = null;
					$scope.canChuteTote = null;
					$scope.canChuteLitter = null;
					$scope.stackTote = null;
					$scope.canStackContainer = null;
					$scope.canStackLitter = null;
					$scope.stackCoop = null;
					
					window.location = "#/teamSearch";
					
					$scope.newRobotId = null;
					
				} else {
					alertm("Server error. Please retry", 6, "error");
				}
			  }).
			  error(function(data, status) {
				alertm("Error saving data. Please retry", 6, "error");
			  });
	}
});

thunderstormCollectionControllers.controller('scoreChooseTeamModal', function ($scope, $http, $rootScope) {
	$scope.firstDigitSelect = 0;
	$scope.secondDigitSelect = 0;
	$scope.thirdDigitSelect = 0;
	
	$scope.teamSelectedStart = null;
	$scope.currentSelectedTeamIdStart = null;
	$scope.currentSelectedTeamNumberStart = null;
	
	$rootScope.teamId = null;
	$rootScope.teamNumber = null;
	$rootScope.roundId = null;
	$rootScope.roundNumber = null;
	
	$rootScope.startSaving = false;
	
	$scope.changeRoundNumStart = function(digit,number) {
		switch(digit) {
		  case 1:
		    $scope.firstDigitSelect = number;
			$scope.updateRoundStart();
		    break;
		  case 2:
		    $scope.secondDigitSelect = number;
			$scope.updateRoundStart();
		    break;
		  case 3:
		    $scope.thirdDigitSelect = number;
			$scope.updateRoundStart();
		    break
		  default:
		    alertm("Error: Invalid digit");
		}
	}
	
	$scope.changeTeamStart = function(team) {
		$scope.teamSelectedStart = team;
		switch(team) {
		  case 'red1':
		    $scope.currentSelectedTeamIdStart = $scope.red1Id;
			$scope.currentSelectedTeamNumberStart = $scope.red1Number;
		    break;
		  case 'red2':
		    $scope.currentSelectedTeamIdStart = $scope.red2Id;
			$scope.currentSelectedTeamNumberStart = $scope.red2Number;
		    break;
		  case 'red3':
		    $scope.currentSelectedTeamIdStart = $scope.red3Id;
			$scope.currentSelectedTeamNumberStart = $scope.red3Number;
		    break
		  case 'blue1':
		    $scope.currentSelectedTeamIdStart = $scope.blue1Id;
			$scope.currentSelectedTeamNumberStart = $scope.blue1Number;
		    break;
		  case 'blue2':
		    $scope.currentSelectedTeamIdStart = $scope.blue2Id;
			$scope.currentSelectedTeamNumberStart = $scope.blue2Number;
		    break;
		  case 'blue3':
		    $scope.currentSelectedTeamIdStart = $scope.blue3Id;
			$scope.currentSelectedTeamNumberStart = $scope.blue3Number;
		    break
		  default:
		    alertm("Error: Invalid team");
		}
	}
	
	$scope.errorGettingData = function(dataType) {
		alertm("Error getting "+dataType+" data. Please retry");
		$("#waitForTeams").hide();
	}
	
	$scope.gotTeamsStart = function(data) {
		$scope.selectedRoundIdStart = data[0]['roundId'];
		$scope.selectedRoundNumberStart = data[0]['number'];
		
		$scope.red1Id = data[0]['red1Id'];
		$scope.red1Number = data[0]['red1Number'];
		$scope.red2Id = data[0]['red2Id'];
		$scope.red2Number = data[0]['red2Number'];
		$scope.red3Id = data[0]['red3Id'];
		$scope.red3Number = data[0]['red3Number'];
		
		$scope.blue1Id = data[0]['blue1Id'];
		$scope.blue1Number = data[0]['blue1Number'];
		$scope.blue2Id = data[0]['blue2Id'];
		$scope.blue2Number = data[0]['blue2Number'];
		$scope.blue3Id = data[0]['blue3Id'];
		$scope.blue3Number = data[0]['blue3Number'];
		
		$scope.teamSelectedStart = null;
		$scope.currentSelectedTeamIdStart = null;
		$scope.currentSelectedTeamNumberStart = null;
		
		$("#waitForTeams").hide();
		$("#roundNotEntered").hide();
		$("#startTeamsBar").show();
	}
	
	$scope.updateRoundStart = function() {
		$("#waitForTeams").show();
		if($scope.firstDigitSelect===0&&$scope.secondDigitSelect===0&&$scope.thirdDigitSelect===0) {
			alertm("Please choose a round greater than 0");
			$("#waitForTeams").hide();
			$("#roundNotEntered").show();
			$("#startTeamsBar").hide();
		} else {
			$scope.teamNumberToSubmit = ""+$scope.firstDigitSelect+$scope.secondDigitSelect+$scope.thirdDigitSelect;
			$scope.teamNumberToSubmit = $scope.teamNumberToSubmit.replace(/^0+/, '');
			$http({method: 'GET', url: '../../rest/round/'+$scope.teamNumberToSubmit, params: Math.random().toString(36).substring(2), cache: false}).
			  success(function(data, status) {
				if(Object.keys(data).length === 0) {
					alertm("No data for that round. Please retry.");
					$("#waitForTeams").hide();
					$("#roundNotEntered").show();
					$("#startTeamsBar").hide();
				} else {
					$scope.gotTeamsStart(data);
				}
			  }).
			  error(function(data, status) {
				$scope.errorGettingData("round");
			  });
		}
	}
	
	$scope.startCollecting = function() {
		$rootScope.teamId = $scope.currentSelectedTeamIdStart;
		$rootScope.teamNumber = $scope.currentSelectedTeamNumberStart;
		$rootScope.roundId = $scope.selectedRoundIdStart;
		$rootScope.roundNumber = $scope.selectedRoundNumberStart;
		
		if(!$rootScope.teamId||!$rootScope.teamNumber||!$rootScope.roundId||!$rootScope.roundNumber) {
			alertm("Please select a round and team");
		} else {
			$(document).foundation('reveal', {animation: 'fadeAndPop'});
			$('#scoreChooseTeam').foundation('reveal', 'close');$('#darkenBack').hide();
			$rootScope.startSaving = true;
			alertm('Collecting for round '+$rootScope.roundNumber+", team "+$scope.currentSelectedTeamNumberStart, 6, 'success');
		}
		
	}
	
});

thunderstormCollectionControllers.controller('collectScoreController', function ($scope, $http, $rootScope, $sce, $compile) {
	$rootScope.newRobotId = null;
	
	$scope.saveStatus = 1;
	$scope.currentStage = 0;
	$scope.quantitySelectBox = 0;
	
	$scope.currentStackLevel = 1;
	
	$scope.actionElement = null;
	
	$scope.nowText = "Select robot starting location";
	
	$scope.autoActionHTML = '<img class="fieldElements" ng-click="selectAction(\'container\')" src="http://ci.stormbots.com/CITools2015/img/container.png"><img class="fieldElements" ng-click="selectAction(\'yellow\')" src="http://ci.stormbots.com/CITools2015/img/yellow.png"><button ng-click="selectEndPos()" class="button alert radius endPosButton">End Pos.</button>';
	
	$scope.teleActionHTML = '<img class="fieldElements" ng-click="selectAction(\'container\')" src="http://ci.stormbots.com/CITools2015/img/container.png"><img class="fieldElements" ng-click="selectAction(\'yellow\')" src="http://ci.stormbots.com/CITools2015/img/yellow.png"><img class="fieldElements" ng-click="selectAction(\'gray\')" src="http://ci.stormbots.com/CITools2015/img/grey.png"><img class="fieldElementsLitter" ng-click="selectAction(\'litter\')" src="http://ci.stormbots.com/CITools2015/img/litter.png">';
	
	$scope.selectActionHTML = '<button class="button radius selectActionButton" ng-click="selectPickUp()">Pick Up</button> <button class="button radius selectActionButton" ng-click="selectStackLevel()">Place &nbsp;&nbsp;&nbsp; </button> <button class="button radius selectActionButton" ng-click="selectFail()">Fail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </button>';
	
	$scope.selectStackLevelHTML = 'Lowest Level: <ul class="button-group round"> <li><a ng-class="{\'activeButton\': currentStackLevel===1}" ng-click="changeStackLevel(1)" class="button selectActionButton">1</a></li> <li><a ng-class="{\'activeButton\': currentStackLevel===2}" ng-click="changeStackLevel(2)" class="button selectActionButton">2</a></li> <li><a ng-class="{\'activeButton\': currentStackLevel===3}" ng-click="changeStackLevel(3)" class="button selectActionButton">3</a></li> <li><a ng-class="{\'activeButton\': currentStackLevel===4}" ng-click="changeStackLevel(4)" class="button selectActionButton">4</a></li> <li><a ng-class="{\'activeButton\': currentStackLevel===5}" ng-click="changeStackLevel(5)" class="button selectActionButton">5</a></li> <li><a ng-class="{\'activeButton\': currentStackLevel===6}" ng-click="changeStackLevel(6)" class="button selectActionButton">6</a></li> </ul> <br /> <label>Quantity <select ng-model="quantitySelectBox"> <option value="0">&nbsp;</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> </select> </label>';
	
	$scope.selectPickUpLevelHTML = 'Number picked up: <select ng-model="pickUpLevelSelectBox"> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> </select>';
	
	$scope.selectFailHTML = 'Number failed: <select ng-model="failSelectBox"> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> </select>';
	
	$("#initialActionChooser").html($scope.autoActionHTML);
	$compile($("#initialActionChooser"))($scope);
	
	$scope.currentActionInfo = {
			"posX": null,
			"posY": null,
			
			"yellowZonePlacement": null,
			"robotPlacement": null,
			
			"pickUpYellow": null,
			"failYellow": null,
			"stackYellow": null,
			"pickUpContainer": null,
			"failContainer": null,
			"stackContainer": null,
			"autoZone": null,	
			
			"pickUpGray": null,
			"failGray": null,
			"stackGray": null,
			"pickUpLitter": null,
			"failLitter": null,
			"scoreLitter": null,
			
			"knockToteRobot": null,
			"knockLitterRobot": null,
			"knockContainerRobot": null,
			"knockToteStack": null,
			"knockLitterStack": null,
			"knockContainerStack": null,
			"stuck": null,
			"foul": null,
			"yellowCard": null,
			"redCard": null,
			
			"star": null
			
		};

	
	$("#scoreChooseTeam").foundation('reveal', 'open');
	$(document).foundation('reveal', {animation: 'fadeAndPop'});
	$(document).foundation('reveal', {animation: 'none'});
	$("#waitForTeams").spin("tiny", "grey");
	
		
	$scope.checkNegative = function(val) {
	  if(val>0) {
		return val - 1;
	  } else {
		return val;  
	  }
	}
	
	
	$scope.changeStage = function (newStage) {
        $scope.currentStage = newStage;
		$scope.resetActionChooser(false);

		switch(newStage) {
			case 0:
				$scope.nowText = "Select robot starting location";
				break;
			case 1:
				$scope.nowText = "Touch field for action.";
				break;
			case 2:
				$scope.nowText = "Touch field for action.";
				break;

		}
    }
	
	
	// Begin action chooser
	
	var initialActionChooser = document.querySelector("#initialActionChooser");
	var container = document.querySelector("#contentContainer");
	$scope.open = false;
	$scope.starOpen = false;
	$scope.yellowPlacementChooseOpen = false;
	
	$scope.resetActionChooser = function (save) {

		$scope.currentActionInfo["teamId"] = $rootScope.teamId;
		$scope.currentActionInfo["roundId"] = $rootScope.roundId;
		$scope.currentActionInfo["stage"] = $scope.currentStage;

		console.log($scope.currentActionInfo);
		if(save && $rootScope.teamId && $scope.currentActionInfo.posX) {

			$scope.saveStatus = 2;

			$http({method: 'POST', url: '../../rest/score/', params: Math.random().toString(36).substring(2), data: $scope.currentActionInfo, cache: false}).
			  success(function(data, status) {
				if(data) {
					$scope.saveStatus = 1;
					//alertm("Data Saved Successfully!");
				} else {
					$scope.saveStatus = 0;
					alertm("There was a server error. Please retry");
				}
			  }).
			  error(function(data, status) {
			  	$scope.saveStatus = 0;
				alertm("There was an error connecting. Please retry");
			  });

		}
		
		switch($scope.currentStage){
			case 1:
				$("#initialActionChooser").html($scope.autoActionHTML);
				$compile($("#initialActionChooser"))($scope);
				break;
			case 2:
				$("#initialActionChooser").html($scope.teleActionHTML);
				$compile($("#initialActionChooser"))($scope);
				break;
		}
		
		
		$scope.open = false;
		$scope.starOpen = false;
		$scope.yellowPlacementChooseOpen = false;

		$scope.quantitySelectBox = 0;
		$scope.changeStackLevel(1);

		$scope.actionElement = null;

		$scope.pickUpLevelSelectBox = null;

		$scope.failSelectBox = null;

		$scope.currentActionInfo = {
			"posX": null,
			"posY": null,
			
			"yellowZonePlacement": null,
			"robotPlacement": null,
			
			"pickUpYellow": null,
			"failYellow": null,
			"stackYellow": null,
			"pickUpContainer": null,
			"failContainer": null,
			"stackContainer": null,
			"autoZone": null,	
			
			"pickUpGray": null,
			"failGray": null,
			"stackGray": null,
			"pickUpLitter": null,
			"failLitter": null,
			"scoreLitter": null,
			
			"knockToteRobot": null,
			"knockLitterRobot": null,
			"knockContainerRobot": null,
			"knockToteStack": null,
			"knockLitterStack": null,
			"knockContainerStack": null,
			"stuck": null,
			"foul": null,
			"yellowCard": null,
			"redCard": null,
			
			"star": null
			
		};

		switch($scope.currentStage) {
			case 0:
				$scope.nowText = "Select robot starting location";
				break;
			case 1:
				$scope.nowText = "Touch field for action.";
				break;
			case 2:
				$scope.nowText = "Touch field for action.";
				break;

		}

	}
	
	var robotPositionStar = document.querySelector("#robotPositionStar");
	var yellowTotePlacementChoose = document.querySelector("#yellowTotePlacementChoose");

	$scope.getClickPosition = function ($event) {
		if($scope.open===false) {
			var parentPosition = getPosition($event.target);
			var xPosition = $event.clientX - parentPosition.x - (initialActionChooser.clientWidth / 2);
			var yPosition = $event.clientY - parentPosition.y - (initialActionChooser.clientHeight / 2);
			 
			initialActionChooser.style.left = xPosition-110 + "px";
			initialActionChooser.style.top = yPosition-container.clientHeight+10 + "px";

			$scope.currentActionInfo.posX = Math.max(0, Math.floor((xPosition / container.clientWidth) * 100));
			$scope.currentActionInfo.posY = Math.max(0, Math.floor((yPosition / container.clientHeight) * 100));
			
			if($scope.currentStage!==0) {
				$scope.open = true;
			} else {
				robotPositionStar.style.left = xPosition-6 + "px";
				robotPositionStar.style.top = yPosition-container.clientHeight-18 + "px";

				yellowTotePlacementChoose.style.left = xPosition-65 + "px";
				yellowTotePlacementChoose.style.top = yPosition-container.clientHeight-15 + "px";

				$scope.starOpen = true;
				$scope.currentActionInfo.robotPlacement = 1;

				$scope.nowText = "Yellow tote placement:";

				$scope.yellowPlacementChooseOpen = true;

				console.log($scope.currentActionInfo);
			}

			//alertm($scope.currentActionInfo.posX+", "+$scope.currentActionInfo.posY);
		} else {
			$scope.resetActionChooser(false);
		}
	}
	 
	function getPosition(element) {
		var xPosition = 0;
		var yPosition = 0;
		  
		while (element) {
			xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
			yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
			element = element.offsetParent;
		}
		return { x: xPosition, y: yPosition };
	}
	

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//										Begin Individual Functions										//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	$scope.starBot = function (placement) {
		$scope.currentActionInfo.star = placement;
		$scope.resetActionChooser(true);
	}

	$scope.selectYellowTotePlacement = function (placement) {
		$scope.currentActionInfo.yellowZonePlacement = placement;
		$scope.resetActionChooser(true);

		$scope.changeStage(1);
	}
	
	$scope.selectAction = function (target) {
		$scope.nowText = "Select action";
		
		$scope.actionElement = target;
		
		$("#initialActionChooser").html($scope.selectActionHTML);
		$compile($("#initialActionChooser"))($scope);
	}
	
	$scope.selectEndPos = function () {
		if($scope.currentActionInfo.posX > 22 && $scope.currentActionInfo.posX < 33) {
			$scope.currentActionInfo.autoZone = 1;
		} else if($scope.currentActionInfo.posX > 66 && $scope.currentActionInfo.posX < 77) {
			$scope.currentActionInfo.autoZone = 1;
		} else {
			$scope.currentActionInfo.autoZone = 0;
		}
		
        $scope.resetActionChooser(true);
		$scope.changeStage(2);
    }
	
	$scope.selectStackLevel = function (target) {
		$scope.nowText = "Select stack level";
		
		$("#initialActionChooser").html($scope.selectStackLevelHTML);
		$compile($("#initialActionChooser"))($scope);
	}

	$scope.selectFail = function (target) {
		$scope.nowText = "Select stack level";
		
		$("#initialActionChooser").html($scope.selectFailHTML);
		$compile($("#initialActionChooser"))($scope);
	}
	
	$scope.changeStackLevel = function (newLevel) {
        $scope.currentStackLevel = newLevel;
    }
	
	$scope.$watch('quantitySelectBox',function(){
		switch($scope.actionElement){
			case "yellow":
				$scope.currentActionInfo.stackYellow = parseInt($scope.currentStackLevel) + parseInt($scope.quantitySelectBox) - 1;
				break;
			case "gray":
				$scope.currentActionInfo.stackGray = parseInt($scope.currentStackLevel) + parseInt($scope.quantitySelectBox) - 1;
				break;
			case "container":
				$scope.currentActionInfo.stackContainer = parseInt($scope.currentStackLevel) + parseInt($scope.quantitySelectBox) - 1;
				break;
			case "litter":
				$scope.currentActionInfo.scoreLitter = 1;
				break;
		}

		$scope.resetActionChooser(true);
		
	})


    $scope.$watch('failSelectBox',function(){
    	if($scope.actionElement=="yellow") {
    		$scope.currentActionInfo.failYellow = parseInt($scope.failSelectBox);
    	} else if($scope.actionElement=="container") {
    		$scope.currentActionInfo.failContainer = parseInt($scope.failSelectBox);
    	} else if($scope.actionElement=="gray") {
    		$scope.currentActionInfo.failGray = parseInt($scope.failSelectBox);
    	} else if($scope.actionElement=="litter") {
    		$scope.currentActionInfo.failLitter = parseInt($scope.failSelectBox);
    	}
		$scope.resetActionChooser(true);
	})

    $scope.selectPickUp = function () {
        $scope.nowText = "Select number picked up";
		
		$("#initialActionChooser").html($scope.selectPickUpLevelHTML);
		$compile($("#initialActionChooser"))($scope);
    }

    $scope.$watch('pickUpLevelSelectBox',function(){
    	if($scope.actionElement=="yellow") {
    		$scope.currentActionInfo.pickUpYellow = parseInt($scope.pickUpLevelSelectBox);
    	} else if($scope.actionElement=="container") {
    		$scope.currentActionInfo.pickUpContainer = parseInt($scope.pickUpLevelSelectBox);
    	} else if($scope.actionElement=="gray") {
    		$scope.currentActionInfo.pickUpGray = parseInt($scope.pickUpLevelSelectBox);
    	} else if($scope.actionElement=="litter") {
    		$scope.currentActionInfo.pickUpLitter = parseInt($scope.pickUpLevelSelectBox);
    	}
		$scope.resetActionChooser(true);
	})

	// End action chooser
	
});


thunderstormCollectionControllers.controller('analysisController', function ($scope, $http, $rootScope) {
	$scope.teamNumberForHeatmap = 2811
	$scope.teamIdForHeatmap = 27;
	$scope.heatmapInstance = h337.create({
				  container: document.querySelector('.heatmap')
				});

	$http({method: 'GET', url: '../../rest/score/', params: Math.random().toString(36).substring(2), cache: false}).
		  success(function(data, status) {
			$scope.scoreElements = data;

			$scope.parseHeatmapData();
		  }).
		  error(function(data, status) {
			alertm("Error getting score data");
		  });
	
	$scope.parseHeatmapData = function() {
		$scope.heatmapMax = 2;
		$scope.points = [];

		for(var i = 0; i < $scope.scoreElements.length; i++) {
		    var obj = $scope.scoreElements[i];

		    if(obj.teamId == $scope.teamIdForHeatmap) {
		    	$scope.points.push({x: (obj.posX * 834)/100, y: (obj.posY * 377)/100});
		    }

		    if(i == $scope.scoreElements.length-1) {
		    	$scope.renderHeatMap();
		    }
		}

	}

	$scope.renderHeatMap = function() {

		var data = { 
		  max: $scope.heatmapMax, 
		  data: $scope.points 
		};

		$scope.heatmapInstance.setData(data);
		$scope.heatmapInstance.repaint();
	}

	$scope.$watch('heatmapMax',function(){
    	$scope.heatmapInstance.setDataMax($scope.heatmapMax);
	})

	$scope.$watch('teamNumberForHeatmap',function(){

		$http({method: 'GET', url: '../../rest/team/', params: Math.random().toString(36).substring(2), cache: false}).
		  success(function(data, status) {

		  	for(var i = 0; i < data.length; i++) {
			    var obj = data[i];

			    if(obj.teamNumber == $scope.teamNumberForHeatmap) {
			    	$scope.teamIdForHeatmap = obj.teamId;
					$scope.parseHeatmapData();
			    }
			}


		  }).
		  error(function(data, status) {
			alertm("Error getting team data");
		  });

		

	})
   





   // Google Charts
	$scope.barGraphData = [];

	$http({method: 'GET', url: '../../rest/score/sum_tele/sort/TEAM_NUMBER', params: Math.random().toString(36).substring(2), cache: false}).
		  success(function(data, status) {

		  	$scope.rawBarGraphData = data;

		  	$scope.sortBarGraphData('pickUpGray');

		  }).
		  error(function(data, status) {
			alertm("Error getting team data");
		  });

	$scope.sortBarGraphData = function(sortType) {
		$scope.rawBarGraphData.sort(function(a, b) {
		        var x = a[sortType]; var y = b[sortType];
		        return ((parseInt(x) < parseInt(y)) ? 1 : ((parseInt(x) > parseInt(y)) ? -1 : 0));
		    });
		  	for(var i = 0; i < $scope.rawBarGraphData.length; i++) {
			    var obj = $scope.rawBarGraphData[i];
			    
			    $scope.barGraphData.push({c: [
		            {v: obj.teamNumber},
		            {v: obj.pickUpGray},
		            {v: obj.pickUpContainer}
		        ]});
			    
			}

			$scope.renderBarGraph();
	}
	$scope.renderBarGraph = function() {
		var chart1 = {};
	    chart1.type = "ColumnChart";
	    chart1.cssStyle = "height:500px; width:1150px;";
	    chart1.data = {"cols": [
	        {id: "team", label: "Team", type: "string"},
	        {id: "gray", label: "Pick Up Gray", type: "number"},
	        {id: "container", label: "Pick Up Container", type: "number"}
	    ], "rows": $scope.barGraphData
		};

	    chart1.options = {
	        "title": "Pick Up Summary",
	        "isStacked": "false",
	        "fill": 20,
	        "displayExactValues": true,
	        "vAxis": {
	            "title": "Number Picked Up", "gridlines": {"count": 10}
	        },
	        "hAxis": {
	            "title": "Team Number"
	        }
	    };

	    chart1.formatters = {};

	    $scope.chart = chart1;
	}

});