/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function updateClientInfo() {

    var name = document.getElementById("name-input").value;
    var lastName = document.getElementById("last-input").value;
    var idCard = document.getElementById("idCard-input").value;
    var address = document.getElementById("address-input").value;
    var email = document.getElementById("email-input").value;
    var phone = document.getElementById("phone-input").value;
    var password = document.getElementById("password-input").value;
    
    
    if (name !== "" && lastName !== "" && phone !== "" && email !== "" && address !== "" && password !== "" && idCard !== "") {

        $.ajax({
            type: "POST",
            url: "UpdateClientServlet",
            dataType: "json",
            data: {
                idClient: user.idClient,
                idCard: idCard,
                name: name,
                lastName: lastName,
                address: address,
                phone: phone,
                email: email,
                password: password
            },
            success: function (msg) {
                alert(msg.status);
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
                    msg = 'Se actualizaron los datos con exito';
                } else if (exception === 'timeout') {
                    msg = 'Time out error.';
                } else if (exception === 'abort') {
                    msg = 'Ajax request aborted.';
                } else {
                    msg = 'Uncaught Error.\n' + jqXHR.responseText;
                }

                alert(msg);
            }


        });
    }
}

