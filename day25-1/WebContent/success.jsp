<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<%-- 可能登陆 --%>
	<c:if test="${not empty tuser}">
		欢迎您登陆${tuser.username}
	</c:if>
	
	<%-- 可能没有登陆 --%>
	<c:if test="${empty tuser}">
		<a href="login.jsp">对不起 请登陆</a>
	</c:if>
</body>
</html>