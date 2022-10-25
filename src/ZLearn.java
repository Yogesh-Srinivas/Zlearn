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
                            if(Authenticator.customerLogin(mobileNumber,password)){
                                isValidLoginCredentials=true;
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
        Learner learner1= new Learner("Yogi","123","1234567890");
    }
}
