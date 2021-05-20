<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error/error.jsp"%>
<%@ page import="java.util.ArrayList" %>
<%-- 
	language : 사용할 스크립트 언어 유형 지정
	contentType : MIME형식 지정 및 캐릭터셋 설정, 웹 브라우저가 받아볼 페이지의 인코딩 방식
	pageEncoding : JSP파일에 기록된 소스코드 자체의 인코딩 방식
	import : = Java import
	errorPage : 오류 페이지 관리, 현재 JSP페이지에서 오류가 발생할 경우 호출할 페이지 지정
	isErrorPage : 오류 페이지 관리, errorPage속성에 설정된 오류 처리 파일로 다른 용도로는 사용 불가, 오직 오류처리에만 이용
				  true로 두면 exception객체를 쓸 수 있게 됨
--%>
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
		
		ArrayList<String> list = new ArrayList<>(); /* 임폴트 직접 써줘야함. 단축키로 안 나옴 */
		list.add(null); /* java.lang.NullPointerException */
		
		System.out.println(list.get(0).charAt(0));
	%>
	
	expression 출력 : 1부터 10까지의 합은 <span style="color:red; font-size: 16pt;"><%= total %></span>입니다.<br> <!-- total;이라고 하면 안 됨! total이라고 해야함 -->
	scriptlet 출력 : 1부터 10까지의 합은 <span style="color:blue; font-size: 16pt;"><%= total %></span>입니다.<br>
</body>
</html>