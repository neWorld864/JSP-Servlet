<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 라이브러리 활용하기</h1>
	<p>
		보안 상 폭탄 메일을 방지하기 위해, <br>
		라이브러리에서는 보낼 사람의 이메일과 사용할 이메일 주소를 미리 정해놓음<br>
		(변경 불가능)
	</p>
	
	<form action="<%= request.getContextPath() %>/sendEmail.do" method="post">
		받는 이 : <input type="text" name="receiver"><br>
		제목 : <input type="text" name="title"><br>
		내용 : <textarea cols="10" rows="5" name="contents"></textarea><br>
		<button>보내기</button> <!-- form태그 안에 있는 버튼은 기본적으로 submit의 역할을 함! 그냥 버튼이 되고싶으면 type="button"을 추가해야함! -->
	</form>
</body>
</html>