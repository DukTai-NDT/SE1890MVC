<%-- 
    Document   : displayEmployee
    Created on : Oct 7, 2024, 8:52:39 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector, entity.Employee" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           Vector<Employee> vector = (Vector<Employee>) request.getAttribute("data");
           String title = (String) request.getAttribute("title");
           Employee emp = (Employee)session.getAttribute("employee");
        %>
        <form action="EmployeeURL" method="get">
            <p>Search name : <input type="text" name = "ename" id ="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="reset" name="clean">
                <input type="hidden" value="listAllEmployee" name="service">
            </p>
        </form>
        <h3 align="right" >
             <span style="color: red">Welcome: <%=emp.getLastName() %></span> 
                <a href="EmployeeURL?service=logoutEmployee">LOGOUT EMPLOYEE</a>
        </h3>
        <p><a href=" EmployeeURL?service=insertEmployee">insert employee</a></p>

        <table>
            <caption><%= title %></caption>
            <tr>
                <th>EmployeeID</th>
                <th>LastName</th>
                <th>FirstName</th>
                <th>Title</th>
                <th>TitleOfCourtesy</th>
                <th>BirthDate</th>
                <th>HireDate</th>
                <th>Address</th>
                <th>City</th>
                <th>Region</th>
                <th>PostalCode</th>
                <th>Country</th>
                <th>HomePhone</th>
                <th>Extension</th>
                <th>Notes</th>
                <th>ReportsTo</th>
                <th>PhotoPath</th>
                <th>update</th>
                <th>delete</th>
            </tr>

            <% if(vector != null){
              for (Employee employee : vector) {
            %>

            <tr> 
                <td><%= employee.getEmployeeID() %></td>
                <td><%= employee.getLastName() %></td>
                <td><%=employee.getFirstName() %></td>
                <td><%= employee.getTitle()%></td>
                <td><%=employee.getTitleOfCourtesy() %></td>
                <td><%= employee.getBirthDate() %></td>
                <td><%=employee.getHireDate() %></td>
                <td><%=employee.getAddress() %></td>
                <td><%= employee.getCity() %></td>
                <td><%= employee.getRegion() %></td>
                <td><%= employee.getPostalCode() %></td>
                <td><%= employee.getCountry() %></td>
                <td><%=employee.getHomePhone() %></td>
                <td><%=employee.getExtension() %></td>
                <td><%=employee.getNotes() %></td>
                <td><%= employee.getReportsTo() %></td>
                <td><%= employee.getPhoto() %></td>
                <td> <p><a href="deleteEmployee?id="<%= employee.getEmployeeID() %>">Delete </a> </td>
                <td> <p><a href="updateEmployee?id="<%= employee.getEmployeeID() %>">Update </a> </td>
            </tr>
            <%
                }
            }
            %>
    </body>
</html>
