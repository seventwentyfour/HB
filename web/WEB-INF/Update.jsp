<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Henry Books - Inventory Update</title>
    </head>
    <body>
        <h1>Inventory Update</h1>
        <br>
        <p>User: ${user.userid} - ${user.username}, ${user.adminlevel} Level</p>
        <br>
            Branch #: <b>${store.storeid}</b><br>
            Branch Name: <b>${store.storename}</b><br>
            Branch Location: <b>${store.storeaddr}</b>
        <br>
        <br>
            Book Cd: <b>${update.bookcd}</b><br>
            Book Title: <b>${update.title}</b><br>
            Author: <b>${update.author}</b>
        <br>
        <br><i>${msg}</i><br>
        <form id="update" action="Update" method="post">
             <input type="hidden" name="action" value="Update Inventory">
                 <input type="hidden" name="storeid" value="${store.storeid}">
                  <input type="hidden" name="bookcd" value="${update.bookcd}">
                Inventory on hand in Branch: <input type="text" name="bookqty" value="">
                <input type="submit" value="Update Inventory">
        </form>
                <br>
                <form id="cancel" action="ViewInventory">
                    <button type="submit">Cancel</button>
                </form>
    </body>
</html>
