var sFlowCtrls = angular.module('sFlowCtrls', []);


sFlowCtrls.controller("loginCtrl", function($scope,$http) {
	$scope.user = {loginAccount: "", passwd: ""};
	
    $("#login-frm").validate({
        rules: {
            loginAccount: {
                required: true,
                email: true
            },
            passwd: 'required'
        },
        messages: {
        	loginAccount: {
                required: "Please enter the login account",
                email: "Please enter the correct login account"
            },
            passwd: "Please enter the password"
        },
        errorClass: "error",
        success: 'valid',
        unhighlight: function (element, errorClass, validClass) { //验证通过
            $(element).tooltip('destroy').removeClass(errorClass);
        },
        //highlight: function (element, errorClass, validClass) { //未通过验证
        //    // TODO
        //}
        //,
        errorPlacement: function (label, element) {
            $(element).tooltip('destroy'); //必需
            $(element).attr('title', $(label).text()).tooltip('show'); 
        },
        submitHandler: function (form) {
            alert.topCenter(true).success('验证通过,提交远程验证');
            $http({method:'POST',url:'api/login',data:$scope.user}).success(function(response) {  
            	if(response.success){
            		alert.topCenter(true).success("success.");
            	}else{
            		alert.topCenter(true).error("failed.");
            		
            	}
         	});
        }
    });

});

/**
 * 这里接着往下写，如果控制器的数量非常多，需要分给多个开发者，可以借助于grunt来合并代码
 */
