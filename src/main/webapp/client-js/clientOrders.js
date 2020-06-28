/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var order;


function loadOrder() {
    var rfInput = document.getElementById("order-input");

    if (rfInput) {
        if (rfInput.value !== '' && typeof rfInput !== 'undefined') {
            $.ajax({
                type: "POST",
                url: "InvoiceServlet",
                dataType: "json",

                data: {
                    idInvoice: rfInput.value
                },
                success: function (json) {
                    order = JSON.parse(JSON.stringify(json));
                    console.log(order);
                    insertTable();
                    console.log("Entre");
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

function insertTable() {
    var tbody = document.getElementById("tbody");
    if (tbody) {
        var row = tbody.insertRow(-1);
        var cell;

        /*ID de factura*/
        cell = row.insertCell(-1);
        cell.className = "column1";
        cell.innerText = order.idInvoice;

        /*Columna Fecha*/
        cell = row.insertCell(-1);
        cell.className = "column2";
        cell.innerText = order.date;

        /*Columna Sub total*/
        cell = row.insertCell(-1);
        cell.className = "column3";
        cell.innerText = order.status;
    }

}


