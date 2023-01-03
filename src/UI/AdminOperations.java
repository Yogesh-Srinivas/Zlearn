package UI;

import Core.Course.Chapter;
import Core.Course.Course;
import Core.Users.Admin;
import Managers.DataManager;
import Managers.SessionHandler;
import Utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdminOperations {
    private final Admin currentAdmin;
    private final DataManager dataManager = new DataManager();


    //*********** Constructor *************************************************************
    public AdminOperations(Admin admin){
        this.currentAdmin = admin;
    }


    //*********** Dashboard *************************************************************
    public void adminDashBoard(){
        label:
        while(true){
            System.out.println("********* Zlearn Control Center *********");
            System.out.println("1. User Control\n2. Courses Control\n3. Zlearn Courses\n0. Log out");
            String controlOption = CustomScanner.getOptions("1","2","3","0");
            switch (controlOption) {
                case "1":
                    openUserControl();
                    break;
                case "2":
                    openCourseControl();
                    break;
                case "3":
                    openZlearnCoursesControl();
                    break;
                case "0":
                    System.out.println("Logged Out!");
                    SessionHandler.logOutUser();
                    break label;
            }
        }

    }

    //*************** User Control  *************************************************************

    private void openUserControl() {
        label:
        while(true) {
            System.out.println("******* User Control *******");
            System.out.println("- [R]emove User\n- reset user [P]assword\n0. Back");
            String controlOption = CustomScanner.getOptions("r","R","p","P","0");
            switch (controlOption) {
                case "r":
                case "R": {
                    System.out.println("Remove [L]earner\nRemove [C]reator");
                    String userType = CustomScanner.getOptions("l","L","c","C");
                    if (userType.equals("L") || userType.equals("l")) {
                        removeLearner();
                    } else if (userType.equals("C") || userType.equals("c")) {
                        removeCreator();
                    }
                    break;
                }
                case "p":
                case "P": {
                    System.out.println("[L]earner password\n[C]reator password");
                    String userType = CustomScanner.getOptions("l","L","c","C");
                    if (userType.equals("L") || userType.equals("l")) {
                        resetLearnerPassword();
                    } else if (userType.equals("C") || userType.equals("c")) {
                       resetCreatorPassword();
                    }

                    break;
                }
                case "0":
                    break label;
            }
        }
    }
    //******************************************************
    private void removeCreator() {
        System.out.println("Enter Creator UserName : ");
        String userName = CustomScanner.getNameInput();
        if(currentAdmin.removeCreator(userName)) System.out.println("Creator Removed Successfully!!");
        else System.out.println("Creator doesn't exist!!");
    }
    private void removeLearner() {
        System.out.println("Enter Learner UserName : ");
        String userName = CustomScanner.getNameInput();
        if(currentAdmin.removeLearner(userName)) System.out.println("Learner Removed Successfully!!");
        else System.out.println("Learner doesn't exist!!");
    }
    //******************************************************
    private void resetCreatorPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Creator UserName : ");
        String userName = sc.nextLine();
        System.out.println("Enter the reset Password : ");
        String newPassword = getPassword();
        if(currentAdmin.changeCreatorPassword(newPassword,userName)) System.out.println("Password Resetted Successfully!!");
        else System.out.println("Creator doesn't Exist!!");
    }
    private void resetLearnerPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Learner UserName : ");
        String userName = sc.nextLine();
        System.out.println("Enter the reset Password : ");
        String newPassword = getPassword();

        if(currentAdmin.changeLearnerPassword(newPassword,userName)) System.out.println("Password Resetted Successfully!!");
        else System.out.println("Learner doesn't Exist!!");
    }

    private String getPassword(){
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();
        while (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()_!])(?=\\S+$).{8,20}$", password)){
            System.out.println("Password should contain atleast an UpperCase letter,a LowerCase letter,a Digit,a Special Character and\nPassword length should be between 8 and 20. Spaces not allowed.");
            System.out.println("Enter New Password : ");
            password = sc.nextLine();
        }
        return password;
    }

    //*************** Course Control  *************************************************************
    private void openCourseControl() {
        label:
        while(true) {
            System.out.println("**************  Course Control *************");
            System.out.println("1. View All Category\n2. Add Course Category\n3. Remove Course Category\n4. View All Course\n5. Delete Course\n0. Back");
            String inputOption = CustomScanner.getOptions("1","2","3","4","5","0");
            switch (inputOption) {
                case "1":
                    ArrayList<String> courseCategories = dataManager.getCategories();
                    if(courseCategories.size()==0){
                        System.out.println("There is no Category to show");
                    }else {
                        for (int i = 1; i <= courseCategories.size(); i++) {
                            System.out.println("[" + i + "] " + courseCategories.get(i - 1));
                        }
                        System.out.println();
                        System.out.println("0. back");
                        CustomScanner.getOptions("0");
                        continue;
                    }
                    break;
                case "2":
                    System.out.println("Enter new Category : ");
                    String newCategory = CustomScanner.getNameInput();
                    currentAdmin.addCategoryToAllCategories(newCategory);
                    System.out.println("Category Added.");
                    break;
                case "3":
                    ArrayList<String> categories = dataManager.getCategories();
                    if(categories.size()==0){
                        System.out.println("There is no Category to edit");
                    }else {
                        System.out.println("Select Category to Delete : ");
                        for (int i = 1; i <= categories.size(); i++) {
                            System.out.println("[" + i + "] " + categories.get(i - 1));
                        }
                        System.out.println();
                        System.out.println("0. back");
                        int categoryIndex = CustomScanner.getIntegerInput(0, categories.size());
                        if (categoryIndex == 0) continue;
                        currentAdmin.deleteCategoryFromAllCategories(categories.get(categoryIndex - 1));
                        System.out.println("Category Deleted.");
                    }
                    break;
                case "4":
                    ArrayList<Course> courses = dataManager.getAllCourses();
                    ArrayList<Course> filteredCourses = new ArrayList<>();
                    for (Course course : courses) {
                        if (!course.getCourseId().contains("ZCourse")) filteredCourses.add(course);
                    }
                    if (filteredCourses.size() != 0) {
                        for (int i = 1; i <= filteredCourses.size(); i++) {
                            System.out.println("[" + i + "] " + filteredCourses.get(
                                    i - 1).getCourseId() + "  " + filteredCourses.get(i - 1).getCourseName());
                        }
                        System.out.println();
                        System.out.println("0. back");
                        int courseIndex = CustomScanner.getIntegerInput(0, filteredCourses.size());
                        if (courseIndex == 0) continue;
                        viewCourseDetails(filteredCourses.get(courseIndex - 1));
                    } else {
                        System.out.println("No Courses Available, Now!");
                    }
                    break;
                case "5":
                    System.out.println("Enter Course Id : ");
                    String courseId = new Scanner(System.in).nextLine();
                    if (!courseId.contains("ZCourse")) {
                        if (currentAdmin.deleteCourse(courseId)) {
                            System.out.println("Course Removed Successfully!!");
                        } else System.out.println("Course doesn't exist,give valid course Id.");
                    } else System.out.println("ZCourses cannot be deleted from here.");
                    break;
                case "0":
                    break label;
            }
        }
    }
    private void viewCourseDetails(Course course) {
        System.out.println("****** " + course.getCourseName() + " *******");
        if (!course.getCourseId().contains("ZCourse")) System.out.println("Creator :" + dataManager.getCreatorName(course.getCreatorId()));
        System.out.println("Rating :" + course.getRating());
        System.out.println("Price :" + (course.getPrice() == 0 ? "Free" : course.getPrice()));
        System.out.println("-What You'll Learn-");
        ArrayList<String> learnings = dataManager.getCourseLearnings(course.getCourseId());
        if (learnings.size() > 0) {
            for (int i = 1; i <= learnings.size(); i++) {
                System.out.println("[" + i + "] " + learnings.get(i - 1));
            }
        }else {
            System.out.println("This Course Doesn't have any Content.\n");
        }
        System.out.println("\n0. back");
        CustomScanner.getOptions("0");
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
            if(currCourseCategories.size()==0) System.out.println("Course Category : General");
            else {
                System.out.print("Course Category :  ");
                for (int i = 0; i < currCourseCategories.size(); i++) {
                    System.out.print(currCourseCategories.get(i));
                    if (i != currCourseCategories.size() - 1) System.out.print(", ");
                }
            }
            System.out.println();
            System.out.println();
            if(availableCategories.size()!=0) System.out.print("1. Add ");
            if(currCourseCategories.size() > 0) System.out.print("2. Delete ");
            System.out.println("0. back");
            boolean isValidEditOperation = false;
            while (!isValidEditOperation) {
                String editOperation = sc.nextLine();
                if (editOperation.equals("1") && availableCategories.size() > 0) {
                    isValidEditOperation = true;
                    int categoryCount = 0;
                    System.out.println("Choose Category");

                    if (availableCategories.size() > 0) {
                        for (String category : availableCategories) {
                            System.out.println("[" + (++categoryCount) + "] " + category);
                        }
                        int categoryIndex = CustomScanner.getIntegerInput(1, availableCategories.size());
                        String catergoryToadd = availableCategories.get(categoryIndex - 1);
                        currentAdmin.addCourseCategory(catergoryToadd, courseId);
                        if(currCourseCategories.contains("General"))  currentAdmin.removeCourseCategory("General",courseId);

                    }
                } else if (editOperation.equals("2") && currCourseCategories.size() > 0) {
                    isValidEditOperation = true;
                    int categoryCount = 0;
                    System.out.println("Choose Category");
                    if(currCourseCategories.size()==1 && currCourseCategories.contains("General")){
                        System.out.println("There No Category to delete.");
                    } else if (currCourseCategories.size() > 0) {
                        for (String category : currCourseCategories) {
                            System.out.println("[" + (++categoryCount) + "] " + category);
                        }
                        int categoryIndex = CustomScanner.getIntegerInput(1, currCourseCategories.size());
                        String categoryToRemove = currCourseCategories.get(categoryIndex - 1);
                        currentAdmin.removeCourseCategory(categoryToRemove, courseId);
                        if (currCourseCategories.size() == 1) {
                            currentAdmin.addCourseCategory("General", courseId);
                            System.out.println("Course Category is set to General");
                        }
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


    //*************** ZCourse Control  *************************************************************

    private void openZlearnCoursesControl() {
        label:
        while (true) {
            System.out.println("***** Zlearn Courses *****");
            System.out.println("1. View Courses\n2. Create Course\n3. Edit Course\n4. Delete ZCourse\n0. back");
            String options = CustomScanner.getOptions("1","2","3","4","0");
            switch (options) {
                case "1": {
                    ArrayList<Course> courses = dataManager.getAllCourses();
                    ArrayList<Course> filteredCourses = new ArrayList<>();
                    for (Course course : courses) {
                        if (course.getCourseId().contains("ZCourse")) filteredCourses.add(course);
                    }
                    if (filteredCourses.size() != 0) {
                        for (int i = 1; i <= filteredCourses.size(); i++) {
                            System.out.println("[" + i + "] " + filteredCourses.get(
                                    i - 1).getCourseId() + "  " + filteredCourses.get(i - 1).getCourseName());
                        }
                        System.out.println();
                        System.out.println("0. back");
                        int courseIndex = CustomScanner.getIntegerInput(0, filteredCourses.size());
                        if (courseIndex == 0) continue;
                        viewCourseDetails(filteredCourses.get(courseIndex - 1));
                    } else System.out.println("No ZCourses are here!");
                    break;
                }
                case "2":
                    createZCourse();
                    break;
                case "3": {
                    ArrayList<Course> courses = dataManager.getAllCourses();
                    ArrayList<Course> filteredCourses = new ArrayList<>();
                    for (Course course : courses) {
                        if (course.getCourseId().contains("ZCourse")) filteredCourses.add(course);
                    }
                    if (filteredCourses.size() != 0) {
                        for (int i = 1; i <= filteredCourses.size(); i++) {
                            System.out.println("[" + i + "] " + filteredCourses.get(
                                    i - 1).getCourseId() + "  " + filteredCourses.get(i - 1).getCourseName());
                        }
                        System.out.println();
                        System.out.println("0. back");
                        int courseIndex = CustomScanner.getIntegerInput(0, filteredCourses.size());
                        if (courseIndex == 0) continue;
                        editZLearnCourses(filteredCourses.get(courseIndex - 1).getCourseId());
                    } else System.out.println("No ZCourses are here!");

                    break;
                }
                case "4":
                    System.out.println("Enter Course Id : ");
                    String courseId = new Scanner(System.in).nextLine();
                    if (courseId.contains("ZCourse")) {
                        if (currentAdmin.deleteCourse(courseId)) {
                            System.out.println("Course Removed Successfully!!");
                        } else System.out.println("Course doesn't exist,give valid course Id.");
                    } else System.out.println("Other courses cannot be deleted from here.");
                    break;
                case "0":
                    break label;
            }
        }
    }
    //******************************************************
    private void createZCourse() {
        System.out.println("Enter Your Course Name");
        String courseName = CustomScanner.getNameInput();
        ArrayList<String> selectedCategories = getNewCourseCategories();
        if(selectedCategories.size()==0){
            System.out.println("No Categories Available for courses.So, Course category is set to General.");
        }
        ArrayList<Chapter> courseContent = getCourseChapters();
        System.out.println("Enter Course Price");
        int coursePrice = CustomScanner.getIntegerInput(0, 100000);
        currentAdmin.addNewCourse(courseName,selectedCategories,courseContent,coursePrice);
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
            if(selectedCategories.size() != 0) System.out.print("[D]elete ");
            System.out.print("[C]onfirm");
            System.out.println();
            String operationChoice = sc.nextLine();
            if(availableCategories.size()!=0 && (operationChoice.equals("A") || operationChoice.equals("a"))){
                for(int ind=1;ind<=availableCategories.size();ind++){
                    System.out.println("["+ind+"] "+availableCategories.get(ind-1));
                }
                int categoryIndex = CustomScanner.getIntegerInput(1, availableCategories.size());
                selectedCategories.add(availableCategories.get(categoryIndex-1));
                availableCategories.remove(categoryIndex-1);
            }else if(selectedCategories.size()!=0 && (operationChoice.equals("D") || operationChoice.equals("d"))){
                for(int ind=1;ind<=selectedCategories.size();ind++){
                    System.out.println("["+ind+"] "+selectedCategories.get(ind-1));
                }
                int categoryIndex = CustomScanner.getIntegerInput(1, selectedCategories.size());
                availableCategories.add(selectedCategories.get(categoryIndex-1));
                selectedCategories.remove(categoryIndex-1);
            }else if(operationChoice.equals("C") || operationChoice.equals("c")){
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
            String options = sc.nextLine();

            if(options.equals("A") || options.equals("a")){
                chapters.add(getNewChapter(chapters.size()+1));
            }else if (chapters.size()!=0 && (options.equals("c") || options.equals("C"))){
                isConfirm = true;
            }else {
                System.out.println("Invalid input!!");
            }
        }
        return  chapters;
    }
    private Chapter getNewChapter(int lessonNumber) {
        String chapterName;
        String lesson;
        System.out.println("Enter Chapter Name");
        chapterName = CustomScanner.getNameInput();
        System.out.println("Enter Chapter Content");
        lesson = CustomScanner.getMultiLineInput();
        return new Chapter(chapterName,lesson,null,lessonNumber);
    }
    private int getContentIndex(String courseId) {
        ArrayList<Chapter> chapters = dataManager.getCourseContent(courseId);
        if(chapters.size()==0) return -1;
        for(int i=1;i<=chapters.size();i++){
            System.out.println("["+i+"] "+chapters.get(i-1).getChapterName());
        }
        return CustomScanner.getIntegerInput(1, chapters.size());
    }

    //******************************************************
    private void editZLearnCourses(String courseId){
        System.out.println("*****Edit Course*****");
        System.out.println("1. Change Course Name\n2. Add/Remove Category\n3. Change Price\n4. Edit Content\n0. Back");
        String editOption = CustomScanner.getOptions("1","2","3","4","0");
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
        System.out.println("Enter New Course Name");
        String newCourseName = CustomScanner.getNameInput();
        currentAdmin.changeCourseName(newCourseName,courseId);
        System.out.println("Course Name Updated!!");
    }
    private void editCoursePrice(String courseId){
        System.out.println("Enter New Price");
        int coursePrice = CustomScanner.getIntegerInput(0,100000);
        currentAdmin.changeCoursePrice(coursePrice,courseId);
    }
    private void editCourseContent(String courseId){
        label:
        while (true) {
            System.out.println("********Edit Course Content******");
            System.out.println("[A]dd [E]dit [B]ack");
            String inputOptions = CustomScanner.getOptions("a","A","e","E","b","B");

            switch (inputOptions) {
                case "a":
                case "A":
                    int lessonNumber = dataManager.getCourseChapterCount(courseId) + 1;
                    currentAdmin.addCourseContent(courseId, getNewChapter(lessonNumber));
                    System.out.println("Content Added!");
                    break;
                case "e":
                case "E":
                    int contentIndex = getContentIndex(courseId);
                    if(contentIndex==-1){
                        System.out.println("There is no content to edit.");
                    }else {
                        Chapter selectedChapter = dataManager.getChapter(courseId, contentIndex);
                        System.out.println("1. Change Chapter Name\n2. Change Content\n0. back");
                        String editOption = CustomScanner.getOptions("1", "2", "0");
                        if (editOption.equals("1")) {
                            System.out.println("Current Chapter Name : " + selectedChapter.getChapterName());
                            System.out.println("Enter New Chapter Name");
                            String newChapterName = CustomScanner.getNameInput();
                            currentAdmin.changeCourseChapterName(newChapterName, courseId, contentIndex);
                            System.out.println("Course Name Changed, Successfully!");
                        } else if (editOption.equals("2")) {
                            System.out.println("***** Current Lesson ***** \n" + selectedChapter.getLesson());
                            System.out.println("**************************");
                            System.out.println("Enter New Content");
                            String newContent = CustomScanner.getMultiLineInput();
                            currentAdmin.changeCourseChapterContent(newContent, courseId, contentIndex);
                            System.out.println("Course Content Changed, Successfully!");
                        }
                    }
                    break;
                case "b":
                case "B":
                    break label;
            }
        }

    }
}
