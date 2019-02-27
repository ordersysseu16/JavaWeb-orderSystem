<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/masterPage.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/changePage.js"></script>
	<title>后台主页</title>
</head>
<body>
  <aside>
    <header>
      <a href="${pageContext.request.contextPath}/admin/logout" class="logo">
        <img src="${pageContext.request.contextPath}/resource/img/Koo_logo_400.png" alt="koo_logo">
      </a>
    </header>
    <nav>
      <a href="${pageContext.request.contextPath}/admin/functions/staffsPage" class="categories">职工管理</a>
      <a href="${pageContext.request.contextPath}/admin/functions/dishesPage" class="categories">菜单管理</a>
      <a href="${pageContext.request.contextPath}/admin/functions/billsPage" class="categories">账单管理</a>
      <a href="${pageContext.request.contextPath}/admin/functions/echartPage" class="categories">业绩统计</a>
    </nav>
  </aside>
  <div class="right">
    <iframe src="${pageContext.request.contextPath}/admin/functions/infoPage" frameborder="0" allowtransparency="true"></iframe>
  </div>
</body>
</html>
