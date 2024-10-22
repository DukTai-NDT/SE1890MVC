<%-- 
    Document   : displayProduct
    Created on : Oct 3, 2024, 11:07:49 AM
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Vector, entity.Product" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Product</title>
    </head>
    <body>
        <%
            Vector<Product> vector = (Vector<Product>) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
        %>

        <form action="ProductURL" method="get">

            <p>
                <input type="hidden" value="service" name="listAllProducts">
                Search name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Reset" name="clean">
            </p>
            <input type="hidden" value="listAllProducts" name="service">
        </form>

        <p><a href="ProductURL?service=insertProduct">Insert product</a></p>
        <p align="right"><a href="CartURL?service=showCart">Show cart product</a></p>

        <table >
            <caption><%= title %></caption>
            <tr>
                <th>ProductName</th>
                <th>SupplierID</th>
                <th>CategoryID</th>
                <th>QuantityPerUnit</th>
                <th>UnitPrice</th>
                <th>UnitsInStock</th>
                <th>UnitsOnOrder</th>
                <th>ReorderLevel</th>
                <th>Discontinued</th>
                <th>Update</th>
                <th>Delete</th>
                <th>Add2Cart</th>
            </tr>
            <% if (vector != null) { 
                for (Product product : vector) { 
            %>
            <tr>
                <td><%= product.getProductName() %></td>
                <td><%= product.getSupplierID() %></td>
                <td><%= product.getCategoryID() %></td>
                <td><%= product.getQuantityPerUnit() %></td>
                <td><%= product.getUnitPrice() %></td>
                <td><%= product.getUnitsInStock() %></td>
                <td><%= product.getUnitsOnOrder() %></td>
                <td><%= product.getReorderLevel() %></td>
                <td><%= product.isDiscontinued() %></td>
                <td><a href="ProductURL?service=updateProduct&pid=<%=product.getProductID()%>">Update</a></td>
                <td><a href="ProductURL?service=deleteProduct&pid=<%=product.getProductID()%>">Delete</a></td>
                <td><a href="CartURL?service=add2Cart&pid=<%=product.getProductID()%>">Add2Cart</a></td>
            </tr>
            <% 
                } 
            } 
            %>
        </table>
    </body>
</html>
