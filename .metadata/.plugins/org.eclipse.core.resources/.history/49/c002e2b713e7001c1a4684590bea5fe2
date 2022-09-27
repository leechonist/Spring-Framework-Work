<%@ page contentType= "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTPYE html>
<html>
    <head> <meta charset="EUC-KR"></head>
    <body>
        <h1>Item Test</h1>
        <ul>
            <c:forEach var = "item" items="${Items}" varStatus="status">
                <li> 
                    (${item.id}) ${item.name} : ${item.price}[${item.count}]
                </li>
            </c:forEach>
        </ul>
    </body>
</html>