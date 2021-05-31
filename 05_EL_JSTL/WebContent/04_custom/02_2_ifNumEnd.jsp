<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- core library 이용하기 -->
	<!--  
		if(num1 > num2){
			num1이 num2보다 큽니다
		} else if(num1 == num2){
			num1과 num2는 같습니다
		} else {
			num1은 num2보다 작습니다
		}
	-->
	<!-- test 속성 : if문의 조건식을 넣는 속성 -->
	<c:if test="${ Integer.parseInt(param.num1) > Integer.parseInt(param.num2) }">
		${ param.num1 } 크다, ${ param.num2 }보다
	</c:if>
	
	<c:if test="${ Integer.parseInt(param.num1) < Integer.parseInt(param.num2) }">
		${ param.num1 } 작다, ${ param.num2 }보다
	</c:if>
	<!-- 입력 값을 숫자가 아닌 문자 취급을 하기 때문에 결과가 잘못 나온다 -->
	
	<c:if test="${ Integer.parseInt(param.num1) == Integer.parseInt(param.num2) }">
		${ param.num1 } 같다, ${ param.num2 } 값과
	</c:if>
	
	<br>
	
	<c:if test="${ Integer.parseInt(param.num1) > Integer.parseInt(param.num2) }">
		${ param.num1 } 크다, ${ param.num2 }보다
	</c:if>
	
	<c:if test="${ Integer.parseInt(param.num1) < Integer.parseInt(param.num2) }">
		${ param.num1 } 작다, ${ param.num2 }보다
	</c:if>
	
	<c:if test="${ Integer.parseInt(param.num1) == Integer.parseInt(param.num2) }">
		${ param.num1 } 같다, ${ param.num2 } 값과
	</c:if>
</body>
</html>