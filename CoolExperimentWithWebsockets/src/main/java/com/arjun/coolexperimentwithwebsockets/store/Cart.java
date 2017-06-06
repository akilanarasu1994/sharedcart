/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arjun.coolexperimentwithwebsockets.store;

/**
 *
 * @author Arjun
 */
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.*;
import javax.json.Json;
import javax.json.JsonObject;
import org.json.JSONObject;
import org.json.JSONArray;

public class Cart {

    private HashMap<Item, Integer> contents;
    private JsonObject json;

    /**
     * Creates a new Cart instance
     */
    public Cart() {
        contents = new HashMap<Item, Integer>();
    }
    
    public Cart(JsonObject json) {
        this.json = json;
    }

    /**
     * Adds a named item to the cart
     *
     * @param itemName The name of the item to add to the cart
     */
    public void addItem(String itemCode) {

        Catalog catalog = new Catalog();

        if (catalog.containsItem(itemCode)) {
            Item item = catalog.getItem(itemCode);

            int newQuantity = 1;
            if (contents.containsKey(item)) {
                Integer currentQuantity = contents.get(item);
                newQuantity += currentQuantity.intValue();
            }

            contents.put(item, new Integer(newQuantity));
        }
    }

    /**
     * Removes the named item from the cart
     *
     * @param itemName Name of item to remove
     */
    public void removeItems(String itemCode) {

        Catalog catalog = new Catalog();
        if (catalog.containsItem(itemCode)) {
            Item item = catalog.getItem(itemCode);

            int newQuantity = -1;
            if (contents.containsKey(item)) {
                Integer currentQuantity = contents.get(item);
                newQuantity += currentQuantity.intValue();
                if (newQuantity > 0) {
                    contents.put(item, new Integer(newQuantity));
                } else {
                    contents.remove(new Catalog().getItem(itemCode));
                }
            }
        }
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        JSONArray jsonItems = new JSONArray();
        json.put("cartGenerated", System.currentTimeMillis());
        json.put("total", getCartTotal());
        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            JSONObject itemobj = new JSONObject();
            itemobj.put("ItemName", item.getName());
            itemobj.put("ItemQuantity", contents.get(item).intValue());
            jsonItems.put(itemobj);
        }

        json.put("Items", jsonItems);
        return json;

    }

    private String getCartTotal() {
        int total = 0;

        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            int itemQuantity = contents.get(item).intValue();

            total += (item.getPrice() * itemQuantity);
        }

        return "$" + new BigDecimal(total).movePointLeft(2);
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }
    
    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        Json.createWriter(writer).write(json);
        return writer.toString();
    }

}
