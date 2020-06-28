<%-- 
    Document   : clientmenu
    Created on : 14/06/2020, 06:31:14 PM
    Author     : Erick Badilla Gonzalez
                   : Carlos Zhou Zheng
                   : David Vargas Mejia
--%>

<%@page import="models.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/ico" href="products-images/favicon.ico">
        <meta HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
        <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="client-css/clientmenu.css" rel="stylesheet" type="text/css"/>
        <title>Pizza Patreon</title>

        <jsp:useBean class="models.Client" id="aux1" scope="session">
        </jsp:useBean>
        <%
            Client temp = (Client) request.getSession(true).getAttribute("userData");
            aux1.setClient(temp);
        %>

    </head>
    <body>
        <div class="wrapper">

            <header>
                <h1>Pizza Patreon</h1>
                <h2>La mejor pizza los mejores ingredientes</h2>
            </header>

            <nav>
                <ul class="main-menu">
                    <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=start">Inicio</a></li>
                    <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=order">Ordenar</a></li>
                    <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=account">Mi cuenta</a></li>
                    <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=orders">Mis Ordenes</a></li>
                    <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=feedback">Feedback</a></li>
                </ul>
            </nav>

            <section class="section-1">
                <div class="article-div">
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                        Pellentesque nibh metus, mollis vel sem in, fermentum placerat lacus. Fusce metus dui, blandit ut nisi in, mattis interdum urna. 
                        Duis venenatis nulla nisl, rhoncus luctus sapien euismod non. Sed semper neque a sem semper, a tristique libero finibus. 
                        Pellentesque faucibus mattis diam id facilisis. Phasellus enim lectus, congue a congue sagittis, sagittis id lectus. 
                        Duis eget massa et nisi efficitur tristique.
                    </p>
                </div>
                <div class="box-images">
                    <img src="products-images/Pizza/Ham&Cheesse.jpg" alt="img" class="image"/>
                </div>

                <div class="box-images">
                    <img src="products-images/Pizza/Margarrita.jpg" alt="img"/>
                </div>

                <div class="box-images">
                    <img src="products-images/Pizza/Peperronie.jpg" alt="img"/>
                </div>

                <div class="box-images">
                    <img src="products-images/Pizza/Supreme.jpg" alt="img"/>
                </div>
            </section>

            <section class="section-2">
                <footer>
                    <article style="margin-top: 50px;">
                        <em>
                            Copyright: Erick Badilla Gonzalez
                        </em>
                    </article>
                </footer>
            </section>

        </div>
    </body>
</html>