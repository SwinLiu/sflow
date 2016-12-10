var sFlowCtrls = angular.module('sFlowCtrls', []);

/**
 * Login page controller method
 */
sFlowCtrls.controller("loginCtrl", function($scope,$http) {
	
	$scope.password;
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
			
			$http({method:'GET',url:'api/secret'}).success(function(response) {
				if(response.success){
					var publicKey = response.result;
					$scope.user.passwd = CommonUtil.encryptPasswd($scope.user.loginAccount, $scope.password, publicKey);
					
					$http({method:'POST',url:'api/login',data:$scope.user}).success(function(response) {  
			        	if(response.success){
			        		alert.topCenter(true).success("success.");
			        	}else{
			        		
			        		if(response.returnCode == returnCode.CAPTCHA_ERROR){
			        			var msg = returnCode.CAPTCHA_ERROR_MSG;
			            		msgDiv.error("#login-msg-area",null,msg,true);
								$("#login-msg-area").show();
			        		}else{
			        			var msg = returnCode.LOGIN_ERROR_MSG;
			            		msgDiv.error("#login-msg-area",null,msg,true);
								$("#login-msg-area").show();
								
								$("#captchaCodeImg").click();
								$scope.user.captchaCode = "";
								
			        		}
			        	}
			     	});
					
					
					
					
	        	}else{
	        		alert.topCenter(true).success("System error, Please try later.");
	        	}
			});
		}
	});
		
	$scope.validator = $("#login-frm").validate({
        rules: {
        	loginAccount: {
                required: true,
                rangelength : [4,40]
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
                required: "Login account cann't be null. ",
                rangelength: $.validator.format( "Please enter a login account between {0} and {1} characters long." )
            },
            passwd: {
            	required: "Password word cann't be null. ",
            	rangelength: $.validator.format( "Please enter a password between {0} and {1} characters long." )
            },
            captchaCode: {
            	required: "Captcha Code cann't be null. "
            }
        },
        onkeyup:false,
        onclick:false,
        onfocusout:false,
        errorClass: "error",	//指定错误提示的 css 类名，可以自定义错误提示的样式。
        success: 'valid',
        focusInvalid:false,//提交表单后，未通过验证的表单（第一个或提交之前获得焦点的未通过验证的表单）会获得焦点
        focusCleanup:false,//当未通过验证的元素获得焦点时，移除错误提示（避免和 focusInvalid 一起使用）
        errorContainer: "#login-msg-area",	//显示或者隐藏验证信息，可以自动实现有错误信息出现时把容器属性变为显示，无错误时隐藏
        //errorLabelContainer: $("#login-msg-div"),	//把错误信息统一放在一个容器里面。
        errorElement:"li",	//用什么标签标记错误，默认是 label，可以改成 em。
        wrapper:"ul",	//用什么标签再把上边的 errorELement 包起来。
        errorPlacement: function(error, element) {  // 指明错误放置的位置，默认情况是：error.appendTo(element.parent());即把错误信息放在验证的元素后面。
        	if($("#login-msg-div").length == 0){
        		$("#login-msg-area").html('<div class="alert alert-danger" id="login-msg-div" ></div>');
        	}
        	$("#login-msg-div").append(error);
        },
        success: function(label) {
            label.parent().remove();
        }
    });
    

});


/**
 * register page controller method
 */
sFlowCtrls.controller("registerCtrl", function($scope,$http) {
	
	$scope.password;
	$scope.user = {userName: "", password: "", email: "", phone: "", captchaCode: ""};
	
	$("#register_btn").unbind("click").click(function(){
		if($scope.validator.form()){
			
			
			$http({method:'GET',url:'api/secret'}).success(function(response) {
				if(response.success){
					var publicKey = response.result;
					$scope.user.password = CommonUtil.encryptPasswd(null, $scope.password, publicKey);
					
			        $http({method:'POST',url:'api/register',data:$scope.user}).success(function(response) {  
			        	if(response.success){
			        		alert.topCenter(true).success("success.");
			        	}else{
			        		
			        		if(response.message == returnCode.CAPTCHA_ERROR){
			        			var msg = returnCode.CAPTCHA_ERROR_MSG;
			            		msgDiv.error("#register-msg-area",null,msg,true);
								$("#register-msg-area").show();
			        		}else{
			        			var msg = 'Login account or password error.';
			            		msgDiv.error("#register-msg-area",null,msg,true);
								$("#register-msg-area").show();
								
								$("#captchaCodeImg").click();
								$scope.user.captchaCode = "";
								
			        		}
			        	}
			     	});
				} else {
					alert.topCenter(true).success("System error, Please try later.");
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
            	equalTo: "#passwd"
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
            	equalTo: "Please enter the same password again."
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
        //errorLabelContainer: $("#register-msg-div"),
        errorElement:"li",
        wrapper:"ul",
        errorPlacement: function(error, element) {  // 指明错误放置的位置，默认情况是：error.appendTo(element.parent());即把错误信息放在验证的元素后面。
        	if($("#register-msg-div").length == 0){
        		$("#register-msg-area").html('<div class="alert alert-danger" id="register-msg-div" ></div>');
        	}
        	$("#register-msg-div").append(error);
        },
        success: function(label) {
            label.parent().remove();
        }
    });
	
	
});

