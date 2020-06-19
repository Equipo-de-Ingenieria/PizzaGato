<%@page import="servlets.PizzaMenuServlet"%>
<%@page import="servlets.CommentarieServlet"%>
<%@page import="models.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/admin.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
        <script src="js/pizzaMenuScript.js" type="text/javascript"></script>
        <title>Bank Crosaint</title>
    </head>
    <jsp:useBean class="models.Admin" id="aux" scope="session">
    </jsp:useBean>
    <script type="text/javascript">
        inicialaizeData(<%= new PizzaMenuServlet().getProducts()%>);
    </script>
    <body>
        <div id="wrapper">
            <div id="content">
                <div id="menubar">
                    <ul>
                        <li class="nombre"><%= aux.getName()%></li>
                        <li><a href="pizzaMenu.jsp">Pizzas</a></li>
                        <li><a href="retiremenu.jsp">Ventas</a></li>
                        <li><a href="commentaries.jsp">Comentarios</a></li>  
                    </ul>
                </div>
                <div id="main">
                    <section>
                        <form id="form" name="form">
                            <table id="table" class="table">
                                <tr>
                                    <td class="c1" style="font-weight: bold;">Código:</td>
                                    <td>
                                         <input type="text" id="code" name="code" size="30"/>
                                    </td>
                                </tr><tr>
                                    <td class="c1">Tamaño:</td>
                                    <td>
                                        <input type="text" id="size" name="size" size="30"/>
                                    </td>
                                </tr><tr>
                                    <td class="c1">Descripcion:</td>
                                    <td>
                                        <input type="text" id="description" name="description" size="30"/>
                                    </td>
                                </tr><tr>
                                    <td class="c1">Precio:</td>
                                    <td>
                                        <input type="number" id="price" name="price" size="30"/>
                                    </td>
                                </tr><tr>
                                    <td colspan="2" class="f_btn">
                                        <button onclick = 'update();'>Actualizar</button>&nbsp;
                                        <button type="button" onclick = 'add();'>Agregar</button>
                                    </td>
                                </tr>
                            </table>
                        </form>                  
                    </section>
                    <table class="tablaGeneral">
                        <caption>Productos</caption>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Codigo</th>
                                <th>Tamaño</th>
                                <th>Descripcion</th>
                                <th>Precio</th>
                            </tr>
                        </thead>
                        <tbody id="bt"></tbody>
                        <tfoot>

                        </tfoot>
                    </table>

                </div>
            </div>
        </div>

    </body>
</html>
