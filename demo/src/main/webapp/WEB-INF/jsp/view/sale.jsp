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
		
		<Form Action = "/saleresult" Method="post">
			<Input type="Submit" Value="카드 결제" Name = "method">
			<Input type="Submit" Value="현금 결제" Name = "method">
				
        <ul>
            <c:forEach var = "item" items="${Items}">
                <li> 
                    ${item.name} : ${item.price}원[${item.count}개] 
					<input type="number" value='0' min='0' max='${item.count}' Name = "Count" >
				</li>
            </c:forEach>
        </ul>
	</body>
</html>