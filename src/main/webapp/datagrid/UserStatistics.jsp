<%--
  Created by IntelliJ IDEA.
  User: gaolong
  Date: 2018-12-1
  Time: 19:48:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        url: "${pageContext.request.contextPath}/selectWeekOneLogonUser",
        // dataType: "JSON",
        // type: "post",
        success: function (dta) {
            console.log(dta.date);


            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '近五周的用户注册统计'
                },
                tooltip: {},
                legend: {
                    data: ['销量柱', '销量线']
                },
                xAxis: {
                    data: ["第一周", "第二周", "第三周", "第四周", "第五周"]
                },
                yAxis: {},
                series: [{
                    name: '销量柱',
                    type: 'bar',
                    data: dta.date,
                }, {
                    name: '销量线',
                    type: 'line',
                    data: dta.date,
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);


        },
    });

</script>

<div id="main" style="width: 600px;height:400px;"></div>

