package ui;

import core.course.Chapter;
import core.course.Comment;
import core.course.Course;
import core.users.Creator;
import managers.DataManager;
import utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;

public final class CreatorOperations {
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
            System.out.println("1. View Created Courses\n2. Create New course\n0. Log Out");
            String creatorOperationOption = CustomScanner.getOptions("1","2","0");
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
    //********* View Created course ************************************************************************
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
            System.out.println("You have not Created any course,Yet!");
        }
    }

    //*********** open course ******************************
    private void openCourse(Course currCourse){
        label:
        while (true) {
            System.out.println("+++++++" + currCourse.getCourseId() + "+++++++");
            System.out.println("1. View course\n2. Edit course\n3. View Comments\n4. Delete course\n0. Back");
            String courseOperation = CustomScanner.getOptions("1","2","3","4","0");
            switch (courseOperation) {
                case "1":
                    viewCourseDetails(currCourse);
                    break;
                case "2":
                    editCourse(currCourse.getCourseId());
                    break;
                case "3":
                    viewComments(currCourse.getCourseId());
                    break;
                case "4":
                    currentCreator.deleteCourse(currCourse.getCourseId());
                    System.out.println("course Deleted!!");
                    break label;
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
                System.out.println("\n0. back");
                option = CustomScanner.getOptions("0");
            } else {
                System.out.println("\n0. back 1. view content");
                option = CustomScanner.getOptions("0", "1");
                if (option.equals("1")) {
                    viewCourseContent(course.getCourseId());
                }
            }
            if(option.equals("0")) openCourseDetailPage = false;
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

    //*********** Edit course ******************************
    private void editCourse(String courseId){
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
        currentCreator.changeCourseName(newCourseName,courseId);
        System.out.println("course Name Updated!!");
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
            if(currCourseCategories.size()==0) System.out.println("course Category : General");
            else {
                System.out.print("course Category :  ");
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
                        currentCreator.addCourseCategory(catergoryToadd, courseId);
                        if(currCourseCategories.contains("General"))  currentCreator.removeCourseCategory("General",courseId);

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
                        currentCreator.removeCourseCategory(categoryToRemove, courseId);
                        if (currCourseCategories.size() == 1) {
                            currentCreator.addCourseCategory("General", courseId);
                            System.out.println("course Category is set to General");
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

    private void editCoursePrice(String courseId){
        System.out.println("Enter New Price");
        int coursePrice = CustomScanner.getIntegerInput(0,100000);
        currentCreator.changeCoursePrice(coursePrice,courseId);
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
                    int lessonNumber = dataManager.getCourseChapterCount(courseId);
                    Chapter newChapter = getNewChapter();
                    newChapter.setLessonNo(lessonNumber+1);
                    currentCreator.addCourseContent(courseId, newChapter);
                    System.out.println("Content Added!");
                    break;
                case "e":
                case "E":
                    int lessonNo = getContentIndex(courseId);
                    if(lessonNo!=-1) {
                        Chapter selectedChapter = dataManager.getChapter(courseId, lessonNo);
                        System.out.println("1. Change Chapter Name\n2. Change Content\n0. back");
                        String editOption = CustomScanner.getOptions("1", "2", "0");
                        if (editOption.equals("1")) {
                            System.out.println("Current Chapter Name : " + selectedChapter.getChapterName());
                            System.out.println("Enter New Chapter Name");
                            String newChapterName = CustomScanner.getNameInput();
                            currentCreator.changeCourseChapterName(newChapterName, courseId, lessonNo);
                            System.out.println("course Name Changed, Successfully!");
                        } else if (editOption.equals("2")) {
                            System.out.println("***** Current Lesson ***** \n" + selectedChapter.getLesson());
                            System.out.println("**************************");
                            System.out.println("Enter New Content");
                            String newContent = CustomScanner.getMultiLineInput();
                            currentCreator.changeCourseChapterContent(newContent, courseId, lessonNo);
                            System.out.println("course Content Changed, Successfully!");
                        }
                    }else {
                        System.out.println("There is no Content to edit");
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
        if(chapters.size()==0) return -1;
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

    //******* Create New course ***************************************************************************
    private void createCourse() {
        //course name
        System.out.println("Enter Your course Name");
        String courseName = CustomScanner.getNameInput();

        //course Category
        ArrayList<String> selectedCategories = getNewCourseCategories();
        if(selectedCategories == null) return;
        if(selectedCategories.size()==0){
            System.out.println("No Categories Available for courses.So,course Category is set to General");
        }

        //course Chapters
        ArrayList<Chapter> courseContent = getCourseChapters();
        if(courseContent==null) return;

        //course Price
        System.out.println("Enter course Price");
        int coursePrice = CustomScanner.getIntegerInput(0, 100000);

        currentCreator.addNewCourse(courseName,selectedCategories,courseContent,coursePrice);
        System.out.println("course Created Successfully!!");
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
            System.out.print("[C]onfirm [B]ack");
            System.out.println();
            String operationChoice = sc.nextLine();
            if(availableCategories.size()!=0 && (operationChoice.equalsIgnoreCase("a"))){
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
                System.out.println("Invalid Input!!");
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
}
