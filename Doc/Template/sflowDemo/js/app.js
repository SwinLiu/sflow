'use strict';

var app = angular.module('app', ['ngStorage','ui.router']);

app.controller('AppCtrl', function($state, $rootScope, $localStorage){
	console.log("AppCtrl test - Parent page. ");
	
	$localStorage.user = {};
	$localStorage.user.userId = '';
	$localStorage.user.role = '';
	
	var noLogin = function() {
		alert("no Login");
		$state.go('login');
	}
	var noAccess = function() {
		alert('no Access')
	}
	$rootScope.$on('noLogin', noLogin);
	$rootScope.$on('noAccess', noAccess);
	
});
app.controller('loginCtrl', function($state, $localStorage){
	console.log("loginCtrl test.");
	
	$("#login_btn").unbind("click").click(function(){
		console.log("Login success.");
		$localStorage.user.userId = 'A001';
		$localStorage.user.role = 'profile1';
		console.log($localStorage.user);
		$state.go('home');
	});
	
});
app.controller('homeCtrl', function($scope, $state, $localStorage){
	console.log("homeCtrl test - home Page. ");
	
	console.log($localStorage.user);
	
	$("#btn-login").unbind("click").click(function(){
		console.log("Login success.");
		$localStorage.user.userId = 'A001';
		$localStorage.user.role = 'profile1';
		console.log($localStorage.user);
	});
	
	$("#btn-logout").unbind("click").click(function(){
		console.log("Login success.");
		$localStorage.user.userId = '';
		$localStorage.user.role = '';
		console.log($localStorage.user);
		$state.go('login');
	});
	
	$("#btn-allRole").unbind("click").click(function(){
		console.log("add admin role.");
		$localStorage.user.role = 'admin';
		console.log($localStorage.user);
	});
	
	$("#btn-noRole").unbind("click").click(function(){
		console.log("clear role.");
		$localStorage.user.role = '';
		console.log($localStorage.user);
	});
	
	$("#btn-profile1").unbind("click").click(function(){
		console.log("add profile1 role.");
		$localStorage.user.role = 'profile1';
		console.log($localStorage.user);
	});
	$("#btn-profile2").unbind("click").click(function(){
		console.log("add profile2 role.");
		$localStorage.user.role = 'profile2';
		console.log($localStorage.user);
	});
	$("#btn-profile3").unbind("click").click(function(){
		console.log("add profile3 role.");
		$localStorage.user.role = 'profile3';
		console.log($localStorage.user);
	});
	$("#btn-profile4").unbind("click").click(function(){
		console.log("add profile4 role.");
		$localStorage.user.role = 'profile4';
		console.log($localStorage.user);
	});
});

app.config(function ($stateProvider,   $urlRouterProvider) {
	  
	  $urlRouterProvider
		  .otherwise('/login');
	
	  // route to show our basic form (/form)
	  $stateProvider
		.state('login', {
            url: '/login',
            templateUrl: 'login.html',
			data: {
			  authorizedRoles: []
			}
        })
		.state('home', {
            url: '/home',
            templateUrl: 'dashbaord.html',
			data: {
			  authorizedRoles: ['admin', 'profile1', 'profile2', 'profile3', 'profile4']
			}
        })
        .state('home.profile1', {
            url: '/profile1',
            templateUrl: 'profile1.html',
			data: {
			  authorizedRoles: ['admin', 'profile1']
			}
        })
		.state('home.profile2', {
            url: '/profile2',
            templateUrl: 'profile2.html',
			data: {
			  authorizedRoles: ['admin', 'profile2']
			}
        })
		.state('home.profile3', {
            url: '/profile3',
            templateUrl: 'profile3.html',
			data: {
			  authorizedRoles: ['admin', 'profile3']
			}
        })
		.state('home.profile4', {
            url: '/profile4',
            templateUrl: 'profile4.html',
			data: {
			  authorizedRoles: ['admin', 'profile4']
			}
        })
		;
  }
);

app.run(function ($rootScope, $localStorage) {
	$rootScope.$on('$stateChangeStart', function (event, next) {
		console.log("ui router change");
		
		var user = $localStorage.user;
		var authorizedRoles = next.data.authorizedRoles;
		console.log(authorizedRoles);
		console.log(user);
		
		if (user.userId == "") {
			console.log(next.data.url);
			if(next.url != "/login"){
				event.preventDefault();
				$rootScope.$broadcast('noLogin');
			}
		} else {
		
			if (!angular.isArray(authorizedRoles)) {
				authorizedRoles = [authorizedRoles];
			}
			var access = (authorizedRoles.indexOf(user.role) !== -1);
			
			console.log(access);
			if (!access) {
				event.preventDefault();
				$rootScope.$broadcast('noAccess');
			}
		}
			
	});
});

