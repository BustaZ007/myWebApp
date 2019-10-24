package org.mycompany.myname.service;

import org.mycompany.myname.model.UserProfile;

import java.util.HashMap;
import java.util.Map;


public class AccountService {
    private static final Map<String, UserProfile> loginToProfile = new HashMap<>();;
    private static final Map<String, UserProfile> mailIdToProfile = new HashMap<>();;

    public AccountService() {
    }

    public static void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        mailIdToProfile.put(userProfile.getEmail(), userProfile);
    }

    public static UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public static UserProfile getUserByMail(String mail) {
        return mailIdToProfile.get(mail);
    }
}