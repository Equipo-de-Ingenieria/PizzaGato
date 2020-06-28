<%-- 
    Document   : feedback
    Created on : Jun 27, 2020, 11:37:02 AM
    Author     : Erick Badilla Gonzalez
                   : Carlos Zhou Zheng
                   : David Vargas Mejia
--%>


<%@page import="models.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/admin.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza Gato</title>
    </head>
      <jsp:useBean class="models.Admin" id="aux" scope="session">
        </jsp:useBean>
    <% 
        Admin aux1 = (Admin) request.getSession(true).getAttribute("userData");
        aux.setAdmin(aux1);
    %>
    <body>
        <div id="wrapper">
            <div id="content">
                <div id="menubar">
                    <ul>
                        <li class="nombre"><%= aux1.getName()%></li>
                        <li><a href="pizzaMenu.jsp">Pizzas</a></li>
                        <li><a href="sellsMenu.jsp">Ventas</a></li>
                        <li><a href="commentaries.jsp">Comentarios</a></li>  
                    </ul>
                </div>
                <div id="main">

                </div>
            </div>
        </div>

    </body>
</html>
