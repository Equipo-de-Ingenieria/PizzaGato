<%-- 
    Document   : clientAccount
    Created on : Jun 26, 2020, 8:59:00 PM
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
        <link href="client-css/clientAccount.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="client-js/clientAccount.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    </head>

    <jsp:useBean class="models.Client" id="aux1" scope="session">
    </jsp:useBean>
    <%
        Client temp = (Client) request.getSession(true).getAttribute("userData");
        aux1.setClient(temp);
    %>

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
                <div class="info-container">
                    <div class="menu-title">
                        <h3>Ingrese los datos que desea actualizar</h3>
                    </div>

                    <div class="name-div">
                        <input id= "name-input" name="name-input" placeholder="nombre" type="text" value="<%=aux1.getName()%>" maxlength="45" >
                    </div>

                    <div class="name-div">
                        <input id= "last-input" name="last-input" placeholder="Apellidos" type="text" value="<%=aux1.getLastName()%>" maxlength="45" >
                    </div>

                    <div class="idCard-div">
                        <input id= "idCard-input" name="idCard-input" placeholder="ID" type="number" value="<%=aux1.getIdCard()%>" maxlength="9" >
                    </div>

                    <div class="email-div">
                        <input id= "email-input" name="email-input" placeholder="e-mail" type="email" value="<%=aux1.getEmail()%>"maxlength="45" >
                    </div>

                    <div class="phone-div">
                        <input id= "phone-input" name="phone-input" placeholder="telefono" type="tel" value="<%=aux1.getPhone()%>" maxlength="8" >
                    </div>

                    <div class="address-div">
                        <input id= "address-input" name="address-input" placeholder="Dirreccion" type="text" value="<%=aux1.getAddress()%>" maxlength="45">
                    </div>

                    <div class="password-div">
                        <input id= "password-input" name="password-input" placeholder="contraseña" type="password" value="<%=aux1.getPassword()%>" maxlength="45">
                    </div>

                    <div class="btn-div">
                        <button type="button" id= "btn" name="btn" onclick="updateClientInfo()">Actualizar</button>
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
