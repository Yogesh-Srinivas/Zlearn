package UI;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Users.Creator;
import Managers.UIManager;
import Utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;

public class CreatorOperations {
    private final Creator currentCreator;
    private final UIManager uiManager = new UIManager();

    public CreatorOperations(Creator creator){
        this.currentCreator = creator;
    }
    public void creatorDashBoard() {
        while (true){
            System.out.println("----To Teach is to Learn Twice Over----");
            System.out.println("1.View Created Courses\n2. Create New Course\n0. Log Out");
            String creatorOperationOption = CustomScanner.getOptions("120");
            if (creatorOperationOption.equals("1")) {
                viewCreatedCourse();
            }
            if (creatorOperationOption.equals("2")) {
                createCourse();
            }
            if (creatorOperationOption.equals("0")) {
                break;
            }
        }
    }

    private void createCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Course Name");
        String courseName = sc.nextLine();
        ArrayList<String> selectedCategories = getNewCourseCategories();
        ArrayList<Chapter> courseContent = getCourseChapters();
        System.out.println("Enter Course Price");
        int coursePrice = CustomScanner.getIntegetInput(0,100000);
        currentCreator.addNewCourse(courseName,selectedCategories,courseContent,coursePrice);
    }
    private ArrayList<String> getNewCourseCategories(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> selectedCategories = new ArrayList<>();
        ArrayList<String> availableCategories = uiManager.getCategories();
        boolean notConfirm=true;
        while(notConfirm) {
            System.out.print("Selected Category : ");
            for (int i = 0; i < selectedCategories.size(); i++) {
                System.out.print(selectedCategories.get(i));
                if (i != selectedCategories.size() - 1) System.out.print(", ");
            }
            System.out.println();
            if(availableCategories.size() != 0) System.out.print("[A]dd ");
            if(selectedCategories.size() != 0) System.out.print("[D]elete [C]onfirm");
            System.out.println();
            String operationChoice = sc.next();
            if(availableCategories.size()!=0 && (operationChoice.equals("A") || operationChoice.equals("a"))){
                for(int ind=1;ind<=availableCategories.size();ind++){
                    System.out.println("["+ind+"] "+availableCategories.get(ind-1));
                }
                int categoryIndex = CustomScanner.getIntegetInput(1,availableCategories.size());
                selectedCategories.add(availableCategories.get(categoryIndex-1));
                availableCategories.remove(categoryIndex-1);
            }else if(selectedCategories.size()!=0 && (operationChoice.equals("D") || operationChoice.equals("d"))){
                for(int ind=1;ind<=selectedCategories.size();ind++){
                    System.out.println("["+ind+"] "+selectedCategories.get(ind-1));
                }
                int categoryIndex = CustomScanner.getIntegetInput(1,selectedCategories.size());
                availableCategories.add(selectedCategories.get(categoryIndex-1));
                selectedCategories.remove(categoryIndex-1);
            }else if(selectedCategories.size()!=0 && (operationChoice.equals("C") || operationChoice.equals("c"))){
                notConfirm = false;
            }else {
                System.out.println("Invalid Input!!");
            }

        }
        return selectedCategories;
    }

    private ArrayList<Chapter> getCourseChapters(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Chapter> chapters = new ArrayList<>();
        boolean isConfirm = false;
        while (!isConfirm){
            System.out.println("*******Add Course Content*********");
            System.out.println("Current Chapters :-");
            for (int i=1;i<=chapters.size();i++){
                System.out.println("["+i+"] "+chapters.get(i-1).getChapterName());
            }

            System.out.print("[A]dd ");
            if(chapters.size()!=0) System.out.print(" [C]onfirm");
            System.out.println();
            String options = sc.next();

            if(options.equals("A") || options.equals("a")){
                chapters.add(getNewChapter());
            }else if (chapters.size()!=0 && (options.equals("c") || options.equals("C"))){
                isConfirm = true;
            }
        }
        return  chapters;
    }

    private Chapter getNewChapter() {
        Scanner sc = new Scanner(System.in);
        String chapterName;
        String lesson;
        System.out.println("Enter Chapter Name");
        chapterName = sc.nextLine();
        System.out.println("Enter Chapter Content");
        lesson = CustomScanner.getMultiLineInput();
        System.out.println(lesson);
        return new Chapter(chapterName,lesson);
    }

    private void viewCreatedCourse(){
        ArrayList<Course> myCourses = currentCreator.getCreatedCourse();
        if (myCourses.size() != 0) {
            System.out.println("-----Created Courses-----");
            int courseNumber = 1;
            for (Course tempCourse : myCourses) {
                System.out.println("[" + courseNumber + "] " + tempCourse.getCourseName());
                courseNumber++;
            }
            int courseIndex = CustomScanner.getIntegetInput(1,myCourses.size());
            openCourse(myCourses.get(courseIndex-1));
        } else {
            System.out.println("You have not Created any Course,Yet!");
        }
    }

    private void openCourse(Course currCourse){
        while (true) {
            System.out.println("+++++++" + currCourse.getCourseId() + "+++++++");
            System.out.println("1. View Course\n2. Edit Course\n3. View Comments\n4. Delete Course\n0. Back");
            String courseOperation = CustomScanner.getOptions("12340");
            if (courseOperation.equals("1")) {
                System.out.println("+++++++" + currCourse.getCourseId() + "+++++++");
                System.out.println("Course Name :" + currCourse.getCourseName());
                System.out.println("Creator :" + uiManager.getCreatorName(currCourse.getCreatorId()));
                System.out.println("Rating :" + currCourse.getRating());
                if (currCourse.getPrice() == 0) System.out.println("Price: Free");
                else System.out.println("Price :" + currCourse.getPrice());
                System.out.println("-What You'll Learn-");
                ArrayList<String> learnings = currCourse.getCourseLearnings();
                for (int i = 1; i <= learnings.size(); i++) {
                    System.out.println("[" + i + "] " + learnings.get(i - 1));
                }
            }
            if (courseOperation.equals("2")) {
                editCourse(currCourse.getCourseId());
            }
            if (courseOperation.equals("3")) {
                viewComments(currCourse.getCourseId());
            }
            if (courseOperation.equals("4")) {
                currentCreator.deleteCourse(currCourse.getCourseId());
                System.out.println("Course Deleted!!");
                break;
            }
            if (courseOperation.equals("0")) {
                break;
            }
        }
    }

    private void editCourse(String courseId){
        System.out.println("*****Edit Course*****");
        System.out.println("1. Change Course Name\n2. Add/Remove Category\n3. Change Price\n4. Edit Content\n0. Back");
        String editOption = CustomScanner.getOptions("12340");
        if(editOption.equals("1")){
            changeCourseName(courseId);
        }
        if(editOption.equals("2")){
            editCategory(courseId);
        }
        if(editOption.equals("3")){
            editCoursePrice(courseId);
        }
        if(editOption.equals("4")){
            editCourseContent(courseId);
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
        boolean isValidCoursePriceInput = false;
        while(!isValidCoursePriceInput){
            System.out.println("Enter New Price");
            try {
                int coursePrice =new Scanner(System.in).nextInt();
                isValidCoursePriceInput=true;
                currentCreator.changeCoursePrice(coursePrice,courseId);
                System.out.println("Course Price Changed!!");
            }catch (Exception e){
                System.out.println("Invalid Input");
            }
        }
    }
    private void editCourseContent(String courseId){
        while (true) {
            System.out.println("********Edit Course Content******");
            System.out.println("[A]dd [D]elete [E]dit [B]ack");
            String inputOptions = CustomScanner.getOptions("aAdDeEbB");
            if (inputOptions.equals("a") || inputOptions.equals("A")) {
                currentCreator.addCourseContent(courseId,getNewChapter());
            }
            if (inputOptions.equals("d") || inputOptions.equals("D")) {
                currentCreator.deleteCourseContent(courseId,getContentIndex(courseId));
            }
            if (inputOptions.equals("e") || inputOptions.equals("E")) {
                int contentIndex = getContentIndex(courseId);
                Chapter selectedChapter = uiManager.getCourseDetails(courseId).getChapter(contentIndex);
                System.out.println("1. Change Chapter Name\n2.Change Content\n3.back");
                String editOption = CustomScanner.getOptions("123");
                if(editOption.equals("1")){
                    System.out.println("Current Chapter Name : "+selectedChapter.getChapterName());
                    System.out.println("Enter New Chapter Name");
                    String newChapterName = new Scanner(System.in).nextLine();
                    currentCreator.changeCourseChapterName(newChapterName,courseId,contentIndex);
                }
                if(editOption.equals("2")){
                    System.out.println("***** Current Lesson ***** \n"+selectedChapter.getLesson());
                    System.out.println("**************************");
                    System.out.println("Enter New Content");
                    String newContent = CustomScanner.getMultiLineInput();
                    currentCreator.changeCourseChapterContent(newContent,courseId,contentIndex);

                }
            }
            if(inputOptions.equals("b") || inputOptions.equals("B")){
                break;
            }
        }

    }

    private int getContentIndex(String courseId) {
        ArrayList<Chapter> chapters = uiManager.getCourseDetails(courseId).getContent();
        for(int i=1;i<=chapters.size();i++){
            System.out.println("["+i+"] "+chapters.get(i-1).getChapterName());
        }
        return CustomScanner.getIntegetInput(1,chapters.size()) - 1;
    }

    private void viewComments(String courseId) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("***********Comment Page*************");
            ArrayList<Comment> comments = currentCreator.getCourseComments(courseId);
            for (Comment comment : comments) {
                System.out.println(uiManager.getLearnerName(comment.getCommentor()) + " : " + comment.getComment());
            }
            System.out.println();
            System.out.println("0. back");
            String backOption = sc.next();
            if(backOption.equals("0")){
                break;
            }else {
                System.out.println("Invalid option!!");
            }
        }
    }
}
