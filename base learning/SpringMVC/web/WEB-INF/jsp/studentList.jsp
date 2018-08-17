<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学生列表</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/item/queryitem.action" method="post">
    查询条件：
    <table width="100%" border=1>
        <tr>
            <td><a href="${pageContext.request.contextPath }/student/add.action">新增</a></td>
        </tr>
    </table>
    商品列表：
    <table width="100%" border=1>
        <tr>
            <td>学生ID</td>
            <td>学生姓名</td>
            <td>学生年龄</td>
            <td>学生生日</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${students }" var="item">
            <tr>
                <td>${item.sid }</td>
                <td>${item.sname }</td>
                <td>${item.age }</td>
                <td><fmt:formatDate value="${item.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><a href="${pageContext.request.contextPath }/itemEdit.action?sid=${item.sid}">修改</a></td>
            </tr>
        </c:forEach>

    </table>
</form>
</body>

</html>