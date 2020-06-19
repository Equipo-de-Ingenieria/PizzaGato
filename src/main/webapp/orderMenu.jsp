<%-- 
    Document   : OrderMenu
    Created on : Jun 17, 2020, 8:36:14 PM
    Author     : Erick
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
        <link href="client-css/orderMenu.css" rel="stylesheet" type="text/css"/>
        <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
        <script src="client-js/order.js" type="text/javascript"></script>
        <title>Pizza Patreon</title>
    </head>
    <body>
        <div class="wrapper">
            <header>
                <h1>Pizza Patreon</h1>
                <h2>La mejor pizza los mejores ingredientes</h2>
            </header>

            <nav>
                <ul class="main-menu">
                    <li href="#"><a>Ordenar</a></li>
                    <li href="#"><a>Mi cuenta</a></li>
                    <li href="#"><a>Mis Ordenes</a></li>
                    <li href="#"><a>Feedback</a></li>
                </ul>
            </nav>


            <section class="section-1">
                <div class="menu-name">
                    <h2>Menu</h2>

                    <div>
                        <select class="product-select" id="product-select" onchange="createProductsMenu()">
                            <option value="Pizza">Pizzas</option>
                            <option value="Bebida">Bebidas</option>
                            <option value="Postre">Postres</option>
                        </select>
                    </div>
                </div>
                <div class="products-container" id="products-container">
                  
                </div>

                <div class="cart-container" id="cart-container">

                </div>
            </section>

        </div>
    </body>
</html>
