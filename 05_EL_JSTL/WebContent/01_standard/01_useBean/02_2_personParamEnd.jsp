<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSP 표준액션 태그 중 useBean의 param속성 이용하기</h2>
	<jsp:useBean id="person" class="action.model.vo.Person">
		<!-- 방법 1 -->
<%--	
		<jsp:setProperty property="name" name="person" param="name"/>
		<!-- param="name" : <input type="text" name="name"> -->
		<jsp:setProperty property="gender" name="person" param="gender"/>
		<jsp:setProperty property="nai" name="person" param="nai"/>
--%>
 	
		<!-- 방법 2 : 뒤에 있는 param 생략 -->
<%--		
		<jsp:setProperty property="name" name="person"/>
		<jsp:setProperty property="gender" name="person"/>
		<jsp:setProperty property="nai" name="person"/>
--%>
 
 		<!-- 방법 3 : * -->
 		<jsp:setProperty property="*" name="person"/>
	
	</jsp:useBean>
	
	<b>정보출력</b><br>
	이름 : <jsp:getProperty property="name" name="person"/><br>
	성별 : <jsp:getProperty property="gender" name="person"/><br>
	나이 : <jsp:getProperty property="nai" name="person"/><br>
</body>
</html>