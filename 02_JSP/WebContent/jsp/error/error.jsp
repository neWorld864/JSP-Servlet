<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 에러가 나면 이 페이지가 뜨도록 -> isErrorPage 설정해주어야 함 -->
	<h1 style="color: red;">에러다, 에러다, 에러야!!</h1>
	<%= exception %>
	
</body>
</html>