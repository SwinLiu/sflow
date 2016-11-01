var sFlowCtrls = angular.module('sFlowCtrls', []);

/**
 * Login page controller method
 */
sFlowCtrls.controller("loginCtrl", function($scope,$http) {
	
	$scope.user = {loginAccount: "", passwd: "", captchaCode: ""};
	
	$("#captchaCodeImg").unbind("click").click(function(){
		var imgSrc = $(this).attr("src");
		var realLength = imgSrc.indexOf('?');
		imgSrc = imgSrc.substring(0, realLength > 0 ? realLength : imgSrc.length); 
		imgSrc = imgSrc + "?random=" + Math.random();
		$(this).attr("src",imgSrc);
	});
	
	$("#login_btn").unbind("click").click(function(){
		if($scope.validator.form()){
	        $http({method:'POST',url:'api/login',data:$scope.user}).success(function(response) {  
	        	if(response.success){
	        		alert.topCenter(true).success("success.");
	        	}else{
	        		
	        		if(response.message == "ERR_01"){
	        			var msg = 'Captcha Code Error.';
	            		msgDiv.error("#login-msg-area",null,msg,true);
						$("#login-msg-area").show();
	        		}else{
	        			var msg = 'Login account or password error.';
	            		msgDiv.error("#login-msg-area",null,msg,true);
						$("#login-msg-area").show();
						
						$("#captchaCodeImg").click();
						$scope.user.captchaCode = "";
						
	        		}
	        	}
	     	});
		}
	});
		
	$scope.validator = $("#login-frm").validate({
        rules: {
        	loginAccount: {
                required: true,
                maxlength:40
            },
            passwd: {
            	required : true,
            	rangelength : [6,20]
            },
            captchaCode: {
            	required : true
            }
        },
        messages: {
        	loginAccount: {
                required: "Login account cann't be null. "
            },
            passwd: {
            	required: "Password word cann't be null. "
            },
            captchaCode: {
            	required: "Captcha Code cann't be null. "
            }
        },
        onkeyup:false,
        onclick:false,
        onfocusout:false,
        errorClass: "error",
        success: 'valid',
        focusInvalid:false,//提交表单后，未通过验证的表单（第一个或提交之前获得焦点的未通过验证的表单）会获得焦点
        focusCleanup:false,//当未通过验证的元素获得焦点时，移除错误提示（避免和 focusInvalid 一起使用）
        errorContainer: "#login-msg-area",
        errorLabelContainer: $("#login-msg-div"),
        errorElement:"li",
        wrapper:"ul"
    });
    

});


/**
 * register page controller method
 */
sFlowCtrls.controller("registerCtrl", function($scope,$http) {
	
	$scope.user = {userName: "", password: "", email: "", phone: "", captchaCode: ""};
	
	$("#register_btn").unbind("click").click(function(){
		if($scope.validator.form()){
			console.log($scope.user);
	        $http({method:'POST',url:'api/register',data:$scope.user}).success(function(response) {  
	        	if(response.success){
	        		alert.topCenter(true).success("success.");
	        	}else{
	        		
	        		if(response.message == "ERR_01"){
	        			var msg = 'Captcha Code Error.';
	            		msgDiv.error("#login-msg-area",null,msg,true);
						$("#login-msg-area").show();
	        		}else{
	        			var msg = 'Login account or password error.';
	            		msgDiv.error("#login-msg-area",null,msg,true);
						$("#login-msg-area").show();
						
						$("#captchaCodeImg").click();
						$scope.user.captchaCode = "";
						
	        		}
	        	}
	     	});
		}
	});
	
	$scope.validator = $("#register-frm").validate({
        rules: {
        	userName: {
                required: true,
                rangelength : [4,40]
            },
            passwd: {
            	required : true,
            	rangelength : [6,20]
            },
            passwdConfirm:{
            	required : true,
            	equalsTo : "#passwd"
            },
            email:{
            	required : true,
            	email : true
            },
            phone:{
            	maxlength:20
            },
            captchaCode: {
            	required : true
            },
            agree: {
            	required : true
            },
        },
        messages: {
        	userName: {
        		required: "User Name cann't be null. ",
        		rangelength: $.validator.format( "Please enter a value between {0} and {1} characters long." )
            },
            passwd: {
            	required: "Password cann't be null. ",
            	rangelength: $.validator.format( "Please enter a value between {0} and {1} characters long." )
            },
            passwdConfirm:{
            	required: "Confirm password cann't be null. ",
            	equalTo: "Please enter the same password again. "
            },
            email:{
            	required: "Email cann't be null. "
            },
            captchaCode:{
            	required: "Captcha Code cann't be null. "
            },
            agree:{
            	required: "Please agree the Terms of Service and Privacy Policy. "
            },
        },
        onkeyup:false,
        onclick:false,
        onfocusout:false,
        errorClass: "error",
        success: 'valid',
        focusInvalid:false,
        focusCleanup:false,
        errorContainer: "#register-msg-area",
        errorLabelContainer: $("#register-msg-div"),
        errorElement:"li",
        wrapper:"ul",
        success: function(label) {
            label.parent().remove();
        }
    });
	
	
});

