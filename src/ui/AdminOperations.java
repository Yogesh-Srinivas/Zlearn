package ui;

import core.course.Chapter;
import core.course.Comment;
import core.course.Course;
import core.users.Admin;
import managers.DataManager;
import utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class AdminOperations {
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
            System.out.println("********* Z-learn Control Center *********");
            System.out.println("1. User Control\n2. Courses Control\n3. Z-learn Courses\n0. Log out");
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
                    if (userType.equalsIgnoreCase("L")) {
                        removeLearner();
                    } else if (userType.equalsIgnoreCase("C")) {
                        removeCreator();
                    }
                    break;
                }
                case "p":
                case "P": {
                    System.out.println("[L]earner password\n[C]reator password");
                    String userType = CustomScanner.getOptions("l","L","c","C");
                    if (userType.equalsIgnoreCase("L")) {
                        resetLearnerPassword();
                    } else if (userType.equalsIgnoreCase("C")) {
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
        if(currentAdmin.changeCreatorPassword(newPassword,userName)) System.out.println("Password Changed Successfully!!");
        else System.out.println("Creator doesn't Exist!!");
    }
    private void resetLearnerPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Learner UserName : ");
        String userName = sc.nextLine();
        System.out.println("Enter the reset Password : ");
        String newPassword = getPassword();

        if(currentAdmin.changeLearnerPassword(newPassword,userName)) System.out.println("Password Changed Successfully!!");
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

    //*************** course Control  *************************************************************
    private void openCourseControl() {
        label:
        while(true) {
            System.out.println("**************  course Control *************");
            System.out.println("1. View All Category\n2. Add course Category\n3. Remove course Category\n4. View All course\n5. Delete course\n0. Back");
            String inputOption = CustomScanner.getOptions("1","2","3","4","5","0");
            switch (inputOption) {
                case "1":
                    ArrayList<String> courseCategories = dataManager.getCategories();
                    if(courseCategories.size()==0){
                        System.out.println("There is no Category to show");
                    }else {
                        System.out.println("******* ZLearn Categories *******");
                        System.out.println("[x] General");
                        for (int i = 1; i <= courseCategories.size(); i++) {
                            System.out.println(courseCategories.get(i - 1));
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
                    if(currentAdmin.addCategoryToAllCategories(newCategory)) System.out.println("Category Added.");
                    else System.out.println("This Category Can't be add.");
                    break;
                case "3":
                    ArrayList<String> categories = dataManager.getCategories();
                    if(categories.size()==0){
                        System.out.println("There is no Category to Delete");
                    }else {
                        System.out.println("Select Category to Delete : ");
                        System.out.println("[x] General");
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
                    System.out.println("Enter course Id : ");
                    String courseId = new Scanner(System.in).nextLine();
                    if (!courseId.contains("ZCourse")) {
                        if (currentAdmin.deleteCourse(courseId)) {
                            System.out.println("course Removed Successfully!!");
                        } else System.out.println("course doesn't exist,give valid course Id.");
                    } else System.out.println("ZCourses cannot be deleted from here.");
                    break;
                case "0":
                    break label;
            }
        }
    }
    private void viewCourseDetails(Course course) {
        boolean openCourseDetailPage = true;
        while (openCourseDetailPage) {
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
            } else {
                System.out.println("This course Doesn't have any Content.\n");
            }

            String option;
            if (learnings.size() == 0) {
                System.out.println("\n0. back 2. view Comments");
                option = CustomScanner.getOptions("0","2");
            } else {
                System.out.println("\n0. back 1. view Content 2. view Comments");
                option = CustomScanner.getOptions("0", "1", "2");
            }
            switch (option) {
                case "0":
                    openCourseDetailPage = false;
                    break;
                case "1":
                    viewCourseContent(course.getCourseId());
                    break;
                case "2":
                    viewComments(course.getCourseId());
                    break;
            }
        }

    }

    private void viewComments(String courseId) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("***********Comment Page*************");
            ArrayList<Comment> comments = currentAdmin.getCourseComments(courseId);
            if(comments.size()>0) {
                for (Comment comment : comments) {
                    String commenterName = dataManager.getLearnerName(comment.getCommenter());
                    if(commenterName.equals("NULL")) commenterName="Anonymous";
                    System.out.println(commenterName + " : " + comment.getComment());
                }
            }
            else {
                System.out.println("No comments were posted.");
            }
            System.out.println();
            System.out.println("0. back");
            String backOption = sc.nextLine();
            if(backOption.equals("0")){
                break;
            }else {
                System.out.println("Invalid option!!");
            }
        }
    }

    private void viewCourseContent(String courseId){
        Scanner sc = new Scanner(System.in);
        boolean loopControl = false;
        Course currCourse = dataManager.getCourseDetails(courseId);
        int chapterIndex = 1;
        while (!loopControl) {
            System.out.println("+++++++" + currCourse.getCourseId() + "+++++++");
            Chapter lesson = dataManager.getChapter(courseId, chapterIndex);
            System.out.println("Chapter : " + lesson.getChapterName());
            System.out.println();
            System.out.println("------- Lesson -------");
            System.out.println(lesson.getLesson());
            System.out.println();
            if (chapterIndex > 1 && chapterIndex < currCourse.getNumberOfChapters()) System.out.println("0. back  1. next  2. Exit");
            else if (chapterIndex == 1 && currCourse.getNumberOfChapters() > 1) {
                System.out.println("1. next 2. Exit");
            } else if (chapterIndex == 1 && currCourse.getNumberOfChapters() == 1) {
                System.out.println("2. Exit");
            } else if (chapterIndex == currCourse.getNumberOfChapters()) {
                System.out.println("0. back 2. Exit");
            }
            while (true) {
                String courseControl = sc.nextLine();
                if (courseControl.equals("0") && chapterIndex > 1) {
                    chapterIndex -= 1;
                    break;
                } else if (courseControl.equals("1") && chapterIndex  < currCourse.getNumberOfChapters()  && currCourse.getNumberOfChapters() > 1) {
                    chapterIndex += 1;
                    break;
                } else if (courseControl.equals("2")) {
                    loopControl = true;
                    break;
                } else {
                    System.out.println("Invalid input!!");
                }
            }
        }

    }


    //*************** ZCourse Control  *************************************************************
    private void openZlearnCoursesControl() {
        label:
        while (true) {
            System.out.println("***** Zlearn Courses *****");
            System.out.println("1. View Courses\n2. Create course\n3. Edit course\n4. Delete ZCourse\n0. back");
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
                    System.out.println("Enter course Id : ");
                    String courseId = new Scanner(System.in).nextLine();
                    if (courseId.contains("ZCourse")) {
                        if (currentAdmin.deleteCourse(courseId)) {
                            System.out.println("course Removed Successfully!!");
                        } else System.out.println("course doesn't exist,give valid course Id.");
                    } else System.out.println("Other courses cannot be deleted from here.");
                    break;
                case "0":
                    break label;
            }
        }
    }

    //******************************************************
    private void createZCourse() {
        //course Name
        System.out.println("Enter Your course Name");
        String courseName = CustomScanner.getNameInput();

        //course Category
        ArrayList<String> selectedCategories = getNewCourseCategories();
        if(selectedCategories == null) return;
        if(selectedCategories.size()==0){
            System.out.println("Course category is set to General.");
        }

        //course Content
        ArrayList<Chapter> courseContent = getCourseChapters();
        if(courseContent == null) return;
        //course Price
        System.out.println("Enter course Price");
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
            for (String selectedCategory : selectedCategories) {
                System.out.print(selectedCategory + ", ");
            }
            System.out.println("General(Default)");
            System.out.println();
            if(availableCategories.size() != 0) System.out.print("[A]dd ");
            if(selectedCategories.size() != 0) System.out.print("[D]elete ");
            System.out.print("[C]onfirm [B]ack");
            System.out.println();
            String operationChoice = sc.nextLine();
            if(availableCategories.size()!=0 && operationChoice.equalsIgnoreCase("A")){
                for(int ind=1;ind<=availableCategories.size();ind++){
                    System.out.println("["+ind+"] "+availableCategories.get(ind-1));
                }
                int categoryIndex = CustomScanner.getIntegerInput(1, availableCategories.size());
                selectedCategories.add(availableCategories.get(categoryIndex-1));
                availableCategories.remove(categoryIndex-1);
            }else if(selectedCategories.size()!=0 && operationChoice.equalsIgnoreCase("D")){
                for(int ind=1;ind<=selectedCategories.size();ind++){
                    System.out.println("["+ind+"] "+selectedCategories.get(ind-1));
                }
                System.out.println("\n\n0. back");
                int categoryIndex = CustomScanner.getIntegerInput(0, selectedCategories.size());
                if(categoryIndex!=0) {
                    availableCategories.add(selectedCategories.get(categoryIndex - 1));
                    selectedCategories.remove(categoryIndex - 1);
                }
            }else if(operationChoice.equalsIgnoreCase("C")){
                notConfirm = false;
            }else if(operationChoice.equalsIgnoreCase("b")){
                return null;
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
            System.out.println("*******Add course Content*********");
            System.out.println("Current Chapters :-");
            for (int i=1;i<=chapters.size();i++){
                System.out.println("["+i+"] "+chapters.get(i-1).getChapterName());
            }

            System.out.print("[A]dd ");
            if(chapters.size()!=0) System.out.print("[D]elete [C]onfirm ");
            System.out.print("[B]ack");
            System.out.println();
            String options = sc.nextLine();

            if(options.equalsIgnoreCase("A")){
                chapters.add(getNewChapter());
            }else if (chapters.size()!=0 && options.equalsIgnoreCase("c")){
                isConfirm = true;
            } else if (chapters.size()!=0 && options.equalsIgnoreCase("d")) {
                System.out.println("******* Delete Content *******");
                int contentNumber = 1;
                for (Chapter chapter : chapters) {
                    System.out.println("[" + contentNumber + "] " + chapter.getChapterName());
                    contentNumber++;
                }
                System.out.println();
                System.out.println("0. back");
                int courseIndex = CustomScanner.getIntegerInput(0, chapters.size());
                if(courseIndex!=0) chapters.remove(courseIndex-1);
            }else if(options.equalsIgnoreCase("b")){
                return null;
            }else {
                System.out.println("Invalid input!!");
            }
        }
        for(int i=1;i<=chapters.size();i++) chapters.get(i-1).setLessonNo(i);
        return  chapters;
    }
    private Chapter getNewChapter() {
        String chapterName;
        String lesson;
        System.out.println("Enter Chapter Name");
        chapterName = CustomScanner.getNameInput();
        System.out.println("Enter Chapter Content");
        lesson = CustomScanner.getMultiLineInput();
        return new Chapter(chapterName,lesson,null,-1);
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
        System.out.println("*****Edit course*****");
        System.out.println("1. Change course Name\n2. Add/Remove Category\n3. Change Price\n4. Edit Content\n0. Back");
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
        System.out.println("Enter New course Name");
        String newCourseName = CustomScanner.getNameInput();
        currentAdmin.changeCourseName(newCourseName,courseId);
        System.out.println("course Name Updated!!");
    }
    private void editCoursePrice(String courseId){
        System.out.println("Enter New Price");
        int coursePrice = CustomScanner.getIntegerInput(0,100000);
        currentAdmin.changeCoursePrice(coursePrice,courseId);
    }
    private void editCourseContent(String courseId){
        label:
        while (true) {
            System.out.println("********Edit course Content******");
            System.out.println("[A]dd [E]dit [B]ack");
            String inputOptions = CustomScanner.getOptions("a","A","e","E","b","B");

            switch (inputOptions) {
                case "a":
                case "A":
                    int lessonNumber = dataManager.getCourseChapterCount(courseId) + 1;
                    Chapter newChapter = getNewChapter();
                    newChapter.setLessonNo(lessonNumber);
                    currentAdmin.addCourseContent(courseId, newChapter);
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
                            System.out.println("course Name Changed, Successfully!");
                        } else if (editOption.equals("2")) {
                            System.out.println("***** Current Lesson ***** \n" + selectedChapter.getLesson());
                            System.out.println("**************************");
                            System.out.println("Enter New Content");
                            String newContent = CustomScanner.getMultiLineInput();
                            currentAdmin.changeCourseChapterContent(newContent, courseId, contentIndex);
                            System.out.println("course Content Changed, Successfully!");
                        }
                    }
                    break;
                case "b":
                case "B":
                    break label;
            }
        }

    }
    private void editCategory(String courseId){
        Scanner sc = new Scanner(System.in);
        boolean exitEdit = false;
        while(!exitEdit) {
            ArrayList<String> availableCategories = dataManager.getCategories();
            ArrayList<String> currCourseCategories = dataManager.getCourseCategories(courseId);
            currCourseCategories.remove("General");
            for(String category:currCourseCategories){
                availableCategories.remove(category);
            }

            System.out.print("course Category :  ");
            for (String currCourseCategory : currCourseCategories) {
                System.out.print(currCourseCategory + ", ");
            }
            System.out.print("General(Default)");
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
                    }
                } else if (editOperation.equals("2") && currCourseCategories.size() > 0) {
                    isValidEditOperation = true;
                    int categoryCount = 0;
                    System.out.println("Choose Category");
                    if(currCourseCategories.size() <= 0){
                        System.out.println("There No Category to delete.");
                    } else {
                        for (String category : currCourseCategories) {
                            System.out.println("[" + (++categoryCount) + "] " + category);
                        }
                        int categoryIndex = CustomScanner.getIntegerInput(1, currCourseCategories.size());
                        String categoryToRemove = currCourseCategories.get(categoryIndex - 1);
                        currentAdmin.removeCourseCategory(categoryToRemove, courseId);
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
}
