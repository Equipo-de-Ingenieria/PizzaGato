/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = fetchProducts;
var productsArray;
var tempSelectedProducts;
var selectedProduct;


var cartProducts = [];

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
        document.getElementById("total").innerHTML = coinFormat(0);
    }).catch(function (error) {
        console.log(error);
    });
}

function createPizza(product, container, productType) {
    var div = document.createElement("div");
    div.style.display = "flex";
    div.style.flexDirection = "column";
    div.style.flexWrap = "nowrap";
    div.style.height = "200px";
    div.style.width = "200px";
    div.style.padding = "10px";


    var divModal = document.createElement("div");


    divModal.style.display = "none";
    divModal.style.padding = "10px";
    divModal.style.position = "absolute";
    divModal.style.width = "180px";
    divModal.style.height = "180px";
    divModal.style.backgroundColor = "rgba(0, 0, 0, 0.35)";
    divModal.style.zIndex = "999";


    var ingriedientsP = document.createElement("p");
    ingriedientsP.style.decoration = "none";
    var formatText = "";

    product.ingredients.forEach(function (item, index, array) {


        if (product.ingredients.length !== index + 1) {
            formatText = formatText + item.name + ",\n";
        } else {
            formatText = formatText + item.name + ".";
        }
    });

    ingriedientsP.innerHTML = formatText;
    ingriedientsP.style.color = "white";
    ingriedientsP.style.fontWeight = "bold";

    divModal.appendChild(ingriedientsP);





    var img = document.createElement("img");
    img.src = product.imgpath;
    img.alt = "img";
    img.height = "200";
    img.width = "200";

    img.onmouseenter = function (e) {
        divModal.style.display = "block";
    };

    img.onmouseout = function (e) {
        divModal.style.display = "none";
    };

    var divTitle = document.createElement("div");
    divTitle.style.margin = "10px 0px 2px 0px";

    var spanTitle = document.createElement("span");
    spanTitle.innerHTML = product.description;
    spanTitle.style.color = "#b10705";
    spanTitle.style.fontWeight = "bold";
    spanTitle.style.backgroundColor = "f3f3f3";
    divTitle.appendChild(spanTitle);



    var sizeSelect = document.createElement("select");
    sizeSelect.id = "sizeSelect-" + product.description;
    sizeSelect.style.padding = "10px";

    var button = document.createElement("button");
    button.onclick = "addProduct(this)";
    button.type = "button";
    button.style.backgroundColor = "#b00000";
    button.style.border = "none";
    button.style.color = "#fff";
    button.style.padding = "5px";
    button.innerHTML = "Agregar";
    button.addEventListener('click', function () {
        console.log(this.getAttribute("idProduct"));
        addPizza(this.getAttribute("idProduct"), sizeSelect.options[sizeSelect.selectedIndex].text);
    });


    var divPrice = document.createElement("div");
    divPrice.style.margin = "0px 0px 10px 0px";

    var spanPrice = document.createElement("span");
    spanPrice.id = "product-" + product.description;
    spanPrice.style.backgroundColor = "f3f3f3";
    spanPrice.style.fontWeight = "400";
    divPrice.appendChild(spanPrice);


    var sizeOpt0 = document.createElement("option");
    sizeOpt0.innerHTML = "Selecione un Tamaño";
    sizeOpt0.disabled = true;
    var sizeOpt1 = document.createElement("option");
    sizeOpt1.value = "Pequeño";
    sizeOpt1.innerHTML = "Pequeña";
    var sizeOpt2 = document.createElement("option");
    sizeOpt2.value = "Mediano";
    sizeOpt2.innerHTML = "Mediana";
    var sizeOpt3 = document.createElement("option");
    sizeOpt3.value = "Grande";
    sizeOpt3.innerHTML = "Grande";

    sizeSelect.appendChild(sizeOpt0);
    sizeSelect.appendChild(sizeOpt1);
    sizeSelect.appendChild(sizeOpt2);
    sizeSelect.appendChild(sizeOpt3);

    sizeSelect.selectedIndex = 0;


    sizeSelect.addEventListener('change', function () {
        var selectVal = this.value;
        var exactProduct = tempSelectedProducts.find(function (item) {
            return item.size === selectVal && product.description === item.description;
        });

        console.log("Producto selecionado por evento");
        console.log(exactProduct);

        if (typeof exactProduct === 'undefined') {
            spanPrice.innerHTML = "";
            selectedProduct = null;
            return;
        }

        spanPrice.innerHTML = coinFormat(exactProduct.price);
        selectedProduct = exactProduct;
        button.setAttribute("idProduct", exactProduct.idProduct);
    });



    div.appendChild(img);
    div.appendChild(divTitle);
    div.appendChild(sizeSelect);
    div.appendChild(divPrice);
    div.appendChild(button);

    div.appendChild(divModal);

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

            tempSelectedProducts = products;

            console.log(products);

            const distinctProducts = products.filter(
                    (item, i, arr) => arr.findIndex(t => t.description === item.description) === i
            );


            console.log(distinctProducts);





            switch (productName) {
                case "Pizza":
                    distinctProducts.forEach(function (product, index, array) {
                        createPizza(product, productsContainer, productName);
                    });
                    break;
                case "Bebida":
                    distinctProducts.forEach(function (product, index, array) {
                        createBeverage(product, productsContainer, productName);
                    });
                    break;
                case "Postre":
                    distinctProducts.forEach(function (product, index, array) {
                        createDessert(product, productsContainer, productName);
                    });
                    break;
                default:

                    break;
            }
        }

    }
}


function addPizza(idProduct, size) {


    if (typeof cartProducts === 'undefined') {
        var exactProduct = tempSelectedProducts.find(function (item) {
            return item.idProduct.toString() === idProduct;
        });

        var rfProductCartDiv = document.getElementById("products-cart");

        if (rfProductCartDiv) {
            var div = document.createElement("div");
            div.class = "product-item";
            var h3 = document.createElement("h3");
            h3.style.margin = "10px 10px";
            h3.innerHTML = exactProduct.description + "  " + size;


            exactProduct.quantity = 1;

            cartProducts.push(exactProduct);

            var divGeneric = document.createElement("div");
            divGeneric.style.display = "flex";

            var divQuantity = document.createElement("div");
            divQuantity.id = "spanQuantity-" + exactProduct.description + "-" + exactProduct.size;
            divQuantity.innerHTML = exactProduct.quantity;
            divQuantity.style.margin = "5px;";

            var divPrice = document.createElement("div");
            divPrice.innerHTML = "  x  " + coinFormat(exactProduct.price);
            divPrice.style.margin = "5px;";

            divGeneric.appendChild(divQuantity);
            divGeneric.appendChild(divPrice);



            div.appendChild(h3);
            div.appendChild(divGeneric);

            rfProductCartDiv.appendChild(div);

            updateSubtotal();
        }

    } else {

        var exactProduct = cartProducts.find(function (item) {
            return item.idProduct.toString() === idProduct;
        });

        if (typeof exactProduct !== 'undefined') {
            exactProduct.quantity = exactProduct.quantity + 1;
            var spanQuantity = document.getElementById("spanQuantity-" + exactProduct.description + "-" + exactProduct.size);
            spanQuantity.innerHTML = exactProduct.quantity;
            updateSubtotal();

        } else {
            var exactProduct = tempSelectedProducts.find(function (item) {
                return item.idProduct.toString() === idProduct;
            });

            var rfProductCartDiv = document.getElementById("products-cart");

            if (rfProductCartDiv) {
                var div = document.createElement("div");
                div.class = "product-item";
                var h3 = document.createElement("h3");
                h3.innerHTML = exactProduct.description + "  " + size;


                exactProduct.quantity = 1;

                cartProducts.push(exactProduct);

                var divGeneric = document.createElement("div");
                divGeneric.style.display = "flex";

                var divQuantity = document.createElement("div");
                divQuantity.id = "spanQuantity-" + exactProduct.description + "-" + exactProduct.size;
                divQuantity.innerHTML = exactProduct.quantity;
                divQuantity.style.margin = "5px;";

                var divPrice = document.createElement("div");
                divPrice.innerHTML = "  x  " + coinFormat(exactProduct.price);
                divPrice.style.margin = "5px;";

                divGeneric.appendChild(divQuantity);
                divGeneric.appendChild(divPrice);



                div.appendChild(h3);
                div.appendChild(divGeneric);

                rfProductCartDiv.appendChild(div);

                updateSubtotal();
            }
        }

    }






}

function updateSubtotal() {
    var subtotal = 0;
    cartProducts.forEach(function (item, index, array) {
        subtotal = subtotal + (item.price * item.quantity);
    });

    document.getElementById("total").innerHTML = coinFormat(subtotal);

}


function openCart() {
    document.getElementById("cart-overlay").style.display = "block";
    document.getElementById("cart-overlay").style.width = "100%";

}

function closeCart() {
    document.getElementById("cart-overlay").style.width = "0%";
//    document.getElementById("cart-overlay").style.display = "none";
}




function createBeverage(product, container, productType) {
    var div = document.createElement("div");
    div.style.display = "flex";
    div.style.flexDirection = "column";
    div.style.flexWrap = "nowrap";
    div.style.height = "200px";
    div.style.width = "200px";
    div.style.padding = "10px";


    var divModal = document.createElement("div");

    var img = document.createElement("img");
    img.src = product.imgpath;
    img.alt = "img";
    img.height = "200";
    img.width = "200";

    img.onmouseenter = function (e) {
        img.style.transition = "transform .2s";
        img.style.transform = "scale(1.1)";
    };

    img.onmouseout = function (e) {
        img.style.transition = "transform .2s";
        img.style.transform = "scale(1)";
    };

    var divTitle = document.createElement("div");
    divTitle.style.margin = "10px 0px 2px 0px";

    var spanTitle = document.createElement("span");
    spanTitle.innerHTML = product.description;
    spanTitle.style.color = "#b10705";
    spanTitle.style.fontWeight = "bold";
    spanTitle.style.backgroundColor = "f3f3f3";
    divTitle.appendChild(spanTitle);

    var sizeSelect = document.createElement("select");
    sizeSelect.id = "sizeSelect-" + product.description;
    sizeSelect.style.padding = "10px";

    var button = document.createElement("button");
    button.onclick = "addProduct(this)";
    button.type = "button";
    button.style.backgroundColor = "#b00000";
    button.style.border = "none";
    button.style.color = "#fff";
    button.style.padding = "5px";
    button.innerHTML = "Agregar";


    button.addEventListener('click', function () {
        console.log(this.getAttribute("idProduct"));
        addBeverage(this.getAttribute("idProduct"), sizeSelect.options[sizeSelect.selectedIndex].text);


    });


    var divPrice = document.createElement("div");
    divPrice.style.margin = "0px 0px 10px 0px";

    var spanPrice = document.createElement("span");
    spanPrice.id = "product-" + product.description;
    spanPrice.style.backgroundColor = "f3f3f3";
    spanPrice.style.fontWeight = "400";
    divPrice.appendChild(spanPrice);


    var sizeOpt0 = document.createElement("option");
    sizeOpt0.innerHTML = "Selecione un Tamaño";
    sizeOpt0.disabled = true;
    var sizeOpt1 = document.createElement("option");
    sizeOpt1.value = "Pequeño";
    sizeOpt1.innerHTML = "Pequeña";
    var sizeOpt2 = document.createElement("option");
    sizeOpt2.value = "Mediano";
    sizeOpt2.innerHTML = "Mediana";
    var sizeOpt3 = document.createElement("option");
    sizeOpt3.value = "Grande";
    sizeOpt3.innerHTML = "Grande";

    sizeSelect.appendChild(sizeOpt0);
    sizeSelect.appendChild(sizeOpt1);
    sizeSelect.appendChild(sizeOpt2);
    sizeSelect.appendChild(sizeOpt3);

    sizeSelect.selectedIndex = 0;


    sizeSelect.addEventListener('change', function () {
        var selectVal = this.value;
        var exactProduct = tempSelectedProducts.find(function (item) {
            return item.size === selectVal && product.description === item.description;
        });

        console.log("Producto selecionado por evento");
        console.log(exactProduct);

        if (typeof exactProduct === 'undefined') {
            spanPrice.innerHTML = "";
            selectedProduct = null;
            return;
        }

        spanPrice.innerHTML = coinFormat(exactProduct.price);
        selectedProduct = exactProduct;
        button.setAttribute("idProduct", exactProduct.code);
    });



    div.appendChild(img);
    div.appendChild(divTitle);
    div.appendChild(sizeSelect);
    div.appendChild(divPrice);
    div.appendChild(button);

    div.appendChild(divModal);

    container.appendChild(div);
}

function createDessert(product, container, productType) {
    var div = document.createElement("div");
    div.style.display = "flex";
    div.style.flexDirection = "column";
    div.style.flexWrap = "nowrap";
    div.style.height = "200px";
    div.style.width = "200px";
    div.style.padding = "10px";


    var divModal = document.createElement("div");

    var img = document.createElement("img");
    img.src = product.imgpath;
    img.alt = "img";
    img.height = "200";
    img.width = "200";

    img.onmouseenter = function (e) {
        img.style.transition = "transform .2s";
        img.style.transform = "scale(1.1)";
    };

    img.onmouseout = function (e) {
        img.style.transition = "transform .2s";
        img.style.transform = "scale(1)";
    };

    var divTitle = document.createElement("div");
    divTitle.style.margin = "10px 0px 2px 0px";

    var spanTitle = document.createElement("span");
    spanTitle.innerHTML = product.description;
    spanTitle.style.color = "#b10705";
    spanTitle.style.fontWeight = "bold";
    spanTitle.style.backgroundColor = "f3f3f3";
    divTitle.appendChild(spanTitle);

    var sizeSelect = document.createElement("select");
    sizeSelect.id = "sizeSelect-" + product.description;
    sizeSelect.style.padding = "10px";

    var button = document.createElement("button");
    button.onclick = "addProduct(this)";
    button.type = "button";
    button.style.backgroundColor = "#b00000";
    button.style.border = "none";
    button.style.color = "#fff";
    button.style.padding = "5px";
    button.innerHTML = "Agregar";


    button.addEventListener('click', function () {
        console.log(this.getAttribute("idProduct"));
        addDessert(this.getAttribute("idProduct"), sizeSelect.options[sizeSelect.selectedIndex].text);
    });


    var divPrice = document.createElement("div");
    divPrice.style.margin = "0px 0px 10px 0px";

    var spanPrice = document.createElement("span");
    spanPrice.id = "product-" + product.description;
    spanPrice.style.backgroundColor = "f3f3f3";
    spanPrice.style.fontWeight = "400";
    divPrice.appendChild(spanPrice);


    var sizeOpt0 = document.createElement("option");
    sizeOpt0.innerHTML = "Selecione un Tamaño";
    sizeOpt0.disabled = true;
    var sizeOpt1 = document.createElement("option");
    sizeOpt1.value = "Pequeño";
    sizeOpt1.innerHTML = "Pequeña";
    var sizeOpt2 = document.createElement("option");
    sizeOpt2.value = "Mediano";
    sizeOpt2.innerHTML = "Mediana";
    var sizeOpt3 = document.createElement("option");
    sizeOpt3.value = "Grande";
    sizeOpt3.innerHTML = "Grande";

    sizeSelect.appendChild(sizeOpt0);
    sizeSelect.appendChild(sizeOpt1);
    sizeSelect.appendChild(sizeOpt2);
    sizeSelect.appendChild(sizeOpt3);

    sizeSelect.selectedIndex = 0;


    sizeSelect.addEventListener('change', function () {
        var selectVal = this.value;
        var exactProduct = tempSelectedProducts.find(function (item) {
            return item.size === selectVal && product.description === item.description;
        });

        console.log("Producto selecionado por evento");
        console.log(exactProduct);

        if (typeof exactProduct === 'undefined') {
            spanPrice.innerHTML = "";
            selectedProduct = null;
            button.removeAttribute("idProduct");
            return;
        }

        spanPrice.innerHTML = coinFormat(exactProduct.price);
        selectedProduct = exactProduct;
        button.setAttribute("idProduct", exactProduct.code);
    });



    div.appendChild(img);
    div.appendChild(divTitle);
    div.appendChild(sizeSelect);
    div.appendChild(divPrice);
    div.appendChild(button);

    div.appendChild(divModal);

    container.appendChild(div);
}

function addBeverage(idProduct, size) {
    if (typeof cartProducts === 'undefined') {
        var exactProduct = tempSelectedProducts.find(function (item) {
            return item.idProduct.toString() === idProduct;
        });

        var rfProductCartDiv = document.getElementById("products-cart");

        if (rfProductCartDiv) {
            var div = document.createElement("div");
            div.class = "product-item";
            var h3 = document.createElement("h3");
            h3.style.margin = "10px 10px";
            h3.innerHTML = exactProduct.description + "  " + size;


            exactProduct.quantity = 1;

            cartProducts.push(exactProduct);

            var divGeneric = document.createElement("div");
            divGeneric.style.display = "flex";

            var divQuantity = document.createElement("div");
            divQuantity.id = "spanQuantity-" + exactProduct.description + "-" + exactProduct.size;
            divQuantity.innerHTML = exactProduct.quantity;
            divQuantity.style.margin = "5px;";

            var divPrice = document.createElement("div");
            divPrice.innerHTML = "  x  " + coinFormat(exactProduct.price);
            divPrice.style.margin = "5px;";

            divGeneric.appendChild(divQuantity);
            divGeneric.appendChild(divPrice);



            div.appendChild(h3);
            div.appendChild(divGeneric);

            rfProductCartDiv.appendChild(div);

            updateSubtotal();
        }

    } else {

        var exactProduct = cartProducts.find(function (item) {
            return item.code === idProduct;
        });

        if (typeof exactProduct !== 'undefined') {
            exactProduct.quantity = exactProduct.quantity + 1;
            var spanQuantity = document.getElementById("spanQuantity-" + exactProduct.description + "-" + exactProduct.size);
            spanQuantity.innerHTML = exactProduct.quantity;
            updateSubtotal();

        } else {
            var exactProduct = tempSelectedProducts.find(function (item) {
                return item.code === idProduct;
            });

            if (typeof exactProduct === 'undefined') {
                return;
            }

            var rfProductCartDiv = document.getElementById("products-cart");

            if (rfProductCartDiv) {
                var div = document.createElement("div");
                div.class = "product-item";
                var h3 = document.createElement("h3");
                h3.innerHTML = exactProduct.description + "  " + size;


                exactProduct.quantity = 1;

                cartProducts.push(exactProduct);

                var divGeneric = document.createElement("div");
                divGeneric.style.display = "flex";

                var divQuantity = document.createElement("div");
                divQuantity.id = "spanQuantity-" + exactProduct.description + "-" + exactProduct.size;
                divQuantity.innerHTML = exactProduct.quantity;
                divQuantity.style.margin = "5px;";

                var divPrice = document.createElement("div");
                divPrice.innerHTML = "  x  " + coinFormat(exactProduct.price);
                divPrice.style.margin = "5px;";

                divGeneric.appendChild(divQuantity);
                divGeneric.appendChild(divPrice);



                div.appendChild(h3);
                div.appendChild(divGeneric);

                rfProductCartDiv.appendChild(div);

                updateSubtotal();
            }
        }

    }


}

function addDessert(idProduct, size) {
    if (typeof cartProducts === 'undefined') {
        var exactProduct = tempSelectedProducts.find(function (item) {
            return item.code === idProduct;
        });

        var rfProductCartDiv = document.getElementById("products-cart");

        if (rfProductCartDiv) {
            var div = document.createElement("div");
            div.class = "product-item";
            var h3 = document.createElement("h3");
            h3.style.margin = "10px 10px";
            h3.innerHTML = exactProduct.description + "  " + size;


            exactProduct.quantity = 1;

            cartProducts.push(exactProduct);

            var divGeneric = document.createElement("div");
            divGeneric.style.display = "flex";

            var divQuantity = document.createElement("div");
            divQuantity.id = "spanQuantity-" + exactProduct.description + "-" + exactProduct.size;
            divQuantity.innerHTML = exactProduct.quantity;
            divQuantity.style.margin = "5px;";

            var divPrice = document.createElement("div");
            divPrice.innerHTML = "  x  " + coinFormat(exactProduct.price);
            divPrice.style.margin = "5px;";

            divGeneric.appendChild(divQuantity);
            divGeneric.appendChild(divPrice);



            div.appendChild(h3);
            div.appendChild(divGeneric);

            rfProductCartDiv.appendChild(div);

            updateSubtotal();
        }

    } else {

        var exactProduct = cartProducts.find(function (item) {
            return item.code === idProduct;
        });

        if (typeof exactProduct !== 'undefined') {
            exactProduct.quantity = exactProduct.quantity + 1;
            var spanQuantity = document.getElementById("spanQuantity-" + exactProduct.description + "-" + exactProduct.size);
            spanQuantity.innerHTML = exactProduct.quantity;
            updateSubtotal();

        } else {
            var exactProduct = tempSelectedProducts.find(function (item) {
                return item.code === idProduct;
            });

            if (typeof exactProduct === 'undefined') {
                return;
            }

            var rfProductCartDiv = document.getElementById("products-cart");

            if (rfProductCartDiv) {
                var div = document.createElement("div");
                div.class = "product-item";
                var h3 = document.createElement("h3");
                h3.innerHTML = exactProduct.description + "  " + size;


                exactProduct.quantity = 1;

                cartProducts.push(exactProduct);

                var divGeneric = document.createElement("div");
                divGeneric.style.display = "flex";

                var divQuantity = document.createElement("div");
                divQuantity.id = "spanQuantity-" + exactProduct.description + "-" + exactProduct.size;
                divQuantity.innerHTML = exactProduct.quantity;
                divQuantity.style.margin = "5px;";

                var divPrice = document.createElement("div");
                divPrice.innerHTML = "  x  " + coinFormat(exactProduct.price);
                divPrice.style.margin = "5px;";

                divGeneric.appendChild(divQuantity);
                divGeneric.appendChild(divPrice);



                div.appendChild(h3);
                div.appendChild(divGeneric);

                rfProductCartDiv.appendChild(div);

                updateSubtotal();
            }
        }

    }


}


function generateTable() {
    var rfTable = document.getElementById("cart-table");

    if (!rfTable && !cartProducts) {

        var row = rfTable.insertRow(-1);
        var cell;

        /*ID de factura*/
        cell = row.insertCell(-1);
        cell.className = "column1";
        cell.innerText = invoice.sequence;

        /*Columna Fecha*/
        cell = row.insertCell(-1);
        cell.className = "column2";
        cell.innerText = invoice.date;

        /*Columna Sub total*/
        cell = row.insertCell(-1);
        cell.className = "column3";
        cell.innerText = coinFormat(subtotal);

        total = total + subtotal;

        /*Se genera tabla vacia*/
    }

}


function saveOrder() {
    if (typeof cartProducts !== 'undefined' && cartProducts.length > 0) {

        var formData = new FormData();
        for (var key in cartProducts) {
            formData.append(key, cartProducts[key]);
        }

        let url = 'CreateOrderServlet';
        let header = {
            'Accept': 'application/json',
            'Content-Type': 'charset=UTF-8'
        };
        let config = {
            method: 'POST',
            headers: header,
            body: formData
//            dataType: 'json',
//            contentType: 'application/json',
//            mimeType: 'application/json'
        };

        fetch(url, config).then(res => res.json())
                .catch(error => console.error('Error', error))
                .then(response => alert('Se guardo la factura', response)).then(cleanOrder());


    } else {
        console.log("Los productos del usuario estan vacios");
    }
}

function cleanOrder() {
    document.getElementById("products-cart").innerHTML = "";
    document.getElementById("total").innerHTML = coinFormat(0);
    cartProducts = [];
}


function sendOrder() {


    fetch('CreateOrderServlet', {
        method: 'POST',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-type': 'application/json'},
        body: {
            order: JSON.stringify(cartProducts),
            client: JSON.stringify(user)
        }
    })
            .then((res) => res.json())
            .then((data) => {
                console.log("I got: " + data);//for now, just printing the data
            })
            .catch((err) => console.error(err));
}



