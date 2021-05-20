<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.Board, board.model.vo.Reply, java.util.ArrayList" %>
<% 
	Board b = (Board)request.getAttribute("board"); 
	ArrayList<Reply> list = (ArrayList<Reply>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		width:800px; min-height:500px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left:auto; margin-right:auto; margin-top:50px;
	} /* 댓글 계속 쓰면 늘어날 수 있으니 height에서 min-height로 변경 */
	.tableArea {width: 450px; height:350px; margin-left:auto; margin-right:auto; align: center;}
	table{align: center;}
	#updateBtn{background: #B2CCFF;}
	#menuBtn{background: #D1B2FF;}
	#deleteBtn{background: #D5D5D5;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
		
	<div class="outer">
		<br>
		<h2 align="center">게시판 상세보기</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/boardUpdateForm.bo" id="detailForm" method="post">
				<table>
					<tr>
						<th>분야</th>
						<td>
							<%= b.getCategory() %>
							<input type="hidden" size="50" name="bId" value="<%= b.getBoardId() %>"> <!-- 없으면 안 됨 -->
							<input type="hidden" size="50" name="category" value="<%= b.getCategory() %>">
						</td>
						<th>제목</th>
						<td colspan="3">
							<input type="hidden" size="50" name="title" value="<%= b.getBoardTitle() %>">
							<%= b.getBoardTitle() %>
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<input type="hidden" size="50" name="nickName" value="<%= b.getNickName() %>">
							<%= b.getNickName() %>
						</td>
						
						<th>조회수</th>
						<td>
							<input type="hidden" size="50" name="count" value="<%= b.getBoardCount() %>">
							<%= b.getBoardCount() %>
						</td>
						
						<th>작성일</th>
						<td>
							<input type="hidden" size="50" name="date" value="<%= b.getModifyDate() %>">
							<%= b.getModifyDate() %>
						</td>
					</tr>
					<tr>
						<th>내용</th>
					</tr>
					<tr>
						<td colspan="6">
							<textarea name="content" cols="60" rows="15" style="resize:none;" value="<%= b.getBoardContent() %>" readonly ><%= b.getBoardContent() %></textarea>
						</td>
					</tr>
				</table>
				
				<div align="center">
				<% if(loginUser != null && b.getBoardWriter().equals(loginUser.getUserId())) { %>
					<input type="submit" id="updateBtn" value="수정">
					<input type="button" onclick="deleteBoard();" id="deleteBtn" value="삭제">
					<% } %>
					<input type="button" onclick="location.href='<%= request.getContextPath() %>/list.bo'" id="menuBtn" value="메뉴로">
				</div>
			</form>
		</div>
		
		<div class="replyArea">
			<div class="replyWriterArea"><!-- 댓글 작성 부분 -->
				<table>
					<tr>
						<td>댓글 작성</td>
						<td><textarea rows="3" cols="80" id="replyContent" style="resize:none;"></textarea>
						<td><button id="addReply">댓글 등록</button></td>
					</tr>
				</table>
			</div>
			
			<div id="replySelectArea"><!-- 댓글 조회 부분 -->
				<table id="replySelectTable">
					<% if(list.isEmpty()){ %>
						<tr><td colspan="3">댓글이 없습니다.</td></tr>
					<% } else { %>
						 <% for(int i = 0; i < list.size(); i++) { %>
							<tr>
								<td width="100px"><%= list.get(i).getNickName() %></td>
								<td width="400px"><%= list.get(i).getReplyContent() %></td>
								<td width="200px"><%= list.get(i).getCreateDate() %></td>
							</tr>
						<% } %>
					<% } %>
				</table>
			</div>
		</div>
	</div>
		
	<script>
		$(function(){
			$('#addReply').on('click', function(){
				var writer = '<%= loginUser.getUserId() %>';
				var bId = <%= b.getBoardId() %>;
				var content = $('#replyContent').val();
				
				$.ajax({
					url: 'insertReply.bo',
					data: {writer:writer, bId:bId, content:content},
					success: function(data){
						console.log(data);
						$replyTable = $('#replySelectTable');
						$replyTable.html('');
						
						for(var key in data){
							var $tr = $('<tr>');
							var $writerTd = $('<td>').text(data[key].nickName).css('width', '100px');
							var $contentTd = $('<td>').text(data[key].replyContent).css('width', '400px');
							var $dateTd = $('<td>').text(data[key].createDate).css('width', '200px');
							
							$tr.append($writerTd);
							$tr.append($contentTd);
							$tr.append($dateTd);
							$replyTable.append($tr);
						}
						
						$('#replyContent').val('');
					}
				});
			});
		});
		
		$(function(){
			$('#deleteBtn').on('click', function(){
				var bool = confirm('정말 삭제하시겠습니까?');
				
				if(bool) {
					location.href='<%= request.getContextPath() %>/delete.bo?bId=' + <%= b.getBoardId() %>;
				}
			});
		});
	</script>
</body>
</html>