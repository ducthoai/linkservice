/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.sessionbean;

import com.butchjgo.linkservice.entity.Request;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateful;
import org.json.simple.JSONObject;

/**
 *
 * @author root
 */
@Stateful
public class ProcessRequestBean implements ProcessRequestBeanLocal {
    private HashMap<String, String> data = new HashMap<String, String>();
    @Override
    public JSONObject add(String url, String password) {
        //return null;
        JSONObject jsono = new JSONObject();
        if(data.containsKey(url)){
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
    
}
