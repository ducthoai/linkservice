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

    private final JSONObject invalidCaptchaJson = new JSONObject();
    private final JSONObject invalidURLJson = new JSONObject();
    private final JSONObject successJson = new JSONObject();

    @PostActivate
    private void prepareJsonMSG() {
        invalidCaptchaJson.put("msg", "invalid captcha");
        invalidCaptchaJson.put("isSuccess", false);

        invalidURLJson.put("msg", "This url may not currently support, please try again");
        invalidURLJson.put("isSuccess", false);

        successJson.put("msg", "Request successfully");
        successJson.put("isSuccess", true);
    }

    @Override
    public JSONObject getInvalidGcaptchaJSON() {
        return this.invalidCaptchaJson;
    }

    @Override
    public JSONObject getInvalidURLJSON() {
        return this.invalidURLJson;
    }

    @Override
    public JSONObject getSuccessJSON() {
        return this.successJson;
    }
}
