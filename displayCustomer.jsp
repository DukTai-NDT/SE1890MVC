<%-- 
    Document   : displayCustomer
    Created on : Oct 13, 2024, 1:02:37 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector, entity.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Customer</title>
    </head>
    <body>
        <%
            Vector<Customer> vector = (Vector<Customer>)request.getAttribute("data");
            String title = (String)request.getAttribute("title");
             Customer cus = (Customer)session.getAttribute("customer");
        %>
        <form action="CustomerURL" method="get">

            <p>
                <input type="hidden" value="service" name="listAllCustomer">
                Search name: <input type="text" name="cusname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Reset" name="clean">
            </p>
            <input type="hidden" value="listAllCustomer" name="service">


            <h2 align="right">
                <%if(cus ==null) {%>
                <a href="CustomerURL?service=loginCustomer">LOGIN CUSTOMER</a>
                <% } else {%>
                <span style="color: red">Welcome <%=cus.getCompanyName() %></span> 
                <a href="CustomerURL?service=loginCustomer">LOGOUT CUSTOMER</a>

                <% } %>
            </h2>
            <p><a href="CustomerURL?service=insertCustomer">Insert customer</a></p> 
            <table> 
<!--                <caption><%= title%></caption>-->
                <tr> 
                    <th>CustomerID</th> 
                    <th>CompanyName</th> 
                    <th>ContactName</th> 
                    <th>ContactTitle</th>     
                    <th>Address</th> 
                    <th>City</th> 
                    <th>Region</th> 
                    <th>PostalCode</th> 
                    <th>Country</th> 
                    <th>Phone</th> 
                    <th>Fax</th> 
                    <th>update</th> 
                    <th>delete</th>
                </tr>
                <%if(vector != null){
                      for (Customer customer : vector) {
                %>
                <tr> 
                    <td><%=customer.getCustomerID()%></td>
                    <td><%=customer.getCompanyName()%></td>
                    <td><a href="OrderURL?service=listAllOrderWithContactName&cid=<%=customer.getCustomerID()%>"><%=customer.getContactName()%></a></td>
                    <td><%=customer.getContactTitle()%></td>
                    <td><%=customer.getAddress()%></td>
                    <td><%=customer.getCity()%></td>
                    <td><%=customer.getRegion()%></td>
                    <td><%=customer.getPostalCode()%></td>
                    <td><%=customer.getCountry()%></td>
                    <td><%=customer.getPhone()%></td>
                    <td><%=customer.getFax()%></td>
                    <td><a href="updateCustomer.jsp?id=<%= customer.getCustomerID() %>">Update</a></td>
                    <td><a href="deleteCustomer.jsp?id=<%= customer.getCustomerID() %>">Delete</a></td>


                </tr> 
                <%}}%>
            </table>
        </form>
    </body>
</html>
