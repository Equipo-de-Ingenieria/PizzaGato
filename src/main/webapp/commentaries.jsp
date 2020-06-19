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
        <script src="js/commentariesScript.js" type="text/javascript"></script>
        <title>Bank Crosaint</title>
    </head>
    <jsp:useBean class="models.Admin" id="aux" scope="session">
    </jsp:useBean>
    <script type="text/javascript">
            inicialaizeData(<%= new CommentarieServlet().getFeedBacks()%>);
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
                    <table>
                        <caption>Comentarios</caption>
                        <thead>
                            <tr>
                                <th colspan="2">Cliente</th>
                                <th colspan="2">Comentario</th>
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
