<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增学生</title>

</head>
<body> 
	<form id="itemForm"	action="${pageContext.request.contextPath }/addStudent.action" method="post">
		新增学生：
		<table width="100%" border=1>
			<tr>
				<td>学生姓名</td>
				<td><input type="text" name="student.sname" /></td>
			</tr>
			<tr>
				<td>学生年龄</td>
				<td><input type="text" name="student.age" /></td>
			</tr>
			<tr>
				<td>学生生日</td>
				<td><input type="text" name="student.birthday"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>