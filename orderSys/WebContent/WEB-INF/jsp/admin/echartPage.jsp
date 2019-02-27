<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
  <title>业绩统计</title>
  <script src="${pageContext.request.contextPath}/resource/js/echarts.js"></script>
  <script src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.min.js"></script>
  <style>
	#main {margin:100px auto;}
  </style>
</head>
<body>
 	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 900px;height:600px;"></div>
	<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
   	var myChart = echarts.init(document.getElementById('main'));
 // Enable data zoom when user click bar.

   	myChart.setOption({
        title:{
            text:'财务统计图'
        },
        tooltip : {
        },
        legend:{
            data:['营业额']
        },
        xAxis:{
            data:[],
            axisLabel:{
			     interval:0,//横轴信息全部显示
			     rotate:-30,//-30度角倾斜显示
			     textStyle: {
	                    color: '#fff'
	             }
            }
        },
        yAxis:{
            axisLabel: {
            	textStyle: {
                    color: '#fff'
                }
            }
        },
        series:[
            {
                name:'营业额',
                type:'bar',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#8B0000'},
                                {offset: 0.5, color: '#A52A2A'},
                                {offset: 1, color: '#A52A2A'}
                            ]
                        )
                    },
                    emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#FF2D2D'},
                                {offset: 0.7, color: '#FF2D2D'},
                                {offset: 1, color: '#EA0000'}
                            ]
                        )
                    }
                },
                data:[]
            },
        ]
	});

   	myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var dates=[];
    var price=[];

    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url :"<%=request.getContextPath()%>/bar",
        data : {},
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                for(var i=0;i<result.length;i++){
                    dates.push(result[i].timeNew);
                }
                for(var i=0;i<result.length;i++){
                    price.push(result[i].price);
                }

                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    xAxis: {
                        name:'时间',
                        data: dates
                    },
                    series: [
                    	{
                        // 根据名字对应到相应的系列
                        name: '营业额',
                        data: price
                    },
                    	]
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })



    </script>
</body>
	<script src="${pageContext.request.contextPath}/resource/js/vue.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/vue-resource.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/index.css">
	<!-- 引入组件库 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/functionPage.css">
	<script src="${pageContext.request.contextPath}/resource/js/index.js"></script>

</html>
