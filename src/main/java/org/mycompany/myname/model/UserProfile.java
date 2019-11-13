package org.mycompany.myname.model;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "users")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String login;
    @Column
    private String pass;
    @Column
    private String email;

    public UserProfile() {
    }

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        File homeDir = new File("/Users/pavelzaborin/MWA/" + login);
        this.email = email;

        homeDir.mkdirs();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

}