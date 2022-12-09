package UI;
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
                    SessionHandler.getCurrentUser().openDashboard();
                    SessionHandler.logOutUser();
                }
            }
        }
    }
}


