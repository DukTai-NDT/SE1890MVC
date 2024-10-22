<%-- 
    Document   : loginCustomer
    Created on : Oct 19, 2024, 4:00:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% String message = (String)request.getAttribute("message"); 
    %>
    <body>
        <form action="CustomerURL" method="post"> 
           <input type="hidden" name="service" value="loginCustomer">
            <p>username:<input type="text" name="userName" value="" />
            <p> password:<input type="text" name="password" value="" />
            <p>   <input type="submit" value="Login" name ="submit" />
                <input type="reset" value="Clear" />
                <%=(message!=null?message:"")%>
    </body>
</html>
