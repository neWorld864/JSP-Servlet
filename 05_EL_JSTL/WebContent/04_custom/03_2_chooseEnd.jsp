<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- uri="" 치고 ctrl+space 누르면 뜸 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${ Integer.parseInt(param.num) % 5 == 0 }">
			청담동 아파트가 당첨 되었습니다!
		</c:when>
		<c:when test="${ Integer.parseInt(param.num) % 5 == 1 }">
			에어팟이 당첨 되었습니다!
		</c:when>
		<c:when test="${ Integer.parseInt(param.num) % 5 == 2 }">
			자바 100문제 풀기가 당첨 되었습니다!
		</c:when>
		<c:otherwise>
			꽝입니다!
		</c:otherwise>
	</c:choose>
</body>
</html>