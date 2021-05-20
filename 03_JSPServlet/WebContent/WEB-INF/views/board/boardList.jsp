<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	
	System.out.println(list);
	System.out.println(pi);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.outer{
		width: 800px; height: 500px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 50px;
	}
	#listArea{text-align: center;}
	.tableArea{width:650px;	height:350px; margin-left:auto;	margin-right:auto;}
	th{border-bottom: 1px solid grey;}
	.pagingArea button{border-radius: 15px; background: #D5D5D5;}
	.buttonArea{margin-right: 50px;}
	.buttonArea button{background: #D1B2FF; border-radius: 5px; color: white; width: 80px; heigth: 25px; text-align: center;}
	button:hover{cursor: pointer;}
	#numBtn{background: #B2CCFF;}
	#choosen{background: #FFD8D8;}
	#listArea{margin: auto;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">게시판</h2>
		<div class="tableArea">
			<table id="listArea">
				<tr>
					<th width="100px">글번호</th>
					<th width="100px">카테고리</th>
					<th width="300px">글제목</th>
					<th width="100px">작성자</th>
					<th width="100px">조회수</th>
					<th width="150px">작성일</th>
				</tr>
				<% if(list.isEmpty()){ %>
				<tr>
					<td colspan="6">조회된 리스트가 없습니다.</td>
				</tr>
				<% } else { 
						for (Board b : list){ %>
				<tr>
					<td><%= b.getBoardId() %></td>
					<td><%= b.getCategory() %></td>
					<td><%= b.getBoardTitle() %></td>
					<td><%= b.getNickName() %></td>
					<td><%= b.getBoardCount() %></td>
					<td><%= b.getModifyDate() %></td>
				</tr>
				<% 		   } 
				 } %>
			</table>
		</div>
		
		<!-- 페이징 -->
		<div class="pagingArea" align="center">
			<!-- 맨 처음으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=1'">&lt;&lt;</button>
			<!-- 이전 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= currentPage-1 %>'" id="beforeBtn">&lt;</button>
			<script>
				if(<%= currentPage %> <= 1){
					var before = $('#beforeBtn');
					before.attr('disabled', true);
				}
			</script>
			<!-- 숫자 버튼 -->
			<% for(int p = startPage; p <= endPage; p++) {
				if(p == currentPage){
			%>
			<button id="choosen" disabled><%= p %></button>
			<%		
				} else {
			%>
			<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= p %>'"><%= p %></button>			
			<%
				}
			}
			%>				
			
			<!-- 다음 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= currentPage+1 %>'" id="afterBtn">&gt;</button>
			<script>
				if(<%= currentPage %> >= <%= maxPage %>){
					$('#afterBtn').attr('disabled', true);
				}
			</script>
			<!-- 맨 뒤로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= maxPage %>'">&gt;&gt;</button>
		</div>
		
		<div class="buttonArea" align="right">
			<% if(loginUser != null){ %>
				<button onclick="location.href='<%= request.getContextPath() %>/writeBoardForm.bo'">작성하기</button>
			<% } %>
		</div>
		
		<script>
			$(function(){
				$('#listArea td').on({'mouseenter':function(){ 
					$(this).parent().css({'background': 'darkgray', 'cursor':'pointer'});
				}, 'mouseout':function(){
					$(this).parent().css('background', 'none');
				}, 'click':function(){
					var num = $(this).parent().children().eq(0).text(); 
					location.href='<%= request.getContextPath() %>/detail.bo?bId=' + num;
				}}); 
			});
		</script>
	</div>
</body>
</html>