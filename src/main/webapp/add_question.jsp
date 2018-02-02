<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<link href="<%=request.getContextPath()%>/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/jquery-1.10.2.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/kindeditor-4.1.10/lang/zh_CN.js"></script>

<form action="/question/add.html" method="post">
    标题：<input type="text" name="title"/><br/>
    <textarea name="detail" id="detail"></textarea>
    <input type="submit" value="提问"/>
</form>
<script type="text/javascript">
    var kingEditorParams ={
        filePostName  : "file",//指定上传文件参数名称
        uploadJson:'<%=request.getContextPath()%>/question/upload.html',//指定上传文件请求的url。
        dir:"image"//上传类型，分别为image、flash、media、file
    }
    var editor;
    $(function () {
        editor=KindEditor.create($("#detail"),kingEditorParams);
    });
</script>
</body>
</html>
