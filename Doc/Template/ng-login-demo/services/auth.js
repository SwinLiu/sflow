angular.module('LoginApp').
service('Auth', ['Session', function(Session) {
    var authService = {};
    // 是否已经登录
    authService.isAuthenticated = function() {
        return true;
    };
    // 是否有进入的权限
    authService.isAuthorized = function(authorizedRoles) {
        return false;
    };
    return authService;
}])
