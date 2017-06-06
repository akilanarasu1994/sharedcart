/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arjun.coolexperimentwithwebsockets.servlet;

import com.arjun.coolexperimentwithwebsockets.store.Cart;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author akilan
 */
public class CartDecoder implements Decoder.Text<Cart> {

    @Override
    public Cart decode(String string) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(string)).readObject();
        return  new Cart(jsonObject);
    }

    @Override
    public boolean willDecode(String string) {
        try {
            Json.createReader(new StringReader(string)).readObject();
            return true;
        } catch (JsonException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    
}
