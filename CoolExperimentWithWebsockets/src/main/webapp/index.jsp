<%@ page import="java.util.*" %>
<%@ page import="com.arjun.coolexperimentwithwebsockets.store.*" %>
<%@ page import="com.arjun.coolexperimentwithwebsockets.servlet.*" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="text/javascript" language="javascript" src="ajax1.js"></script>
        <script type="text/javascript" language="javascript" src="cart.js"></script>
    </head>
    <body onload = addToCart()>
        <div style="float: left; width: 500px">
            <h2>Catalog</h2>
            <table border="1">
                <thead><th>Name</th><th>Description</th><th>Price</th><th></th><th></th></thead>
                <tbody>
                    <%
                        for (Iterator<Item> I = new Catalog().getAllItems().iterator(); I.hasNext();) {
                            Item item = I.next();
                    %>
                    <tr>
                        <td><%= item.getName()%></td>
                        <td><%= item.getDescription()%></td>
                        <td><%= item.getFormattedPrice()%></td>
                        <!-- remove sendAdd() and sendRemove() to not broadcast individual item actions -->
                        <td><button onclick="addToCart('<%= item.getCode()%>'); sendAdd('<%= item.getCode()%>')">Add to Cart</button></td>
                        <td><button onclick="removeFromCart('<%= item.getCode()%>'); sendRemove('<%= item.getCode()%>')">Remove from Cart</button></td>
                    </tr
                    <% }%>
                </tbody>
            </table>
            <div style="position: absolute; top: 0px; right: 0px; width: 250px">
                <h2>Cart Contents</h2>
                <ul id="contents">
                </ul>
                Total cost: <span id="total">$0.00</span>
            </div>
        </div>
        <div id="output"></div>
        <script type="text/javascript" language="javascript" src="websocket.js"></script>
    </body>
</html>