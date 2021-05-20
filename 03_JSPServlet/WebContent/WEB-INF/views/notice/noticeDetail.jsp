<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.Notice" %>
<% Notice notice = (Notice)request.getAttribute("notice");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.outer{
		width: 600px; height: 500px; background-color: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 50px;
	}
	.tableArea {width:450px; height:350px; margin-left:auto; margin-right:auto;}
	#updateNoBtn{background: #D1B2FF;}
	#cancelBtn{background: #B2CCFF;}
	#deleteNoBtn{background: #D5D5D5;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">공지사항</h2>
		<div class="tableArea">
			<form action="noticeUpdateForm.no" id="detailForm" name="detailForm" method="post">
				<table>
					<tr>
						<th>제목</th>
						<td colspan="3">
							<input type="hidden" name="no" value="<%= notice.getNoticeNo() %>">
							<input type="hidden" name="title" value="<%= notice.getNoticeTitle() %>">
							<%= notice.getNoticeTitle() %>
						</td>				
					</tr>
					<tr>
						<th>작성자</th>
							<td>
								<%= notice.getNickName() %>
								<input type="hidden" name="nickName" value="<%= notice.getNickName() %>">
							</td>
						<th>작성일</th>
						<td>
							<input type="hidden" name="date" value="<%= notice.getNoticeDate() %>">
							<%= notice.getNoticeDate() %>
						</td>
					</tr>
					<tr>
						<th>내용</th>
					</tr>
					<tr>
						<td colspan="4">
							<textarea name="content" cols="60" rows="15" style="resize:none;" readonly><%= notice.getNoticeContent() %></textarea> 
							<!-- 길지만 이렇게 넣어주는게 공백 방지됨 -->
						</td>
					</tr>
				</table>
			
				<br>
				
				<div align="center">
					<% if(loginUser != null && loginUser.getUserId().equals(notice.getNoticeWriter())){ %>
						<input type="submit" id="updateNoBtn" value="수정">
						<input type="button" id="deleteNoBtn" value="삭제">
					<% } %>
					<input type="button" onclick="location.href='javascript:history.go(-1);'" id="cancelBtn" value="취소">
				</div>
			</form>
		</div>
	</div>
	
	<script>
		$('#deleteNoBtn').on('click', function(){
			var bool = confirm('정말 삭제하시겠습니까?');
			
			if(bool) {
				location.href='<%= request.getContextPath() %>/delete.no?no=' + <%= notice.getNoticeNo() %>;
			}
		
		})
	</script>
</body>
</html>