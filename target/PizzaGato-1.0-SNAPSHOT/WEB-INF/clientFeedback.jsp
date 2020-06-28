<%-- 
    Document   : feedback
    Created on : Jun 27, 2020, 11:37:02 AM
    Author     : Erick Badilla Gonzalez
                   : Carlos Zhou Zheng
                   : David Vargas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/ico" href="products-images/favicon.ico">
        <meta HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
        <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza Patreon</title>
        <link href="client-css/clientFeedback.css" rel="stylesheet" type="text/css"/>
        <script src="client-js/clientFeedback.js" type="text/javascript"></script>
    </head>

    <jsp:useBean class="models.Client" id="aux1" scope="session">
    </jsp:useBean>

    <script type="text/javascript">

        var user = {
            idClient: parseInt("<%=aux1.getIdClient()%>")
        };
        console.log(user);
    </script>

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
                <div class="feedback-div">
                    <h3>Deje su comentario sobre nosotros</h3>


                    <div class="text-div">
                        <textarea id="feedback-text"></textarea>
                    </div>

                    <div class="send-div">
                        <button type="button" onclick="sendFeedback()">Enviar</button>
                    </div>      
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
