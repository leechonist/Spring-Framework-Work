<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Pos 웹 페이지</title>
</head>
	<body>
		<%
		String name = (String)session.getAttribute("name");
		String ID = (String)session.getAttribute("id");
		String password = (String)session.getAttribute("pw");		
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
				<Form Action = "editaccount.do" Method="post">
					이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : <Input type="text" Value = <%=name%> name = "strName"><BR>
					아이디&nbsp;&nbsp;&nbsp; : <Input type="text" Value = <%=ID%> name = "strID"><BR>
					패스워드 : <Input type="text" Value = <%=password%> name = "strPW"><BR>
						${Text}<BR>
					<Input type="Submit" Value="확인" Name = "value">
					<Input type="Submit" Value="메인" Name = "value"><BR><BR>
				</Form>
			</center>
	</body>
</html>