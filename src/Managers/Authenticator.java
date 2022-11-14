package Managers;

import Core.Users.Admin;
import Core.Users.User;
import Database.UserDatabase;
import UI.AuthStatus;

public class Authenticator {
    private static final UserDatabase userdb = UserDatabase.getInstance();
    public static AuthStatus learnerAuthentication(String userName, String password){
        return userdb.learnerAuthentication(userName,password);
    }

    public static AuthStatus creatorAuthentication(String userName, String password){
        return userdb.creatorAuthentication(userName,password);
    }

    public static AuthStatus adminAuthentication(String adminId, String adminPassword){
        return userdb.adminAuthentication(adminId,adminPassword);
    }

    public static User getLearner(String userName) {
        return userdb.getLearner(userName);
    }

    public static User getCreator(String userName) {
        return userdb.getCreator(userName);
    }

    public static Admin getAdmin(String adminId) {
        return userdb.getAdmin(adminId);
    }
}
