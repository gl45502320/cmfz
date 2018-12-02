<%--
  Created by IntelliJ IDEA.
  User: gaolong
  Date: 2018-12-1
  Time: 18:00:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-20130406125519344_easyicon_net_16',
        text: "用户信息导入",
        handler: function () {

        }
    }, '-', {
        iconCls: 'icon-20130406125647919_easyicon_net_16',
        text: "用户信息导出",
        handler: function () {
            location.href = "${pageContext.request.contextPath}/exportUserMessage";
            // $.ajax({
            //     url: "exportUserMessage",
            //     type:"get",
            //     success: function (data) {
            //         console.log(data)
            //         if (data) {
            //             $.messager.alert('下载提示', '下载成功');
            //         } else {
            //             $.messager.alert('下载提示', '下载失败');
            //
            //         }
            //     },
            // });
        }
    }]

    $('#dg').datagrid({
        url: 'selectAllUser',
        fit: true,
        toolbar: toolbar,
        fitColumns: true,
        rownumbers: true, //显示行号
        pagination: true,//显示分页工具栏
        pageSize: 10,//页数初始化
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'phonenum', title: '电话', width: 100, align: 'center'},
            {field: 'username', title: '用户名', width: 100, align: 'center'},
            {field: 'password', title: '密码', width: 100, align: 'center'},
            {field: 'salt', title: '盐值', width: 100, align: 'center'},
            {field: 'dharmname', title: '法名', width: 100, align: 'center'},
            {field: 'province', title: '省份', width: 100, align: 'center'},
            {field: 'city', title: '城市', width: 100, align: 'center'},
            {field: 'sex', title: '性别', width: 100, align: 'center'},
            {field: 'sign', title: '签名', width: 100, align: 'center'},
            {field: 'headPic', title: '用户头像', width: 100, align: 'center'},
            {field: 'status', title: '状态', width: 100, align: 'center'},
            {field: 'date', title: '时间', width: 100, align: 'center'},
        ]]
    });
</script>
<table id="dg"></table>
