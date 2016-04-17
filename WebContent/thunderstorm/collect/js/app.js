var thunderstormCollectionApp = angular.module('thunderstormCollectionApp', [
  'ngRoute',
  'ngAnimate',
  'thunderstormCollectionControllers',
  'xeditable',
  'googlechart'
]);
 
thunderstormCollectionApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/home', {
        templateUrl: 'partials/home.html',
        controller: 'collectHomeController'
      }).
	  when('/team', {
        templateUrl: 'partials/team.html',
        controller: 'collectTeamController'
      }).
	  when('/teamSearch', {
        templateUrl: 'partials/search/team.html',
        controller: 'searchTeamController'
      }).
	  when('/score', {
        templateUrl: 'partials/score.html',
        controller: 'collectScoreController'
      }).
	  when('/robot', {
        templateUrl: 'partials/robot.html',
        controller: 'collectRobotController'
      }).
	  when('/robotDetails', {
        templateUrl: 'partials/robot-details.html',
        controller: 'collectRobotDetailsController'
      }).
	  when('/analysis', {
        templateUrl: 'partials/analysis.html',
        controller: 'analysisController'
      }).
      otherwise({
        redirectTo: '/home'
      });
  }]);

