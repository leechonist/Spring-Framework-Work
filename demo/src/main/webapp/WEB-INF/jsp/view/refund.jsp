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
		
		<Form Action = "refund.do" Method="post">
			${head}
			<ul>
				<c:forEach var = "text" items="${texts}" varStatus="status">
					<li> 
						${text}
						<input type="number" value='${items[status.index].count}' min='0' max='${items[status.index].count}' Name = "Count">
					</li>
				</c:forEach>
			</ul>
			<center>
				<Input type="Submit" Value="취소" Name = "type">
				<Input type="Submit" Value="환불" Name = "type">
			</center>
		</Form>
	</body>
</html>