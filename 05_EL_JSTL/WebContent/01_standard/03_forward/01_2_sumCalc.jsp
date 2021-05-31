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
		int first = Integer.parseInt(request.getParameter("firstNum"));
		int second = Integer.parseInt(request.getParameter("secondNum"));
		
		int result = 0;
//		for(int i = first; i <= second; i++){
//			result += i;				
//		}

		for(; first <= second; first++){
			result += first;
		}
		
		request.setAttribute("result", result);
	%>
	
	<%-- 01_3_sumView.jsp로 페이지 이동 --%>
	<jsp:forward page="01_3_sumView.jsp" />

	
</body>
</html>