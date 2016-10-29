var alert = (function(){
	
	return (function(){
		
		var alert = {
			topCenter:topCenter,
			topFullWidth:topFullWidth,
			topRight:topRight,
			topLeft:topLeft,
			bottomCenter:bottomCenter,
			bottomFullWidth:bottomFullWidth,
			bottomRight:bottomRight,
			bottomLeft:bottomLeft
		};
		
		return alert;
		
		/////////
		function getToastr(positionClass,closeButton){
			positionClass = positionClass || "toast-bottom-center";
			closeButton = closeButton || false;
			toastr.options = {
		        "closeButton": closeButton, //是否显示关闭按钮
		        "debug": false, //是否使用debug模式
		        "positionClass": positionClass,//弹出窗的位置
		        "showDuration": "300",//显示的动画时间
		        "hideDuration": "300",//消失的动画时间
		        "timeOut": "3000", //展现时间
		        "extendedTimeOut": "1000",//加长展示时间
		        "showEasing": "swing",//显示时的动画缓冲方式
		        "hideEasing": "linear",//消失时的动画缓冲方式
		        "showMethod": "fadeIn",//显示时的动画方式
		        "hideMethod": "fadeOut" //消失时的动画方式
			};
			return toastr;
		}
		
		function topCenter(closeButton){
			return getToastr("toast-top-center",closeButton);
		};
		
		function topFullWidth(closeButton){
			return getToastr("toast-top-full-width",closeButton);
		};
		
		function topRight(closeButton){
			return getToastr("toast-top-right",closeButton);
		};
		
		function topLeft(closeButton){
			return getToastr("toast-top-left",closeButton);
		};
		
		function bottomCenter(closeButton){
			return getToastr("toast-bottom-center",closeButton);
		};
		
		function bottomFullWidth(closeButton){
			return getToastr("toast-bottom-full-width",closeButton);
		};
		
		function bottomRight(closeButton){
			return getToastr("toast-bottom-right",closeButton);
		};
		
		function bottomLeft(closeButton){
			return getToastr("toast-bottom-left",closeButton);
		};
		
	})();
	
//	return new Alert();
	
})();

var msgDiv = (function(){
	
	return (function(){
		
		var msgDiv = {
			success:success,
			info:info,
			warning:warning,
			error:error
		};
		
		return msgDiv;
		
		function success(obj,title,msg,closeBtn){
			showMsgDiv(obj,title,msg,'alert-success',closeBtn);
		}
		
		function info(obj,title,msg,closeBtn){
			showMsgDiv(obj,title,msg,'alert-info',closeBtn);
		}
		
		function warning(obj,title,msg,closeBtn){
			showMsgDiv(obj,title,msg,'alert-warning',closeBtn);
		}
		
		function error(obj,title,msg,closeBtn){
			showMsgDiv(obj,title,msg,'alert-danger',closeBtn);
		}
		
		function showMsgDiv(obj,title,msg,type,closeBtn){
			closeBtn = closeBtn || false;
			var html = new Array();
			html.push('<div class="alert '+ type);
			if(closeBtn){
				html.push(' alert-dismissible ');
			}
			html.push('" role="alert">');
			if(closeBtn){
				html.push('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
			}
			if(title != null){
				html.push('<strong>');
				html.push(title);
				html.push('</strong>&nbsp;');
			}
			html.push('<span>');
			html.push(msg);
			html.push('</span>');
			
			html.push('</div>');
			$(obj).empty().html(html.join(''));
		}
		
	})();
	
})();
