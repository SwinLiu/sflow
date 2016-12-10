var CommonUtil = (function(){
	
	return (function(){
		
		var splitStr = "_@_";
		
		var CommonUtil = {
			randomString:randomString,
			encryptPasswd:encryptPasswd
		};
		
		return CommonUtil;
		
		/////////
		
		function randomString(len){
			len = len || 8;
			var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /** **默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1*** */
			var maxPos = chars.length;
			var str = '';
			for (i = 0; i < len; i++) {
				str += chars.charAt(Math.floor(Math.random() * maxPos));
			}
			return str;
		};
		
		function encryptPasswd(loginAccount, password, publicKey){
			var encrypt = new JSEncrypt();
	        encrypt.setPublicKey(publicKey);
	        
	        var shaObj = new jsSHA("SHA-1", "TEXT");
	        shaObj.update(password);
	        
	        var encryptStr = "";
	        if(loginAccount != null){
	        	encryptStr = loginAccount + splitStr;
	        }
	        encryptStr += shaObj.getHash("HEX") + splitStr + randomString(8);
	        
	        return encrypt.encrypt(encryptStr);
		};
		
	})();
	
//	return new Alert();
	
})();

