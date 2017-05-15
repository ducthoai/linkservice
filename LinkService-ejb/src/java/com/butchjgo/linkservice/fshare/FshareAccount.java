package com.butchjgo.linkservice.fshare;

import com.butchjgo.linkservice.factory.Account;
import org.json.simple.JSONObject;

/**
 * Created by root on 5/14/2017.
 */
public class FshareAccount extends Account {
    public FshareAccount(String email, String password) {
        super(email, password);
    }

    public JSONObject getLoginData() {
        JSONObject object = new JSONObject();
        object.put("app_key", FshareConstant.APP_KEY);
        object.put("user_email", super.email);
        object.put("password", super.password);
        return object;
    }
}
