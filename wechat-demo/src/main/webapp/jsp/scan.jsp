<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath}/jquery-3.1.1.min.js"></script> --%>
<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<body>
 <span id="login_container"></span>
</body>
<script>
var obj = new WxLogin({
	id : "login_container",
	appid : "wx11459d57ff138801",
	scope : "snsapi_login",
	redirect_uri : encodeURIComponent("http://" + window.location.host + "/response"),
	state : Math.ceil(Math.random()*1000),//实际开发 可生成随机码(不是math) 放session作用域  
	style : "",
	href : ""
});
</script>
</html>