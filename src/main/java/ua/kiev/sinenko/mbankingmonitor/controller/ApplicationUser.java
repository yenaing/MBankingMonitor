package ua.kiev.sinenko.mbankingmonitor.controller;

import ua.kiev.sinenko.mbankingmonitor.model.entity.Userinfo;
import ua.kiev.sinenko.mbankingmonitor.model.service.UserinfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
/**
 * Created by a.sinenko on 10.02.2016.
 */

@ManagedBean
@SessionScoped
public class ApplicationUser implements Serializable{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EJB
    private UserinfoServiceImpl userDAO;
    private Userinfo userInfo;

    private static final long serialVersionUID = 334L;
    private int type=0;
    private String login = "guest";
    private String permissions=null;

    public ApplicationUser() {
        logger.info("ApplicationUser create");
    }

    public int getType() {return type;}

    public void setType(int type) {
        this.type = type;
    }
    
    public String getFormatedName(){
        if(userInfo!=null && userInfo.getUname()!=null && userInfo.getUname().length()>0){
            return userInfo.getUname();
        }else{
            return login;
        }
    }

    public Userinfo getUserInfo() {
        return userInfo;
    }

    private void setUserInfo(Userinfo userInfo) {
        this.userInfo = userInfo;
        this.login=userInfo.getName();
        this.permissions=userInfo.getPermissions();
        this.type=Integer.parseInt(userInfo.getType());
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public boolean login(String login,String password){
        this.userInfo = userDAO.login(login, password);
        if(this.userInfo==null) {
            this.setLogin("guest");
            this.setPermissions(null);
            return false;
        }else{
            this.setUserInfo(userInfo);
            return true;
        }
    }

    @Override
    public String toString() {
        return "ApplicationUser{login:"+login+";permissions:"+permissions+"}";
    }

}
