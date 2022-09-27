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
		
		<Form Action = "/stockchange" Method="post">
			<Input type="Submit" Value="재고 수정" Name = "value">
			<%
			if(position.equals("관리자"))
			{
			%>
			<Input type="Submit" Value="정보 수정" Name = "value">
			<Input type="Submit" Value="상품 추가" Name = "value">
			<Input type="Submit" Value="재고 내역" Name = "value">
			<%
			}
			%>
			<Input type="Submit" Value="메인" Name = "value">
			<ul>
				<c:forEach var = "item" items="${Items}">
					<li> 
						<Input type="radio" Value=${item.id} Name = "itemid">
						${item.name} : ${item.price}원[${item.count}개] 
					</li>
				</c:forEach>
			</ul>
		</Form>
	</body>
</html>