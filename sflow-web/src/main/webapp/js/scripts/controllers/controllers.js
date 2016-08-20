var sFlowCtrls = angular.module('sFlowCtrls', []);


sFlowCtrls.controller("loginCtrl", function($scope,$http) {
	$scope.user = {loginAccount: "", passwd: ""};
    $scope.login = function(){
    	$http({method:'POST',url:'api/login',params:$scope.user}).success(function(response) {  
        	if(response.success){
        		toastr.success("success");
        	}else{
        		toastr.error("failed");
        		
        	}
     	}); 
    }

});

/**
 * 这里接着往下写，如果控制器的数量非常多，需要分给多个开发者，可以借助于grunt来合并代码
 */
