var sFlowCtrls = angular.module('sFlowCtrls', []);


sFlowCtrls.controller("loginCtrl", function($scope,$http) {
	$scope.user = {loginAccount: "", passwd: ""};
	
    $("#login-frm").validate({
        rules: {
            loginAccount: {
                required: true,
                maxlength:20
            },
            passwd: {
            	required : true,
            	rangelength : [6,20]
            } 
        },
        messages: {
        	loginAccount: {
                required: "Please enter the login account"
            },
            passwd: {
            	required: "Please enter the password"
            }
        },
        onkeyup:false,
        onclick:false,
        onfocusout:false,
        errorClass: "error",
        success: 'valid',
        unhighlight: function (element, errorClass, validClass) { //验证通过
            $(element).tooltip('destroy').removeClass(errorClass);
        },
        //highlight: function (element, errorClass, validClass) { //未通过验证
        //    // TODO
        //},
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
            		var html = new Array();
            		html.push('<div class="alert alert-danger alert-dismissible" role="alert">');
            		html.push('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
            		html.push('<strong>Error!</strong> <span>Login account or password error.</span>');
            		html.push('</div>');
					$("#login-msg-area").empty().html(html.join(''));
					$("#login-msg-area").removeClass('hidden');
            	}
         	});
        }
    });

});

/**
 * 这里接着往下写，如果控制器的数量非常多，需要分给多个开发者，可以借助于grunt来合并代码
 */
