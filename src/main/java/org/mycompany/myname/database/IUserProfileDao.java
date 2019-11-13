package org.mycompany.myname.database;

import org.mycompany.myname.model.UserProfile;

public interface IUserProfileDao {
    void addUser(String login, String email, String pass);
    UserProfile findUserByLoginOrEmail(String login);
}
