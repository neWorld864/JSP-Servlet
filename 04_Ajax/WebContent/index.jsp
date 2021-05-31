<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<style>
	.test{width: 300px; min-height: 50px; border: 1px solid red;}
</style>
</head>
<body>
	<h1>jQuery를 통한 Ajax 구현</h1>
	<h3>1. 버튼 선택 시 전송 값 서버에 출력</h3>
	이름 : <input type="text" id="myName"> <button id="nameBtn">이름 전송</button>
	<script>
		$('#nameBtn').on('click', function(){
			var name = $('#myName').val();
			
			$.ajax({
				url : 'jQueryTest1.do',
				data : {name:name}, // name이라고 하는 파라미터(앞에 있는 name)에 받아온 name값(뒤에 있는 name)을 담아 전달
				// 데이터들 다 객체로 보냄
				type: 'post',
				// post방식일 경우 인코딩 해줘야하지만 에이작스는 인코딩 안 해줘도 되긴 함 근데 해주는게 좋음 오류가 생길 수도 있기 때문에
				success: function(data){
					console.log("서버 전송 성공 시 호출되는 함수");
					console.log(data);
				},
				error: function(data){
					console.log('서버 전송 실패 시 호출되는 함수');
					console.log(data);
				},
				complete: function(data){
					console.log('무조건 실행되는 함수');
				}
			});
		});
	</script>
	
	<br>
	
	<h3>2. 버튼 선택 시 서버에서 보낸 값 사용자가 수신</h3>
	<button id="getServerTextBtn">서버에서 보낸 값 확인</button>
	<p class="test" id="p1"></p>
	<script>
		$('#getServerTextBtn').on('click', function(){
			$.ajax({
				url: 'jQueryTest2.do',
				// data, type 없어도 됨: 값을 주는 게 아니고 받아오는 것이기 때문에
				success: function(data){
					console.log("통신 성공");
					console.log(data);
					$('#p1').text(data); // 박스에 '서버에서 전송한 값입니다.' 출력됨
				}
				
			});
		});
	</script>
	
	<br>
	
	<h3>3. 서버로 기본형 전송 값이 있고 결과로 문자열을 받아 처리</h3>
	<h4>두 개의 값을 더한 결과를 받아옴</h4>
	첫 번째 숫자 : <input type="text" id="firstNum"><br>
	두 번째 숫자 : <input type="text" id="secondNum"><br>
	<button id="plusBtn">더하기</button>
	<p class="test" id="p2"></p>
	<script>
		$('#plusBtn').on('click', function(){
			var firstNum = $('#firstNum').val();
			var secondNum = $('#secondNum').val();
			
			$.ajax({
				url: 'jQueryTest3.do',
				data: {num1:firstNum, num2:secondNum},
				// num1, num2라는 이름으로 넘어가는 것임
				success: function(data){
					console.log(data);
					console.log(typeof data);
					$('#p2').text(data);
				}
			});
		});
	</script>
	
	<br>
	
	<h3>4. Object형태의 데이터를 서버에 전송, 서버에서 처리 후 서버 console로 출력</h3>
	학생 1: <input type="text" id="student1"><br>
	학생 2: <input type="text" id="student2"><br>
	학생 3: <input type="text" id="student3"><br>
	<button id="studentTest">결과확인</button>
	<script>
		$('#studentTest').on('click', function(){
			var student1 = $('#student1').val();
			var student2 = $('#student2').val();
			var student3 = $('#student3').val();
			
			var students = {
				student1: student1,	
				student2: student2,	
				student3: student3	
			};
			// 객체로 보냄
			
			$.ajax({
				url: 'jQueryTest4.do',
				data: students,
				success: function(data){
					console.log('성공');
				}
				
			});
		});
	</script>
	
	<br>
	
	<h3>5. 서버로 기본형 데이터 전송, 서버에서 객체 반환</h3>
	<h4>유저 번호 보내서 해당 유저 정보 가져오기</h4>
	유저 번호 : <input type="text" id="userNo">
	<button id="getUserInfoBtn">정보가져오기</button><br>
	<p class="test" id="p3"></p>
	<textarea class="test" id="textarea3" cols="40" rows="5"></textarea>
	<script>
		// 유저 번호를 받아와서 jQueryTest5.do에 보내는 ajax 생성
		$('#getUserInfoBtn').on('click', function(){
			var userNo = $('#userNo').val();
			
			$.ajax({
				url: 'jQueryTest5.do',
				data: {userNo:userNo},
				success: function(data){
					
					// console.log(data.userNo);
					// Object가 떠야하는데 String으로 뜸 -> 잘못된 것임
					// -> JSON 사용해야 한다 json.org
					// json.org -> json-simple -> 깃허브 readme에 있는 주소 -> download -> json-simple-1.1.1.jar 0412 5교시
					
					console.log(data); // {userNation: "미국", userNo: 2, userName: "타일러 라쉬"} => map 방식 : 순서 유지 안 됨
					console.log(typeof data); // object
					
					var resultStr = "";
					
					if(data != null){
						resultStr = data.userNo + ', ' + data.userName + ", " + data.userNation;
					} else {
						resultStr = "해당 회원이 없습니다.";
					}
					
					$('#p3').text(resultStr); // 2, 타일러 라쉬, 미국
					$('#textarea3').val(resultStr); // 2, 타일러 라쉬, 미국
					
				}
			});
		});
	</script>
	
	<br>
	
	<h3>6. 서버로 기본 값 전송, 서버에서 리스트 객체 반환</h3>
	<h4>유저 번호 요청 --> 해당 유저가 있는 경우 유저 정보를, 없는 경우 전체 정보 반환</h4>
	유저 번호 : <input type="text" id="userNo2">
	<button id="getUserInfoBtn2">정보 가져오기</button>
	<p class="test" id="p4"></p>
	<script>
		$('#getUserInfoBtn2').on('click', function(){
			$.ajax({
				url: 'jQueryTest6.do',
				data: {userNo:$('#userNo2').val()},
				success: function(data){
					console.log(data);
					// ajax할 때 데이터 항상 찍어보기
					
					var resultStr = "";
					for(var i in data){
						// 배열일 때 for in문에서 i에는 index가 담김
						var user = data[i];
						resultStr += user.userNo + ", "
									+ user.userName + ", "
									+ user.userNation + "<br>";
					}
					
					$('#p4').html(resultStr);
				}
			});
		});
	</script>
	
	<br>
	
	<h3>7. 서버로 데이터 여러 개 전송, 서버에서 리스트 객체 반환</h3>
	<h4>유저 번호 전송 --> 현재 있는 유저 정보만 모아서 출력</h4>
	<h4>10이상의 숫자는 ','로 쓸 수 없다고 설정</h4>
	유저 정보(번호,번호,번호) : <input type="text" id="userNo3">
	<button id="getUserInfoBtn3">정보 가져오기</button><br>
	<textarea class="test" id="textarea5" cols="40" rows="5"></textarea>
	
	<script>
		$('#getUserInfoBtn3').on('click', function(){
			$.ajax({
				url: 'jQueryTest7.do',
				data: {userNo:$('#userNo3').val()},
				success: function(data){
					console.log(data);
					console.log(data.list);
					
					var result = "";
					
					for(var i in data.list){
						var user = data.list[i];
						result += user.userNo + ", " + user.userName + ", " + user.userNation + "\n";
					}
					
					$('#textarea5').val(result);
				}
			});
		});
	</script>
	
	<br>
	
	
	<h3>8. 서버로 데이터 여러 개 전송, 서버에서 맵 형태의 객체 반환(JSONObject 반환)</h3>
	<h4>이하 내용은 7번 문제와 동일</h4>
	유저 정보(이름,이름,이름) : <input type="text" id="userName">
	<button id="getUserInfoBtn4">정보 가져오기</button><br>
	<textarea class="test" id="textarea6" cols="40" rows="5"></textarea>
	
	<script>
		$('#getUserInfoBtn4').on('click', function(){
			$.ajax({
				url: 'jQueryTest8.do',
				data: {userName:$('#userName').val()},
				success: function(data){
					console.log(data);
					
					var result = "";
					for(var key in data){
						var user = data[key];
						result += user.userNo + ", " + user.userName + ", " + user.userNation + "\n";
					}
					$('#textarea6').val(result);
				}
			});
		});
	</script>
	
	<br>
	
	<h3>9. 서버 유저 정보로 표 구성하기</h3>
	<button id="userInfoBtn">유저 정보 불러오기</button>
	<table id="userInfoTable" border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>국적</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<script>
		$('#userInfoBtn').on('click', function(){
			$.ajax({
				url: 'jQueryTest9.do',
				success: function(data){
					console.log(data);
					
					var $tableBody = $('#userInfoTable tbody');
					// 변수에 들어갈 수 있는 특수문자는 언더바랑 달러만 가능
					// 내 안에 제이쿼리가 있어요~ 알려주기 위해 변수명에 달러를 추가
					$tableBody.html(''); // 여러 번 눌러도 계속 추가 안 되도록 설정
					
					$.each(data, function(index, value){// for in문과 비슷한 함수
						var $tr = $('<tr>');
						var $noTd = $('<td>').text(value.userNo);
						var $nameTd = $("<td>").text(value.userName);
						var $nationTd = $('<td>').text(value.userNation);
						
						$tr.append($noTd);
						$tr.append($nameTd);
						$tr.append($nationTd);
						
						$tableBody.append($tr);
					});
				}
			});
		});
	</script>
	
	<h3>10. 서버에서 List객체 반환 받아 select태그를 이용해 보여주기</h3>
	
	유저 이름 : <input type="text" id="selectUserName">
	<button id="selectListBtn">9번 예제 이어서</button>
	<select id="selectListTest"></select>
	<script>
		$('#selectListBtn').on('click', function(){
			$.ajax({
				url: 'jQueryTest9.do',
				success: function(data){
					console.log(data);
					
					var $select = $('#selectListTest');
					$select.find('option').remove();
					
					for(var i = 0; i < data.length; i++){
						var name = data[i].userName;
						
						var selected = (name == $('#selectUserName').val()) ? 'selected' : '';
						
						$select.append('<option value="' + data[i].userNo + '"' + selected + ">" + name + "</option>");
					}
				}	
			});
		});
	</script>
	
	<h3>11. Gson을 이용한 List 반환</h3>
	<button id="gsonListBtn">list 가져오기</button>
	<select id="gsonListSelect"></select>
	<script>
		$('#gsonListBtn').on('click', function(){
			$.ajax({
				url: 'jQueryTest11.do',
				success: function(data){
					console.log(data);
					
					$select = $('#gsonListSelect');
					$select.find('option').remove();
					
					for(var i in data){
						var $option = $('<option>');
						$option.val(data[i].userNo);
						$option.text(data[i].userName);
						// val()이나 text() 중 아무거나 써도됨
						
						$select.append($option);
					}
				}
			});
		});
	</script>
</body>
</html>