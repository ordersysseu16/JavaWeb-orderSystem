<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/cookPage.css">
  <title>后厨</title>
</head>
<body>
	<header>
		KOO后厨
	</header>
	<div id="app">
		<ul id="cooks">
		  <li v-for="cook in cooks">
				<div class="cookInfo">
					{{cook.name}}
				</div>
				<button type="button" @click='finishCook(cook)'>
					<span>完成</span>
				</button>
				<ul id="cookList">
					<li v-for="item in cook.cookList">
						<div class="itemInfo">
							{{item.dish_name}}<br/>{{item.num}}份
						</div>
					</li>
				</ul>
		  </li>
		</ul>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resource/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/vue-resource.js"></script>
<script>
	new Vue({
		el:'#app',
		data: {
			cooks:[],
		},
		created: function() {
			var _this = this;
			//发送get请求,初始化厨师列表头
			var url="${pageContext.request.contextPath}/cooks";
			_this.$http.get(url).then((response) => {
				_this.cooks = eval(response.data);
				_this.cooks.forEach(function(cook, index, array) {
					_this.getCookList(cook);
				});
			});
		},
		methods: {
			getCookList: function(cook) {
				//发送get请求,获取厨师烹饪列表
				var url="${pageContext.request.contextPath}/cookList/"+cook.id;
				this.$http.get(url).then((response) => {
					console.log(eval(response.data));
					Vue.set(cook, 'cookList', eval(response.data));
				});
			},

			finishCook: function(cook) {
				//发送put请求,修改烹饪列表
				var url="${pageContext.request.contextPath}/cookList/"+cook.id;
				this.$http.put(url).then((response) => {
					this.getCookList(cook);
				});
			}
		},
	})
</script>
</html>
