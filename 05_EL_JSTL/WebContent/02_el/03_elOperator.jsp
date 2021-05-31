<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="action.model.vo.Person, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String str1 = "안녕";
		String str2 = new String("안녕");
		
		int big = 10;
		int small = 3;
		
		Person p1 = new Person("박신우", '여', 20);
		Person p2 = new Person("박신우", '여', 20);
	
		// page 스코프에 담기
		pageContext.setAttribute("str1", str1);
		pageContext.setAttribute("str2", str2);
		pageContext.setAttribute("big", big);
		pageContext.setAttribute("small", small);
		pageContext.setAttribute("p1", p1);
		pageContext.setAttribute("p2", p2);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(str1);
		pageContext.setAttribute("list", list);
		
		ArrayList<String> list2 = new ArrayList<String>();
		pageContext.setAttribute("list2", list2);
		
		ArrayList<String> list3 = null;
		pageContext.setAttribute("list3", list3);
	%>
	<h2>EL연산</h2>
	<p>
		el을 가지고 어떠한 연산처리를 하는 것이 아닌 속성 값들을 화면에 뿌리는 용도<br>
		하지만 산술 연산 및 논리 연산을 지원하므로 간단한 논리 연산은 자주 사용함
	</p>
	<h3>산술 연산</h3>
	10 곱하기 7 = ${ 10 * 7 }<br>
	100 나누기 3 = ${ 100 / 3 } 또는 ${ 100 div 3 }<br>
	15 나머지 4 = ${ 15 % 4 } 또는 ${ 15 mod 4 }<br>
	
	<hr>
	
	<h3>비교 연산</h3>
	<b>str1 == str2</b><br>
	&emsp; 스크립팅 : <%= str1 == str2 %><br><!-- 주소값 비교 : 주소가 서로 다르므로 false가 나옴 -->
	&emsp; el : ${ str1 == str2 } 또는 ${ str1 eq str2 }<br><!-- true 또는 true -->
	
	<br>
	
	<b>str1 != str2</b><br>
	&emsp; 스크립팅 : <%= str1 != str2 %><br><!-- true -->
	&emsp; el : ${ str1 != str2 } 또는 ${ str1 ne str2 }<br><!-- false 또는 false / ne: not equal -->

	<br>
	
	<b>숫자형은 자동 형변환 후 처리</b><br>
	&emsp; big > small : ${ big > small } 또는 ${ big gt small }<br> <!-- big > smaill : true 또는 true -->
	&emsp; big < small : ${ big < small } 또는 ${ big lt small }<br> <!-- big < smaill : false 또는 false -->
	&emsp; big >= small : ${ big >= small } 또는 ${ big ge small }<br> <!-- big >= smaill : true 또는 true -->
	&emsp; big <= small : ${ big <= small } 또는 ${ big le small }<br> <!-- big <= smaill : false 또는 false -->

	<br>
	
	&emsp; \${ big + small } : ${ big + small } <br> <!-- ${ big + small } : 13 -->
	
	<br>
	
	&emsp; p1 == p2 : <%= p1 == p2 %> 또는 ${ p1 == p2 } 또는 ${ p1 eq p2 }<br> <!-- p1 == p2 : false 또는 false 또는 false -->
	
	<br>
	
	<b>객체가 null 또는 비어있는지 체크하는 연산자 empty</b><br>
	&emsp; \${ empty list } : ${ empty list }<br><!-- ${ empty list } : false -->
	&emsp; \${ empty list } : ${ empty list2 }<br><!-- ${ empty list } : true -->
	&emsp; \${ empty list } : ${ empty list3 }<br><!-- ${ empty list } : true -->
	
	
	<br>
	
	<b>논리연산자/부정연산자</b>
	&emsp; \${ true && true } : ${ true && true }<br>
	&emsp; \${ true and true } : ${ true and true }<br>
	&emsp; \${ true || false } : ${ true || false }<br>
	&emsp; \${ true or false } : ${ true or false }<br>
	&emsp; \${ !true } : ${ !true }<br>


</body>
</html>