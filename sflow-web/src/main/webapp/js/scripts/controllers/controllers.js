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
		console.log($scope.user);
	});
	
	$("#login_btn").unbind("click").click(function(){
		if(validator.form()){
	    	//通过验证后运行的函数，里面要加上表单提交的函数，否则表单不会提交。
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
		
	var validator = $("#login-frm").validate({
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
//        unhighlight: function (element, errorClass, validClass) { 
//        	//验证通过
//        },
//        highlight: function (element, errorClass, validClass) { 
//        	//未通过验证
//        },
//        errorPlacement: function (errorLabel, element) {
//        	//指明错误放置的位置，默认情况是：error.appendTo(element.parent());即把错误信息放在验证的元素后面。
//        	msgDiv.error("#login-msg-area",errorLabel.html(),true);
//			$("#login-msg-area").removeClass('hidden');
//        },
        errorContainer: "#login-msg-area",
        errorLabelContainer: $("#login-msg-div")//,
        //errorElement:"li",
        //wrapper:"ul",
//        submitHandler: function (form) {
//        	form.submit();
//        }
    });
    

});


/**
 * Login page controller method
 */
sFlowCtrls.controller("registerCtrl", function($scope,$http) {
	
	$scope.user = {userName: "", email: "", phone: ""};
	$scope.password = "";
	
	$("#register-frm").validate({
        rules: {
        	userName: {
                required: true,
                maxlength:40
            },
            password: {
            	required : true,
            	rangelength : [6,20]
            },
            passwdConfirm:{
            	required : true,
            	equalsTo : "#password"
            },
            email:{
            	required : true,
            	email : true
            },
            phone:{
            	maxlength:20
            }
        },
        messages: {
        	userName: {
            },
            password: {
            },
            passwdConfirm:{
            },
            email:{
            },
            phone:{
            }
        },
        onkeyup:false,
        onclick:false,
        onfocusout:false,
        errorClass: "error",
        success: 'valid',
        focusInvalid:false,//提交表单后，未通过验证的表单（第一个或提交之前获得焦点的未通过验证的表单）会获得焦点
        focusCleanup:true,//当未通过验证的元素获得焦点时，移除错误提示（避免和 focusInvalid 一起使用）
        errorContainer: "#login-msg-area",
        errorLabelContainer: $("#login-msg-div"),
        errorElement:"li",
        wrapper:"ul",
        submitHandler: function (form) {
        	
        }
    });
	
	
});

