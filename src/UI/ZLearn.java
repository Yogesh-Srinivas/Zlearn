package UI;
import Core.Users.Creator;
import Core.Users.Learner;
import Core.Users.ROLE;
import Managers.SessionHandler;
import UI.Initializers.CourseInitializer;
import UI.Initializers.UserInitializer;

public class ZLearn {
    public static void main(String[] args) {
        UserInitializer.initiateUsers();
//        CourseInitializer.initiateCourses();
        while(true) {
            System.out.println("****Welcome to Zlearn****");
            AuthStatus sessionStatus = SessionHandler.authenticate();
            if (sessionStatus.equals(AuthStatus.LOGIN_SUCCESS)) {
                if (SessionHandler.getCurrentUser() != null) {
                    if (SessionHandler.getCurrentUser().getRole().equals(ROLE.LEARNER)) {
                        new LearnerOperations((Learner) SessionHandler.getCurrentUser()).learnerDashBoard();
                    } else if (SessionHandler.getCurrentUser().getRole().equals(ROLE.CREATOR)) {
                        new CreatorOperations((Creator) SessionHandler.getCurrentUser()).creatorDashBoard();
                    }
                    SessionHandler.logOutUser();
                }
                if (SessionHandler.getCurrentAdmin() != null) {
                    new AdminOperations(SessionHandler.getCurrentAdmin()).adminDashBoard();
                    SessionHandler.logOutAdmin();
                }
            }
        }
    }
}

