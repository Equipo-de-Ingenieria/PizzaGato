<%-- 
    Document   : index
    Created on : Apr 24, 2020, 4:27:41 PM
    Author     : Erick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <meta charset="UTF-8">
    </head>
    <body>
        <div class="wrapper">
            <div class="content">
                <div class="loginContainer">
                    <form class="loginForm" action="ClientSigninServlet" method="post">
                        <div class="box1">
                            <span>
                                Crear cuenta
                            </span>
                        </div>

                        <div class="box2">
                            <span>
                                <input class="user" placeholder="Cedula" name="userInput" required />
                            </span>

                            <span> 
                                <input class="password" type="password" placeholder="Contraseña" name="passInput" required/>
                            </span>
                            <span>
                                <input class="user" placeholder="Nombre" name="name" required />
                            </span>
                            <span>
                                <input class="user" placeholder="Apellidos" name="lastname" required />
                            </span>
                            <span>
                                <input class="user" placeholder="Direccion" name="address" required />
                            </span>
                            <span>
                                <input class="user" placeholder="Telefono" name="phone" required />
                            </span>
                            <span>
                                <input class="user" placeholder="Email" name="email" required />
                            </span>
                        </div>

                        <div class="box3">
                            <span>
                                <button class="summitBtn" type="submit">Crear</button>
                            </span>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
