<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>

<%--<html>--%>
<%--<head>--%>
<%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">--%>
<%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">--%>
<%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>--%>
<script type="text/javascript">
    var toolbar = [{
        // handler: function(){alert('编辑按钮')}
    }, {
        iconCls: 'icon-add',
        text: "添加",
        handler: function () {
            $("#carouse_dd").panel("open", true);
        }
    }, '-', {
        iconCls: 'icon-delete',
        text: "删除",
        handler: function () {
            $("#carouse_dg").edatagrid("destroyRow");
        }
    }, '-', {
        iconCls: 'icon-1012333',
        text: "修改",
        handler: function () {
            var rows = $("#carouse_dg").edatagrid("getSelected");
            if (rows == null) {
                $.messager.alert('警告', '警告消息');
            } else {
                $("#carouse_dg").edatagrid("editRow", index);
            }
        }
    }, '-', {
        iconCls: 'icon-picture_save',
        text: "保存",
        handler: function () {
            $("#carouse_dg").edatagrid("saveRow");
        }
    }];


    $(function () {
        $('#carouse_dg').edatagrid({
            destroyUrl: "deleteOneCarousel",
            updateUrl: "updateCarouselStatus",
            toolbar: toolbar,
            url: "selectCarouselAll",
            fit: true,
            fitColumns: true,
            rownumbers: true, //显示行号
            pagination: true,//显示分页工具栏
            pageSize: 10,//页数初始化
            pageList: [3, 5, 10, 20],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                //console.log(rowData)
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/image/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>时间: ' + rowData.date + '</p>' +
                    '<p>描述: ' + rowData.desc + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
            destroyMsg: {
                norecord: {    // 在没有记录选择的时候执行
                    title: '提示信息',
                    msg: '没有选择内容.'
                },
                confirm: {       // 在选择一行的时候执行
                    title: '删除确认',
                    msg: '你确定要删除吗?'
                }
            },


            columns: [[
                {field: 'title', title: '标题', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    },
                    formatter: function (value, row, index) {
                        // alert(value)
                        // alert(row.status)
                        // alert(index)
                        if (value == 0) {
                            return "展示";
                        } else {
                            return "不展示";
                        }
                    }
                },
                {field: 'desc', title: '描述', width: 100},
            ]]
        });


        $('#carouse_tb1').textbox({
            buttonText: '&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;题&nbsp;&nbsp;',
            buttonAlign: 'left'
        });

        $('#carouse_tb2').textbox({
            buttonText: '&nbsp;&nbsp;描&nbsp;&nbsp;&nbsp;述&nbsp;&nbsp;',
            buttonAlign: 'left'
        });

        $('#carouse_fb').filebox({
            buttonText: '选择文件',
            buttonAlign: 'left'
        });

        $('#carouse_btn').linkbutton();

        $('#carouse_fb').validatebox({
            required: true,
        });
        $.extend($.fn.validatebox.defaults.rules, {
            minLength: {
                validator: function (value, param) {
                    return value.length > param[0];
                },
                message: '内容不能为空.',
            }
        });

    });

    function addCarousel() {
        $("#addFormCarousel").form("submit", {
            url: "addOneCarousel",
            success: function (data) {
                $("#carouse_dd").dialog("close", true);

            },
        });

    }

</script>
<%--</head>--%>
<%--<body>--%>
<table id="carouse_dg"></table>
<div id="carouse_dd" align="center">
    <form id="addFormCarousel" method="post" enctype="multipart/form-data"><br/>
        <input id="carouse_tb1" type="text" name="title" style="width:200px"/><br/><br/>
        <input id="carouse_tb2" type="text" name="desc" style="width:200px"/><br/><br/>
        <input id="carouse_fb" type="text" name="file" style="width:200px"/><br/><br/>
        <a id="carouse_btn" href="#" onclick="addCarousel()">提交</a>
    </form>
</div>
<%--</body>--%>
<%--<html>--%>