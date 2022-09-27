<%@ page contentType= "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTPYE html>
<html>
    <head> <meta charset="EUC-KR"></head>
    <body>
        <h1>Stock Test</h1>
        <ul>
            <c:forEach var = "stock" items="${Stocks}" varStatus="status">
                <li> 
                    (${stock.date}) ${stock.itemid} : ${stock.count}[${stock.desc}]
                </li>
            </c:forEach>
        </ul>
    </body>
</html>