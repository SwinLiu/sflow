'use strict';


angular.module('app')
.service('AuthService', ['$http', 'SessionService', function ($http, SessionService) {
	
	var authService = {};

	authService.login = function(credentials) {
		return $http.post('/login', credentials).then(
			function(res) {
				SessionService.create(res.data.id,
						res.data.user.id,
						res.data.user.role);
				return res.data.user;
			});
	};

	authService.isAuthenticated = function() {
		return !!SessionService.userId;
	};

	authService.isAuthorized = function(authorizedRoles) {
		if (!angular.isArray(authorizedRoles)) {
			authorizedRoles = [ authorizedRoles ];
		}
		return (authService.isAuthenticated() && authorizedRoles.indexOf(SessionService.userRole) !== -1);
	};
	
	return authService;
	
}]);