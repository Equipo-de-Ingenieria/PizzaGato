/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var aux = null;
function createDetails() {
    var id = localStorage.getItem("id");
    fetch('SellsMenuServlet?id=' + id).then(function (resultado) {
        return resultado.json();
    }).then(function (param) {
        cargarTabla(param);
    });
}
function init() {
    createDetails();

}

function cargarTabla(details) {
    var refTabla = document.getElementById('bt');
    if (refTabla) {
        var total = 0;
        details.forEach(function (fila, i, arreglo) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.product;
            nuevaCelda.setAttribute('class', 'd1');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.stock;
            nuevaCelda.setAttribute('class', 'd2');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.price;
            nuevaCelda.setAttribute('class', 'd2');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.subtotal;
            nuevaCelda.setAttribute('class', 'd2');


           
            total = fila.subtotal + total;
        });
        var nuevaFila = refTabla.insertRow(-1);
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.textContent = '';
        nuevaCelda.setAttribute('class', 'd2');
        nuevaCelda.setAttribute('colspan', '2');
        
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.textContent = 'Total =';
        nuevaCelda.setAttribute('class', 'd2');
        
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.textContent = total;
        nuevaCelda.setAttribute('class', 'd2');
    


    }
}


window.onload = init;