/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = fetchProducts;

var productsArray;

function coinFormat(value) {

    // https://www.fileformat.info/info/unicode/char/20a1/index.htm
    // 20A1(16) = 8353(10)

    return String.fromCharCode(8353) + numeral(value).format(" 0,0.00");
}

function fetchProducts() {
    fetch('OrderServlet').then(function (result) {
        if (!result.ok) {
            console.log("Error en fetch de servlet");
            throw Error(result.statusText);
        }
        console.log(result);
        return result.json();

    }).then(function (json) {
        if (!json) {
            throw Error("JSON VACIO");
        }
        productsArray = json;
        console.log(productsArray);
        return json;

    }).then(function () {
        createProductsMenu();
    }).catch(function (error) {
        console.log(error);
    });
}

function createProduct(product, container) {
    var div = document.createElement("div");
    div.style.display = "flex";
    div.style.flexDirection = "column";
    div.style.flexWrap = "nowrap";


    div.style.height = "200px";
    div.style.width = "200px";
    div.style.padding = "10px";

    var img = document.createElement("img");
    img.src = product.imgpath;
    img.alt = "img";

    img.height = "200";
    img.width = "200";

    var divTitle = document.createElement("div");
    divTitle.style.margin = "10px 0px 2px 0px";
    var spanTitle = document.createElement("span");
    spanTitle.innerHTML = product.description;
    spanTitle.style.backgroundColor = "f3f3f3";
    divTitle.appendChild(spanTitle);


    var divPrice = document.createElement("div");
    divPrice.style.margin = "0px 0px 10px 0px";
    var spanPrice = document.createElement("span");
    spanPrice.innerHTML = coinFormat(product.price);
    spanPrice.style.backgroundColor = "f3f3f3";
    divPrice.appendChild(spanPrice);



    var button = document.createElement("button");
    button.action = "addProduct()";
    button.type = "button";
    button.innerHTML = "Agregar";


    div.appendChild(img);
    div.appendChild(divTitle);
    div.appendChild(divPrice);
    div.appendChild(button);
    container.appendChild(div);
}



function createProductsMenu() {
    var productsContainer = document.getElementById("products-container");
    productsContainer.innerHTML = "";

    var productSelect = document.getElementById("product-select");
    var productName;

    if (productSelect) {
        productName = productSelect.options[productSelect.selectedIndex].value;

        if (productsContainer && productsArray) {
            var products = productsArray.filter(function (product) {
                return product.type === productName;
            });

            switch (productName) {
                case "Pizza":
                    products.forEach(function (product, index, array) {
                        createProduct(product, productsContainer);
                    });
                    break;

                case "Bebida":
                    products.forEach(function (product, index, array) {
                        createProduct(product, productsContainer);
                    });
                    break;

                case "Postre":
                    products.forEach(function (product, index, array) {
                        createProduct(product, productsContainer);
                    });
                    break;

                default:

                    break;
            }
        }

    }
}





