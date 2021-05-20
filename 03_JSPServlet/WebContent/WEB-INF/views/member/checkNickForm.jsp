<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="inputValue();">
	<b>닉네임 중복 검사</b>
	<br>
	<form action="<%= request.getContextPath() %>/checkNick.me" id="nickCheckForm">
		<input type="text" id="inputNick" name="inputNick">
		<input type="submit" value="중복확인"/>
	</form>
	
	<br>
	<%
		Integer result = (Integer)request.getAttribute("result");
		if(result != null) {
			if(result > 0){
	%>
			이미 사용중인 닉네임입니다.
	<%
			} else {
	%>				
			사용 가능한 닉네임입니다.
	<%
			}
		}
	%>				
	
	<br>
	<br>
	
	<input type="button" id="usedNick" value="확인" onclick="confirm();">
	<input type="button" id="cancelNick" value="취소" onclick="window.close();"> 
	
	<script>
		function inputValue(){
			console.log(<%= request.getAttribute("result") %>);
			
			if(<%= request.getAttribute("result") %> == null){
				document.getElementById('inputNick').value = opener.document.joinForm.nickName.value;
				// 															회원가입폼이름      아이디
				// 자식창 공간에 부모의 값을 가져옴
			} else {
				document.getElementById('inputNick').value = '<%= (String)request.getAttribute("checkedNick") %>';
				// 이렇게만 쓰면 변수로만 인지
				
			}
		}
		
		function confirm(){
			opener.document.joinForm.nickName.value = document.getElementById('inputNick').value;
			// 부모창 공간에 자식의 값을 가져옴
			
			window.close();
		}
	</script>
</body>
</html>