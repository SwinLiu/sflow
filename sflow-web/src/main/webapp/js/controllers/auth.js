app.controller('LoadingController',function($scope,$resource,$state,$localStorage){
    if($localStorage.token != null){
    	var $com = $resource($scope.app.appUrl + "/api/auth");
        $com.get(function(){
            $state.go('app.dashboard');
        },function(){	
            $state.go('auth.login');
        }); 
    }else{
    	$state.go('auth.login');
    }
	 
});


app.controller('LoginController',function($scope,$state,$http,$localStorage){
        
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
			        		$http.defaults.headers.common['X-API-Token'] = response.result.USER_TOKEN;
			        		$localStorage.token = response.result.USER_TOKEN;

			        		$scope.session_user = $localStorage.session_user = response.result.USER_SESSION; //保存用户信息
			        		
			        		$state.go('app.dashboard');
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


