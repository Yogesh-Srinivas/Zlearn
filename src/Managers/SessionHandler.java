package Managers;

import Core.Users.User;
import UI.AuthStatus;
import Utilities.CustomScanner;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SessionHandler {
    private static User currentUser = null;
    private static final DataManager dataManager = new DataManager();

    //***************************************************************************
    public static User getCurrentUser() {
        return currentUser;
    }

    //******** Auth *******************************************************************

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

    //******** Login ******************************************************************

    private static AuthStatus login() {
        AuthStatus authStatus = AuthStatus.AUTH_FAILED;
        Scanner sc =new Scanner(System.in);
        System.out.println("[L]earner login\n[C]reator Login\n[A]dmin Login");
        String loginOption = CustomScanner.getOptions("lLcCaA");
        if(loginOption.equals("l") || loginOption.equals("L")){
            System.out.println("Enter UserName ");
            String userName = sc.next();
            System.out.println("Enter Password ");
            String password = sc.next();
            authStatus = dataManager.learnerAuthentication(userName,password);
            if(authStatus.equals(AuthStatus.LOGIN_SUCCESS)) currentUser = dataManager.getLearner(userName);
        }
        if(loginOption.equals("c") || loginOption.equals("C")){
            System.out.println("Enter UserName ");
            String userName = sc.next();
            System.out.println("Enter Password ");
            String password = sc.next();
            authStatus  = dataManager.creatorAuthentication(userName,password);
            if(authStatus.equals(AuthStatus.LOGIN_SUCCESS)) currentUser = dataManager.getCreator(userName);
        }
        if(loginOption.equals("a") || loginOption.equals("A")){
            System.out.println("Enter AdminId ");
            String adminId = sc.next();
            System.out.println("Enter Password ");
            String adminPassword = sc.next();
            authStatus = dataManager.adminAuthentication(adminId,adminPassword);
            if(authStatus.equals(AuthStatus.LOGIN_SUCCESS)) currentUser = dataManager.getAdmin(adminId);
        }

        if(authStatus.equals(AuthStatus.USERNAME_NOT_FOUND)) System.out.println("User Name Not Found");
        if(authStatus.equals(AuthStatus.PASSWORD_MISMATCH)) System.out.println("Password Mismatch");
        if(authStatus.equals(AuthStatus.ID_NOT_FOUND)) System.out.println("Invalid Admin Id");
        if(authStatus.equals(AuthStatus.LOGIN_SUCCESS)) System.out.println("Login Success!!!");
        return authStatus;
    }

    //********* Sign Up ***************************************************************

    private static AuthStatus signUp() {
        AuthStatus authStatus = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("[L]earner SignUp\n[C]reator Signup");
        String signupOption = CustomScanner.getOptions("lLcC");
        if(signupOption.equals("l") || signupOption.equals("L")){
            String userName = getNewLearnerUserName();
            String password = getPassword();
            System.out.println("Enter your First Name : ");
            String firstName = sc.nextLine();
            while(firstName.length() > 64) {
                System.out.println(
                        "FirstName should be less than 64 characters\nEnter First Name Again : ");
                firstName = sc.nextLine();
            }
            dataManager.addLearner(userName,password,firstName);
            System.out.println("Account created Successfully!!!");
            currentUser = dataManager.getLearner(userName);
            authStatus = AuthStatus.LOGIN_SUCCESS;
        }
        if(signupOption.equals("c") || signupOption.equals("C")){
            String userName = getNewCreatorUserName();
            String password = getPassword();
            System.out.println("Enter your First Name : ");
            String firstName = sc.nextLine();
            while(firstName.length() > 64) {
                System.out.println(
                        "FirstName should be less than 64 characters\nEnter First Name Again : ");
                firstName = sc.nextLine();
            }
            dataManager.addCreator(userName,password,firstName);
            System.out.println("Account created Successfully!!!");
            currentUser = dataManager.getCreator(userName);
            authStatus = AuthStatus.LOGIN_SUCCESS;
        }
        return authStatus;
    }

    private static String getNewLearnerUserName(){
        Scanner sc = new Scanner(System.in);
        String userName;
        System.out.println("Enter User Name ");
        userName = sc.nextLine();
        while(userName.length() > 64 || userName.contains(" ")) {
            System.out.println(
                    "Username should be less than 64 characters and spaces are not allowed.\nEnter User Name Again");
            userName = sc.nextLine();
        }
        while (true){
            if(dataManager.isLearnerUserNameAvailable(userName)){
                return userName;
            }else System.out.println("UserName already Exist");
        }
    }

    private static String getNewCreatorUserName(){
        Scanner sc = new Scanner(System.in);
        String userName;
        System.out.println("Enter User Name ");
        userName = sc.nextLine();
        while(userName.length() > 64 || userName.contains(" ")) {
            System.out.println(
                    "Username should be less than 64 characters and spaces are not allowed.\nEnter User Name Again");
            userName = sc.nextLine();
        }
        while (true){
            if(dataManager.isCreatorUserNameAvailable(userName)){
                return userName;
            }else System.out.println("UserName already Exist");
        }
    }

    private static String getPassword(){
        Scanner sc = new Scanner(System.in);
        String password;
        while (true){
            System.out.println("Enter Password");
            password = sc.next();
            while (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",password)){
                System.out.println("Password should contain atleast an UpperCase letter,a LowerCase letter,a Digit,a Special Character and\nPassword length should be between 8 and 20");
                System.out.println("Enter Password");
                password = sc.next();
            }
            System.out.println("Confirm Password :");
            String confirmPassword = sc.next();
            if(password.equals(confirmPassword)) break;
            System.out.println("Password mismatch! Type Again.");
        }
        return password;
    }

    //*********** Log Out **********************************************************
    public static void logOutUser() {
        currentUser = null;
    }
}
