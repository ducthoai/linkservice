package com.butchjgo.linkservice.factory;

import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by root on 5/14/2017.
 */
public interface LinkService {

    public JSONObject doLogin();

    public JSONObject doGetLink(Link link);

    //public List<URLData> getRequestLink();
}
