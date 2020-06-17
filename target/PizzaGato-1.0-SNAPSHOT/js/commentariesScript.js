/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var comments = null;

function inicialaizeData(comment){ 
    comments = comment;
}
function init(){
//    fetch('CommentarieServlet').then(function (resultado) {
//        return resultado;
//    }).then(function (datos) {
//        cargarTabla(datos['data']);
//    });
cargarTabla();
}
function cargarTabla() {
    var refTabla = document.getElementById('bt');
    if (refTabla) {

        /*
         var totalPoblacion = 0;
         for(var i = 0; i < datos.length; i++) {
         totalPoblacion += datos[i].poblacion;
         }
         */

//        var totalPoblacion = datos.reduce(function (acumulador, elemento) {
//            return acumulador + elemento.poblacion;
//        }, 0);

        comments.forEach(function (fila, i, arreglo) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.name;
            nuevaCelda.setAttribute('class', 'd1');
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.description;
            nuevaCelda.setAttribute('class', 'd2');
        });

        
    }
}


window.onload = init;


