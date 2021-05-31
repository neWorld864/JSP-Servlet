<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="action.model.vo.Person" %><!-- scriptlet 할 때만 필요 -->
<% 
	/* scriptlet 할 때만 필요 */
	Person person = (Person)request.getAttribute("person"); 
	String beverage = (String)request.getAttribute("beverage"); 
	int year = (int)request.getAttribute("year"); 
	String[] products = (String[])request.getAttribute("products"); 
	String book = (String)session.getAttribute("book"); 
	String movie = (String)application.getAttribute("movie"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>scriptlet을 통해 request객체에 저장된 데이터 출력하기</h2>
	<h4>개인정보(<%= year %>)</h4>
	이름 : <%= person.getName() %><br>
	성별 : <%= person.getGender() %><br>
	나이 : <%= person.getNai() %><br>
	<h4>취향정보</h4>
	<%= person.getName() %>님이 가장 좋아하는 음료 : <%= beverage %><br>
	<%= person.getName() %>님이 가장 좋아하는 물건 : <%= products[0] %>, <%= products[1] %>, <%= products[2] %><br>
	<%= person.getName() %>님이 가장 좋아하는 도서 : <%= book %><br>
	<%= person.getName() %>님이 가장 좋아하는 영화 : <%= movie %><br>
	<!-- scriptlet을 통해 값을 가져올 때 해당 영역에 갖고올 데이터가 존재하지 않으면 null 발생 -->
	
	<hr>
	
	<!-- el은 scriptlet과 별개임! -->
	<h2>el의 내장객체 XXXScope를 통해 저장된 데이터 출력하기</h2>
	<h4>개인정보(${ requestScope.year })</h4>
	이름 : ${ requestScope.person.name }<br><!-- person객체가 출력됨 -->
	성별 : ${ requestScope.person.gender }<br>
	나이 : ${ requestScope.person.nai }<br>
	<h4>취향정보</h4>
	${ requestScope.person.name }님이 가장 좋아하는 음료 : ${ requestScope.beverage }<br>
	${ requestScope.person.name }님이 가장 좋아하는 물건 : ${ requestScope.products[0] }, 
												  ${ requestScope.products[1] }, 
												  ${ requestScope.products[2] }<br>
	${ requestScope.person.name }님이 가장 좋아하는 도서 : ${ requestScope.book }<br>
	${ requestScope.person.name }님이 가장 좋아하는 영화 : ${ requestScope.movie }<br>
	<!-- 값이 없을 때 null이 뜨는 게 아니라 아무 것도 안 뜬다 -->
	${ requestScope.person.name }님이 가장 좋아하는 도서 : ${ sessionScope.book }<br>
	${ requestScope.person.name }님이 가장 좋아하는 영화 : ${ applicationScope.movie }<br>
	
	
	<hr>
	
	
	<h2>scope를 생략하여 저장된 데이터 출력하기</h2>
	<p>
		el 내장객체 pageScope, requestScope, sessionScope, applicationScope는 생략 가능<br>
		pageScope -> requestScope -> sessionScope -> applicationScope 순으로 찾음
	</p>
	<h4>개인정보(${ year })</h4>
	이름 : ${ person.name }<br>
	성별 : ${ person.gender }<br>
	나이 : ${ person.nai }<br>
	<h4>취향 정보</h4>
	${ person.name }님이 가장 좋아하는 음료 : ${ beverage }<br>
	${ person.name }님이 가장 좋아하는 음료 : ${ sessionScope.beverage }<br>
	${ person.name }님이 가장 좋아하는 음료 : ${ applicationScope.beverage }<br>
	${ person.name }님이 가장 좋아하는 물건 : ${ products[0] }, ${ products[1] }, ${ products[2] }<br>
	${ person.name }님이 가장 좋아하는 도서 : ${ book }<br>
	${ person.name }님이 가장 좋아하는 영화 : ${ movie }<br>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>