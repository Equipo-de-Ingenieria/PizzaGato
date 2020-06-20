/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var invoices = null;
function inicialaizeData(invoice) {
    invoices = invoice;
}
function dummie() {
    var i = 0;
}

function init() {
    cargarTabla();
}

function cargarTabla() {
    var refTabla = document.getElementById('bt');
    if (refTabla) {
        invoices.forEach(function (fila, i, arreglo) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.idInvoice;
            nuevaCelda.setAttribute('class', 'd1');
            nuevaCelda.setAttribute('contenteditable', 'true');
            nuevaCelda.setAttribute('onchange', 'update();');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.idClient;
            nuevaCelda.setAttribute('class', 'd2');
            nuevaCelda.setAttribute('contenteditable', 'true');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.status;
            nuevaCelda.setAttribute('class', 'd2');
            nuevaCelda.setAttribute('contenteditable', 'true');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.date;
            nuevaCelda.setAttribute('class', 'd2');
            nuevaCelda.setAttribute('contenteditable', 'true');


            var btn = document.createElement("BUTTON");
            btn.innerHTML = "<i onclick='details();'>Detalles</i>";
            btn.setAttribute('class', 'borrar' + i);
            btn.setAttribute('indice', i);
            btn.setAttribute('id', fila.idInvoice);
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.className = "c3";
            nuevaCelda.appendChild(btn);
        });
    }
}
function details() {
    var id = event.target.parentNode.getAttribute('id');
    localStorage.setItem("id", id);
    changePage(); 
}
function changePage() {
    var url = 'detailMenu.jsp';
     window.location= url;
}
function createDetails(){
     var id = localStorage.getItem("id");
     fetch('SellsMenuServlet?id=' + id).then(function (resultado) {
            return resultado.json();
        }).then(response);
}
window.onload = init;