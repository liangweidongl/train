<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<form action="/user/login.html" method="post">
    用户名：<input type="text" name="username" value="${param.username}"/><br/>
    密码：<input type="password" name="password"/><br/>
    <input type="submit" value="登录"/>${message}
</form>
</body>
</html>
