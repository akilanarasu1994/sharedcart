/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "sharedcart";
var websocket = new WebSocket(wsUri);

websocket.onerror = function (evt) {
    onError(evt)
};

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

websocket.onmessage = function processMessage(message) {
    
    // COMMENT this code if you want to broadcast only individual item changes.
//    console.log("received updated cart: " + message.data);
//    var jsonData = JSON.parse(message.data);
//    if (jsonData != null) {
//        var cart = jsonData;
//        var generated = cart.cartGenerated;
//        if (generated > lastCartUpdate) {
//
//            lastCartUpdate = generated;
//            var contents = document.getElementById("contents");
//            contents.innerHTML = "";
//            var items = cart.Items;
//            for (var I = 0; I < items.length; I++) {
//
//                var item = items[I];
//                var name = item.ItemName;
//                var quantity = item.ItemQuantity;
//
//                var listItem = document.createElement("li");
//                listItem.appendChild(document.createTextNode(name + " x " + quantity));
//                contents.appendChild(listItem);
//
//            }
//
//
//        }
//        document.getElementById("total").innerHTML = cart.total;
//    }


    // COMMENT this code if you want to broadcast the entire cart.
    console.log("received actionitem: " + message.data);
    var receivedObject = JSON.parse(message.data);
    if (receivedObject != null) {
//        var item = receivedObject.item;
//        var action = receivedObject.action;
//        if (action === "add") {
//            addToCart(item);
//        } else if (action === "remove") {
//            removeFromCart(item);
//        }
        refreshCart();
    }
}

function sendText(json) {
    console.log("sending updated cart: " + json);
    websocket.send(json);
}

// For testing purposes
var output = document.getElementById("output");
websocket.onopen = function (evt) {
    onOpen(evt)
};

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}
// End test functions
