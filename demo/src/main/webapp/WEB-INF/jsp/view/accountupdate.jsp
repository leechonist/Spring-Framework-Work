<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Pos 웹 페이지 로그인</title>
</head>
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
	<Form Action = "accountupdate.do" Method="post">
		이름 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: <input type="text" value = ${account.name} Name = "strName"><BR>
		아이디 &nbsp&nbsp&nbsp: <input type="text" value = ${account.id} Name = "strID"><BR>
		비밀번호 : <input type="password" value = ${account.password} name = "strPW"><BR>
		직책&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp : 
		<select name="accountposition">
			<c:if test="${account.position eq '1'}">
				<option value="1" selected>직원</option>
				<option value="2">관리자</option>
			</c:if>
			<c:if test="${account.position eq '2'}">
				<option value="1">직원</option>
				<option value="2" selected>관리자</option>
			</c:if>
		</select>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp.
		${Text}<BR>

			<input type="hidden" value =${account.id} name="curID">
		<Input type="Submit" Value="수정" Name = "value">
		<Input type="Submit" Value="취소" Name = "value">
	</Form>
	</center>
</html>