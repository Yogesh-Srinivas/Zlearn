package Managers;

import Core.Users.Admin;
import Core.Users.User;
import UI.AuthStatus;
import UI.Utilities.CustomScanner;

import java.util.Scanner;

public class SessionHandler implements SignupManager,LoginManager{
    private static User currentUser = null;
    private static Admin currentAdmin =null;
    public static User getCurrentUser() {
        return currentUser;
    }
    public static Admin getCurrentAdmin(){ return currentAdmin;}

    public static AuthStatus authenticate() {
        AuthStatus authStatus = null;
        System.out.println("Do you want to");
        System.out.println("[L]ogin  or   [S]ignup");
        String loginOption = CustomScanner.getOptions("lLsS");
        if(loginOption.equals("l") || loginOption.equals("L")){
            authStatus = login();
        }
        if(loginOption.equals("s") || loginOption.equals("S")){
            authStatus = signUp();
        }
        return authStatus;
    }

    private static AuthStatus login() {
        AuthStatus authStatus = null;
        Scanner sc =new Scanner(System.in);
        System.out.println("[L]earner login\n[C]reator Login\n[A]dmin Login");
        String loginOption = CustomScanner.getOptions("lLcCaA");
        if(loginOption.equals("l") || loginOption.equals("L")){
            System.out.println("Enter UserName ");
            String userName = sc.next();
            System.out.println("Enter Password ");
            String password = sc.next();
            authStatus = Authenticator.learnerAuthentication(userName,password);
            if(authStatus == AuthStatus.LOGIN_SUCCESS) currentUser = Authenticator.getLearner(userName);
        }
        if(loginOption.equals("c") || loginOption.equals("C")){
            System.out.println("Enter UserName ");
            String userName = sc.next();
            System.out.println("Enter Password ");
            String password = sc.next();
            authStatus  = Authenticator.creatorAuthentication(userName,password);
            if(authStatus == AuthStatus.LOGIN_SUCCESS) currentUser = Authenticator.getCreator(userName);
        }
        if(loginOption.equals("a") || loginOption.equals("A")){
            System.out.println("Enter AdminId ");
            String adminId = sc.next();
            System.out.println("Enter Password ");
            String adminPassword = sc.next();
            authStatus = Authenticator.adminAuthentication(adminId,adminPassword);
            if(authStatus == AuthStatus.LOGIN_SUCCESS) currentAdmin = Authenticator.getAdmin(adminId);
        }
        return authStatus;
    }

    private static AuthStatus signUp() {
        AuthStatus authStatus = null;

        return authStatus;
    }

    public static void logOutUser() {
        currentUser = null;
    }

    public static void logOutAdmin() {
        currentAdmin =null;
    }
}
