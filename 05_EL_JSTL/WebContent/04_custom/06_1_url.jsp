<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="06_2_urlEnd.jsp?pname=귀걸이&pcount=10&option=귀찌&option=알러지x">쿼리스트링</a>
	
	<%-- <c:url>안에는 param 태그가 들어갈 수 있다 --%>
	<%-- 목표지점은 value로 해서 넣음 --%>
	<c:url var="u" value="06_2_urlEnd.jsp">
		<c:param name="pname" value="귀걸이"/>
		<c:param name="pcount" value="10"/>
		<c:param name="option" value="귀찌"/>
		<c:param name="option" value="알러지x"/>
	</c:url>
	<a href="${ u }">c:url</a>
	
	
	
	
	
	
	
</body>
</html>