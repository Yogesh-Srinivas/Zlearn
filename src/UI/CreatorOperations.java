package UI;

import Core.Course.Course;
import Core.Users.Creator;
import Managers.UIManager;
import java.util.ArrayList;
import java.util.Scanner;

public class CreatorOperations {
    private Creator currentCreator;
    private UIManager uiManager = new UIManager();

    public CreatorOperations(Creator creator){
        this.currentCreator = creator;
    }
    public void creatorDashBoard() {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("----To Teach is to Learn Twice Over----");
            System.out.println("1.View Created Courses\n2. Create New Course\n0. Log Out");
            boolean isValidCreatorOperationOption = false;
            while (!isValidCreatorOperationOption) {
                String creatorOperationOption = sc.next();
                if (creatorOperationOption.equals("1")) {
                    isValidCreatorOperationOption = true;
                    viewCreatedCourse();
                } else if (creatorOperationOption.equals("2")) {
                    isValidCreatorOperationOption = true;
                    createCourse();
                } else if (creatorOperationOption.equals("0")) {
                    isValidCreatorOperationOption = true;
                } else {
                    System.out.println("Invalid Input");
                }
            }
        }while (true);
    }

    private void createCourse() {
    }

    private void viewCreatedCourse(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> myCourses = currentCreator.getCreatedCourse();
        if(myCourses.size() != 0) {
            System.out.println("-----Created Courses-----");
            int courseNumber = 1;
            for (Course tempCourse : myCourses) {
                System.out.println("["+courseNumber +"] "+tempCourse.getCourseName());
                courseNumber++;
            }
            System.out.println("0. Back");
            boolean isValidCourseOption = false;
            while (!isValidCourseOption){
                String courseOption = sc.next();
                if(courseOption.equals("0")) isValidCourseOption=true;
                else if (Integer.parseInt(courseOption) <= myCourses.size()) {
                    int courseIndex = Integer.parseInt(courseOption)-1;
                    openCourse(myCourses.get(courseIndex));
                    isValidCourseOption = true;
                }else{
                    System.out.println("Invalid Option");
                }
            }

        }else{
            //need to add break
            System.out.println("You have not Created any Course,Yet!");
        }

    }

    private void openCourse(Course currCourse){
        Scanner sc = new Scanner(System.in);
        System.out.println("+++++++"+currCourse.getCourseId()+"+++++++");
        System.out.println("1. View Course\n2. Edit Course\n3. View Comments\n4. Delete Course\n0. Back");
        String courseOperation = sc.next();
        boolean isValidCourseOperation = false;
        while (!isValidCourseOperation){
            if(courseOperation.equals("1")){
                isValidCourseOperation=true;
                System.out.println("+++++++"+currCourse.getCourseId()+"+++++++");
                System.out.println("Course Name :"+ currCourse.getCourseName());
                System.out.println("Creator :"+ uiManager.getCreatorName(currCourse.getCreatorId()));
                System.out.println("Rating :"+currCourse.getRating());
                if(currCourse.getPrice() == 0) System.out.println("Price: Free");
                else System.out.println("Price :"+currCourse.getPrice());
                System.out.println("-What You'll Learn-");
                ArrayList<String> learnings = currCourse.getCourseLearnings();
                for(int i=1;i<learnings.size();i++){
                    System.out.println("["+i+"] "+learnings.get(i-1));
                }
            } else if(courseOperation.equals("2")){
                isValidCourseOperation=true;
                this.editCourse(currCourse.getCourseId());
            } else if (courseOperation.equals("3")) {
                isValidCourseOperation=true;
                this.viewComments(currCourse.getCourseId());
            } else if (courseOperation.equals("4")) {
                isValidCourseOperation=true;
                currentCreator.deleteCourse(currCourse.getCourseId());
                System.out.println("Course Deleted!!");
            } else if (courseOperation.equals("0")) {
                isValidCourseOperation=true;
            } else {
                System.out.println("Invalid Option");
            }
        }
    }

    private void editCourse(String courseId){
        Scanner sc = new Scanner(System.in);
        System.out.println("*****Edit Course*****");
        System.out.println("1. Change Course Name\n2. Add/Remove Category\n3. Change Price\n4. Edit Content\n0. Back");
        boolean isValidEditOption = false;
        while (!isValidEditOption){
            String editOption = sc.next();
            if(editOption.equals("1")){
                isValidEditOption=true;
                changeCourseName(courseId);
            }else if(editOption.equals("2")){
                isValidEditOption=true;
                editCategory(courseId);
            }else if(editOption.equals("3")){
                isValidEditOption=true;
                editCoursePrice(courseId);
            }else if(editOption.equals("4")){
                isValidEditOption=true;
                editCourseContent(courseId);
            }else if(editOption.equals("5")){
                isValidEditOption=true;
            }else {
                System.out.println("Invalid Option!!");
            }
        }
    }
    private void changeCourseName(String courseId){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter New Course Name");
        String newCourseName = sc.nextLine();
        currentCreator.changeCourseName(newCourseName,courseId);
        System.out.println("Course Name Updated!!");
    }
    private void editCategory(String courseId){
        Scanner sc = new Scanner(System.in);
        boolean exitEdit = false;
        while(!exitEdit) {
            ArrayList<String> availableCategories = uiManager.getCategories();
            Course currCourse = uiManager.getCourseDetails(courseId);
            ArrayList<String> currCourseCategories = currCourse.getCourseCategories();
            for(String category:currCourseCategories){
                availableCategories.remove(category);
            }
            System.out.print("Course Category\n ");
            for (String category : currCourseCategories) {
                System.out.print(category + " | ");
            }
            System.out.println();
            System.out.println();
            if(availableCategories.size()!=0) System.out.print("1.Add ");
            if(currCourseCategories.size() > 1) System.out.print("2. Delete ");
            System.out.println("0.back");
            boolean isValidEditOperation = false;
            while (!isValidEditOperation) {
                String editOperation = sc.next();
                if (availableCategories.size()>0 && editOperation.equals("1")) {
                    isValidEditOperation = true;
                    int categoryCount = 0;
                    System.out.println("Choose Category");
                    if(availableCategories.size() >0) {
                        for (String category : availableCategories) {
                            System.out.println("[" + (++categoryCount) + "] " + category);
                        }
                        while (true) {
                            String categoryInput = sc.next();
                            if (Integer.parseInt(categoryInput) > 0 && Integer.parseInt(
                                    categoryInput) <= availableCategories.size()) {
                                int categoryIndex = Integer.parseInt(categoryInput) - 1;
                                String catergoryToadd = availableCategories.get(categoryIndex);
                                currentCreator.addCourseCategory(catergoryToadd,courseId);
                                break;
                            } else {
                                System.out.println("Invalid input");
                            }
                        }
                    }else {
                        System.out.println("No Categories Left.");
                        break;
                    }
                } else if (currCourseCategories.size()>1 && editOperation.equals("2")) {
                    isValidEditOperation = true;
                    int categoryCount = 0;
                    System.out.println("Choose Category");
                    if(currCourseCategories.size() > 1) {
                        for (String category : currCourseCategories) {
                            System.out.println("[" + (++categoryCount) + "] " + category);
                        }
                        while (true) {
                            String categoryInput = sc.next();
                            if (Integer.parseInt(categoryInput) > 0 && Integer.parseInt(
                                    categoryInput) <= currCourseCategories.size()) {
                                int categoryIndex = Integer.parseInt(categoryInput) - 1;
                                String categoryToRemove = currCourseCategories.get(categoryIndex);
                                currentCreator.removeCourseCategory(categoryToRemove,courseId);
                                break;
                            } else {
                                System.out.println("Invalid input");
                            }
                        }
                    }else {
                        System.out.println("Course Must Contain One Category.");
                        break;
                    }
                } else if (editOperation.equals("0")) {
                    isValidEditOperation = true;
                    exitEdit = true;
                } else {
                    System.out.println("Invalid Option!!");
                }
            }
        }
    }
    private void editCoursePrice(String courseId){
        Scanner sc = new Scanner(System.in);
        boolean isValidCoursePriceInput = false;
        while(!isValidCoursePriceInput){
            System.out.println("Enter New Price");
            try {
                int coursePrice =new Scanner(System.in).nextInt();
                isValidCoursePriceInput=true;
                currentCreator.changeCoursePrice(coursePrice,courseId);
            }catch (Exception e){
                System.out.println("Invalid Input");
            }
        }
    }
    private void editCourseContent(String courseId){
        Scanner sc = new Scanner(System.in);
    }
    private void viewComments(String courseId){
        Scanner sc = new Scanner(System.in);
    }
}
