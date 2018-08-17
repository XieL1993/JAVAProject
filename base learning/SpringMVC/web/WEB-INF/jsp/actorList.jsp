<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>演员列表</title>
</head>
<body>
<form>
    <table width="100%" border=1>
        <tr>
            <td><a href="${pageContext.request.contextPath }/actor/add.action">新增</a></td>
        </tr>
    </table>
    演员列表：
    <table width="100%" border=1>
        <tr>
            <td>演员ID</td>
            <td>演员姓名</td>
            <td>演员图片</td>
        </tr>
        <c:forEach items="${list}" var="item">
            <tr>
                <td>${item.id }</td>
                <td>${item.name }</td>
                <td><c:if test="${item.image !=null}">
                    <img src="/image/${item.image}" width=100 height=100/>
                    <br/>
                </c:if></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>

</html>