<%-- 
    Document   : displayOrder
    Created on : Oct 18, 2024, 12:34:29 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Orders,java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Display</title>
    </head>
    <body>
       <form action="OrderURL" method="get">
            <p>Search name: 
                <input type="text" name="oname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="reset" name="clean">
                <input type="hidden" value="listAllOrder" name="service">
            </p>
        </form>
        
        <p><a href="HTML/insertOrder.html">Insert Order</a></p>

        <% 
            Vector<Orders> vector = (Vector<Orders>) request.getAttribute("data");
            
        %>
        <table border="1">
            <tr>
                <th>OrderID</th>
                <th>CustomerID</th>
                <th>EmployeeID</th>
                <th>OrderDate</th>
                <th>RequiredDate</th>
                <th>ShippedDate</th>
                <th>ShipVia</th>
                <th>Freight</th>
                <th>ShipName</th>
                <th>ShipAddress</th>
                <th>ShipCity</th>
                <th>ShipRegion</th>
                <th>ShipPostalCode</th>
                <th>ShipCountry</th>
                <th>Bill</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>

            <% 
                for (Orders orders : vector) { 
            %>
            <tr>
                <td><%= orders.getOrderID() %></td>
                <td><%= orders.getCustomerID() %></td>
                <td><%= orders.getEmployeeID() %></td>
                <td><%= orders.getOrderDate() %></td>
                <td><%= orders.getRequiredDate() %></td>
                <td><%= orders.getShippedDate() %></td>
                <td><%= orders.getShipVia() %></td>
                <td><%= orders.getFreight() %></td>
                <td><%= orders.getShipName() %></td>
                <td><%= orders.getShipAddress() %></td>
                <td><%= orders.getShipCity() %></td>
                <td><%= orders.getShipRegion() %></td>
                <td><%= orders.getShipPostalCode() %></td>
                <td><%= orders.getShipCountry() %></td>
                <td><a href="OrderURL?service=BillOrder&id=<%= orders.getOrderID() %>">Bill</a></td>
                <td><a href="updateOrder?id=<%= orders.getOrderID() %>">Update</a></td>
                <td><a href="deleteOrder?id=<%= orders.getOrderID() %>">Delete</a></td>
            </tr>
            <% 
                } 
            %>
        </table>
        
    </body>
</html>
