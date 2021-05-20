<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="inputValue();">
	<b>아이디 중복 검사</b>
	<br>
	<form action="<%= request.getContextPath() %>/checkId.me" id="idCheckForm">
		<input type="text" id="inputId" name="inputId">
		<input type="submit" value="중복확인"/>
	</form>
	
	<br>
	<%
//		System.out.println(request.getAttribute("result"));
		/* 처음 중복확인창 띄울때가 null / 중복이 없으면 0 / 중복이 있으면 1 */
		
		Integer result = (Integer)request.getAttribute("result");
		if(result != null) {
			if(result > 0){
	%>
			이미 사용중인 아이디입니다.
	<%
			} else {
	%>				
			사용 가능한 아이디입니다.
	<%
			}
		}
	%>				
	
	<br>
	<br>
	
	<input type="button" id="usedId" value="확인" onclick="confirm();">
	<input type="button" id="cancel" value="취소" onclick="window.close();"> <!-- window.close() : 누르면 창이 닫히는 메소드 -->
	
	<script>
		function inputValue(){
//			document.getElementById('inputId').value = opener.document.joinForm.joinUserId.value;
			// 부모 요소를 가져온 것임 -> 그래서 아이디창 입력하고 중복확인 누른 후에 값을 바꿔도 그대로 유지되는 것
//			console.log(request.getAttribute("result")); // 스크립트릿이랑 자바스크립트는 다름
			console.log(<%= request.getAttribute("result") %>);
			
			if(<%= request.getAttribute("result") %> == null){
				document.getElementById('inputId').value = opener.document.joinForm.joinUserId.value;
				// 자식창 공간에 부모의 값을 가져옴
			} else {
				document.getElementById('inputId').value = '<%= (String)request.getAttribute("checkedId") %>';
				// 이렇게만 쓰면 변수로만 인지
			}
		}
		
		function confirm(){
			opener.document.joinForm.joinUserId.value = document.getElementById('inputId').value;
			// 부모창 공간에 자식의 값을 가져옴
			
			window.close();
		}
	</script>
</body>
</html>