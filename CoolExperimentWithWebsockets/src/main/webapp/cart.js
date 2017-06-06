/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Timestamp of cart that page was last updated with
var lastCartUpdate = 0;

/*
 * Adds the specified item to the shopping cart, via Ajax call
 * itemCode - product code of the item to add
 */
function addToCart(itemCode) {

 var req = newXMLHttpRequest();

 req.onreadystatechange = getReadyStateHandler(req, updateCart);
 
 req.open("POST", "cart.do", true);
 req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 req.send("action=add&item="+itemCode);
 
 
}

// This will broadcast individual items and actions
//function sendAdd(itemCode) {
//    sendText("{\"action\":\"add\"," + "\"item\":\"" + itemCode + "\"}");
//}

// This will broadcast individual items and actions
//function sendRemove(itemCode) {
//    sendText("{\"action\":\"remove\"," + "\"item\":\"" + itemCode + "\"}");
//}

function removeFromCart(itemCode) {

 var req = newXMLHttpRequest();

 req.onreadystatechange = getReadyStateHandler(req, updateCart);
 
 req.open("POST", "cart.do", true);
 req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 req.send("action=remove&item="+itemCode);

}

/*
 * Update shopping-cart area of page to reflect contents of cart
 * described in XML document.
 */
//function updateCart(cartXML) {
// var cart = cartXML.getElementsByTagName("cart")[0];
// var generated = cart.getAttribute("generated");
// if (generated > lastCartUpdate) {
//   lastCartUpdate = generated;
//   var contents = document.getElementById("contents");
//   contents.innerHTML = "";
//
//   var items = cart.getElementsByTagName("item");
//   for (var I = 0 ; I < items.length ; I++) {
//
//     var item = items[I];
//     var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
//     var quantity = item.getElementsByTagName("quantity")[0].firstChild.nodeValue;
//
//     var listItem = document.createElement("li");
//     listItem.appendChild(document.createTextNode(name+" x "+quantity));
//     contents.appendChild(listItem);
//   }
//
//// }
//
// document.getElementById("total").innerHTML = cart.getAttribute("total");
//}
function updateCart(cartJson){
    
    // UNCOMMENT this line if you don't like the way this works. 
    // This will broadcast the entire cart.
     sendText(cartJson);
    
    var cart = JSON.parse(cartJson);
    var generated = cart.cartGenerated;
    if (generated > lastCartUpdate) {
        
        lastCartUpdate = generated;
        var contents = document.getElementById("contents");
        contents.innerHTML= "";
        var items = cart.Items;
        for (var I = 0; I< items.length; I++){
            
            var item = items[I];
            var name = item.ItemName;
            var quantity = item.ItemQuantity;
            
            var listItem = document.createElement("li");
            listItem.appendChild(document.createTextNode(name+" x "+quantity));
            contents.appendChild(listItem);
            
        }
        
        
    }
    document.getElementById("total").innerHTML = cart.total;
}
