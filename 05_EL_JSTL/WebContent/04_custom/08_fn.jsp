<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>문자열 관련 함수 태그</h2>
	<c:set var="str" value="How are you?"/>
	str : ${ str }<br>
	모두 대문자로 : ${ fn:toUpperCase(str) }<br>
	모두 소문자로 : ${ fn:toLowerCase(str) }<br>
	are의 위치는 : ${ fn:indexOf(str, "are") }<br>
	are를 were로 바꾸기 : ${ fn:replace(str, "are", "were") }<br>
	문자열 길이 : ${ fn:length(str) }
</body>
</html>