import java.util.Scanner;

public class CustomerOperations {
    void leanerOperation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----Learning never exhausts the mind----");
        System.out.println("1. View Enrolled Courses\n2. Enroll new Course\n3. View Certificates\n4. Switch to Creator\n0. Log Out");
        String learnerOperationOption = sc.next();
        boolean isValidLearnerOperationOption = false;
        while(!isValidLearnerOperationOption){
            if(learnerOperationOption.equals("1")){
                isValidLearnerOperationOption=true;
                viewEnrolledCourse();
            }else if(learnerOperationOption.equals("2")){
                isValidLearnerOperationOption=true;
            }else if(learnerOperationOption.equals("3")){
                isValidLearnerOperationOption=true;
            }else if(learnerOperationOption.equals("4")){
                isValidLearnerOperationOption=true;
                creatorOperartions();
            }else if(learnerOperationOption.equals("0")){
                isValidLearnerOperationOption=true;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }
    void creatorOperartions(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----To Teach is to Learn Twice Over----");
        System.out.println("1.View Created Courses\n2. Create New Course\n3. Switch to Learner\n0. Log Out");
        String creatorOperationOption = sc.next();
        boolean isValidCreatorOperationOption = false;
        while(!isValidCreatorOperationOption){
            if(creatorOperationOption.equals("1")){
                isValidCreatorOperationOption=true;
            }else if(creatorOperationOption.equals("2")){
                isValidCreatorOperationOption=true;
            }else if(creatorOperationOption.equals("3")){
                isValidCreatorOperationOption=true;
                leanerOperation();
            }else if(creatorOperationOption.equals("0")){
                isValidCreatorOperationOption=true;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }

    void viewEnrolledCourse(){
        Database db = Database.getInstance();
        if(ZLearn.currUser instanceof Learner) {
            for (String courseId : ((Learner) ZLearn.currUser).getMyCourses()) {
                Course tempCourse = db.getCourseDetails(courseId);
                System.out.println(tempCourse.getCourseId());
                System.out.println(tempCourse.getRating());
            }
        }
    }
}
