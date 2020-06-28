<%-- 
    Document   : OrderMenu
    Created on : Jun 17, 2020, 8:36:14 PM
    Author     : Erick Badilla Gonzalez
                   : Carlos Zhou Zheng
                   : David Vargas Mejia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/ico" href="products-images/favicon.ico">
        <meta HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
        <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
        <link href="client-css/orderMenu.css" rel="stylesheet" type="text/css"/>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
        <jsp:useBean class="models.Client" id="aux1" scope="session">
        </jsp:useBean>
        <script type="text/javascript">

            var user = {
                idClient: parseInt("<%=aux1.getIdClient()%>"),
                idCard: "<%=aux1.getIdCard()%>",
                name: "<%=aux1.getName()%>",
                lastName: "<%=aux1.getLastName()%>",
                address: "<%=aux1.getAddress()%>",
                phone: "<%=aux1.getPhone()%>",
                email: "<%=aux1.getEmail()%>",
                password: "<%=aux1.getPassword()%>"
            };
            console.log(user);
        </script>
        <script src="client-js/order.js" type="text/javascript"></script>
        <title>Pizza Patreon</title>
    </head>


    <body>
        <div class="wrapper">

            <div class="cart-overlay" id="cart-overlay">
                <a href="javascript:void(0)" class="closebtn" class= "closebtn" onclick="closeCart()">
                    &times;
                </a>


                <div class="cart-content">
                    <table class="cart-table">
                        <caption>
                            Carrito de Orden
                        </caption>
                        <thead id="cart-table-id">
                            <tr>
                                <td class="column1">
                                    <strong>Product ID</strong>
                                </td>
                                <td class="column2">
                                    <strong>Nombre</strong>
                                </td>
                                <td class="column3">
                                    <strong>Tamaño</strong>
                                </td>

                                <td class="column4">
                                    <strong>Cantidad</strong>
                                </td>

                                <td class="column5">
                                    <strong>Precio</strong>
                                </td>

                                <td class="column6">
                                    <strong>Sub total</strong>
                                </td>

                                <td class="column7">

                                </td>
                            </tr>
                        </thead>

                        <tbody id="cart-table-body">

                        </tbody>

                        <tfoot id="cart-table-foot">

                        </tfoot>
                    </table>
                </div>
            </div>

            <header>
                <h1>Pizza Patreon</h1>
                <h2>La mejor pizza los mejores ingredientes</h2>
            </header>

            <nav>
                <ul class="main-menu">
                    <ul class="main-menu">
                        <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=start">Inicio</a></li>
                        <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=order">Ordenar</a></li>
                        <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=account">Mi cuenta</a></li>
                        <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=orders">Mis Ordenes</a></li>
                        <li><a href="<%=request.getContextPath()%>/ClientRedirectServlet?menu=feedback">Feedback</a></li>
                    </ul>
                </ul>
            </nav>


            <section class="section-1">
                <div>
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
                </div>

                <div>
                    <div class="menu-name">
                        <h2>Orden</h2>
                    </div>

                    <div class="cart-container" id="cart-container">
                        <div class="products-cart" id="products-cart">

                        </div>

                        <div class="total-div">
                            <div>
                                <span>Subtotal:</span>
                                <span id="total"></span>
                            </div>

                            <input type="radio" id="cash-radio" name="payment-option" value="cash">
                            <label for="cash-radio">Efectivo</label>
                            <input type="radio" id="card-radio" name="payment-option" value="card">
                            <label for="card-radio">Tarjeta</label>

                            <div class="divBtn">
                                <!-- <button type="button" onclick="openCart()">Ver carrito</button> -->
                                <button id="buy-btn" type="button" >Comprar</button>
                            </div>

                            <script>
                                $("#buy-btn").click(function (e) {
                                    e.preventDefault();

                                    if (typeof user !== 'undefined' && typeof cartProducts !== 'undefined' && cartProducts.length > 0) {
                                        $.ajax({

                                            type: "POST",
                                            url: "CreateOrderServlet",
                                            dataType: "JSON",
                                            data: {
                                                order: JSON.stringify(JSON.parse(JSON.stringify(cartProducts))),
                                                user: JSON.stringify(JSON.parse(JSON.stringify(user)))
                                            },

                                            success: function (msg) {
                                                alert(msg.status);
                                                cleanOrder();
                                            },
                                            error: function (jqXHR, exception) {
                                                var msg = '';
                                                if (jqXHR.status === 0) {
                                                    msg = 'Not connect.\n Verify Network.';
                                                } else if (jqXHR.status === 404) {
                                                    msg = 'Requested page not found. [404]';
                                                } else if (jqXHR.status === 500) {
                                                    msg = 'Internal Server Error [500].';
                                                } else if (exception === 'parsererror') {
                                                    msg = 'Requested JSON parse failed.';
                                                } else if (exception === 'timeout') {
                                                    msg = 'Time out error.';
                                                } else if (exception === 'abort') {
                                                    msg = 'Ajax request aborted.';
                                                } else {
                                                    msg = 'Uncaught Error.\n' + jqXHR.responseText;
                                                }




                                            }


                                        });

                                    }


                                });
                            </script>
                        </div>
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
