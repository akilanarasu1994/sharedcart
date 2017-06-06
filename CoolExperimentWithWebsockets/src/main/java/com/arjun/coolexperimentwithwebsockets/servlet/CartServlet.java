/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arjun.coolexperimentwithwebsockets.servlet;
import com.arjun.coolexperimentwithwebsockets.store.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.Enumeration;
import org.json.JSONObject;


/**
 *
 * @author Arjun
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {
    
    
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {

    Enumeration headers = req.getHeaderNames();
    while (headers.hasMoreElements()) {
      String header  =(String) headers.nextElement();
      System.out.println(header+": "+req.getHeader(header));
    }

    Cart cart = getCartFromSession(req);

    String action = req.getParameter("action");
    String item = req.getParameter("item");
    
    if ((action != null)&&(item != null)) {

      if ("add".equals(action)) {
        cart.addItem(item);

      } else if ("remove".equals(action)) {
        cart.removeItems(item);
      }
    }

    JSONObject cartJson = cart.toJSONObject();
   // 
    res.setContentType("application/json");
    String dummy = cartJson.toString();
    res.getWriter().write(cartJson.toString());
    
  }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
       
        doPost(req,res);
    }
    
    private Cart getCartFromSession(HttpServletRequest req) {

    HttpSession session = req.getSession(true);
    Cart cart = (Cart)session.getAttribute("cart");
   
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }

    return cart;
  }
   
}