package Managers;

import Core.Users.User;
import Database.UserDatabase;

public class SessionHandler implements SignupManager,LoginManager{
    private static User currentUser;

    public static void initializeUser(){
        UserDatabase udb = UserDatabase.getInstance();
//        currentUser = udb.getLearner("1001");
        currentUser = udb.getCreator("1004");
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
