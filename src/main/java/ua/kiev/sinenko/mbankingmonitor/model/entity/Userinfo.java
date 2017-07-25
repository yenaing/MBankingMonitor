package ua.kiev.sinenko.mbankingmonitor.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name="TUSERINFO")
@NamedQueries(value = {@NamedQuery(name="Userinfo.refreshUser",query = "SELECT t FROM Userinfo t where t.id = :id"),
                        @NamedQuery(name="Userinfo.findUserByName", query="select s from Userinfo s where s.name = :name"),
                        @NamedQuery(name = "Userinfo.identifyUser", query = "SELECT t FROM Userinfo t where t.name = :name and t.pass=:pass"),
                        @NamedQuery(name="Userinfo.findAll", query="SELECT s FROM Userinfo s")})
public class Userinfo implements Serializable {

    public static final String findUserByName = "Userinfo.findUserByName";
    public static final String refreshUser = "Userinfo.refreshUser";
    public static final String identifyUser = "Userinfo.identifyUser";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigDecimal id;
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Column(name="NAME")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="PASS")
    private String pass;
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Column(name="PERMISSIONS")
    private String permissions;    public String getPermissions() {
        return permissions;
    }
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Column(name="TYPE")
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name="UNAME")
    private String uname;
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Userinfo{name:"+name+";password:"+pass+"permissions:"+permissions+"}"; //To change body of generated methods, choose Tools | Templates.
    }

    
}
