package UI;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Users.Creator;
import Managers.DataManager;
import Managers.SessionHandler;
import Utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;

public class CreatorOperations {
    private final Creator currentCreator;
    private final DataManager dataManager = new DataManager();

    //********* Constructor *************************************************************************
    public CreatorOperations(Creator creator){
        this.currentCreator = creator;
    }

    //**********************************************************************************
    public void creatorDashBoard() {
        label:
        while (true){
            System.out.println("----To Teach is to Learn Twice Over----");
            System.out.println("1.View Created Courses\n2. Create New Course\n0. Log Out");
            String creatorOperationOption = CustomScanner.getOptions("120");
            switch (creatorOperationOption) {
                case "1":
                    viewCreatedCourse();
                    break;
                case "2":
                    createCourse();
                    break;
                case "0":
                    System.out.println("Logged Out!");
                    SessionHandler.logOutUser();
                    break label;
            }
        }
    }
    //********* View Created Course ************************************************************************
    private void viewCreatedCourse(){
        ArrayList<Course> myCourses = currentCreator.getCreatedCourse();
        if (myCourses.size() != 0) {
            System.out.println("-----Created Courses-----");
            int courseNumber = 1;
            for (Course tempCourse : myCourses) {
                System.out.println("[" + courseNumber + "] " + tempCourse.getCourseName());
                courseNumber++;
            }
            System.out.println();
            System.out.println("0. back");
            int courseIndex = CustomScanner.getIntegerInput(0, myCourses.size());
            if(courseIndex==0) return;
            openCourse(myCourses.get(courseIndex-1));
        } else {
            System.out.println("You have not Created any Course,Yet!");
        }
    }

    //*********** open Course ******************************
    private void openCourse(Course currCourse){
        label:
        while (true) {
            System.out.println("+++++++" + currCourse.getCourseId() + "+++++++");
            System.out.println("1. View Course\n2. Edit Course\n3. View Comments\n4. Delete Course\n0. Back");
            String courseOperation = CustomScanner.getOptions("12340");
            switch (courseOperation) {
                case "1":
                    System.out.println("+++++++" + currCourse.getCourseId() + "+++++++");
                    System.out.println("Course Name :" + currCourse.getCourseName());
                    System.out.println("Creator :" + dataManager.getCreatorName(currCourse.getCreatorId()));
                    System.out.println("Rating :" + currCourse.getRating());
                    if (currCourse.getPrice() == 0) System.out.println("Price: Free");
                    else System.out.println("Price :" + currCourse.getPrice());
                    System.out.println("-What You'll Learn-");
                    ArrayList<String> learnings = dataManager.getCourseLearnings(currCourse.getCourseId());
                    for (int i = 1; i <= learnings.size(); i++) {
                        System.out.println("[" + i + "] " + learnings.get(i - 1));
                    }
                    break;
                case "2":
                    editCourse(currCourse.getCourseId());
                    break;
                case "3":
                    viewComments(currCourse.getCourseId());
                    break;
                case "4":
                    currentCreator.deleteCourse(currCourse.getCourseId());
                    System.out.println("Course Deleted!!");
                    break label;
                case "0":
                    break label;
            }
        }
    }

    //*********** Edit Course ******************************
    private void editCourse(String courseId){
        System.out.println("*****Edit Course*****");
        System.out.println("1. Change Course Name\n2. Add/Remove Category\n3. Change Price\n4. Edit Content\n0. Back");
        String editOption = CustomScanner.getOptions("12340");
        switch (editOption) {
            case "1":
                changeCourseName(courseId);
                break;
            case "2":
                editCategory(courseId);
                break;
            case "3":
                editCoursePrice(courseId);
                break;
            case "4":
                editCourseContent(courseId);
                break;
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
            ArrayList<String> availableCategories = dataManager.getCategories();
            ArrayList<String> currCourseCategories = dataManager.getCourseCategories(courseId);
            for(String category:currCourseCategories){
                availableCategories.remove(category);
            }
            System.out.print("Course Category :  ");
            for(int i=0;i<currCourseCategories.size();i++){
                System.out.print(currCourseCategories.get(i));
                if(i!=currCourseCategories.size()-1) System.out.print(", ");
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
                        int categoryIndex = CustomScanner.getIntegerInput(1, availableCategories.size());
                        String catergoryToadd = availableCategories.get(categoryIndex - 1);
                        currentCreator.addCourseCategory(catergoryToadd, courseId);
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
                        int categoryIndex = CustomScanner.getIntegerInput(1, currCourseCategories.size());
                        String categoryToRemove = currCourseCategories.get(categoryIndex-1);
                        currentCreator.removeCourseCategory(categoryToRemove,courseId);
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
        System.out.println("Enter New Price");
        int coursePrice = CustomScanner.getIntegerInput(0,100000);
        currentCreator.changeCoursePrice(coursePrice,courseId);
    }

    private void editCourseContent(String courseId){
        label:
        while (true) {
            System.out.println("********Edit Course Content******");
            System.out.println("[A]dd [D]elete [E]dit [B]ack");
            String inputOptions = CustomScanner.getOptions("aAdDeEbB");
            switch (inputOptions) {
                case "a":
                case "A":
                    int lessonNumber = dataManager.getCourseChapterCount(courseId);
                    currentCreator.addCourseContent(courseId, getNewChapter(lessonNumber + 1));
                    break;
                case "d":
                case "D":
                    currentCreator.deleteCourseContent(courseId, getContentIndex(courseId));
                    break;
                case "e":
                case "E":
                    int lessonNo = getContentIndex(courseId);
                    Chapter selectedChapter = dataManager.getChapter(courseId, lessonNo);
                    System.out.println("1. Change Chapter Name\n2. Change Content\n0. back");
                    String editOption = CustomScanner.getOptions("120");
                    if (editOption.equals("1")) {
                        System.out.println("Current Chapter Name : " + selectedChapter.getChapterName());
                        System.out.println("Enter New Chapter Name");
                        String newChapterName = new Scanner(System.in).nextLine();
                        currentCreator.changeCourseChapterName(newChapterName, courseId, lessonNo);
                    } else if (editOption.equals("2")) {
                        System.out.println("***** Current Lesson ***** \n" + selectedChapter.getLesson());
                        System.out.println("**************************");
                        System.out.println("Enter New Content");
                        String newContent = CustomScanner.getMultiLineInput();
                        currentCreator.changeCourseChapterContent(newContent, courseId, lessonNo);

                    }
                    break;
                case "b":
                case "B":
                    break label;
            }
        }

    }
    private int getContentIndex(String courseId) {
        ArrayList<Chapter> chapters = dataManager.getCourseContent(courseId);
        for(int i=1;i<=chapters.size();i++){
            System.out.println("["+i+"] "+chapters.get(i-1).getChapterName());
        }
        return CustomScanner.getIntegerInput(1, chapters.size());
    }
    //******************************************************
    private void viewComments(String courseId) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("***********Comment Page*************");
            ArrayList<Comment> comments = currentCreator.getCourseComments(courseId);
            for (Comment comment : comments) {
                System.out.println(dataManager.getLearnerName(comment.getCommentor()) + " : " + comment.getComment());
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

    //******* Create New Course ***************************************************************************
    private void createCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Course Name");
        String courseName = sc.nextLine();
        ArrayList<String> selectedCategories = getNewCourseCategories();
        if(selectedCategories.size()==0){
            System.out.println("No Categories Available for courses now.Currently Cant able to create any course.\nContact Application Admin!");
            return;
        }
        ArrayList<Chapter> courseContent = getCourseChapters();
        System.out.println("Enter Course Price");
        int coursePrice = CustomScanner.getIntegerInput(0, 100000);
        currentCreator.addNewCourse(courseName,selectedCategories,courseContent,coursePrice);
        System.out.println("Course Created Successfully!!");
    }

    private ArrayList<String> getNewCourseCategories(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> selectedCategories = new ArrayList<>();
        ArrayList<String> availableCategories = dataManager.getCategories();
        if(availableCategories.size()==0) return selectedCategories;
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
                int categoryIndex = CustomScanner.getIntegerInput(0, availableCategories.size());
                selectedCategories.add(availableCategories.get(categoryIndex-1));
                availableCategories.remove(categoryIndex-1);
            }else if(selectedCategories.size()!=0 && (operationChoice.equals("D") || operationChoice.equals("d"))){
                for(int ind=1;ind<=selectedCategories.size();ind++){
                    System.out.println("["+ind+"] "+selectedCategories.get(ind-1));
                }
                int categoryIndex = CustomScanner.getIntegerInput(1, selectedCategories.size());
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
                chapters.add(getNewChapter(chapters.size()+1));
            }else if (chapters.size()!=0 && (options.equals("c") || options.equals("C"))){
                isConfirm = true;
            }
        }
        return  chapters;
    }
    private Chapter getNewChapter(int lessonNumber) {
        Scanner sc = new Scanner(System.in);
        String chapterName;
        String lesson;
        System.out.println("Enter Chapter Name");
        chapterName = sc.nextLine();
        System.out.println("Enter Chapter Content");
        lesson = CustomScanner.getMultiLineInput();
        return new Chapter(chapterName,lesson,null,lessonNumber);
    }
}
