/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.sessionbean;

import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import org.json.simple.JSONObject;

/**
 *
 * @author root
 */
@Stateless
public class JSonMSGSessionBean implements JSonMSGSessionBeanLocal {

    JSONObject invalidCaptchaJson = new JSONObject();
    JSONObject invalidURLJson = new JSONObject();

    @PostActivate
    private void prepareJsonMSG() {
        invalidCaptchaJson.put("msg", "invalid captcha");
        invalidCaptchaJson.put("error", "Please try again");
        
        invalidURLJson.put("error", "Invalid url");
        invalidURLJson.put("msg", "This url may not currently support, please try again");
    }

    @Override
    public JSONObject getInvalidGcaptchaJSON() {
        return this.invalidCaptchaJson;
    }

    @Override
    public JSONObject getInvalidURLJSON() {
        return this.invalidURLJson;
    }
    
}
