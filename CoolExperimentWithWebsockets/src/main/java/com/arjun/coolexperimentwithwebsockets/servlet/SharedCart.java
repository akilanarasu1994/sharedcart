/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arjun.coolexperimentwithwebsockets.servlet;

import com.arjun.coolexperimentwithwebsockets.store.Cart;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author akilan
 */
@ServerEndpoint(value = "/sharedcart", encoders = {CartEncoder.class}, decoders = {CartDecoder.class})
public class SharedCart {
    
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void broadcastFigure(Cart cart, Session session) throws IOException, EncodeException {
        System.out.println("broadcastCart: " + cart);
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(cart);
            }
        }
    }
    
    @OnOpen
    public void onOpen (Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
    
}
