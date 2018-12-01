<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>

<script type="text/javascript">

    var toolbar = [{
        // handler: function(){alert('编辑按钮')}
    }, {
        iconCls: 'icon-tip',
        text: "专辑详情",
        handler: function () {
            var row = $('#albumTable').treegrid("getSelected");
            if (row != null) {
                if (row.score != null) {
                    $('#detailsDiv').dialog("open");
                    $("#detailsForm").form("load", row);
                    $("#album_img").prop("src", "${pageContext.request.contextPath}/image/" + row.coverimg);
                } else {
                    $.messager.alert('提示', '请选择专辑');

                }
            } else {
                $.messager.alert('提示', '请选择专辑');

            }
        }
    }, '-', {
        iconCls: 'icon-add',
        text: "添加专辑",
        handler: function () {

        }
    }, '-', {
        iconCls: 'icon-add',
        text: "添加章节",
        handler: function () {
            var row = $('#albumTable').treegrid("getSelected");
            console.log(row);
            if (row != null) {
                if (row.score != null) {
                    $('#album_dd').dialog("open");
                    $("#album_id").val(row.id);

                } else {
                    $.messager.alert('提示', '请选择专辑');

                }
            } else {
                $.messager.alert('提示', '请选择专辑');

            }
        }
    }, '-', {
        iconCls: 'icon-20130406125519344_easyicon_net_16',
        text: "下载音频",
        handler: function () {
            var row = $('#albumTable').treegrid("getSelected");
            if (row != null) {
                if (row.parentId != null) {
                    location.href = "${pageContext.request.contextPath}/downloadFile?downpath=" + row.downpath + "&title=" + row.title;
                    console.log(row);
                    // $.ajax({
                    //     url: "downloadFile",
                    //     data: "downpath=" + row.downpath + "&title=" + row.title,
                    // });

                } else {
                    $.messager.alert('提示', '请选择章节');

                }
            } else {
                $.messager.alert('提示', '请选择章节');

            }

        }
    }];

    $(function () {

        $('#albumTable').treegrid({
            onDblClickRow: function (row) {
                if (row.parentId != null) {
                    $("#audio_b").dialog("open");
                    $("#buttonsssss").prop("src", "${pageContext.request.contextPath}/audio/" + row.downpath);
                } else {
                    $.messager.alert('提示', '请选择章节');
                }
            },
            toolbar: toolbar,
            lines: false,
            animate: true,
            method: "post",
            url: 'listAllAlbum',
            idField: 'id',
            treeField: 'title',
            rownumbers: true, //显示行号
            pagination: true,//显示分页工具栏
            pageSize: 10,//页数初始化
            pageList: [3, 5, 10, 20],
            fit: true,
            fitColumns: true,
            columns: [[
                {field: 'title', title: '名字', width: 120},
                {field: 'downpath', title: '下载路径', width: 100},
                {field: 'size', title: '章节大小', width: 100},
                {field: 'duration', title: '章节时长', width: 100},
            ]]
        });


        $('#detailsDiv').dialog({
            title: '专辑详情',
            width: 700,
            height: 330,
            closed: true,
            cache: false,
            resizable: true,
            modal: true
        });
        $('#audio_b').dialog({
            title: '专辑详情',
            width: 400,
            height: 300,
            closed: true,
            cache: false,
            resizable: true,
            modal: true
        });

        $('#album_dd').dialog({
            title: '添加信息',
            width: 400,
            height: 200,
            closed: true,
            cache: false,
            href: "",
            modal: true,
            buttons: [{
                text: '确认',
                handler: function () {
                    $("#addFormAudio").form("submit", {
                        url: "addOneAudio",
                        success: function (data) {
                            $("#album_dd").dialog("close", true);

                        },
                    });

                }
            }, {
                text: '取消',
                handler: function () {
                    $("#album_dd").dialog("close", true);
                }
            }],

        });

        $('#album_tb').textbox({
            buttonText: '&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;题&nbsp;&nbsp;',
            buttonAlign: 'left'
        });

        $('#album_fb').filebox({
            buttonText: '选择文件',
            buttonAlign: 'left'
        });
        $("#album_btn").linkbutton();

    });

    function addAudio() {
        $("#addFormAudio").form("submit", {
            url: "addOneAudio",
            success: function (data) {
                $("#album_dd").dialog("close", true);

            },
        });
    }

</script>
<table id="albumTable"></table>

<div id="detailsDiv" align="center">
    <form id="detailsForm" method="post"><br/><br/>
        标题 : <input class="easyui-textbox" type="text" name="title" data-options="required:true"/>
        数量 : <input class="easyui-textbox" type="text" name="count" data-options="required:true'"/><br/><br/>
        评分 : <input class="easyui-textbox" type="text" name="score" data-options="required:true'"/>
        作者 : <input class="easyui-textbox" type="text" name="author" data-options="required:true'"/><br/><br/>
        播音 : <input class="easyui-textbox" type="text" name="broadcast" data-options="required:true'"/>
        简介 : <input class="easyui-textbox" type="text" name="brief" data-options="required:true'"/><br/><br/>
        时间 : <input class="easyui-textbox" type="text" name="publishdate" data-options="required:true'"/>
        图片 : <img src="" id="album_img" width="150" height="100"><br/><br/>
    </form>
</div>
<div id="album_dd" align="center">
    <form id="addFormAudio" method="post" enctype="multipart/form-data"><br/>
        <input id="album_tb" type="text" name="title" style="width:200px"/><br/><br/>
        <input id="album_id" type="hidden" name="id" value="" style="width:200px"/><br/><br/>
        <input id="album_fb" type="text" name="file" style="width:200px"/><br/><br/>
        <%--<a id="album_btn" href="#" onclick="addAudio()">提交</a>--%>
    </form>

</div>
<div id="audio_b">
    <audio src="" id="buttonsssss" autoplay="autoplay" controls="controls" loop="loop"></audio>
</div>