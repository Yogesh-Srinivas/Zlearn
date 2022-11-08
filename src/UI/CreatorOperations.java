package UI;

import Core.Users.Creator;

import java.util.Scanner;

public class CreatorOperations {
    private Creator currentCreator;

    public CreatorOperations(Creator creator){
        this.currentCreator = creator;
    }
    public void creatorDashBoard() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----To Teach is to Learn Twice Over----");
        System.out.println("1.View Created Courses\n2. Create New Course\n0. Log Out");
        boolean isValidCreatorOperationOption = false;
        while(!isValidCreatorOperationOption){
            String creatorOperationOption = sc.next();
            if(creatorOperationOption.equals("1")){
                isValidCreatorOperationOption=true;
                viewCreatedCourse();
            }else if(creatorOperationOption.equals("2")){
                isValidCreatorOperationOption=true;
                createCourse();
            }else if(creatorOperationOption.equals("0")){
                isValidCreatorOperationOption=true;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }

    private void createCourse() {
    }

    private void viewCreatedCourse() {
        
    }
}
