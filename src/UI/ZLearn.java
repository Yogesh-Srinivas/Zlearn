package UI;
import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;
import Managers.SessionHandler;
import UI.Initializers.CourseInitializer;
import UI.Initializers.UserInitializer;

public class ZLearn {
    public static void main(String[] args) {
        UserInitializer.initiateUsers();
        CourseInitializer.initiateCourses();
        while(true) {
            System.out.println("****Welcome to Zlearn****");
            AuthStatus sessionStatus = SessionHandler.authenticate();

            if (sessionStatus.equals(AuthStatus.LOGIN_SUCCESS)) {
                if (SessionHandler.getCurrentUser() != null) {
                    if (SessionHandler.getCurrentUser() instanceof Learner) {
                        new LearnerOperations((Learner) SessionHandler.getCurrentUser()).learnerDashBoard();
                    }
                    if (SessionHandler.getCurrentUser() instanceof Creator) {
                        new CreatorOperations((Creator) SessionHandler.getCurrentUser()).creatorDashBoard();
                    }
                    if (SessionHandler.getCurrentUser() instanceof Admin) {
                        new AdminOperations((Admin) SessionHandler.getCurrentUser()).adminDashBoard();
                    }
                    SessionHandler.logOutUser();
                }
            }
        }
    }
}

