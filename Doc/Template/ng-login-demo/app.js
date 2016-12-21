angular.module('LoginApp').run(function($rootScope, Auth) {
    $rootScope.$on('$stateChangeStart', function(event, next) {

        if (angular.isDefined(next.data)) {
            var roles = next.data.authorizedRoles;

            if (!Auth.isAuthenticated()) {
                event.preventDefault();
                $rootScope.$broadcast('noLogin');
            } else if (!Auth.isAuthorized(roles)) {
                event.preventDefault();
                $rootScope.$broadcast('noAccess');
            }
        }
    })
})
