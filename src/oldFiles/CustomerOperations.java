package oldFiles;

import java.util.Scanner;

public class CustomerOperations {
    public static void learnerOperation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----Learning never exhausts the mind----");
        System.out.println("1. View Enrolled Courses\n2. Enroll new oldFiles.Course\n3. View Certificates\n4. Switch to oldFiles.Creator\n0. Log Out");
        String learnerOperationOption = sc.next();
        boolean isValidLearnerOperationOption = false;
        while(!isValidLearnerOperationOption){
            if(learnerOperationOption.equals("1")){
                isValidLearnerOperationOption=true;
                ((Learner)ZLearn.currUser).viewEnrolledCourse();
            }else if(learnerOperationOption.equals("2")){
                isValidLearnerOperationOption=true;
                ((Learner) ZLearn.currUser).enrollNewCourse();
            }else if(learnerOperationOption.equals("3")){
                isValidLearnerOperationOption=true;
            }else if(learnerOperationOption.equals("4")){
                isValidLearnerOperationOption=true;
                CustomerOperations.creatorOperations();
            }else if(learnerOperationOption.equals("0")){
                isValidLearnerOperationOption=true;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }
    public static void creatorOperations(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----To Teach is to Learn Twice Over----");
        System.out.println("1.View Created Courses\n2. Create New oldFiles.Course\n3. Switch to oldFiles.Learner\n0. Log Out");
        boolean isValidCreatorOperationOption = false;
        while(!isValidCreatorOperationOption){
            String creatorOperationOption = sc.next();
            if(creatorOperationOption.equals("1")){
                isValidCreatorOperationOption=true;
                ((Creator)ZLearn.currUser).viewCreatedCourse();
            }else if(creatorOperationOption.equals("2")){
                isValidCreatorOperationOption=true;
                ((Creator)ZLearn.currUser).createCourse();
            }else if(creatorOperationOption.equals("3")){
                isValidCreatorOperationOption=true;
                CustomerOperations.learnerOperation();
            }else if(creatorOperationOption.equals("0")){
                isValidCreatorOperationOption=true;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }
}
