<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
欢迎：${sessionScope.SESSION_USER.nickname} <a href="/user/logout.html">退出登录</a>
<form action="<%=request.getContextPath()%>/question/list.html" method="post" id="searchForm">
    <input type="hidden" name="pageNum" id="pageNum"/>
    问题<input type="text" name="title" value="${param.title}"/>
    <input type="submit" value="搜索"/>
</form> <a href="<%=request.getContextPath()%>/add_question.jsp">+我要提问</a>
<table border="1" cellspacing="0">
    <tr>
        <td>编号</td>
        <td>题目</td>
        <td>作者</td>
        <td>回答数</td>
        <td>更新时间</td>
        <td>发布时间</td>
    </tr>
    <c:forEach items="${pageInfo.list}" var="question">
        <tr>
            <td>${question.id}</td>
            <td><a href="<%=request.getContextPath()%>/question/detail.html?id=${question.id}">${question.title}</a></td>
            <td>${question.user.nickname}</td>
            <td>${question.replyCount}</td>
            <td><fmt:formatDate value="${question.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${question.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
    </c:forEach>
</table>
当前第${pageInfo.pageNum}页 共${pageInfo.pages}页
<!--如果是没有上一页，就不显示上一页的连接-->
<c:if test="${pageInfo.isHasPreviousPage()}">
    <a href="#" onclick="page(${pageInfo.pageNum-1})">上一页</a>
</c:if>
<!--导航栏上显示几个-->
<c:forEach items="${pageInfo.navigatepageNums}" var="nav">
    <a href="#" onclick="page(${nav})" style="${pageInfo.pageNum==nav?'color:red':''}">${nav}</a>
</c:forEach>
<!--如果没有下一页，就不限时下一页连接-->
<c:if test="${pageInfo.isHasNextPage()}">
    <a href="#" onclick="page(${pageInfo.pageNum+1})">下一页</a>
</c:if>
<input type="text" id="go" size="4" value="${param.pageNum}"><input type="button" value="跳转" onclick="goPage()"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
    function page(pageNum) {
        $("#pageNum").val(pageNum);
        $("#searchForm").submit();
    }

    function goPage() {
        var pageNum = $('#go').val();
        if (isNaN(pageNum)) {
            alert("必须输入整数")
            return;
        }
        if (pageNum > parseInt("${pageInfo.pages}")) {
            pageNum = parseInt("${pageInfo.pages}");
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        page(pageNum)
    }
</script>
</body>
</html>
