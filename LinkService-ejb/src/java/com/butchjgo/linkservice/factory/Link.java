package com.butchjgo.linkservice.factory;

/**
 * Created by root on 5/14/2017.
 */
public class Link {

    private String link = "", password = "";

    public Link(String link, String password) {
        this.link = link;
        this.password = password;
    }

    public Link(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Link{"
                + "link='" + link + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
