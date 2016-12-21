angular.module('LoginApp')
    .config(['$stateProvider', '$urlRouterProvider',
        function($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/");

            $stateProvider.state('home', {
                url: "/",
                templateUrl: "./home.html"
            }).state('teacher', {
                url: "/teacher",
                templateUrl: "./teacher.html",
                data: {
                    authorizedRoles: ['admin', 'teacher']
                }
            }).state('student', {
                url: "/student",
                templateUrl: "./student.html"
            }).state('login', {
                url: "/login",
                templateUrl: "./login.html"
            })
        }
    ]);
