package Managers;

import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;
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

    public static boolean isLearnerUserNameAvailable(String userName) {
        return !userdb.isLearnerExist(userName);
    }

    public static boolean isCreatorUserNameAvailable(String userName) {
        return !userdb.isCreatorExist(userName);
    }

    public static void addLearner(String userName, String password, String firstName) {
        String userId = IdGenerator.getNewLearnerId();
        userdb.addLearner(new Learner(userId,userName,password,firstName));
    }

    public static void addCreator(String userName, String password, String firstName) {
        String userId = IdGenerator.getNewCreatorId();
        userdb.addCreator(new Creator(userId,userName,password,firstName));
    }
}
