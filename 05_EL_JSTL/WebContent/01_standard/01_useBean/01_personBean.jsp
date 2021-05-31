<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- personBean = person객체 ==> person class 필요 -->
	<h2>JSP표준 액션 태그 중 useBean을 사용하여 VO클래스의 객체 정보 불러오기</h2>
	<jsp:useBean id="person1" class="action.model.vo.Person" scope="request"/>
	<%-- action.model.vo.Person person1 = new action.model.vo.Person(); --%>
	<!-- 영역 좁은 것부터 큰 것까지 => page - request - session - appilication -->
	<!-- 해당 스코프에 객체를 만들어두는데, Person객체가 존재하면 그 객체를 가져오고 없으면 새로 생성(기본 값 page) -->
	
	<!-- getProperty는 getter의 이름을 가지고 가져와야 한다! 고로 Person객체에 있는 getter의 이름과 property에 있는 이름이 다르면 값을 받아오지 못하므로 꼭 일치하는 지 확인할 것. -->
	<!-- name은 객체명 -->
	이름 : <jsp:getProperty property="name" name="person1"/><br> <!-- 이름 : null -->
	성별 : <jsp:getProperty property="gender" name="person1"/><br> <!-- 성별 : -->
	나이 : <jsp:getProperty property="nai" name="person1"/><br> <!-- 나이 : 0 -->
	<%-- 나이 : <jsp:getProperty property="age" name="person1"/><br> <!-- 나이 : 0 --> --%>
	<!-- 설정해준 값이 없어서 기본값으로 들어감 -->
	
	<hr>
	
	<h2>JSP표준액션 태그 중 useBean을 사용하여 VO클래스에 데이터 초기화하기</h2>
	<!-- 여기 다시 보기 -->
	<!-- person2라는 변수에 Person클래스를 request영역에 객체로 생성하기 -->
	<jsp:useBean id="person2" class="action.model.vo.Person" scope="request"/>
	
	<jsp:setProperty property="name" name="person2" value="박신우"/><br>
	<jsp:setProperty property="gender" name="person2" value="F"/><br>
	<jsp:setProperty property="nai" name="person2" value="20"/><br>
	
	이름 : <jsp:getProperty property="name" name="person2"/><br> 
	성별 : <jsp:getProperty property="gender" name="person2"/><br> 
	나이 : <jsp:getProperty property="nai" name="person2"/><br> 
	
	
</body>
</html>