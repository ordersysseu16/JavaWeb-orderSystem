<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/adminLogin.css">
		<title>后台登录</title>
	</head>
	<body>
		<div class="mainContent">
			<div class="login_picture">
				<img class="logo" src="${pageContext.request.contextPath}/resource/img/Koo_logo_400.png" alt="koo_logo">
			</div>
			<div class="adminLogin">
				<div class="loginTips">
					<span class="alert">${alert}</span>
				</div>
				<form action="${pageContext.request.contextPath}/admin/login" method="post" >
					<div id="accArea" class="text_input">
						<label>账号</label>
						<input type="text" name="account" value="${admin.account}">
					</div>
					<div id="pwdArea" class="text_input">
						<label>密码</label>
						<input type="password" name="password" value="${admin.password}">
					</div>
					<input type="submit" name="submit" value="登录">
				</form>
			</div>
		</div>
	</body>
</html>
