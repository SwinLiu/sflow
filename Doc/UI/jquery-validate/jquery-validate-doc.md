# 1) 引用jquery.validate js

<script src="js/plugins/jquery/jquery-1.11.2.min.js"></script>
<script src="js/plugins/jquery/validate/jquery.validate.min.js" ></script>

# 2) Sample 

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
        }
});

两个重要的概念

1. method : 验证方法，指的是校验的逻辑方法。
	比如email方法，检查输入的文本是否符合email的规则。
2. rule : 验证规则，指的是元素和验证方法的关联。
	比如页面是id为email的文本框，需要带有email的验证方法。


# 3) 基本API

1. validate() 方法。 插件的核心方法，定义的基本的校验规则和一些有用的配置。

$("#login-frm").validate({...});

2. debug：进行调试模式（表单不提交）

$("#login-frm").validate({
	debug:true
})

3. 基本的验证方法。

默认校验规则:
1 	required:true 	必须输入的字段。
2 	remote:"check.php" 	使用 ajax 方法调用 check.php 验证输入值。
3 	email:true 	必须输入正确格式的电子邮件。
4 	url:true 	必须输入正确格式的网址。
5 	date:true 	必须输入正确格式的日期。日期校验 ie6 出错，慎用。
6 	dateISO:true 	必须输入正确格式的日期（ISO），例如：2009-06-23，1998/01/22。只验证格式，不验证有效性。
7 	number:true 	必须输入合法的数字（负数，小数）。
8 	digits:true 	必须输入整数。
9 	creditcard: 	必须输入合法的信用卡号。
10 	equalTo:"#field" 	输入值必须和 #field 相同。
11 	accept: 	输入拥有合法后缀名的字符串（上传文件的后缀）。
12 	maxlength:5 	输入长度最多是 5 的字符串（汉字算一个字符）。
13 	minlength:10 	输入长度最小是 10 的字符串（汉字算一个字符）。
14 	rangelength:[5,10] 	输入长度必须介于 5 和 10 之间的字符串（汉字算一个字符）。
15 	range:[5,10] 	输入值必须介于 5 和 10 之间。
16 	max:5 	输入值不能大于 5。
17 	min:10 	输入值不能小于 10。


默认提示:
messages: {
	required: "This field is required.",
	remote: "Please fix this field.",
	email: "Please enter a valid email address.",
	url: "Please enter a valid URL.",
	date: "Please enter a valid date.",
	dateISO: "Please enter a valid date ( ISO ).",
	number: "Please enter a valid number.",
	digits: "Please enter only digits.",
	creditcard: "Please enter a valid credit card number.",
	equalTo: "Please enter the same value again.",
	maxlength: $.validator.format( "Please enter no more than {0} characters." ),
	minlength: $.validator.format( "Please enter at least {0} characters." ),
	rangelength: $.validator.format( "Please enter a value between {0} and {1} characters long." ),
	range: $.validator.format( "Please enter a value between {0} and {1}." ),
	max: $.validator.format( "Please enter a value less than or equal to {0}." ),
	min: $.validator.format( "Please enter a value greater than or equal to {0}." )
}


required: true 值是必须的。
required: "#aa:checked" 表达式的值为真，则需要验证。
required: function(){} 返回为真，表示需要验证。

后边两种常用于，表单中需要同时填或不填的元素。


# 4）高级API

1. valid()  检查表单或某些元素是否有效。

2. rules  

rules() 获取表单元素的校验规则。   e.g. $("#username").rules();

rules("add",rules) 增加校验规则。	e.g. $("#username").rules("add",{maxlength:10,minlength:2});

rules("remove",rules) 删除校验规则。	e.g. $("#username").rules("remove","maxlength");

3. Validator 对象。

validate 方法返回Validator对象, Validator对象有很多有用的方法：

validator.form() 验证表单是否有效。 返回是否true/false

validator.element(element) 验证某个元素是否有效
