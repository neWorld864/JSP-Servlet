<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 스크립트릿 : 자바코드를 쓸 수 있는 곳
	// 데이터를 갖고 올 수 있는 메소드
	//		1. request.getParameter():String --> 출발지점이 View일 때 (View -> Controller, View -> View)
	//		2. request.getAttribute():Object --> 출발지점이 Controller일 때(Controller -> View, Controller -> Controller)
	String name = (String)request.getAttribute("name"); // 반환값이 Object여야하는데 String으로 받음 -> 형변환 해줘야 함
	// 자식 클래스는 부모 클래스를 받아줄 수 없다 -> 다형성 위배 => 다운 캐스팅을 통해 자식(String)으로 바꿔줌
	String age = (String)request.getAttribute("age"); 
	String gender = (String)request.getAttribute("gender"); 
	String city = (String)request.getAttribute("city"); 
	String height = (String)request.getAttribute("height"); 
	String foods = (String)request.getAttribute("foods"); 
	String recommendation = (String)request.getAttribute("recommendation"); 
	
	// 이걸 해야 값 내용이 정상적으로 출력됨!
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>;
	h2{color: red;}
	span.name{color: orange; font-weight: bold;}
	span.gender{color: yellow; background: black; font-weight: bold;}
	span.age{color: green; font-weight: bold;}
	span.city{color: blue; font-weight: bold;}
	span.height{color: navy; font-weight: bold;}
	span.food{color: purple; font-weight: bold;}
</style>
</head>
<body>
	<h2>개인 취향 테스트 결과(POST)</h2>
	<span class='name'><%= name %></span>님은 
	<span class='age'><%= age %></span>이시며 
	<span class='city'><%= city %></span>에 사는 
	<span class='height'><%= height %></span>cm인 
	<span class='gender'><%= gender %></span>입니다. 
	좋아하는 음식은 <span class='food'><%= foods %></span>입니다.
	<hr>
	<h3><%= age %>에 맞는 선물 추천</h3>
	'<%= recommendation %>' 선물은 어떠신가요?
	
	<%-- <%= %> 이걸 꼭 해줘야한다~~  --%>
</body>
</html>