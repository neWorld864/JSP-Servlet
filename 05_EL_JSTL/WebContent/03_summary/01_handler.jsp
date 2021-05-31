<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입</h2>
	<form name="personFrm" action="${ pageContext.request.contextPath }/handler.do" method="post">
		<table>
			<tr>
				<td class="Mytd">성명</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td class="Mytd">성별</td>
				<td>
					<div id="radioset">
						남 <input type="radio" name="gender" id="man" value="남">
						여 <input type="radio" name="gender" id="woman" value="여">
					</div>
				</td>
			</tr>
			<tr>
				<td class="Mytd">나이</td>
				<td><input type="number" name="age" id="age"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" name="전송" value="전송" onclick="goSubmit(0);">
					<input type="button" name="전송" value="전송" onclick="goSubmit(1);">
					<input type="button" name="전송" value="전송" onclick="goSubmit(2);">
				</td>
			</tr>
		</table>
		<input type="hidden" name="view">
	</form>
	
	<script>
		function goSubmit(flag){
			var frm = document.personFrm;
			if(flag == 0) {
				frm.view.value = "02_scriptlet.jsp";
			} else if(flag == 1) {
				frm.view.value = "03_action.jsp";
			} else {
				frm.view.value = "04_el.jsp";
			}
			frm.submit();
		}
	</script>
</body>
</html>