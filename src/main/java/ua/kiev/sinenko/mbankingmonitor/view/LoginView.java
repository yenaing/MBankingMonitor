package ua.kiev.sinenko.mbankingmonitor.view;

import ua.kiev.sinenko.mbankingmonitor.controller.ApplicationUser;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by a.sinenko on 27.01.2016.
 */
@ManagedBean
@ViewScoped
public class LoginView implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ManagedProperty(value="#{applicationUser}")
    private ApplicationUser applicationUser;

    private String username;
    private String password;


    public ApplicationUser getApplicationUser() {return applicationUser; }
    public void setApplicationUser(ApplicationUser applicationUser) { this.applicationUser = applicationUser;  }


    public String getPassword() { return password;}
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String login() {
        if(applicationUser.login(username, password)){
            logger.info("LoginView.login(): user {} logged in", username);
            return "main?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));
            return "login";
        }
    }

    public void checkUser(){
        if(applicationUser.getPermissions()==null){
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();
            nav.performNavigation("login?faces-redirect=true");
        }
    }

}
