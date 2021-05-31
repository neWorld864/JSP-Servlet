<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>주문 내역</h2>
	<%-- 상품 명 : ${ pname }<br>
	수량 : ${ pcount }<br>
	옵션 1 : ${ option[0] }<br>
	옵션 2 : ${ option[1] }<br> --%>
	<!-- scope가 생략이 되어있는 상태로 인지 -->
	
	상품 명 : ${ param.pname }<br>
	수량 : ${ param.pcount }<br>
	옵션 1 : ${ paramValues.option[0] }<br>
	옵션 2 : ${ paramValues.option[1] }<br>
</body>
</html>