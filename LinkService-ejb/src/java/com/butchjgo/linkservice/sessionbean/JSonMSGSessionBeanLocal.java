/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.sessionbean;

import javax.ejb.Local;
import org.json.simple.JSONObject;

/**
 *
 * @author root
 */
@Local
public interface JSonMSGSessionBeanLocal {

    JSONObject getInvalidGcaptchaJSON();

    JSONObject getInvalidURLJSON();
    
}
