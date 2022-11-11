package Managers;

import Core.Users.Admin;
import Core.Users.User;
import Database.UserDatabase;

public class SessionHandler implements SignupManager,LoginManager{
    private static User currentUser = null;
    private static Admin currentAdmin =null;

    public static void initializeUser(){
        UserDatabase udb = UserDatabase.getInstance();
//        currentUser = udb.getLearner("1001");
//        currentUser = udb.getCreator("1004");
        currentAdmin = udb.getAdmin("ad123");
    }

    public static User getCurrentUser() {
        return currentUser;
    }
    public static Admin getCurrentAdmin(){ return currentAdmin;}
}
