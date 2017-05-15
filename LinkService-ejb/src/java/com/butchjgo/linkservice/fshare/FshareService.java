package com.butchjgo.linkservice.fshare;

import com.butchjgo.linkservice.factory.Link;
import com.butchjgo.linkservice.factory.LinkService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by root on 5/14/2017.
 */
public class FshareService implements Runnable, LinkService {

    private FshareAccount account;
    private JSONObject loginResult = null;
    private List<Link> requestLinks = null;


    public FshareService(FshareAccount account) {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        this.account = account;
    }

    public static void main(String[] args) {
        FshareService fservice = new FshareService(new FshareAccount("fshare3@gramy.vn", "lamlaitudau"));
        Thread t = new Thread(fservice);
        t.start();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter to exit");
        sc.nextLine();
    }

    @Override
    public JSONObject doLogin() {
        URL url;
        JSONObject object = null;
        String result;
        HttpURLConnection hurlc;
        OutputStream os;
        BufferedReader br;
        String tmp = "";
        StringBuilder stringBuilder = new StringBuilder();
        JSONParser parser = new JSONParser();
        try {
            url = new URL(FshareConstant.URL_API_LOGIN);
            hurlc = (HttpURLConnection) url.openConnection();

            hurlc.setRequestMethod("POST");
            hurlc.setRequestProperty("User-Agent", FshareConstant.USER_AGENT);
            hurlc.setRequestProperty("Content-Type", "application/json");
            hurlc.setDoOutput(true);

            hurlc.connect();

            os = hurlc.getOutputStream();

            os.write(account.getLoginData().toJSONString().getBytes());
            os.flush();
            os.close();
            br = new BufferedReader(new InputStreamReader(hurlc.getInputStream()));
            while ((tmp = br.readLine()) != null) {
                stringBuilder.append(tmp);
            }
            result = stringBuilder.toString();
            object = (JSONObject) parser.parse(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            this.loginResult = object;
            return object;
        }
    }

    @Override
    public JSONObject doGetLink(Link link) {

        JSONObject retObject = null;
        if (loginResult == null) {
            return retObject;
        }
        String token = String.valueOf(loginResult.get("token"));
        if (token == null || token.isEmpty()) {
            return retObject;
        }
        JSONObject postData = new JSONObject();
        postData.put("url", link.getLink());
        postData.put("password", link.getPassword());
        postData.put("token", token);
        ////
        URL url;
        HttpURLConnection hurlc;
        OutputStream os;
        BufferedReader br;
        String tmp = "";
        StringBuilder stringBuilder = new StringBuilder();
        JSONParser parser = new JSONParser();
        ////
        try {
            url = new URL(FshareConstant.URL_API_DOWNLOAD);
            hurlc = (HttpURLConnection) url.openConnection();

            hurlc.setRequestMethod("POST");
            hurlc.setRequestProperty("User-Agent", FshareConstant.USER_AGENT);
            hurlc.setRequestProperty("Content-Type", "application/json");
            hurlc.setDoOutput(true);

            hurlc.connect();

            os = hurlc.getOutputStream();

            os.write(postData.toJSONString().getBytes());
            os.flush();
            os.close();
            br = new BufferedReader(new InputStreamReader(hurlc.getInputStream()));
            while ((tmp = br.readLine()) != null) {
                stringBuilder.append(tmp);
            }
            retObject = (JSONObject) parser.parse(stringBuilder.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return retObject;
        }
    }

//    @Override
//    public List<URLData> getRequestLink() {
//        List<URLData> links = new LinkedList<>();
//        return links;
//    }

//    public void run() {
//        while (true) {
//            loginResult = null;
//            loginResult = doLogin();
//            if (loginResult == null) {
//                continue;
//            }
//            List<URLData> requestLinks = getRequestLink();
//            if (requestLinks == null || requestLinks.size() == 0) {
//                try {
//                    System.out.println("Relax, no more job available");
//                    Thread.sleep(60 * 1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    continue;
//                }
//            }
//
//            for (URLData link : requestLinks) {
//                
//                JSONObject retLink = doGetLink(new Link(link.getOriginRequestURL(), link.getPassword()));
//                
//                if (retLink != null) {
//                    
//                    String resultURL = String.valueOf(retLink.get("location"));
//                    System.out.println(resultURL);
//                    if (resultURL != null) {
//                        link.setOriginResultURL(resultURL);
//                    }
//                }
//            }
//        }
//    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
