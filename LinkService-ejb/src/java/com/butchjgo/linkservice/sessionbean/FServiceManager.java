/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.butchjgo.linkservice.sessionbean;

import com.butchjgo.linkservice.factory.LinkService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Singleton
public class FServiceManager implements FServiceManagerLocal {

    @PersistenceContext(unitName = "LinkService-ejbPU")
    private EntityManager em;
    private LinkService service;

    @PostConstruct
    private void prepareService() {
        //service = new FshareService(new FshareAccount(email, password))

    }
    
//    private List<Account> getAccountList() {
//        List<Account> list = null;
//        Server server = (Server) em.createNamedQuery("Server.findByAddress")
//                .setParameter("address", "https://www.fshare.vn")
//                .getSingleResult();
//        list = em.createNamedQuery("Account.findAll").getResultList();
//        int serverId = server.getId();
//        return list.stream().filter(a -> a.getServer().getId() == serverId)
//                .collect(Collectors.toList());
//    }
    public void persist(Object object) {
        em.persist(object);
    }
}
