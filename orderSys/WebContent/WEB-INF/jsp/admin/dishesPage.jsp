<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
  <title>菜单管理</title>
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
						label="菜名"
						sortable
						width="200px">
					</el-table-column>
					<el-table-column
						prop="price"
						label="单价"
						sortable
						width="100px">
					</el-table-column>
					<el-table-column
						prop="time"
						label="烹饪时间"
						sortable
						width="150px">
					</el-table-column>
					<el-table-column
						prop="type"
						label="类别"
						:filters="[{text: '主食', value: '主食'}, {text: '酒水', value: '酒水'}, {text: '小吃', value: '小吃'}]"
      			:filter-method="filterHandler"
						width="150px">
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
				<el-form :model="editDish"  :rules="rules_create">
					<el-form-item label="菜名" prop="name">
						<el-input v-model="editDish.name" auto-complete="off"></el-input>
					</el-form-item>
					<el-form-item label="单价" prop="price">
						<el-input v-model="editDish.price" auto-complete="off"></el-input>
					</el-form-item>
					<el-form-item label="烹饪时间" prop="time">
						<el-input v-model="editDish.time" auto-complete="off"></el-input>
					</el-form-item>
					<el-form-item label="类别" prop="type">
						<el-input v-model="editDish.type" auto-complete="off"></el-input>
					</el-form-item>
					<!-- 图片上传 -->
					<el-form-item label="图片">
						<el-upload
						  class="upload-demo"
						  action="${pageContext.request.contextPath}/resource/picture_dish/"
							ref="upload_create"
						  :limit="1"
						  :file-list="fileList"
							:auto-upload="false"
							:http-request="inputFile">
						  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
						  <div slot="tip" class="el-upload__tip">上传文件不超过1mb</div>
						</el-upload>
					</el-form-item>

					<el-form-item label="描述" prop="description">
						<el-input v-model="editDish.description" auto-complete="off"></el-input>
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
				<el-form :model="editDish">
					<el-form-item label="菜名" prop="name">
						<el-input v-model="editDish.name" auto-complete="off"></el-input>
					</el-form-item>
					<el-form-item label="单价" prop="price">
						<el-input v-model="editDish.price" auto-complete="off"></el-input>
					</el-form-item>
					<el-form-item label="烹饪时间" prop="time">
						<el-input v-model="editDish.time" auto-complete="off"></el-input>
					</el-form-item>
					<el-form-item label="类别" prop="type">
						<el-input v-model="editDish.type" auto-complete="off"></el-input>
					</el-form-item>
					<!-- 图片上传 -->
					<el-form-item label="图片">
						<el-upload
							class="upload-demo"
							action="${pageContext.request.contextPath}/resource/picture_dish/"
							ref="upload_edit"
							:limit="1"
							:file-list="fileList"
							:auto-upload="false"
							:http-request="inputFile">
							<el-button slot="trigger" size="small" type="primary">选取文件</el-button>
							<div slot="tip" class="el-upload__tip">上传文件不超过1mb</div>
						</el-upload>
					</el-form-item>

					<el-form-item label="描述" prop="description">
						<el-input v-model="editDish.description" auto-complete="off"></el-input>
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
			  <span>确定删除id={{deleteDish.id}}的菜品?</span>
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
				fileList:[],
				formData:new FormData(),
        editDish:{
          name:'',
					price:'',
					time:'',
					image:'',
					type:'',
					description:'',
        },
				deleteDish:{id:''},
        tableData:[],
				rules_create: {
          name: [
            { required: true, message: '请输入菜名', trigger: 'blur' },
            { type: 'string', message: '请输入合理的菜名', trigger: 'change' }
          ],
          price: [
						{ required: true, message: '请输入价格', trigger: 'blur' },
            { type: 'float', message: '请输入合理的价格', trigger: 'change' }
          ],
          time: [
						{ required: true, message: '请输入烹饪时间', trigger: 'blur' },
            { type: 'integer', message: '请输入合理的烹饪时间', trigger: 'change'  }
          ],
					type: [
						{ required: true, message: '请输入类别', trigger: 'blur' },
            { type: 'string', message: '请输入合理的类别', trigger: 'change'  }
          ],
					description: [
						{ required: true, message: '请输入描述', trigger: 'blur' },
            { type: 'string', message: '请输入合理的描述', trigger: 'change'  }
          ]
				},
				// rules_edit: {
				// 	name: [
        //     { type: 'string', message: '请输入合理的菜名', trigger: 'change' }
        //   ],
        //   price: [
        //     { type: 'float', message: '请输入合理的价格', trigger: 'change' }
        //   ],
        //   time: [
        //     { type: 'integer', message: '请输入合理的烹饪时间', trigger: 'change'  }
        //   ],
				// 	type: [
        //     { type: 'string', message: '请输入合理的类别', trigger: 'change'  }
        //   ],
				// 	description: [
        //     { type: 'string', message: '请输入合理的描述', trigger: 'change'  }
        //   ]
        // }
      },
			created: function() {
				this.getTableDate();
			},
			methods: {
				getTableDate: function() {
					//发送get请求表数据
					var url="${pageContext.request.contextPath}/dishes";
					this.$http.get(url).then((response) => {
            this.tableData = eval(response.data)
          });
				},

				createDo: function() {
					this.$refs.upload_create.submit();
					let value = this.editDish;
					Object.keys(value).forEach(key => this.formData.append(key, value[key]));
					//发送post请求创建表数据
					var url="${pageContext.request.contextPath}/dish";
					this.$http.post(url,this.formData,{
            headers: {
              "Content-Type": "multipart/form-data"
            }
					}).then((response) => {
							this.getTableDate();
						});
						//关闭弹窗
	          this.dialogCreateVisible=false;
						//清空数据
						this.editDish = {};
						this.formData=new FormData();
						this.fileList=[];
				},

				editDo: function() {
					this.$refs.upload_edit.submit();
					let value = this.editDish;
					Object.keys(value).forEach(key => this.formData.append(key, value[key]));
					//发送post请求修改表数据
					var url="${pageContext.request.contextPath}/dish/"+this.editDish.id;
					this.$http.post(url,this.formData,{
							headers: {
								"Content-Type": "multipart/form-data"
							}
						}).then((response) => {
							this.getTableDate();
						});
          //关闭弹窗
          this.dialogEditVisible=false;
					//清空数据
					this.editDish = {};
					this.formData=new FormData();
					this.fileList=[];
				},

				deleteDo: function() {
					//发送post请求修改表数据
					var url="${pageContext.request.contextPath}/dish/"+this.deleteDish.id;
					this.$http.delete(url).then((response) => {
						this.getTableDate();
					});
					//关闭弹窗
					this.dialogDeleteVisible=false;
				},

				editCause: function() {
					this.dialogEditVisible = false;
					this.editDish = {};
				},

				filterHandler(value, row, column) {
					const property = column['property'];
					return row[property] === value;
				},

				handleCreate() {
					//清楚数据
					this.editDish = {};
	        //显示弹窗
	        this.dialogCreateVisible=true;
				},

				handleEdit(index, row) {
					//记录索引
	        this.listIndex=index;
	        //记录数据
	        this.editDish=JSON.parse(JSON.stringify(row));
	        //显示弹窗
	        this.dialogEditVisible=true;
				},

				handleDelete(index, row) {
					//记录索引
	        this.listIndex=index;
					//记录id
					this.deleteDish.id = row.id;
	        //显示弹窗
	        this.dialogDeleteVisible=true;
				},

				inputFile(param) {
					fileObj = param.file;
				  const isLt1M = fileObj.size / 1024 / 1024 < 1;

				  if (!isLt1M) {
				 	  this.$message.error('上传头像图片大小不能超过 1MB!');
				  }

					this.formData.append('file',fileObj);
				  return isLt1M;
			  },
			}
    })
  </script>
</html>
