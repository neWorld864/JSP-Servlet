<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- HTML주석 -->
	<%-- JSP 주석 --%>
	
	<%
		int total = 0;
	
		for(int i = 1; i <= 10; i++){
			total += i;
		}
		
		System.out.println("덧셈 끝");
	%>
	expression 출력 : 1부터 10까지의 합은 <span style="color:red; font-size: 16pt;"><%= total %></span>입니다.<br> <!-- total;이라고 하면 안 됨! total이라고 해야함 -->
	scriptlet 출력 : 1부터 10까지의 합은 <span style="color:blue; font-size: 16pt;"><%= total %></span>입니다.<br>

</body>
</html>