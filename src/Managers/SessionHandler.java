package Managers;

import Core.Users.Admin;
import Core.Users.User;
import UI.AuthStatus;
import UI.Utilities.CustomScanner;

import java.util.Scanner;

public class SessionHandler {
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
            if(authStatus.equals(AuthStatus.LOGIN_SUCCESS)) currentUser = Authenticator.getLearner(userName);
        }
        if(loginOption.equals("c") || loginOption.equals("C")){
            System.out.println("Enter UserName ");
            String userName = sc.next();
            System.out.println("Enter Password ");
            String password = sc.next();
            authStatus  = Authenticator.creatorAuthentication(userName,password);
            if(authStatus.equals(AuthStatus.LOGIN_SUCCESS)) currentUser = Authenticator.getCreator(userName);
        }
        if(loginOption.equals("a") || loginOption.equals("A")){
            System.out.println("Enter AdminId ");
            String adminId = sc.next();
            System.out.println("Enter Password ");
            String adminPassword = sc.next();
            authStatus = Authenticator.adminAuthentication(adminId,adminPassword);
            if(authStatus.equals(AuthStatus.LOGIN_SUCCESS)) currentAdmin = Authenticator.getAdmin(adminId);
        }

        if(authStatus.equals(AuthStatus.USERNAME_NOT_FOUND)) System.out.println("User Name Not Found");
        if(authStatus.equals(AuthStatus.PASSWORD_MISMATCH)) System.out.println("Password Mismatch");
        if(authStatus.equals(AuthStatus.ID_NOT_FOUND)) System.out.println("Invalid Admin Id");
        if(authStatus.equals(AuthStatus.LOGIN_SUCCESS)) System.out.println("Login Success!!!");
        return authStatus;
    }

    private static AuthStatus signUp() {
        AuthStatus authStatus = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("[L]earner SignUp\n[C]reator Signup");
        String signupOption = CustomScanner.getOptions("lLcC");
        if(signupOption.equals("l") || signupOption.equals("L")){
            String userName = getNewLearnerUserName();
            String password = getPassword();
            System.out.println("Enter your First Name : ");
            String firstName = sc.next();
            Authenticator.addLearner(userName,password,firstName);
            System.out.println("Account created Successfully!!!");
            currentUser = Authenticator.getLearner(userName);
            authStatus = AuthStatus.LOGIN_SUCCESS;
        }
        if(signupOption.equals("c") || signupOption.equals("C")){
            String userName = getNewCreatorUserName();
            String password = getPassword();
            System.out.println("Enter your First Name : ");
            String firstName = sc.next();
            Authenticator.addCreator(userName,password,firstName);
            System.out.println("Account created Successfully!!!");
            currentUser = Authenticator.getCreator(userName);
            authStatus = AuthStatus.LOGIN_SUCCESS;
        }
        return authStatus;
    }

    private static String getNewLearnerUserName(){
        String username = null;
        Scanner sc = new Scanner(System.in);
        boolean isUserNameAvailable = false;
        while (!isUserNameAvailable){
            System.out.println("Enter User Name ");
            String userName = sc.next();
            if(Authenticator.isLearnerUserNameAvailable(userName)){
                isUserNameAvailable = true;
                username = userName;
            }else System.out.println("UserName already Exist");
        }
        return username;
    }

    private static String getNewCreatorUserName(){
        String username = null;
        Scanner sc = new Scanner(System.in);
        boolean isUserNameAvailable = false;
        while (!isUserNameAvailable){
            System.out.println("Enter User Name ");
            String userName = sc.next();
            if(Authenticator.isCreatorUserNameAvailable(userName)){
                isUserNameAvailable = true;
                username = userName;
            }else System.out.println("UserName already Exist");
        }
        return username;
    }

    private static String getPassword(){
        Scanner sc = new Scanner(System.in);
        String password = null;
        while (true){
            System.out.println("Enter Password :");
            password = sc.next();
            System.out.println("Confirm Password :");
            String confirmPassword = sc.next();
            if(password.equals(confirmPassword)) break;
            System.out.println("Password mismatch! Type Again.");
        }
        return password;
    }

    public static void logOutUser() {
        currentUser = null;
    }

    public static void logOutAdmin() {
        currentAdmin =null;
    }
}
