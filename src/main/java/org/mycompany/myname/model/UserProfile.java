package org.mycompany.myname.model;

import java.io.File;

public class UserProfile {
    private final String login;
    private final String pass;
    private final File homeDir;

    public UserProfile(String login, String pass) {
        this.login = login;
        this.pass = pass;
        this.homeDir = new File("/Users/pavelzaborin/MWA/"+login);
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

}