<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
  <title>账单管理</title>
</head>
<body>
	<div id="app">
		<!-- 表格 -->
		<div class="table">
			<template>
				<el-table
					:data="tableData"
					style="width: 600px; float: left; margin: 0 auto 0 5%"
					max-height="800"
					border>
					<el-table-column
						prop="bill_number"
						label="订单ID"
						sortable
						width="150px">
					</el-table-column>
					<el-table-column
						prop="price"
						label="总价"
						sortable
						width="100px">
					</el-table-column>
					<el-table-column
						prop="time"
						label="完成时间"
						sortable
						width="200px">
					</el-table-column>
					<el-table-column label="操作">
			      <template slot-scope="scope">
			        <el-button
			          size="mini"
								type="primary"
			          @click="showDetail(scope.$index, scope.row)">查看明细</el-button>
			      </template>
  			  </el-table-column>
				</el-table>
			</template>
			<template>
				<el-table
					:data="detailData"
					style="width: 400px; float: left; margin: 0 auto 0 5%"
					max-height="800"
					border>
					<el-table-column
			      type="index"
			      :index="1"
						width="50px">
			    </el-table-column>
					<el-table-column
						prop="dish_name"
						label="菜名"
						sortable
						width="200px">
					</el-table-column>
					<el-table-column
						prop="num"
						label="数量"
						sortable>
					</el-table-column>
				</el-table>
			</template>
		</div>

	</div>
</body>
	<script src="${pageContext.request.contextPath}/resource/js/vue.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/vue-resource.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/index.css">
	<!-- 引入组件库 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/functionPage.css">
	<script src="${pageContext.request.contextPath}/resource/js/index.js"></script>
	<script>
    new Vue({
			el: '#app',
      data: {
        tableData: [],
				detailData: []
      },
			created: function() {
				this.getTableDate();
			},
			methods: {
				getTableDate: function() {
					//发送get请求表数据
					var url="${pageContext.request.contextPath}/bills";
					this.$http.get(url).then((response) => {
            this.tableData = eval(response.data)
          });
				},

				showDetail: function(index, row) {
					this.detailData=row.list_json;
				}
			}
    })
  </script>
</html>
