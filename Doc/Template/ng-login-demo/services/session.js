angular.module('LoginApp').service('Session', function() {
    this.create = function(user) {
        this.user = user;
        this.userRole = user.userRole;
    };
    return this;
})
