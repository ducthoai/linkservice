/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.sessionbean;

import java.io.IOException;
import javax.ejb.Local;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author root
 */
@Local
public interface ProcessRequestBeanLocal {

    JSONObject add(String url, String password);

    String getHistory();

    void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
}
