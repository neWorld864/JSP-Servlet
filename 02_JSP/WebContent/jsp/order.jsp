<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pizza = (String)request.getAttribute("pizza");
	String topping = (String)request.getAttribute("topping");
	String side = (String)request.getAttribute("side");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>주문내역</h2>
	피자는 <span style="color: red;"><%= pizza %></span>,&nbsp;
	토핑은 <span style="color: green;"><%= topping %></span>,&nbsp;
	사이드는 <span style="color: blue;"><%= side %></span> 주문하셨습니다.
</body>
</html>