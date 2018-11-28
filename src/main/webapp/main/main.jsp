<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
        $(function () {
            //注册弹窗的样式=======(START)======
            $("#updatePassword").dialog({
                //modal:true,
                title: "修改密码",
                resizable: true,
                closed: true,
                width: 280,
                height: 150,
            });
            //注册弹窗的样式=======(END)======
            // alert("1");
            $.ajax({
                url: "${pageContext.request.contextPath}/selectAllMenu",
                dataType: "JSON",
                type: "post",
                success: function (dta) {
                    // var menu = "";
                    // for (var i = 0; i < dta.menus.length; i++) {
                    //     for (var j = 0; j < dta.menus[i].listManu.length; j++) {
                    //         if (dta.menus[i].id == dta.menus[i].listManu[j].parent_id) {
                    //             // console.log(dta.menus[i].listManu[j].title);
                    //             // console.log(dta.menus[i].listManu[j].iconcls);
                    //             //menu+=dta.menus[i].listManu[j].title;
                    //             menu += "<a id=\"btn\" href=\"#\" class=\"easyui-linkbutton\"  data-options=\"iconCls:'" + dta.menus[i].listManu[j].iconcls + "',plain:'true'\">" + dta.menus[i].listManu[j].title + "</a><br/>";
                    //             //menu+="<span data-options=\"fit:true\">"+dta.menus[i].listManu[j].title+"</span>";
                    //         }
                    //     }
                    //     console.log("menu" + menu);
                    //     $('#aa').accordion('add', {
                    //         title: dta.menus[i].title,
                    //         iconCls: dta.menus[i].iconcls,
                    //         content: menu,
                    //         selected: false
                    //     });
                    //
                    //     menu = "";
                    //
                    // }
                    $.each(dta, function (index, first) {
                        // console.log(dta);
                        // console.log(index);
                        // console.log(first.title);
                        var menu = "";
                        $.each(first.listManu, function (index1, second) {
                            // console.log(second);

                            menu += "<div style=\"text-align: center\"><a id=\"btn\" href=\"#\" onclick=\"addTabs('" + second.title + "','" + second.url + "','" + second.iconcls + "')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'" + second.iconcls + "'\">" + second.title + "</a></div>";

                        }),
                            $('#aa').accordion('add', {
                                title: first.title,
                                iconCls: first.iconcls,
                                content: menu,
                                selected: false
                            });
                    });
                },
            });

        });

        //点击注册，显示（注册）窗口=======(START)======
        function update() {
            $("#updatePassword").panel("open", true);
        };

        //点击注册，显示（注册）窗口=======(END)======

        function addTabs(title, url, iconcls) {
            console.log(title);
            var bool = $('#tt').tabs("exists", title);
            if (bool) {
                //（按标题）选中一个已经存在的选项卡
                $('#tt').tabs("select", title);
            } else {
                // 添加一个未选中状态的选项卡面板
                $('#tt').tabs('add', {
                    title: title,
                    closable: true,
                    href: "${pageContext.request.contextPath}/datagrid/carousel.jsp",
                    selected: true,
                    //...
                });
            }


        }


    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${sessionScope.admins.name}
        &nbsp;<a href="#" class="easyui-linkbutton" onclick="update()" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a
                href="${pageContext.request.contextPath}/login.jsp"
                class="easyui-linkbutton"
                data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">

    </div>
</div>
<!-- 用户注册界面 (logon)弹窗-->
<div id="updatePassword" align="center">
    <form id="myformes">
        <table align="center" border="1xp" cellspacing="0">
            <tr align="center">
                <td>原密码:</td>
                <td><input id="usees" type="password" name="password" onblur="yonghues()"/></td>
            </tr>
            <tr align="center">
                <td>新密码:</td>
                <td><input id="pwes" type="password" name="passwords" onblur="mimaes()"/></td>
            </tr>
            <tr align="center">
                <td>确认新密码:</td>
                <td><input id="pwess" type="password" onblur="querenmimaes()"/></td>
            </tr>
            <tr align="center">
                <td colspan="2"><a id="zhuce" type="button" class="easyui-linkbutton" onclick="doLogon()">注册</a></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>