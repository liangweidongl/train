<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>${question.title}</h1>
<h3>${question.user.nickname} :<fmt:formatDate value="${question.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></h3>
<hr/>
${question.detail}
<hr/>
<table border="1" id="answerTable">
    <c:forEach items="${answers}" var="ans">
        <tr id="answer-${ans.id}">
            <td>${ans.user.nickname}:</td>
            <td>
                <fmt:formatDate value="${ans.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br/>
                    ${ans.content}
            </td>
            <td>
                <%--如果当前登录的用户是发布这条回答的人，或者当前登录的用户就是楼主--%>
                <c:if test="${ans.user.id==sessionScope.SESSION_USER.id or sessionScope.SESSION_USER.id==question.user.id}">
                    <a href="#" onclick="deleteAnswer(${ans.id})">删除</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<script src="<%=request.getContextPath()%>/jquery-1.10.2.min.js"></script>
<form id="form">
    <input type="hidden" name="questionId" value="${question.id}"/>
    我要回答：<textarea id="reply" name="content"></textarea>
    <input type="button" value="提交" onclick="replyQuestion()"/>
</form>
<script type="text/javascript">
    function replyQuestion() {
        if ($("#reply").val() == "") {
            return;
        }
        $.ajax({
            url: "<%=request.getContextPath()%>/answer/add.html",
            method: "post",
            data: $("#form").serialize(),
            success: function (data) {
                if (data.status) {
                    var date = new Date(data.answer.createdTime);
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
                    if (month < 10) {
                        month = "0" + month;
                    }
                    var day = date.getDate();
                    if (day < 10) {
                        day = "0" + day;
                    }
                    var time = year + "-" + month + "-" + day + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
                    var tr = "<tr id='answer-"+data.answer.id+"'><td>" + data.answer.user.nickname + ":</td><td>" + time +
                        "<br/>" + data.answer.content + "</td><td><a href=\"#\" onclick='deleteAnswer("+data.answer.id+")'>删除</a></td></tr>";
                    $("#answerTable").append(tr);
                    $("#reply").val("")
                } else {
                    alert(data.message);
                }
            }
        })
    }
    function deleteAnswer(id){
        $.ajax({
            url:"<%=request.getContextPath()%>/answer/delete.html",
            data:"id="+id+"&questionId=${question.id}",
            method:"post",
            success:function(data){
                if(data.status){
                    $("#answer-"+id).remove();
                }else{
                    alert(data.message);
                }
            }
        })
    }
</script>
</body>
</html>
