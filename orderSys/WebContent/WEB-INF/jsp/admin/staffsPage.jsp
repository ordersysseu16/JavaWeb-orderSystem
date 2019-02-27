<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
  <title>职工管理</title>
</head>
<body>
	<div id="app">
		<!-- 表格 -->
		<div class="table">
			<template>
				<el-table
					:data="tableData"
					style="width: 1000px; margin: auto"
					max-height="800"
					border>
					<el-table-column
						prop="id"
						label="ID"
						sortable
						width="100px">
					</el-table-column>
					<el-table-column
						prop="name"
						label="姓名"
						sortable
						width="250px">
					</el-table-column>
					<el-table-column
						prop="position"
						label="职位"
						width="250px"
						:filters="[{text: '厨师', value: '厨师'}, {text: '服务员', value: '服务员'}]"
      			:filter-method="filterHandler">
					</el-table-column>
					<el-table-column
						prop="permission"
						label="权限"
						width="100px">
					</el-table-column>
					<el-table-column label="操作">
			      <template slot-scope="scope">
			        <el-button
			          size="mini"
								type="primary"
			          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
			        <el-button
			          size="mini"
			          type="danger"
			          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
			      </template>
  			  </el-table-column>
				</el-table>
			</template>
			<!--新建按钮-->
			<template>
				<el-button class="create_button" @click="handleCreate" type="primary" icon="el-icon-plus" circle></el-button>
			</template>
		</div>
		<!-- 新建弹窗 -->
		<template>
			<el-dialog title="创建" :visible.sync="dialogCreateVisible">
				<el-form :model="editStaff" :rules="rules_create">
					<el-form-item label="名字" prop="name">
						<el-input v-model="editStaff.name" auto-complete="off"></el-input>
					</el-form-item>
					<el-form-item label="擅长菜id (留空为服务员)" prop="cook">
						<el-input v-model="editStaff.cook" auto-complete="off"></el-input>
					</el-form-item>
					<el-form-item label="权限" prop="permission">
						<el-input v-model="editStaff.permission" auto-complete="off"></el-input>
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="dialogCreateVisible = false">取 消</el-button>
					<el-button type="primary" @click="createDo">确 定</el-button>
				</div>
			</el-dialog>
		</template>
		<!-- 修改弹窗 -->
		<template>
	    <el-dialog title="修改" :visible.sync="dialogEditVisible">
	      <el-form :model="editStaff">
	        <el-form-item label="名字" prop="name">
	          <el-input v-model="editStaff.name" auto-complete="off"></el-input>
	        </el-form-item>
	        <el-form-item label="擅长菜id (留空为服务员)" prop="cook">
	          <el-input v-model="editStaff.cook" auto-complete="off"></el-input>
	        </el-form-item>
	        <el-form-item label="权限" prop="permission">
	          <el-input v-model="editStaff.permission" auto-complete="off"></el-input>
	        </el-form-item>
	      </el-form>
	      <div slot="footer" class="dialog-footer">
	        <el-button @click="editCause">取 消</el-button>
	        <el-button type="primary" @click="editDo">确 定</el-button>
	      </div>
	    </el-dialog>
		</template>
		<!--删除弹窗-->
		<template>
			<el-dialog title="提示" :visible.sync="dialogDeleteVisible">
			  <span>确定删除id={{deleteStaff.id}}的职工?</span>
			  <span slot="footer" class="dialog-footer">
			    <el-button @click="dialogDeleteVisible = false">取 消</el-button>
			    <el-button type="primary" @click="deleteDo">确 定</el-button>
			  </span>
			</el-dialog>
		</template>
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
				dialogCreateVisible:false,
				dialogEditVisible:false,
				dialogDeleteVisible:false,
				listIndex:'',
        editStaff:{
          name:'',
					cook:'',
					permission:'',
        },
				deleteStaff:{id:''},
        tableData: [],
				rules_create: {
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { type: 'string', message: '请输入合理的姓名', trigger: 'change' }
          ],
          // cook: [
          //   { type: 'string', pattern: '/^(\d*/)*\d*$/', message: '请用"/"间隔各菜品id', trigger: 'change' }
          // ],
          permission: [
						{ required: true, message: '请输入权限', trigger: 'blur' },
            { type: 'string', message: '请输入合理的权限', trigger: 'change'  }
          ]
				},
				// rules_edit: {
        //   name: [
        //     { type: 'string', message: '请输入合理的姓名', trigger: 'change' }
        //   ],
        //   // cook: [
        //   //   { type: 'string', pattern: '/^(\d*/)*\d*$/', message: '请用"/"间隔各菜品id', trigger: 'change' }
        //   // ],
        //   permission: [
        // 	  { type: 'string', message: '请输入合理的权限', trigger: 'change'  }
        //   ]
        // }
      },
			created: function() {
				this.getTableDate();
			},
			methods: {
				getTableDate: function() {
					//发送get请求表数据
					var url="${pageContext.request.contextPath}/staffs";
					this.$http.get(url).then((response) => {
            this.tableData = eval(response.data)
          });
				},

				createDo: function() {
					//发送post请求创建表数据
					var url="${pageContext.request.contextPath}/staff";
					this.$http.post(url,this.editStaff,{
							emulateJSON:true
						}).then((response) => {
							this.getTableDate();
						});
						//关闭弹窗
						this.dialogCreateVisible=false;
						//清空数据
						this.editStaff = {};
				},

				editDo: function() {
					//发送post请求修改表数据
					var url="${pageContext.request.contextPath}/staff/"+this.editStaff.id;
					this.$http.post(url,this.editStaff,{
							emulateJSON:true
						}).then((response) => {
							this.getTableDate();
						});
          //关闭弹窗
          this.dialogEditVisible=false;
					//清空数据
					this.editStaff = {};
				},

				deleteDo: function() {
					//发送post请求修改表数据
					var url="${pageContext.request.contextPath}/staff/"+this.deleteStaff.id;
					this.$http.delete(url).then((response) => {
						//根据索引，移除行
						let index=this.listIndex;
						this.getTableDate();
					});
					//关闭弹窗
					this.dialogDeleteVisible=false;
				},

				editCause: function() {
					this.dialogEditVisible = false;
					this.editStaff = {};
				},

				filterHandler(value, row, column) {
	        const property = column['property'];
	        return row[property] === value;
    	  },

				handleCreate() {
					//清楚数据
					this.editStaff = {};
	        //显示弹窗
	        this.dialogCreateVisible=true;
				},

				handleEdit(index, row) {
					//记录索引
	        this.listIndex=index;
	        //记录数据
	        this.editStaff=JSON.parse(JSON.stringify(row));
	        //显示弹窗
	        this.dialogEditVisible=true;
				},

				handleDelete(index, row) {
					//记录索引
	        this.listIndex=index;
					//记录id
					this.deleteStaff.id = row.id;
	        //显示弹窗
	        this.dialogDeleteVisible=true;
				}
			}
    })
  </script>
</html>
