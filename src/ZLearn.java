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
                switch (loginType){
                    case "1":
                        isloginTypeValid=true;
                        boolean isValidLoginCredentials = false;
                        System.out.println("-----Customer Login Page-----");
                        while (!isValidLoginCredentials) {
                            System.out.println("Enter Mobile No. : ");
                            String mobileNumber = sc.next();
                            System.out.println("Enter Password : ");
                            String password = sc.next();
                            String authString = Authenticator.customerLogin(mobileNumber,password);
                            if(!authString.equals("User Not Found")){
                                isValidLoginCredentials=true;
                                if(authString.equals(role.LEARNER_AND_CREATOR.toString())){
                                    System.out.println("1.Learner Profile\n2.Creator Profile");
                                }else if(authString.equals(role.LEARNER_ONLY.toString())){
                                    System.out.println("Welcome Learner");
                                }else if(authString.equals(role.CREATOR_ONLY.toString())){
                                    System.out.println("Welcome Creator");
                                }
                            }
                        }

                        break;
                    case "2":
                        System.out.println("Admin logged in");
                        isloginTypeValid=true;
                        break;
                    default:
                        System.out.println("Invalid input");
                }
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
