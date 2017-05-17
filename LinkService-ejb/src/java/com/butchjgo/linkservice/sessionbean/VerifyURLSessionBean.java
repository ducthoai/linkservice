/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.sessionbean;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author root
 */
@Stateless
public class VerifyURLSessionBean implements VerifyURLSessionBeanLocal {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36";
    private static final String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    private static final String FSHARE_FILE_NOT_FOUND = "Tập tin quý khách yêu cầu không tồn tại.";
    List<String> list = new LinkedList<>();

    @PostConstruct
    private void prepareServerInfo() {
        //have to reimplement again do not hard code
        this.list.add("https://www.fshare.vn/");
    }

    @Override
    public Boolean isValidURL(String url) {
        boolean isValid = getSupportServers().stream().anyMatch(server -> url.contains(server));
        if (!isValid || is404(url)) {
            return false;
        }
        return true;
    }

    private List<String> getSupportServers() {
        return this.list;
    }

    private boolean is404(String url) {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        HttpURLConnection hurlc = null;
        int res = 404;
        try {
            hurlc = (HttpURLConnection) new URL(url).openConnection();
            hurlc.setRequestMethod("HEAD");
            hurlc.setRequestProperty("User-Agent", USER_AGENT);
            hurlc.setRequestProperty("Accept", ACCEPT);
            hurlc.connect();
            res = hurlc.getResponseCode();
        } finally {
            return res == 404;
        }
    }
}
