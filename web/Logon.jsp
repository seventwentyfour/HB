<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HenryBooks Logon</title>
    </head>
    <body>
        <h1>Welcome to the Inventory System</h1>
        <h2>Please enter your user id and password:</h2>
        <p><i>${msg}</i></p>   
        <form action="Authenticate" method="post">
            <table>
                <tr>
                    <td>User ID:</td>
                    <td><input type="text" name="userid" id="userid"
                               value="${empty user.userid ? cookie.userid.value : user.userid}">
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" id="password">
                    </td>
                </tr>
            </table><br>
            <input type="submit" value="Authentication">
        </form>
        <br>
    </body>
</html>
