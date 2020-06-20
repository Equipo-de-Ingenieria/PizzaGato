/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var products = null;
function inicialaizeData(product) {
    products = product;
}

function init() {
    cargarTabla();
}
function cargarTabla() {
    var refTabla = document.getElementById('bt');
    if (refTabla) {

        products.forEach(function (fila, i, arreglo) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.idProduct;
            nuevaCelda.setAttribute('class', 'd1');
            nuevaCelda.setAttribute('contenteditable', 'true');
            nuevaCelda.setAttribute('onchange', 'update();');


            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.code;
            nuevaCelda.setAttribute('class', 'd2');
            nuevaCelda.setAttribute('contenteditable', 'true');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.size;
            nuevaCelda.setAttribute('class', 'd2');
            nuevaCelda.setAttribute('contenteditable', 'true');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.description;
            nuevaCelda.setAttribute('class', 'd2');
            nuevaCelda.setAttribute('contenteditable', 'true');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.price;
            nuevaCelda.setAttribute('class', 'd2');
            nuevaCelda.setAttribute('contenteditable', 'true');

            var btn = document.createElement("BUTTON");
            btn.innerHTML = "<i onclick='deleteProduct();'>Borrar</i>";
            btn.setAttribute('class', 'borrar' + i);
            btn.setAttribute('indice', i);
            btn.setAttribute('id', fila.idProduct);
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.className = "c3";
            nuevaCelda.appendChild(btn);

        });


    }
}
function deleteProduct() {
    deletePro(event.target.parentNode.getAttribute('indice'), event.target.parentNode.getAttribute('id'));
}
function deletePro(row, idProduct) {
    var refTable = document.getElementById("bt");
    if (refTable) {
        refTable.deleteRow(row);
        fetch('PizzaMenuServlet?row=' + idProduct + '&method=delete').then(function (resultado) {
            return resultado.json();
        }).then(response);


        for (var i = row; i < refTable.rows.length; i++) {
            var refFila = refTable.rows[i];

            var campo = refFila.getElementsByTagName("BUTTON")[0];
            campo.setAttribute("indice", i);

            var refSubtotal = document.getElementById("total" + String(i + 1));

            // (Observe que nunca se da el caso de que hayan elementos con
            // un id repetido).

            refSubtotal.id = "total" + String(i);

            var btn = refFila.getElementsByTagName("I")[0].parentNode;
            btn.setAttribute("indice", i);
        }
    }
}

function response(data) {
    console.log(data);
}

function update() {
    var code = document.getElementById("code").value;
    var size = document.getElementById("size").value;
    var desc = document.getElementById("description").value;
    var price = document.getElementById("price").value;
    var type = document.getElementById("type").value;
    var imgPath = document.getElementById("imgPath").value;
    fetch('PizzaMenuServlet?code=' + code + '&price=' + price + '&desc=' + desc + '&size=' + size + '&type=' + type + '&imgPath=' + imgPath + '&method=update').then(function (resultado) {
        return resultado.json();
    }).then(response);
}
function add() {
    var code = document.getElementById("code").value;
    var size = document.getElementById("size").value;
    var desc = document.getElementById("description").value;
    var price = document.getElementById("price").value;
    var type = document.getElementById("type").value;
    var imgPath = document.getElementById("imgPath").value;
    fetch('PizzaMenuServlet?code=' + code + '&price=' + price + '&desc=' + desc + '&size=' + size + '&type=' + type + '&imgPath=' + imgPath + '&method=add').then(function (resultado) {
        return resultado.json();
    }).then(response);
}

window.onload = init;