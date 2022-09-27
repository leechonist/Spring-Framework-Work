<%@ page contentType= "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTPYE html>
<html>
    <head> <meta charset="EUC-KR"></head>
    <body>
        <h1>Sale Test</h1>
        <ul>
            <c:forEach var = "saleitem" items="${SaleItems}" varStatus="status">
                <li> 
                    ${saleitem.saleID} : ${saleitem.itemID}, ${saleitem.count} 
                </li>
            </c:forEach>
        </ul>
    </body>
</html>