
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.Store"%>
<%@page import="business.Book"%>
<%@page import="business.User"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory View/Update</title>
    </head>
        <body>            
            <c:if test="${empty showuser}">
                <h1>Inventory View/Update</h1>
                <p>User: ${user.userid} - ${user.username}, ${user.adminlevel} Level</p>            
            </c:if>
            <br><p><i>${msg}</i></p>
            <br>
            Branch #: <b>${store.storeid}</b><br>
            Branch Name: <b>${store.storename}</b><br>
            Branch Location: <b>${store.storeaddr}</b>
            <br>
            
            <c:if test="${user.adminlevel == 'Admn' }">                  
                <form id="edit_record" action="Update" method="get">
                    <input type="hidden" name="action" value="Edit Record">
                    <input type="hidden" name="storeid" value="${store.storeid}">                    
                    Book Cd: <input type="text" id="bookcd" name="bookcd">
                    <input type="submit" value="Edit Record">
                </form> 
            </c:if>
            
            <table border="1">
            <tr>
                <th>Store</th>
                <th>Book Cd</th>
                <th>Title</th>
                <th>Retail Price</th>
                <th>Quantity</th>
            </tr>
            <c:forEach var="b" items="${bookinv}">
                <tr>
                    <td align="left">${b.storeid}</td>
                    <td align="left">${b.bookcd}</td>
                    <td align="left">${b.title}</td>
                    <td align="left"><fmt:formatNumber value = "${b.price}" type = "currency"/></td>
                    <td align="right">${b.qty}</td>
                </tr>
            </c:forEach>
            </table>            
        </body>
</html>
