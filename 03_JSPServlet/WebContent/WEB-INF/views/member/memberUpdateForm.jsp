<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member myInfo = (Member)request.getAttribute("myInfo");
	String userId = myInfo.getUserId();
	String userName = myInfo.getUserName();
	String nickName = myInfo.getNickName();
	String phone = myInfo.getPhone().equals("null") ? "" : myInfo.getPhone();
	String email = myInfo.getEmail().equals("null") ? "" : myInfo.getEmail();
	String address = myInfo.getAddress().equals("null") ? "" : myInfo.getAddress();
	
	String[] checkedInterest = new String[6];
	String interestStr = myInfo.getInterest();
	if(!interestStr.equals("null")){
		String[] splitStr = interestStr.split(",");
		
		for(int i = 0; i < splitStr.length; i++) {
			switch(splitStr[i]) {
			case "운동" : checkedInterest[0] = "checked"; break;
			case "등산" : checkedInterest[1] = "checked"; break;
			case "낚시" : checkedInterest[2] = "checked"; break;
			case "요리" : checkedInterest[3] = "checked"; break;
			case "게임" : checkedInterest[4] = "checked"; break;
			case "기타" : checkedInterest[5] = "checked"; break;
			}
		}
	} 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.outer{
		width: 48%; height: 450px; background-color: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 5%;
	}
	#updateForm td {text-align: right;}
	#updateForm>table{margin: auto;}
	#updateForm tr:nth-child(3) > td:nth-child(3){text-align: left;}
	td>.must{color: red; font-weight: bold;}
	#nickCheck{background: #FFD8D8;}
	#updateBtn {background: #D1B2FF;}
	#cancelBtn {background: #B2CCFF;}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">내 정보 수정하기</h2>
		
		<form action="<%= request.getContextPath() %>/update.me" method="post" id="updateForm" name="updateForm">
										<!-- UpdateMemberServlet(/update.me) -->
			<table>
				<tr>
					<td width="200px"><label class="must">*</label> 아이디</td>
					<td width="200px">
						<input type="text" name="myId" style="background:lightgray;" readonly value="<%= userId %>">
					</td>
				</tr>
				<tr>
					<td><label class="must">*</label> 이름</td>
					<td>
						<input type="text" name="myName" required value="<%= userName %>">
					</td>
				</tr>
				<tr>
					<td><label class="must">*</label> 닉네임</td>
					<td>
						<input type="text" maxlength="15" name="myNickName" required value="<%= nickName %>">
					</td>
					<td><input type="button" id="nickCheck" onclick="checkNick();" value="중복확인"></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td>
						<input type="tel" maxlength="11" name="myPhone" placeholder="(-없이)01012345678" value="<%= phone %>">
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="email" name="myEmail" value="<%= email %>">
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<input type="text" name="myAddress" value="<%= address %>">
					</td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td>
						<input type="checkbox" id="sports" name="myInterest" value="운동" <%= checkedInterest[0] %>>
						<label for="sports">운동</label>
						<input type="checkbox" id="climbing" name="myInterest" value="등산" <%= checkedInterest[1] %>>
						<label for="climbing">등산</label>
						<input type="checkbox" id="fishing" name="myInterest" value="낚시" <%= checkedInterest[2] %>>
						<label for="fishing">낚시</label>
						<input type="checkbox" id="cooking" name="myInterest" value="요리" <%= checkedInterest[3] %>>
						<label for="cooking">요리</label>
						<input type="checkbox" id="game" name="myInterest" value="게임" <%= checkedInterest[4] %>>
						<label for="game">게임</label>
						<input type="checkbox" id="etc" name="myInterest" value="기타" <%= checkedInterest[5] %>>
						<label for="etc">기타</label>
					</td>
				</tr>
			</table>
			
			<br>
			
			<div class="btns" align="center">
				<input id="updateBtn" type="submit" value="수정하기"> <!-- 수정하기를 누르면 update.me로 -->
				<input type="button" id="cancelBtn" onclick="location.href='javascript:history.go(-1)'" value="취소하기">
			</div>
		</form>
	</div>
</body>
</html>