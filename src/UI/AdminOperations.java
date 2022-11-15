package UI;

import Core.Course.Chapter;
import Core.Course.Course;
import Core.Users.Admin;
import Managers.UIManager;
import Utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminOperations {
    private final Admin currentAdmin;
    private final UIManager uiManager = new UIManager();

    AdminOperations(Admin admin){
        this.currentAdmin = admin;
    }

    public void adminDashBoard(){
        while(true){
            System.out.println("********* Zlearn Control Center *********");
            System.out.println("1. User Control\n2. Courses Control\n3. Zlearn Courses\n0. Log out");
            String controlOption = CustomScanner.getOptions("1230");
            if(controlOption.equals("1")){
                openUserControl();
            }
            if(controlOption.equals("2")){
                openCourseControl();
            }
            if(controlOption.equals("3")){
                openZlearnCoursesControl();
            }
            if(controlOption.equals("0")){
                break;
            }
        }

    }

    private void openZlearnCoursesControl() {
        while (true) {
            System.out.println("***** Zlearn Courses *****");
            System.out.println("1. View Courses\n2. Create Course\n3. Edit Course\n0. back");
            String options = CustomScanner.getOptions("1230");
            if (options.equals("1")) {
                ArrayList<Course> courses = uiManager.getAllCourses();
                ArrayList<Course> filteredCourses = new ArrayList<>();
                for(Course course : courses){
                    if(course.getCourseId().contains("ZCourse")) filteredCourses.add(course);
                }
                for(int i=1;i<=filteredCourses.size();i++){
                    System.out.println("["+i+"] "+filteredCourses.get(i-1).getCourseId()+"  "+filteredCourses.get(i-1).getCourseName());
                }
                int courseIndex = CustomScanner.getIntegetInput(1,filteredCourses.size()) - 1;
                viewCourseDetails(filteredCourses.get(courseIndex));
            }
            if (options.equals("2")) {
                createZCourse();
            }
            if(options.equals("3")){
                ArrayList<Course> courses = uiManager.getAllCourses();
                ArrayList<Course> filteredCourses = new ArrayList<>();
                for(Course course : courses){
                    if(course.getCourseId().contains("ZCourse")) filteredCourses.add(course);
                }
                for(int i=1;i<=filteredCourses.size();i++){
                    System.out.println("["+i+"] "+filteredCourses.get(i-1).getCourseId()+"  "+filteredCourses.get(i-1).getCourseName());
                }
                int courseIndex = CustomScanner.getIntegetInput(1,filteredCourses.size()) - 1;
                editZLearnCourses(filteredCourses.get(courseIndex).getCourseId());

            }
            if (options.equals("0")) {break;
            }
        }
    }

    private void editZLearnCourses(String courseId){
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
        currentAdmin.changeCourseName(newCourseName,courseId);
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
                                currentAdmin.addCourseCategory(catergoryToadd,courseId);
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
                                currentAdmin.removeCourseCategory(categoryToRemove,courseId);
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
                currentAdmin.changeCoursePrice(coursePrice,courseId);
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
                currentAdmin.addCourseContent(courseId,getNewChapter());
            }
            if (inputOptions.equals("d") || inputOptions.equals("D")) {
                currentAdmin.deleteCourseContent(courseId,getContentIndex(courseId));
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
                    currentAdmin.changeCourseChapterName(newChapterName,courseId,contentIndex);
                }
                if(editOption.equals("2")){
                    System.out.println("***** Current Lesson ***** \n"+selectedChapter.getLesson());
                    System.out.println("**************************");
                    System.out.println("Enter New Content");
                    String newContent = CustomScanner.getMultiLineInput();
                    currentAdmin.changeCourseChapterContent(newContent,courseId,contentIndex);

                }
            }
            if(inputOptions.equals("b") || inputOptions.equals("B")){
                break;
            }
        }

    }

    private void createZCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Course Name");
        String courseName = sc.nextLine();
        ArrayList<String> selectedCategories = getNewCourseCategories();
        ArrayList<Chapter> courseContent = getCourseChapters();
        System.out.println("Enter Course Price");
        int coursePrice = CustomScanner.getIntegetInput(0,100000);
        currentAdmin.addNewCourse(courseName,selectedCategories,courseContent,coursePrice);
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
    private int getContentIndex(String courseId) {
        ArrayList<Chapter> chapters = uiManager.getCourseDetails(courseId).getContent();
        for(int i=1;i<=chapters.size();i++){
            System.out.println("["+i+"] "+chapters.get(i-1).getChapterName());
        }
        return CustomScanner.getIntegetInput(1,chapters.size()) - 1;
    }


    private void openCourseControl() {
        while(true) {
            System.out.println("1. View All Course\n2. Add Course Category\n3. Remove Course Category\n4. Delete Course\n0. Back");
            String inputOption = CustomScanner.getOptions("12340");
            if(inputOption.equals("1")){
                ArrayList<Course> courses = uiManager.getAllCourses();
                ArrayList<Course> filteredCourses = new ArrayList<>();
                for(Course course : courses){
                    if(!course.getCourseId().contains("ZCourse")) filteredCourses.add(course);
                }
                for(int i=1;i<=filteredCourses.size();i++){
                    System.out.println("["+i+"] "+filteredCourses.get(i-1).getCourseId()+"  "+filteredCourses.get(i-1).getCourseName());
                }
                int courseIndex = CustomScanner.getIntegetInput(1,filteredCourses.size()) - 1;
                viewCourseDetails(filteredCourses.get(courseIndex));
            }
            if(inputOption.equals("2")){
                System.out.println("Enter new Category : ");
                String newCategory = new Scanner(System.in).nextLine();
                currentAdmin.addCategoryToAllCategories(newCategory);
                System.out.println("Category Added !!!");
            }
            if(inputOption.equals("3")){
                System.out.println("Select Category to Delete : ");
                ArrayList<String> categories = uiManager.getCategories();
                for(int i=1;i<=categories.size();i++){
                    System.out.println("["+i+"] "+categories.get(i-1));
                }
                int categoryIndex = CustomScanner.getIntegetInput(1,categories.size());
                currentAdmin.deleteCategoryFromAllCategories(categories.get(categoryIndex-1));
            }
            if(inputOption.equals("4")){
                System.out.println("Enter Course Id : ");
                String courseId = new Scanner(System.in).next();
                currentAdmin.removeCourse(courseId);
            }
            if(inputOption.equals("0")){ break; }
        }
    }

    private void viewCourseDetails(Course course) {
        System.out.println("****** " + course.getCourseName() + " *******");
        if(!course.getCreatorId().contains("Adm")) System.out.println("Creator :" + uiManager.getCreatorName(course.getCreatorId()));
        System.out.println("Rating :" + course.getRating());
        System.out.println("Price :" + (course.getPrice() == 0 ? "Free" : course.getPrice()));
        System.out.println("-What You'll Learn-");
        ArrayList<String> learnings = course.getCourseLearnings();
        for (int i = 1; i <= learnings.size(); i++) {
            System.out.println("[" + i + "] " + learnings.get(i - 1));
        }
        System.out.println("\n0. back");
        CustomScanner.getOptions("0");
    }

    private void openUserControl() {
        while(true) {
            System.out.println("******* User Control *******");
            System.out.println("- [R]emove User\n- change user [P]assword\n0. Back");
            String controlOption = CustomScanner.getOptions("rRpP0");
            if (controlOption.equals("r") || controlOption.equals("R")) {
                System.out.println("Remove [L]earner\nRemove [C]reator");
                String userType = CustomScanner.getOptions("LlCc");
                if(userType.equals("L") || userType.equals("l")){
                    removeLearner();
                }
                if(userType.equals("C") || userType.equals("c")){
                    removeCreator();
                }
            }
            if (controlOption.equals("p") || controlOption.equals("P")) {
                System.out.println("[L]earner password\n[C]reator password");
                String userType = CustomScanner.getOptions("LlCc");
                if(userType.equals("L") || userType.equals("l")){
                    changeLearnerPassword();
                }
                if(userType.equals("C") || userType.equals("c")){
                    changeCreatorPassword();
                }

            }
            if (controlOption.equals("0")) {
                break;
            }
        }
    }

    private void changeCreatorPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Creator UserName : ");
        String userName = sc.next();
        System.out.println("Enter New Password : ");
        String newPassword = sc.next();
        currentAdmin.changeCreatorPassword(newPassword,userName);
    }

    private void changeLearnerPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Learner UserName : ");
        String userName = sc.next();
        System.out.println("Enter New Password : ");
        String newPassword = sc.next();
        currentAdmin.changeLearnerPassword(newPassword,userName);
    }


    private void removeCreator() {
        System.out.println("Enter Creator UserName : ");
        String userName = new Scanner(System.in).next();
        currentAdmin.removeCreator(userName);
    }

    private void removeLearner() {
        System.out.println("Enter Learner UserName : ");
        String userName = new Scanner(System.in).next();
        currentAdmin.removeLearner(userName);
    }
}
