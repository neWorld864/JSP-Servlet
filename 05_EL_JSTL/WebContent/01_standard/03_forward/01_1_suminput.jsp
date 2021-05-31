<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>입력한 두 개의 수 사이의 값들을 더한 누적 값 구하기</h2>
	<form action="01_2_sumCalc.jsp">
		첫 번째 수 : <input type="text" name="firstNum"><br>
		두 번째 수 : <input type="text" name="secondNum"><br>
		<button>계산하기</button><!-- form태그 안 button의 기본값이 submit -->
	</form>
	
	<%-- 
		localhost:port/1_JSPServlet/		--> index.jsp(메인)
		localhost:port/1_JSPServlet/enroll.me	--> 회원가입 화면 요청
				(회원가입 페이지를 출력해주는 Servlet)
				response.sendRedirect("WEB-INF/views/member/enroll.jsp"); (X)
				--> localhost:port/1_JSPServlet/WEB-INF/views/member/enroll.jsp
			
				RequestDispatcher.forward(("WEB-INF/views/member/enroll.jsp"); (O)
				--> localhost:port/1_JSPServlet/enroll.me
	 --%>
</body>
</html>