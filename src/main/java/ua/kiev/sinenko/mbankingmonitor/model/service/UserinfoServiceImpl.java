package ua.kiev.sinenko.mbankingmonitor.model.service;

/**
 * Created by a.sinenko on 29.01.2016.
 */

import ua.kiev.sinenko.mbankingmonitor.model.entity.Userinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class UserinfoServiceImpl implements UserinfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext(unitName = "infra")
    private EntityManager entityManager;

    public Userinfo findUserByName(String Username) throws PersistenceException {
        List<Userinfo> userinfo = (entityManager.createNamedQuery(Userinfo.findUserByName, Userinfo.class).setParameter("name", Username)).getResultList();
        if (userinfo.size() > 0) {
            return userinfo.get(0);
        }
        throw new PersistenceException("User not found.");
    }

    public Userinfo login(String name, String pass) {
        pass = getHash(pass);
        List<Userinfo> users = entityManager.createNamedQuery("Userinfo.identifyUser", Userinfo.class).setParameter("name", name).setParameter("pass", pass).getResultList();
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public void saveNewUser(String username, String password) {
        password = getHash(password);
        if (entityManager.createNamedQuery("Userinfo.identifyUser", Userinfo.class).setParameter("name", username).setParameter("pass", password).getResultList().isEmpty()) {
            Userinfo userinfo = new Userinfo();
            userinfo.setName(username);
            userinfo.setPass(password);
            entityManager.persist(username);
        }
    }

    public List<Userinfo> findUsers() {
        return entityManager.createNamedQuery("Userinfo.findAll", Userinfo.class).getResultList();
    }

    private String getHash(String pass) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(pass);
        //return org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass);
    }

    public Userinfo refreshUser(Userinfo user) {
        List<Userinfo> users = entityManager.createNamedQuery("Userinfo.refreshUser", Userinfo.class).setParameter("id", user.getId()).getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

}
