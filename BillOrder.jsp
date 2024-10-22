<%-- 
    Document   : BillOrder
    Created on : Oct 18, 2024, 1:21:53 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Orders,java.util.Vector,entity.Bill" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            Vector<Bill> vector = (Vector<Bill>) request.getAttribute("data");
            Bill bill = null; // Khai báo biến bill trước khi dùng
            
            
                bill = vector.get(0);
        %>

        
        <form action="CustomerURL" method="post"> 
            <input type="hidden" name="service" value="BillOrder">
            <p>OrderID: <%= bill.getOrderID() %></p>
            <p>OrderDate: <%= bill.getOrderDate() %></p>
            <p>RequiredDate: <%= bill.getRequiredDate() %></p>
            <p>CustomerName: <%= bill.getContactName() %></p>
            <p>EmployeeName: <%= bill.getFirstName() %></p>
        </form>

        <table border="1">
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>UnitPrice</th>
                <th>Quantity</th>
                <th>Discount</th>
                <th>Total</th>
            </tr>
            <% 
            double grandTotal = 0;
            for (Bill bill1 : vector) {
                double total = (bill1.getUnitPrice() * bill1.getQuantity()) * (1 - bill1.getDiscount()); // Tính tổng sau khi áp dụng chiết khấu
                grandTotal += total; // Cộng vào tổng lớn
            %>

            <tr>
                <td><%= bill1.getProductID() %></td>
                <td><%= bill1.getProductName() %></td>
                <td><%= bill1.getUnitPrice() %></td>
                <td><%= bill1.getQuantity() %></td>
                <td><%= bill1.getDiscount() %></td>
                <td><%= total %></td>
            </tr>
            <% } %>
        </table>
        <p>Grand Total: <%= grandTotal %></p>

        
        
    </body>
</html>
