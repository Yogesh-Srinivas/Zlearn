package UI;
import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;
import Core.Users.ROLE;
import Managers.SessionHandler;
import UI.Initializers.CourseInitializer;
import UI.Initializers.UserInitializer;

public class ZLearn {
    public static void main(String[] args) {
        UserInitializer.initiateUsers();
        CourseInitializer.initiateCourses();
        SessionHandler.initializeUser();
        if(SessionHandler.getCurrentUser() != null) {
            if (SessionHandler.getCurrentUser().getRole().equals(ROLE.LEARNER)) {
                new LearnerOperations((Learner) SessionHandler.getCurrentUser()).learnerDashBoard();
            } else if (SessionHandler.getCurrentUser().getRole().equals(ROLE.CREATOR)) {
                new CreatorOperations((Creator) SessionHandler.getCurrentUser()).creatorDashBoard();
            }
        }
        if(SessionHandler.getCurrentAdmin() != null){
            new AdminOperations((Admin) SessionHandler.getCurrentAdmin()).adminDashBoard();
        }
    }
}

