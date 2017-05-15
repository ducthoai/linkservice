/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "request", catalog = "linkservice", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
    , @NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id")
    , @NamedQuery(name = "Request.findByIp", query = "SELECT r FROM Request r WHERE r.ip = :ip")
    , @NamedQuery(name = "Request.findByUserAgent", query = "SELECT r FROM Request r WHERE r.userAgent = :userAgent")
    , @NamedQuery(name = "Request.findByUrl", query = "SELECT r FROM Request r WHERE r.url = :url")
    , @NamedQuery(name = "Request.findByPassword", query = "SELECT r FROM Request r WHERE r.password = :password")
    , @NamedQuery(name = "Request.findByResult", query = "SELECT r FROM Request r WHERE r.result = :result")
    , @NamedQuery(name = "Request.findByStatus", query = "SELECT r FROM Request r WHERE r.status = :status")
    , @NamedQuery(name = "Request.findByRetry", query = "SELECT r FROM Request r WHERE r.retry = :retry")
    , @NamedQuery(name = "Request.findByIsProcessed", query = "SELECT r FROM Request r WHERE r.isProcessed = :isProcessed")
    , @NamedQuery(name = "Request.findByIsLeech", query = "SELECT r FROM Request r WHERE r.isLeech = :isLeech")
    , @NamedQuery(name = "Request.findByIsServe", query = "SELECT r FROM Request r WHERE r.isServe = :isServe")
    , @NamedQuery(name = "Request.findByReceive", query = "SELECT r FROM Request r WHERE r.receive = :receive")
    , @NamedQuery(name = "Request.findByServe", query = "SELECT r FROM Request r WHERE r.serve = :serve")
    , @NamedQuery(name = "Request.findByFinalResult", query = "SELECT r FROM Request r WHERE r.finalResult = :finalResult")})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ip", nullable = false, length = 255)
    private String ip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_agent", nullable = false, length = 255)
    private String userAgent;
    @Size(max = 255)
    @Column(name = "url", length = 255)
    private String url;
    @Size(max = 255)
    @Column(name = "password", length = 255)
    private String password;
    @Size(max = 255)
    @Column(name = "result", length = 255)
    private String result;
    @Column(name = "status")
    private Integer status;
    @Column(name = "retry")
    private Integer retry;
    @Column(name = "is_processed")
    private Boolean isProcessed;
    @Column(name = "is_leech")
    private Boolean isLeech;
    @Column(name = "is_serve")
    private Boolean isServe;
    @Column(name = "receive")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receive;
    @Column(name = "serve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serve;
    @Size(max = 255)
    @Column(name = "final_result", length = 255)
    private String finalResult;
    @JoinColumn(name = "server", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Server server;

    public Request() {
    }

    public Request(Integer id) {
        this.id = id;
    }

    public Request(Integer id, String ip, String userAgent) {
        this.id = id;
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Boolean getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public Boolean getIsLeech() {
        return isLeech;
    }

    public void setIsLeech(Boolean isLeech) {
        this.isLeech = isLeech;
    }

    public Boolean getIsServe() {
        return isServe;
    }

    public void setIsServe(Boolean isServe) {
        this.isServe = isServe;
    }

    public Date getReceive() {
        return receive;
    }

    public void setReceive(Date receive) {
        this.receive = receive;
    }

    public Date getServe() {
        return serve;
    }

    public void setServe(Date serve) {
        this.serve = serve;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.butchjgo.entity.Request[ id=" + id + " ]";
    }
    
}
