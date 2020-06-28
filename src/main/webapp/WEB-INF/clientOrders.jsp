
<%-- 
    Document   : clientOrders
    Created on : Jun 28, 2020, 1:06:33 AM
    Author     : Erick Badilla Gonzalez
                   : Carlos Zhou Zheng
                   : David Vargas Mejia
                   
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="client-css/clientOrders.css" rel="stylesheet" type="text/css"/>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="client-js/clientOrders.js" type="text/javascript"></script>
        <title>Pizza Patreon</title>

        <jsp:useBean class="models.Client" id="aux1" scope="session">
        </jsp:useBean>

        <script type="text/javascript">

            var user = {
                idClient: parseInt("<%=aux1.getIdClient()%>")
            };
            console.log(user);
        </script>
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
                <div class="orders-container">


                    <div class="table-container">
                        <table class="invoice-table">
                            <thead>
                                <tr>
                                    <td class="column1">Numero</td>
                                    <td class="column2">Fecha</td>
                                    <td class="column3">Estado</td>
                                    <td>     <div class="search">
                                            <input id= "order-input" name="order-input" type="number" placeholder="Numero de Orden">
                                            <button id="search-btn" onclick="loadOrder()">Buscar</button>
                                        </div></td>
                                </tr>
                            </thead>

                            <tbody id="tbody">

                            </tbody>


                        </table>
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
