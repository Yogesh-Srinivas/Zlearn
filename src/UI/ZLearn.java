package UI;
import Managers.SessionHandler;
import UI.Initializers.ZLearnInitializer;

public class ZLearn {
    public static void main(String[] args) {
        ZLearnInitializer.initiate();

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


