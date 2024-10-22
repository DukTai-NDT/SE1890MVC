<%-- 
    Document   : loginEmployee
    Created on : Oct 20, 2024, 4:29:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <% String messageNull = (String)request.getAttribute("messageNull"); 
String messageFail = (String)request.getAttribute("messageFail"); 
    %>
    <body>
        <form action="EmployeeURL" method="post"> 
           <input type="hidden" name="service" value="loginEmployee">
            <p>username:<input type="text" name="userName" value="" />
            <p> password:<input type="text" name="passWord" value="" />
            <p>   <input type="submit" value="Login" name ="submit" />
                <input type="reset" value="Clear" />
         
        </form>
       
        <%if(messageFail != null){%>
        <%=messageFail %>
        <%  }%>
        
    </body>
</html>
