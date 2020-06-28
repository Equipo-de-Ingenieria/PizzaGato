/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function sendFeedback() {
    var rfTextArea = document.getElementById("feedback-text");

    if (rfTextArea) {
        if (rfTextArea.value !== "" || typeof rfTextArea.value !== 'undefined') {

            var message = rfTextArea.value;

            $.ajax({
                type: "POST",
                url: "SendFeedbackServlet",
                dataType: "json",
                data: {
                    idClient: user.idClient,
                    message: message
                },
                success: function (msg) {
                    alert(msg.status);
                   document.getElementById("feedback-text").value = "";
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
}



