package ui;
import ui.initializers.ZLearnInitializer;

final class ZLearn {
    public static void main(String[] args) {
        ZLearnInitializer.initiate();

        while(true) {
            System.out.println("****Welcome to Zlearn****");
            AuthStatus sessionStatus = SessionHandler.authenticate();

            if (sessionStatus.equals(AuthStatus.LOGIN_SUCCESS)) {
                if (SessionHandler.getCurrentUser() != null) {
                    SessionHandler.getCurrentUser().openDashboard();
                }
            }
        }
    }
}


