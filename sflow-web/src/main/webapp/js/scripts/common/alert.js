var allM = "asdf";
var alert = function(){
	
	return "sdfaf";
	
	
};

function alertSuccess(msg){
	toastr.success(msg);
}

function alertSuccess2(msg){
	toastr.clear();
	toastr.options = {
	        "closeButton": true, //是否显示关闭按钮
	        "debug": false, //是否使用debug模式
	        "positionClass": "toast-top-full-width",//弹出窗的位置
	        "showDuration": "300",//显示的动画时间
	        "hideDuration": "1000",//消失的动画时间
	        "timeOut": "5000", //展现时间
	        "extendedTimeOut": "1000",//加长展示时间
	        "showEasing": "swing",//显示时的动画缓冲方式
	        "hideEasing": "linear",//消失时的动画缓冲方式
	        "showMethod": "fadeIn",//显示时的动画方式
	        "hideMethod": "fadeOut" //消失时的动画方式
	        };
	toastr.success(msg);
}

