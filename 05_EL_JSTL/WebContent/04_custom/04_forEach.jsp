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
	<!-- for(int i = 1; i <= 6; i++){} -->
	<c:forEach var="i" begin="1" end="6">
		<h${ i }>반복문</h${ i }>
	</c:forEach>
	
	<!-- for(int i = 1; i <= 6; i+=2){} -->
	<c:forEach var="i" begin="1" end="6" step="2">
		<h${ i }>건너뛰기</h${ i }>
	</c:forEach>
	
	<!-- for(int i = 6; i >= 1; i--){} -->
	<c:forEach var="i" begin="1" end="6">
		<h${ 7-i }>거꾸로</h${ 7-i }>
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="6" varStatus="vs">
		vs.first : ${ vs.first }<br><!-- 현재 위치가 first인지 -->
		vs.last : ${ vs.last }<br><!-- 현재 위치가 last인지 -->
		vs.index : ${ vs.index }<br><!-- 현재 몇 번째로 돌고 있는지 / 0 기반-->
		vs.count : ${ vs.count }<br><!-- 현재 몇 번째로 돌고 있는지 / 1 기반-->
		vs.current : ${ vs.current }<br><!-- 현재 몇 번째로 돌고 있는지 -->
	</c:forEach>
	<!--  
		vs.first : true
		vs.last : false
		vs.index : 1
		vs.count : 1
		vs.current : 1
		vs.first : false
		vs.last : false
		vs.index : 2
		vs.count : 2
		vs.current : 2
		vs.first : false
		vs.last : false
		vs.index : 3
		vs.count : 3
		vs.current : 3
		vs.first : false
		vs.last : false
		vs.index : 4
		vs.count : 4
		vs.current : 4
		vs.first : false
		vs.last : false
		vs.index : 5
		vs.count : 5
		vs.current : 5
		vs.first : false
		vs.last : true
		vs.index : 6
		vs.count : 6
		vs.current : 6
	-->
	<!-- 뭐 더 한 거 없는지 확인할 것 -->
</body>
</html>