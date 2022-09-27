<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Pos 웹 페이지 로그인</title>
</head>
	<a href="main" style="font-size:200%";>POS 웹 서비스</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#">판매</a>&nbsp;
	<a href="#">재고 관리</a>&nbsp;
	<a href="#"> 거래 내역 확인</a>&nbsp;
	<br><br><br><br>
	<center>
	<Form Action = "signincheck.do" Method="post">
		이름 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: <input type="text" Name = "strName"><BR>
		아이디 &nbsp&nbsp&nbsp: <input type="text" Name = "strID"><BR>
		비밀번호 : <input type="password" name = "strPW"><BR>
		재확인&nbsp&nbsp&nbsp : <input type="password" name = "strCheck"><BR>
		${Text}<BR>

		<Input type="Submit" Value="회원가입" Name = "value">
		<Input type="Submit" Value="취소" Name = "value">
	</Form>
	</center>
</html>