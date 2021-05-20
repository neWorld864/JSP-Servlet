<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
	/* loginUser 만들기 */
	// LoginServlet에서 loginUser를 만들었었음
	Member loginUser = (Member)session.getAttribute("loginUser"); /* session에 데이터 저장했으므로 */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP&Servlet</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<style>
	body{
		background:url('<%= request.getContextPath() %>/images/bg.png') no-repeat center center fixed;
		background-size: cover;
	}
	.loginArea{float: right;}
	#loginTable{text-align: right;}
	#loginTable td:nth-child(1){padding-right: 15px;}
	.loginBtns{float: right; margin-left: 5px;}
	#loginBtn, #myPage{background: #D1B2FF;}
	#joinBtn, #logout{background: #B2CCFF;}
	input[type=button], input[type=submit]{cursor: pointer; border-radius: 15px; color: white;}
	#userInfo label{font-weight: bold;}

	.wrap{background: white; width: 100%; height: 50px;}
	.menu{
		background: white; color: navy; text-align: center; font-weight: bold; 
		vertical-align: middle; width: 150px; height: 50px; display: table-cell;
	}
	nav{width: 600px; margin-left: auto; margin-right: auto;}
	.menu:hover {background: beige; color:orangered; font-weight:bold; cursor:pointer;}
</style>
</head>
<body>
	<h1 align="center">Welcome to JSP&amp;Servlet World!</h1>
	
	<div class="loginArea">
		<% if(loginUser == null){ %>
			<form id="loginForm" action="<%= request.getContextPath() %>/login.me" method="post" onsubmit="" return validate();>
											<!-- 루트 컨텍스트?를 받아오는 역할 --><!-- 로그인을 처리하는 url --> <!-- 아이디, 비밀번호 입력하지 않으면 넘어가지 않도록 -->
				<!-- 로그인 유저가 null인 경우에만 떠야함 -->
				<table id="loginTable">
					<tr>
						<td><label>ID</label></td>
						<td><input type="text" name="userId" id="userId"></td>
					</tr>
					<tr>
						<td><label>PWD</label></td>
						<td><input type="password" name="userPwd" id="userPwd"></td>
					</tr>
				</table>
				<div class="loginBtns">
					<input type="submit" id="loginBtn" value="로그인">
					<input type="button" id="joinBtn" value="회원가입">
				</div>
			</form>
		<% } else{ %>
			<div id="userInfo" align="right">
				<label><%= loginUser.getUserName() %>님의 방문을 환영합니다.</label>
				<br clear="all">
				<div class="loginBtns">
					<input type="button" id="myPage" value="내 정보보기">
					<input type="button" id="logout" onclick="logout();" value="로그아웃">
					
				</div>
			</div>
		<% } %>
	</div>
	
	<br clear="all">
	<br>
	
	<div class="wrap">
	 <nav>
	 	<div class="menu" onclick="goHome();">Home</div>
	 	<div class="menu" onclick="goNotice();">공지사항</div>
	 	<div class="menu" onclick="goBoard();">게시판</div>
	 	<div class="menu" onclick="goThumbnail();">사진 게시판</div>
	 	<div class="menu" onclick="goEmail();">이메일 인증</div>
	 	
	 </nav>
	</div>
	
	<script>
		function validate(){
			var id = $('#userId');
			var pwd = $('#userPwd');
			
			if(id.val().trim().length == 0){
				alert("아이디를 입력해주세요.");
				id.focus();
				
				return false;
			} else if(pwd.val().trim().length) {
				alert("비밀번호를 입력해주세요.");
				pwd.focus();
				
				return false;
			}
			
			return true;
		}
		
		function logout() {
			location.href='<%= request.getContextPath() %>/logout.me';
			/* 자바스크립트 안에 스크립트립을 넣었다고 해서 스크립트립안에 자바스크립트 넣으려고 하면 안 됨!! */
		}
		
		$('#joinBtn').on('click', function(){
			location.href="<%= request.getContextPath() %>/signUpForm.me";
		});
		
		$('#myPage').on('click', function(){
			location.href="<%= request.getContextPath() %>/mypage.me";
		})
		
		function goHome (){
			location.href="<%= request.getContextPath() %>";
		}
		
		function goNotice (){
			location.href="<%= request.getContextPath() %>/list.no";
		}
		
		function goBoard(){
			location.href="<%= request.getContextPath() %>/list.bo";
		}
		
		function goThumbnail(){
			location.href="<%= request.getContextPath() %>/list.th";
		}
		
		function goEmail(){
			location.href="<%= request.getContextPath() %>/email.do";
		}
	</script>
	
	<br clear="all">
</body>
</html>