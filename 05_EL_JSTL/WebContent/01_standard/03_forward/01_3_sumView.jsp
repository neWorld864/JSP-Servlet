<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String firstNum = request.getParameter("firstNum");
		String secondNum = request.getParameter("secondNum");
		
		int result = (int)request.getAttribute("result");
	%>
	<%= firstNum %>부터 <%=secondNum %>까지의 합은? <span style="color: pink;"><%= result %></span>
	
	
	<%--
	
		firstNum과 secondNum을 request를 통해 받아올 수 있는 이유 : forward를 사용해서 넘겨주는데 url을 그대로 두는 특성 때문에 값이 그대로 넘어오는 것처럼 보이는 것
		forward는 페이지 이동시킴. url은 그대로 둠. 뒤에 쿼리스트링이 그대로 들어가 있음.
		페이지는 01_3을 출력하고 있는데 위에 있는 url이 그대로 있음 -> url에 있는 firstNum과 secondNum을 그대로 이용
		
		http://localhost:9580/05_EL_JSTL/01_standard/03_forward/01_2_sumCalc.jsp?firstNum=3&secondNum=4 
		에서 ?firstNum=3&secondNum=4를 이용하는 것임
	
	 --%>
</body>
</html>