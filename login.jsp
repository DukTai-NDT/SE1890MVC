<%-- 
    Document   : login
    Created on : Oct 17, 2024, 11:34:25 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
       <form action="../CustomerURL" method="post"> 
           <input type="hidden" name="service" value="loginWithContactName">
            <p>username:<input type="text" name="userName" value="" />
            <p> password:<input type="text" name="password" value="" />
            <p>   <input type="submit" value="Login" name ="Submit " />
                <input type="reset" value="Clear" />
         
        </form>
       
    </body>
</html>
