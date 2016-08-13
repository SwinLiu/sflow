var sFlowApp = angular.module("sFlowApp", ['sFlowCtrls','ngRoute']);

sFlowApp.config(['$routeProvider', function($routeProvider){
    $routeProvider.when('/login', {
        templateUrl: 'pages/login.html',
        controller: 'loginCtrl'
    }).when('/index',{
    	templateUrl:'pages/home.html'
    }).otherwise({
        redirectTo: '/login'
    })
}]);

