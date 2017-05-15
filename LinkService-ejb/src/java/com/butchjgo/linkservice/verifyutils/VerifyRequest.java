/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.verifyutils;

import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;

/**
 *
 * @author root
 */
public class VerifyRequest {

    public static JSONObject accept(HttpServletRequest request) {
        JSONObject jsono = new JSONObject();
        String capcha = request.getParameter("g-recaptcha-response");
        if (capcha == null || capcha.isEmpty() || !ReCaptcha.isValid(capcha)) {
            jsono.put("error", "Invalid captcha");
            jsono.put("isValid", false);
            return jsono;
        }
        String link = request.getParameter("link");
        if (link == null || link.isEmpty()) {
            jsono.put("error", "Invalid link");
            jsono.put("msg", "This link may not currently support");
            jsono.put("isValid", false);
            return jsono;
        }
        jsono.put("isValid", true);
        return jsono;
    }
}
