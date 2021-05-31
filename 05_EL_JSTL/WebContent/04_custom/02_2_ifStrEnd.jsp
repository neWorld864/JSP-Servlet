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
	<%
		String str1 = request.getParameter("num1");
		String str2 = request.getParameter("num2");
	%>
	스크립트릿 : <%= str1 == str2 %>
	
	<br>
	
	<c:if test="${ param.num1 == param.num2 }">
		${ param.num1 }과 ${ param.num2 }는 같습니다
	</c:if>
	<c:if test="${ param.num1 != param.num2 }">
		${ param.num1 }과 ${ param.num2 }는 같지 않습니다
	</c:if>
	
	<br>
	
	<c:if test="${ param.num1 eq param.num2 }">
		${ param.num1 }과 ${ param.num2 }는 같습니다
	</c:if>
	<c:if test="${ param.num1 ne param.num2 }">
		${ param.num1 }과 ${ param.num2 }는 같지 않습니다
	</c:if>
	
	<br>
	
	<c:if test="${ param.num1.equals(param.num2) }">
		${ param.num1 }과 ${ param.num2 }는 같습니다
	</c:if>
	<c:if test="${ !param.num1.equals(param.num2) }">
		${ param.num1 }과 ${ param.num2 }는 같지 않습니다
	</c:if>
	
	
</body>
</html>