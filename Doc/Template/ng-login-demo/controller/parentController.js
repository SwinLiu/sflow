angular.module('LoginApp', ['ui.router'])
    .controller('ParentController', function($scope, $rootScope, $state) {
        console.log('ParentController');

        var noLogin = function() {
            $state.go('login');
        }
        var noAccess = function() {
            alert('没有进入的权限')
        }
        $rootScope.$on('noLogin', noLogin);
        $rootScope.$on('noAccess', noAccess);
    })
