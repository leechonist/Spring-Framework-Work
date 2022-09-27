<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Pos 웹 페이지</title>
</head>
	<body>		
		<%
		String name = (String)session.getAttribute("name");
		String position = (String)session.getAttribute("position");

		%>
		<div style="float: left;">
			<a href="main" style="font-size:200%;";>POS 웹 서비스</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="sale">판매</a>&nbsp;
			<a href="stock">재고 관리</a>&nbsp;
			<a href="checksale"> 거래 내역 확인</a>&nbsp;
		<%
		if(position.equals("관리자"))
		{
		%>			
			<a href="employee">직원 관리</a>&nbsp;
			<a href="analyze"> 통계</a>&nbsp;
		<%
		}
		%>
		</div>
		<div style="float: right;">
			<br>
			<p1 style="text-align: right;"> <%=name%>님(<%=position%>)</p1>
			<a href="editaccount"> 개인 정보 수정</a>&nbsp;
			<a href="logout"> 로그아웃</a>&nbsp;
		</div>
		<br><br><br><br>
		<center>
			거래가 취소되었습니다.
			<button onclick="location='main'">확인</button><BR><BR>
		</center>
	</body>
</html>