package ua.kiev.sinenko.mbankingmonitor.model.service;

import ua.kiev.sinenko.mbankingmonitor.model.entity.Userinfo;

import java.util.List;

/**
 * Created by a.sinenko on 12.04.2016.
 */
public interface UserinfoService {
    Userinfo findUserByName(String Username);
    Userinfo login(String name, String pass);
    void saveNewUser(String username, String password);
    List<Userinfo> findUsers();
    Userinfo refreshUser(Userinfo user);
}
