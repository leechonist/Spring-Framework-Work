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
		
		<Form Action = "analyze.do" Method="post">
			보고싶은 통계 데이터를 클릭해주세요<BR><BR>
			하루 <input type="radio" value="1" name="value"> 일주일<input type="radio" value="2" name="value"> 한달<input type="radio" value="3" name="value">
			<Input type="submit" value ="판매량" name ="type">
			<Input type="submit" value ="판매액" name ="type">
			<Input type="submit" value ="최다 판매 제품" name ="type">
			<Input type="submit" value ="메인" name ="type">
		</Form>
	</body>
</html>