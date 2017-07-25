package ua.kiev.sinenko.mbankingmonitor.view;

/**
 * Created by a.sinenko on 04.02.2016.
 */
import org.slf4j.LoggerFactory;

import java.io.Serializable;
//import java.util.Locale;
import java.util.Locale;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LanguageSwitcher implements Serializable {

    private static final long serialVersionUID = 1L;

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    public String ukrainianAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.forLanguageTag("ua"));
        return null;
    }

    public String russianAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.forLanguageTag("ru"));
        return null;
    }

    public String englishAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.ENGLISH);
        return null;
    }

}
