<%-- 
    Document   : showCart
    Created on : Oct 21, 2024, 8:39:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Cart,java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% 
     Vector<Cart> vector = (Vector<Cart>)request.getAttribute("vectorCart");%>
    <body>
        <form action="CartURL" method="get">

            <p>
                <input type="hidden" value="service" name="showCart">
                Search name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Reset" name="clean">
            </p>
            <input type="hidden" value="listAllProducts" name="service">
        </form>

        <table>
            <caption>Show Cart</caption>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>UnitPrice</th>
                <th>Quantity</th>
                <th>Discount</th>
                <th>subTotal</th>
                <th>Remove</th>
            </tr>
            <% double grandTotal = 0; 
            if (vector != null) { 
            double total = 0;
            
                for (Cart cart : vector) { 
                total = cart.getUnitPrice()*cart.getQuantity() - (cart.getUnitPrice()*cart.getQuantity())*cart.getDiscount();
                grandTotal += total;
%>
            <tr>
                <td><%= cart.getProductID() %></td>
                <td><%= cart.getProductName() %></td>
                <td><%= cart.getUnitPrice() %></td>
                <td><%= cart.getQuantity() %></td>
                <td><%= cart.getDiscount() %></td>
                <td><%=total %></td>
                <td><a href="CartURL?service=removeCart&pid=<%=cart.getProductID()%>">Remove Cart</a></td>
            </tr>
            <% 
                } 
            } 
            %>
        </table>
            <p>grandTotal = <%=grandTotal %></p> 
            <p><a href="CartURL?service=removeAllCart">Remove All Cart</a></p>
        <p><a href="ProductURL">Continued</a></p>
    </body>
</html>
