import java.util.Scanner;

public class ZLearn {
    public static void main(String[] args) {
        dbInitializer();
        Scanner sc = new Scanner(System.in);
        System.out.println("********Welcome********");
        System.out.println("*     IT's  ZLEARN    *");
        System.out.println("***********************");
        while(true){
            System.out.println("-------Login---------");
            System.out.println("1. Customer Login\n2. Admin Login");
            boolean isloginTypeValid = false;
            while(!isloginTypeValid){
                String loginType = sc.next();
                if(loginType=="1") {
                    isloginTypeValid = true;
                    boolean isValidLoginCredentials = false;
                    System.out.println("-----Customer Login Page-----");
                    while (!isValidLoginCredentials) {
                        System.out.println("Enter Mobile No. : ");
                        String mobileNumber = sc.next();
                        System.out.println("Enter Password : ");
                        String password = sc.next();
                        String authString = Authenticator.customerLogin(mobileNumber, password);
                        if (!authString.equals("User Not Found")) {
                            isValidLoginCredentials = true;
                            int profileFlag = 0;
                            if (authString.equals(role.LEARNER_AND_CREATOR.toString())) {
                                System.out.println("1.Learner Profile\n2.Creator Profile");
                                boolean isValidProfilePref = false;
                                while (!isValidProfilePref) {
                                    String profilePref = sc.next();
                                    if ("1".equals(profilePref)) {
                                        isValidProfilePref = true;
                                        profileFlag = 1;
                                    } else if ("2".equals(profilePref)) {
                                        isValidProfilePref = true;
                                        profileFlag = 2;
                                    } else {
                                        System.out.println("Invalid Option!!");
                                    }
                                }
                            }
                            if (profileFlag == 1 || authString.equals(role.LEARNER_ONLY.toString())) {
                                System.out.println("----Learning never exhausts the mind----");
                                System.out.println("1. View Enrolled Courses\n2. Enroll new Course\n3.View Certificates");
                                if (authString.equals(role.LEARNER_AND_CREATOR.toString())) {
                                    System.out.println("4. Switch to Creator");
                                }
                                System.out.println("0. Log out");

                            } else if (profileFlag == 2 || authString.equals(role.CREATOR_ONLY.toString())) {
                                System.out.println("----To Teach is to Learn Twice Over----");
                                System.out.println("1.View Created Courses\n2. Create New Course");
                                if (authString.equals(role.LEARNER_AND_CREATOR.toString())) System.out.println(
                                        "3. Switch to Learner");
                                System.out.println("0. Log Out");
                            }
                        }
                    }
                } else if(loginType.equals("2")) {
                        System.out.println("Admin logged in");
                        isloginTypeValid = true;
                    }
                   else System.out.println("Invalid input");
                }
            }
    }

    static void dbInitializer(){
        Database db = Database.getInstance();
        Learner learner1= new Learner("Yogi","123","12345");
        db.addLearner(learner1);
        Learner learner3= new Learner("Avi","123","34567");
        db.addLearner(learner3);
        Learner learner2= new Learner("Logan","123","23456");
        db.addLearner(learner2);
        Creator creator1 = new Creator("Yogi","123","12345");
        db.addCreator(creator1);
        Creator creator2 = new Creator("Sathya","123","45678");
        db.addCreator(creator2);
    }
}
