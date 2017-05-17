/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.servlet;

import com.butchjgo.linkservice.sessionbean.ProcessRequestBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author root
 */
@WebServlet(name = "Download", urlPatterns = {"/Download"})
public class Download extends HttpServlet {

    ProcessRequestBeanLocal processRequestBean = lookupProcessRequestBeanLocal();

    JSONObject result;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try (PrintWriter out = response.getWriter()) {
            result = processRequestBean.processGetRequest(request);
            out.write(result.toJSONString());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try (PrintWriter out = response.getWriter()) {
            result = processRequestBean.processPostRequest(request);
            out.write(result.toJSONString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private ProcessRequestBeanLocal lookupProcessRequestBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ProcessRequestBeanLocal) c.lookup("java:global/LinkService/LinkService-ejb/ProcessRequestBean!com.butchjgo.linkservice.sessionbean.ProcessRequestBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
