<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pizza = (String)request.getAttribute("pizza");
	String topping = (String)request.getAttribute("topping");
	String side = (String)request.getAttribute("side");
	int total = (int)request.getAttribute("total");
	
	if(topping.equals("null")){
		topping = "(없음)";
	}
	
	if(side.equals("null")){
		side = "(없음)";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	#pizza{color: red;}
	#topping{color: green;}
	#side{color: blue;}
	#total{text-decoration: underline;}
</style>
<title>Insert title here</title>
</head>
<body>
	<h1>주문 내역</h1>
	<h2>
		피자는 <span id="pizza"><%= pizza %></span>, 토핑은 <span id="topping"><%= topping %></span>, 사이드는 <span id="side"><%= side %></span> 주문하셨습니다.
	</h2>
	
	<br>
	<br>
	<br>
	
	<h2>총합 : <span id="total"><%= total %>원</span></h2>
	
	<br>
	
	<h1 style="color: pink;">즐거운 식사시간 되세요 ~</h1>
</body>
</html>

<!--
getParameter
	클라이언트의 HTML페이지에서 필요한 정보를 얻는데 사용
	웹 브라우저에서 전송 받은 request영역에서 name 값이 같은 것을 찾아 값을 읽어옴
	항상 String 반환
getAttribute
	이전에 다른 JSP 또는 Servlet페이지에 설정된 매개변수를 가져오는데 사용
	request.setAttribute에서 설정을 해준 값을 가져오며 설정이 없으면 무조건 null
	Object 반환
-->