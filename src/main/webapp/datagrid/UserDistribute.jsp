<%--
  Created by IntelliJ IDEA.
  User: gaolong
  Date: 2018-12-1
  Time: 21:47:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
<script type="text/javascript">
    $.ajax({
        url: "${pageContext.request.contextPath}/listAllMan",
        // dataType: "JSON",
        // type: "post",
        success: function (data) {


            var dom = document.getElementById("map");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            option = {
                title: {
                    text: '用户分布',
                    subtext: '男女用户地区分布图',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['男', '女']
                },
                visualMap: {
                    min: 0,
                    max: 150,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: true
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: [
                    {
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.data,  //---------------------------------------------------------------
                    }, {
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.date,   ///=======================================================================
                    }

                ]
            };
            // myChart.setOption(option);
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }


        },
    });


</script>
<div id="map" style="width: 600px;height:400px;"></div>

