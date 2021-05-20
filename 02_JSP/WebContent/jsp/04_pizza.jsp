<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table,th,td{border: 1px solid black; border-collapse: collapse;}
</style>
</head>
<body>
	<h2>오늘 날짜<span style="color: lightpink;"><%@include file="today.jsp" %></span></h2>	
	
	<h2>피자 아카데미</h2>
	
	<table>
		<tr>
			<th>종류</th>
			<th>이름</th>
			<th>가격</th>
			<th rowspan="12">&nbsp;&nbsp;</th>
			<th>종류</th>
			<th>이름</th>
			<th>가격</th>
		</tr>
		<tr>
			<td rowspan="5";>피자</td>
			<td>치즈피자</td>
			<td>5000</td>
			<td rowspan="11">사이드</td>
			<td>오븐구이통닭</td>
			<td>9000</td>
		</tr>
		<tr>
			<td>콤비네이션피자</td>
			<td>6000</td>
			<td>치킨스틱&윙</td>
			<td>4900</td>
		</tr>
		<tr>
			<td>포테이토피자</td>
			<td>7000</td>
			<td>치즈오븐스파게티</td>
			<td>4000</td>
		</tr>
		<tr>
			<td>고구마피자</td>
			<td>7000</td>
			<td>새우링&웨지감자</td>
			<td>3500</td>
		</tr>
		<tr>
			<td>불고기피자</td>
			<td>8000</td>
			<td>갈릭포테이토</td>
			<td>3000</td>
		</tr>
		<tr>
			<td rowspan="6">토핑</td>
			<td>고구마무스</td>
			<td>1000</td>
			<td>콜라</td>
			<td>1500</td>
		</tr>
		<tr>
			<td>콘크림무스</td>
			<td>1500</td>
			<td>사이다</td>
			<td>1500</td>
		</tr>
		<tr>
			<td>파인애플토핑</td>
			<td>2000</td>
			<td>갈릭소스</td>
			<td>500</td>
		</tr>
		<tr>
			<td>치즈토핑</td>
			<td>2000</td>
			<td>피클</td>
			<td>300</td>
		</tr>
		<tr>
			<td>치즈크러스트</td>
			<td>2000</td>
			<td>핫소스</td>
			<td>100</td>
		</tr>
		<tr>
			<td>치즈바이트</td>
			<td>3000</td>
			<td>파마산 치즈가루</td>
			<td>100</td>
		</tr>
	</table>
	<form action="<%= request.getContextPath() %>/confirmPizza" name="myInfoForm" method="post">
	<br><br>
	<label>피자 : </label>
	<select id="pizza" name="pizza" required>
		<option value="치즈피자">치즈피자</option>
		<option value="콤비네이션피자">콤비네이션피자</option>
		<option value="포테이토피자">포테이토피자</option>
		<option value="고구마피자">고구마피자</option>
		<option value="불고기피자">불고기피자</option>
	</select>
	
	<br>
	
	<label>토핑 : </label>
	<input type="checkbox" id="sweetpotato" name="topping" value="고구마무스"><label>고구마무스</label>
	<input type="checkbox" id="corncream" name="topping" value="콘크림무스"><label>콘크림무스</label>
	<input type="checkbox" id="pineapple" name="topping" value="파인애플토핑"><label>파인애플토핑</label>
	<input type="checkbox" id="cheese" name="topping" value="치즈토핑"><label>치즈토핑</label>
	<input type="checkbox" id="cheesecrust" name="topping" value="치즈크러스트"><label>치즈크러스트</label>
	<input type="checkbox" id="cheesebite" name="topping" value="치즈바이트"><label>치즈바이트</label>
	
	
	<br>
	
	<label>사이드 : </label>
	<input type="checkbox" id="ovenchicken" name="side" value="오븐구이통닭"><label>오븐구이통닭</label>
	<input type="checkbox" id="chickenstick" name="side" value="치킨스틱&윙"><label>치킨스틱&윙</label>
	<input type="checkbox" id="cheeseoven" name="side" value="치즈오븐스파게티"><label>치즈오븐스파게티</label>
	<input type="checkbox" id="shrimpring" name="side" value="새우링&웨지감자"><label>새우링&웨지감자</label>
	<input type="checkbox" id="garlic" name="side" value="갈릭포테이토"><label>갈릭포테이토</label>
	<input type="checkbox" id="coke" name="side" value="콜라"><label>콜라</label>
	<input type="checkbox" id="cidar" name="side" value="사이다"><label>사이다</label>
	<input type="checkbox" id="garlicsource" name="side" value="갈릭소스"><label>갈릭소스</label>
	<input type="checkbox" id="pickle" name="side" value="피클"><label>피클</label>
	<input type="checkbox" id="hotsource" name="side" value="핫소스"><label>핫소스</label>
	<input type="checkbox" id="parmesan" name="side" value="파마산 치즈가루"><label>파마산 치즈가루</label>
	
	<br><br>
	
	<input type="submit" value="확인">
	</form>
	
</body>
</html>