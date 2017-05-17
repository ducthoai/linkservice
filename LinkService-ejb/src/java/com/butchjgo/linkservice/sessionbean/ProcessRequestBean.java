/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.sessionbean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author root
 */
@Stateful
public class ProcessRequestBean implements ProcessRequestBeanLocal {

    @EJB
    private VerifyURLSessionBeanLocal verifyURLSessionBean;

    @EJB
    private JSonMSGSessionBeanLocal jSonMSGSessionBean;

    @EJB
    private RecaptchaSessionBeanLocal recaptchaSessionBean;

    @EJB
    private LinkServiceSessionBeanLocal linkServiceSessionBean;

    @PersistenceContext(unitName = "LinkService-ejbPU")
    private EntityManager em;

    private HashMap<String, String> data = new HashMap<String, String>();

    @Override
    public JSONObject add(String url, String password) {
        //return null;
        JSONObject jsono = new JSONObject();
        if (data.containsKey(url)) {
            jsono.put("error", "you have already request this link");
            jsono.put("isSuccess", false);
            return jsono;
        }
        data.put(url, password);
        jsono.put("msg", "added successfully");
        jsono.put("isSuccess", true);
        return jsono;
    }

    @Override
    public String getHistory() {
        return data.toString();
    }

    @Override
    public JSONObject processPostRequest(HttpServletRequest request)
            throws ServletException, IOException {
        JSONObject an = new JSONObject();
        String capcha = request.getParameter("g-recaptcha-response");
        if (capcha == null || capcha.isEmpty() || !recaptchaSessionBean.isValid(capcha)) {
            return jSonMSGSessionBean.getInvalidGcaptchaJSON();
        }

        String url = request.getParameter("link");
        if (url == null || url.isEmpty() || !verifyURLSessionBean.isValidURL(url)) {
            return jSonMSGSessionBean.getInvalidURLJSON();
        }

        String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
            password = "";
        }
        linkServiceSessionBean.invokeService();
        return jSonMSGSessionBean.getSuccessJSON();
    }

    @Override
    public JSONObject processGetRequest(HttpServletRequest request)
            throws ServletException, IOException {
        return null;
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
