package Managers;

import Core.Users.User;
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
        String loginOption = CustomScanner.getOptions("l","L","s","S");
        if(loginOption.equalsIgnoreCase("l")){
            authStatus = login();
        }
        else if(loginOption.equalsIgnoreCase("s")){
            authStatus = signUp();
        }
        return authStatus;
    }

    //******** Login ******************************************************************

    private static AuthStatus login() {
        AuthStatus authStatus;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter UserName ");
        String userName = sc.nextLine();
        System.out.println("Enter Password ");
        String password = sc.nextLine();
        authStatus = dataManager.userAuthentication(userName, password);
        if (authStatus.equals(AuthStatus.LOGIN_SUCCESS)) currentUser = dataManager.getUser(userName);

        switch (authStatus) {
            case USERNAME_NOT_FOUND:
                System.out.println("User Name Not Found");
                break;
            case PASSWORD_MISMATCH:
                System.out.println("Password Mismatch");
                break;
            case ID_NOT_FOUND:
                System.out.println("Invalid Admin Id");
                break;
            case LOGIN_SUCCESS:
                System.out.println("Login Success!!!");
                break;
        }
        return authStatus;
    }

    //********* Sign Up ***************************************************************

    private static AuthStatus signUp() {
        AuthStatus authStatus;

        System.out.println("[L]earner SignUp\n[C]reator Signup");
        String signupOption = CustomScanner.getOptions("l","L","c","C");
        String userName = getNewUserName();
        String password = getPassword();
        String firstName = getFirstName();

        if(signupOption.equalsIgnoreCase("l"))
            dataManager.addLearner(userName,password,firstName);
        else if(signupOption.equalsIgnoreCase("c"))
            dataManager.addCreator(userName,password,firstName);

        System.out.println("Account created Successfully!!!");
        currentUser = dataManager.getUser(userName);
        authStatus = AuthStatus.LOGIN_SUCCESS;

        return authStatus;
    }

    private static String getNewUserName(){
        String userName;
        System.out.println("Enter User Name ");
        userName = CustomScanner.getNameInput();
        while(userName.length() < 3 || userName.length() > 64 || userName.contains(" ")) {
            System.out.println(
                    "Username should be less than 64 characters and greater than 2 characters and spaces are not allowed.\nEnter User Name Again");
            userName = CustomScanner.getNameInput();
        }
        while (true){
            if(dataManager.isUserNameAvailable(userName)){
                return userName;
            }else {
                System.out.println("UserName already Exist\nEnter User Name Again");
                userName = CustomScanner.getNameInput();
            }
        }
    }


    private static String getPassword(){
        Scanner sc = new Scanner(System.in);
        String password;
        while (true){
            System.out.println("Enter Password");
            password = sc.nextLine();
            while (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()_!*])(?=\\S+$).{8,20}$",password)){
                System.out.println("Password should contain atleast an UpperCase letter,a LowerCase letter,a Digit,a Special Character and\nPassword length should be between 8 and 20. Spaces Not Allowed");
                System.out.println("Enter Password");
                password = sc.nextLine();
            }
            System.out.println("Confirm Password :");
            String confirmPassword = sc.nextLine();
            if(password.equals(confirmPassword)) break;
            System.out.println("Password mismatch! Type Again.");
        }
        return password;
    }

    private static String getFirstName(){
        System.out.println("Enter Your Name to be display : ");
        String firstName = CustomScanner.getNameInput();
        while(firstName.length() < 3 || firstName.length() > 64) {
            System.out.println(
                    "Name should be less than 64 characters and greater than 2 characters\nEnter First Name Again : ");
            firstName = CustomScanner.getNameInput();
        }
        return firstName;
    }

    //*********** Log Out **********************************************************
    public static void logOutUser() {
        currentUser = null;
    }
}
