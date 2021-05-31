<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- c: core를 나타내는 별칭같은 것 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- num1이랑 num2가 넘어오게 됨 -->
	<h1>jstl core라이브러리</h1>
	<p>
		사용할 태그 라이브러리를 선언할 때는 taglib지시자 이용<br>
		prefix : 앞 첨자, 다른 태그와 구별할 수 있는 name space를 제공
		uri : 실제 웹 주소는 아님, 태그 라이브러리를 나타내는 식별자
	</p>
	<h2>c:set</h2>
	<!-- 변수 선언 태그, scope를 지정하여 선언 가능(기본 page) -->
	<!-- c => 위에 있는 별칭 그대로 써줘야 함. 만약 위에서 prefix="core"로 썼으면 core:set~으로 써야 한다. -->
	<c:set var="no1" value="${ param.num1 }"/>
	<c:set var="no2" value="${ param.num2 }"/>
	<!-- no1, no2 변수에 ${ param.num1 }, ${ param.num2 }를 받아둠 -->
	
	<!-- 방법1 -->
	<%-- <c:set var="result" value="${ param.num1 * param.num2 }"/>
	${ param.num1 } 곱하기 ${ param.num2 }의 값은 ${ result } --%>
	<!-- 방법2 -->
	<c:set var="result" value="${ no1 * no2 }"/>
	${ no1 } 곱하기 ${ no2 }의 값은 ${ result }
	
	<hr>
	
	<h2>c:remove</h2>
	<p></p>
	<c:set var="result" value="9999" scope="request"/>
	삭제 전 \${ result } : ${ result }<br>
	삭제 전 \${ requestScope.result } : ${ requestScope.result }<br>
	
	<br>
	
	<%-- <c:remove var="result"/> --%>
	<!-- 모든 scope의 result라는 이름의 변수 삭제 -->
	<c:remove var="result" scope="request"/>
	<!-- requestScope에 있는 result라는 이름의 변수 삭제 -->
	삭제 후 \${ result } : ${ result }<br>
	삭제 후 \${ requestScope.result } : ${ requestScope.result }<br>
	
	<!-- 뭘 하면 둘 다 9999가 나온다고?? 0527 5교시 -->
</body>
</html>