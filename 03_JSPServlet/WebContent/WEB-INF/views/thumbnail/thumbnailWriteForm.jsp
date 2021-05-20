<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer {
		width:1000px; height:650px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left:auto; margin-right:auto; margin-top:50px;
	}
	.insertArea {width:500px; height:450px; margin-left:auto; margin-right:auto;}
	.btnArea {width:180px; margin-left:auto; margin-right:auto;}
	#titleImgArea {width:350px; height:200px; border:2px dashed darkgray; text-align:center; display:table-cell; vertical-align:middle;}
	#titleImgArea:hover, #contentImgArea1:hover, #contentImgArea2:hover, #contentImgArea3:hover {cursor:pointer;}
	#contentImgArea1, #contentImgArea2, #contentImgArea3 {
		width:150px; height:100px; border:2px dashed darkgray;
		text-align:center; display:table-cell; vertical-align:middle;
	}
	/* 부모태그에 display : table을 선언해 주고! 자식태그에 display : table-cell을 선언해주면 수가 몇개가 되든 동일한 간격으로 영역 가지게 됩니다 */
	
	#insertBtn{background: #D1B2FF;}
	#cancelBtn{background: #B2CCFF}
	#insertThumbTable{margin: auto;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">사진 게시판 작성</h2>
		
		<!-- 파일업로드를 위해 enctype을 지정해줘야 된다. -->
		<!-- <form> 태그의 enctype 속성은 폼 데이터(form data)가 서버로 제출될 때 해당 데이터가 인코딩되는 방법을 명시 -->
		<!-- enctype : 무조건!! 파일을 서버에 넘기기 위해 무조건 필요 -->
		<form action="<%= request.getContextPath() %>/insert.th" method="post" encType="multipart/form-data"><!-- 파일올리는 거기 때문에 -->
			<div class="insertArea">
				<table id="insertThumbTable">
					<tr>
						<th width="100px">제목</th>
						<td colspan="3"><input type="text" size="45" name="title"></td>
					</tr>
					<tr>
						<th>대표 이미지</th>
						<td colspan="3">
							<div id="titleImgArea">
								<img id="titleImg" width="350" height="200">
							</div>
						</td>
					</tr>
					<tr>
						<th>내용 사진</th>
						<td>
							<div id="contentImgArea1">
								<img id="contentImg1" width="120" height="100"> 
							</div>
						</td>
						<td>
							<div id="contentImgArea2">
								<img id="contentImg2" width="120" height="100"> 
							</div>
						</td>
						<td>
							<div id="contentImgArea3">
								<img id="contentImg3" width="120" height="100"> 
							</div>
						</td>
					</tr>
					<tr>
						<th width="100px">사진 메모</th>
						<td colspan="3"><textarea name="content" rows="5" cols="50" style="resize:none;"></textarea>
					</tr>
				</table>
				
				<!-- 파일 업로드 하는 부분 -->
				<div id="fileArea">
					<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)">
					<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)">
					<input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this,3)">
					<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this,4)">
					<!-- onchange는 이벤트가 변경됐을때 작동하는 함수이고, onclick은 이벤트가 클릭됐을때 작동하는 함수이다. -->
					<!-- 각 div를 누르면 파일 업로드가 뜨도록 설정 -->
				</div>
				<script>
					// 내용 작성 부분의 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
					$(function(){
						$("#fileArea").hide(); // 원래 있던 div는 사라지고 첨부한 파일이 들어가도록?
						
						$("#titleImgArea").click(function(){
							$("#thumbnailImg1").click();
						});
						$("#contentImgArea1").click(function(){
							$("#thumbnailImg2").click();
						});
						$("#contentImgArea2").click(function(){
							$("#thumbnailImg3").click();
						});
						$("#contentImgArea3").click(function(){
							$("#thumbnailImg4").click();
						});
					});
					
					// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
					// onchange (this, i) => (value, num)으로 받아줌 
					function LoadImg(value, num){
						if(value.files && value.files[0]){
							var reader = new FileReader();
							
							reader.onload = function(e){								
								switch(num){
								case 1: 
									$("#titleImg").attr("src", e.target.result); // e.target.result : 이미지 경로(이벤트가 일어나는 곳)
									break;
								case 2:
									$("#contentImg1").attr("src", e.target.result);
									break;
								case 3: 
									$("#contentImg2").attr("src", e.target.result);
									break;
								case 4:
									$("#contentImg3").attr("src", e.target.result);
									break;
								}
							}
							
							reader.readAsDataURL(value.files[0]);
						}
					}
				</script>
				
			</div>
			<br>
			<div class="btnArea">
				<input type="submit" id="insertBtn" value="작성완료">
				<input type="button" id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/list.th'" value="취소하기">
			</div>
		</form>
	</div>
</body>
</html>