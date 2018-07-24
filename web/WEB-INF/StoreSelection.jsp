<%@page import="java.util.ArrayList"%>
<%@page import="business.Store"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <script src="ajax.js" type="text/javascript"></script>
    <script src="inventory.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript">
        function pageAction()
        {
            if (ajax) {
                var sid = document.getElementById("storeid").value;
                ajax.open('get','ViewInventory?storeid=' + encodeURIComponent(sid));
                ajax.send(null);
            } else {
                document.selection.submit();
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Henry Books</title>
    </head>
    <body>
        <h1>Select Store for Inventory:</h1>
        <p>User: ${user.userid} - ${user.username}, ${user.adminlevel} Level</p>
        <p><i>${msg}</i></p>
        <form action="ViewInventory" id="selection" method="post">
            Stores: 
            <select id="storeid" name="storeid">
                <c:forEach var="st" items="${sl}">
                    <option ${st.storeid == user.storeid ? 'Selected' : ''}
                        value="${st.storeid}">${st.storename}
                    </option>
                </c:forEach>
            </select>
            <input type="button" value="View Inventory" onclick="pageAction()">
        </form>        
        <br>
        <div id="results"></div>
    </body>
</html>
