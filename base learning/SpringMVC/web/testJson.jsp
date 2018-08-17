<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增学生</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var params = '{"id": 1,"name": "郑爽","image": "123456.jpg"}';
            $.ajax({
                url : "${pageContext.request.contextPath }/json.action",
                data : params,
                contentType : "application/json;charset=UTF-8",//发送数据的格式
                type : "post",
                dataType : "json",//回调
                success : function(data){
                    alert(data.name)
                    console.log(data)
                }

            });
        })
    </script>

</head>
<body>
<form id="form" action="${pageContext.request.contextPath }/testList.action" method="post">
    <input type="text" name="students[0].sname"/>
    <input type="text" name="students[0].age"/><br/>
    <input type="text" name="students[1].sname"/>
    <input type="text" name="students[1].age"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>

</html>