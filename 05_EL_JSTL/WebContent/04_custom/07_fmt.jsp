<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 원화표시가 제대로 나오지 않는 경우 --%>
	<fmt:setLocale value="ko_kr"/>
	
	
	<h2>jstl, fmt</h2>
	<p>날짜, 시간, 숫자 데이터의 출력 형식을 지정할 때 사용</p>
	<h3>숫자 데이터 포맷 지정 : formatNumber</h3>
	천단위 구분 기호 적용 : <fmt:formatNumber value="123456789"/><br><%-- 123,456,789 --%>
	숫자 그대로 출력 : <fmt:formatNumber value="123456789" groupingUsed="false"/><br><%-- 123456789 --%>
	천단위 구분 기호를 사용하고 싶지 않으면 groupingUsed="false"를 넣어주고, 기본 값은 true
	
	<br><br>
	
	<b>실수값 소수점 아래 자릿수 지정 : pattern속성 사용</b><br>
	<fmt:formatNumber value="1.234567"/><br><%-- 1.235  소수 셋째자리까지 표기, 반올림--%>
	<fmt:formatNumber value="1.234567" pattern="#.##"/><br><%-- 1.23 소수 둘째자리까지 표기, 셋째자리에서 반올림--%>
	<fmt:formatNumber value="1.2" pattern="#.##"/><%-- 1.2 --%>
	<fmt:formatNumber value="1.2" pattern="0.00"/><%-- 1.20 --%>
	
	<h3>type속성으로 백분율, 통화기호 표시 처리</h3>
	<fmt:formatNumber value="0.12" type="percent"/><br>
	<fmt:formatNumber value="123456789" type="currency"/><br>
	<fmt:formatNumber value="123456789" type="currency" currencySymbol="$"/><br>
	
	
	
	
</body>
</html>