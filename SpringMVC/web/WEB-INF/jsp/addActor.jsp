<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增演员</title>

</head>
<body>
<form id="itemForm" action="${pageContext.request.contextPath }/addActor.action" method="post"
      enctype="multipart/form-data">
    新增演员：
    <table width="100%" border=1>
        <tr>
            <td>演员姓名</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>演员图片</td>
            <td>
                <input type="file" name="file"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>
</body>

</html>