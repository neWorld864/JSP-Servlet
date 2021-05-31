<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 이거 안 하면 값 출력 안 됨! -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String[] nameArr = {"신형만", "봉미선", "신짱구", "신짱아"};
		request.setAttribute("family", nameArr);
	%>
	<h2>우리 가족</h2>
	<ol>
		<%-- var="f" : forEach 안에서 사용할 변수명 --%>
		<%-- for(String f : family){} <-- 향상된 for문(for-each) --%>
		<c:forEach var="f" items="${ family }">
			<li>${ f }</li>
		</c:forEach>
	</ol>
	
	<hr>
	
	
	<c:set var="family">신형만, 봉미선, 신짱구, 신짱아</c:set>
	<ul>
		<c:forTokens items="${ family }" delims=", " var="f">
			<li>${ f }</li>
		</c:forTokens>
	</ul>
	
	<hr>
	
	<h2>멋진 가족</h2>
	<c:set var="families">신형만$봉미선/신짱구 신짱아^둘리_또치-도우너</c:set>
	<ul>
		<c:forTokens items="${ families }" delims="$/ ^_-" var="f">
			<li>${ f }</li>
		</c:forTokens>
	</ul>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>