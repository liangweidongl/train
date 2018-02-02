<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-1.10.2.min.js"></script>
<form action="<%=request.getContextPath()%>/user/regist.html" method="post">
    用户名：<input type="text" name="username" onblur="check()" value="${param.username}"/><span id="message"></span><br/>
    密码：<input type="password" name="password"/><br/>
    确认密码：<input type="password" name="repwd"/><br/>
    昵称:<input type="text" name="nickname" value="${param.nickname}"/><br/>
    <input type="submit" value="注册" disabled="disabled"/>${message}
</form>
<script type="text/javascript">
    function check() {
        var username = $("input[name=username]").val();
        $.ajax({
            url: "<%=request.getContextPath()%>/user/check.html",
            data: "username=" + username,
            type: "post",
            success: function (data) {
                if (!data.status) {
                    $("input[type=submit]").attr("disabled", "disabled");
                }else{
                    $("input[type=submit]").removeAttr("disabled");
                }
                $("#message").html(data.message)
            }
        })
    }
</script>
</body>
</html>
