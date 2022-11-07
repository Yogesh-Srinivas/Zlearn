package oldFiles;

import java.util.ArrayList;
import java.util.Scanner;

public class ZLearn {
    static User currUser;
    public static void main(String[] args) {
        Database db = Database.getInstance();
        dbInitializer();
        Scanner sc = new Scanner(System.in);
        System.out.println("********Welcome********");
        System.out.println("*     IT's  ZLEARN    *");
        System.out.println("***********************");
//        while(true){
//            System.out.println("-------Login---------");
//            System.out.println("1. Customer Login\n2. Admin Login");
//            boolean isloginTypeValid = false;
//            while(!isloginTypeValid){
//                String loginType = sc.next();
//                if(loginType=="1") {
//                    isloginTypeValid = true;
//                    boolean isValidLoginCredentials = false;
//                    System.out.println("-----Customer Login Page-----");
//                    while (!isValidLoginCredentials) {
//                        System.out.println("Enter Mobile No. : ");
//                        String mobileNumber = sc.next();
//                        System.out.println("Enter Password : ");
//                        String password = sc.next();
//                        String authString = Authenticator.customerLogin(mobileNumber, password);
//                        if (!authString.equals("oldFiles.User Not Found")) {
//                            isValidLoginCredentials = true;
//                            int profileFlag = 0;
//                            if (authString.equals(role.LEARNER_AND_CREATOR.toString())) {
//                                System.out.println("1.oldFiles.Learner Profile\n2.oldFiles.Creator Profile");
//                                boolean isValidProfilePref = false;
//                                while (!isValidProfilePref) {
//                                    String profilePref = sc.next();
//                                    if ("1".equals(profilePref)) {
//                                        isValidProfilePref = true;
//                                        profileFlag = 1;
//                                    } else if ("2".equals(profilePref)) {
//                                        isValidProfilePref = true;
//                                        profileFlag = 2;
//                                    } else {
//                                        System.out.println("Invalid Option!!");
//                                    }
//                                }
//                            }
//                            if (profileFlag == 1 || authString.equals(role.LEARNER_ONLY.toString())) {
//                                System.out.println("----Learning never exhausts the mind----");
//                                System.out.println("1. View Enrolled Courses\n2. Enroll new oldFiles.Course\n3.View Certificates");
//                                if (authString.equals(role.LEARNER_AND_CREATOR.toString())) {
//                                    System.out.println("4. Switch to oldFiles.Creator");
//                                }
//                                System.out.println("0. Log out");
//
//                            } else if (profileFlag == 2 || authString.equals(role.CREATOR_ONLY.toString())) {
//                                System.out.println("----To Teach is to Learn Twice Over----");
//                                System.out.println("1.View Created Courses\n2. Create New oldFiles.Course");
//                                if (authString.equals(role.LEARNER_AND_CREATOR.toString())) System.out.println(
//                                        "3. Switch to oldFiles.Learner");
//                                System.out.println("0. Log Out");
//                            }
//                        }else{
//                            System.out.println("oldFiles.User Not Found/ Invalid Password");
//                        }
//                    }
//                } else if(loginType.equals("2")) {
//                        System.out.println("Admin logged in");
//                        isloginTypeValid = true;
//                    }
//                   else System.out.println("Invalid input");
//                }
//            }
        Learner user = new Learner("curUser", "123", "21345");
        user.addMyCourse("cs001");
        user.addMyCourse("cs002");
        db.addUserProgress("cs001",user.getUserId());
        db.addUserProgress("cs002", user.getUserId());
        db.addLearner(user);

        Creator creator = new Creator("createUser", "123", "12435");
        db.addCreator(creator);
        ZLearn.currUser = user;
//        oldFiles.ZLearn.currUser = creator;
        while (true) {
            System.out.println("1.oldFiles.Learner\n2.oldFiles.Creator");
            String profileOption = sc.next();
            boolean isValidProfileOption = false;
            while (!isValidProfileOption) {
                if (profileOption.equals("1")) {
                    isValidProfileOption = true;
                    CustomerOperations.learnerOperation();
                } else if (profileOption.equals("2")) {
                    isValidProfileOption = true;
                    CustomerOperations.creatorOperations();
                } else {
                    System.out.println("Invalid Option");
                }
            }
        }
    }

    static void dbInitializer(){
        Database db = Database.getInstance();
        Learner learner1= new Learner("Yogi", "123", "12345");
        db.addLearner(learner1);
        Learner learner3= new Learner("Avi", "123", "34567");
        db.addLearner(learner3);
        Learner learner2= new Learner("Logan", "123", "23456");
        db.addLearner(learner2);
        Creator creator1 = new Creator("Yogi", "123", "12345");
        db.addCreator(creator1);
        Creator creator2 = new Creator("Satya", "123", "45678");
        db.addCreator(creator2);

        ArrayList<String> category = new ArrayList<String>(){
            {
                add("Finance");
                add("Self Help");
            }
        };
        db.addCategory("Finance");
        db.addCategory("Self Help");
        db.addCategory("Business");
        db.addCategory("Coding");

        ArrayList<Chapter> content = new ArrayList<Chapter>(){
            {
                add(new Chapter("Lesson 1", "Yellow is new black"));
                add(new Chapter("Lesson 2","This is minions, they have yellow skin color with round eyes!"));
                add(new Chapter("Lesson 3","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 4","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 5","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 6","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 7","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 8","Minions appears in despicable me movie and owns its franchises"));

            }
        };
        Course course1 = new Course("The Minions",category,"cs001",1007,200,content);
        course1.addComment("Nice oldFiles.Course",1001);
        course1.addComment("Awesome oldFiles.Course",1002);
        course1.addComment("Need More Improvement",1003);
        course1.addComment("Add more content like this.",1006);
        course1.addComment("Add more content like this.Yes!!",1006);
        course1.addComment("Add more content like this.No more",1006);
        db.addCourses(course1);
        Course course2 = new Course("The Minions 2", category, "cs002", 1007, 0, content);
        course2.addComment("Need More Improvement",1003);
        course2.addComment("Awesome oldFiles.Course",1002);
        course2.addComment("Add more content like this.",1006);
        course2.addComment("Nice oldFiles.Course",1001);
        db.addCourses(course2);
    }
}
