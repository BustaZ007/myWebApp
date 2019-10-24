package org.mycompany.myname.model;

import java.io.File;

public class UserProfile {
    private final String login;
    private final String pass;
    private final File homeDir;
    private final String email;

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.homeDir = new File("/Users/pavelzaborin/MWA/"+login);
        this.email = email;

        homeDir.mkdirs();
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public File getHomeDir() {
        return homeDir;
    }

    public String getEmail(){ return email; }

}