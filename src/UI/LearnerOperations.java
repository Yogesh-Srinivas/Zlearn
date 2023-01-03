package UI;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Users.Learner;
import Managers.DataManager;
import Managers.SessionHandler;
import Utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;

public class LearnerOperations {
    private final Learner currentLearner;
    private final DataManager dataManager = new DataManager();

    //******* Constructor ***************************************************************************

    public LearnerOperations(Learner learner){
        this.currentLearner = learner;
    }

    //**********************************************************************************
    public void learnerDashBoard(){
        label:
        while (true) {
            System.out.println("----Learning never exhausts the mind----");
            System.out.println("1. View Enrolled Courses\n2. Enroll new Course\n0. Log Out");
            String learnerOperationOption = CustomScanner.getOptions("1","2","0");
            switch (learnerOperationOption) {
                case "1":
                    viewEnrolledCourse();
                    break;
                case "2":
                    enrollNewCourse();
                    break;
                case "0":
                    System.out.println("Logged Out!!");
                    SessionHandler.logOutUser();
                    break label;
            }
        }
    }
    //*******View Enrolled Course ***************************************************************************
    private void viewEnrolledCourse() {
        if(currentLearner.getEnrolledCourses().size() != 0) {
            System.out.println("-----Enrolled Courses-----");
            int courseNumber = 1;
            for (String courseId : currentLearner.getEnrolledCourses()) {
                Course tempCourse = dataManager.getCourseDetails(courseId);
                System.out.println("["+courseNumber +"] "+tempCourse.getCourseName());
                courseNumber++;
            }
            System.out.println();
            System.out.println("0. Back");
            int courseOption = CustomScanner.getIntegerInput(0, currentLearner.getEnrolledCourses().size());
            if(courseOption==0) return;
            openCourse(currentLearner.getEnrolledCourses().get(courseOption-1));
        }else{
            //need to add back to this
            System.out.println("You have not enrolled any course Yet!");
        }
    }

    private void openCourse(String courseId) {
        label:
        while (true) {
            Course currCourse = dataManager.getCourseDetails(courseId);
            System.out.println("+++++++" + currCourse.getCourseName() + "+++++++");
            boolean isUserRated = dataManager.isRatedBy(currentLearner.getUserId(), courseId);
            String courseOperation;
            if (isUserRated) {
                System.out.println("1. Learn Course\n2. Course Details\n3. Comment Page\n4. Unenroll Course\n0. Back");
                courseOperation = CustomScanner.getOptions("1","2","3","4","0");
            } else {
                System.out.println(
                        "1. Learn Course\n2. Course Details\n3. Comment Page\n4. Unenroll Course\n5. Rate Course\n0. Back");
                courseOperation = CustomScanner.getOptions("1","2","3","4","5","0");
            }

            switch (courseOperation) {
                case "1":
                    startLearning(courseId);
                    break;
                case "2":
                    System.out.println("+++++++" + currCourse.getCourseId() + "+++++++");
                    System.out.println("Course Name :" + currCourse.getCourseName());
                    System.out.println("Creator :" + dataManager.getCreatorName(currCourse.getCreatorId()));
                    System.out.println("Rating :" + currCourse.getRating());
                    System.out.println("-What You'll Learn-");
                    ArrayList<String> learnings = dataManager.getCourseLearnings(courseId);
                    if (learnings.size() > 0) {
                        for (int i = 1; i <= learnings.size(); i++) {
                            System.out.println("[" + i + "] " + learnings.get(i - 1));
                        }
                    }else {
                        System.out.println("This Course Doesn't have any Content.\n");
                    }
                    System.out.println("\n0. back");
                    CustomScanner.getOptions("0");
                    break;
                case "3":
                    openCommentPage(courseId);
                    break;
                case "4":
                    currentLearner.unenrollCourse(courseId);
                    System.out.println("Course Unenrolled!!");
                    break label;
                case "5":
                    rateCourse(courseId, currCourse.getCourseName());
                    System.out.println("Rated Successfully!!");
                    break;
                case "0":
                    break label;
            }
        }


    }
    private void startLearning(String courseId) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> courseLearnings = dataManager.getCourseLearnings(courseId);
        if(courseLearnings.size()>0) {
            Course currCourse = dataManager.getCourseDetails(courseId);

            int chapterIndex = currentLearner.getCourseProgress(courseId)+1;
            boolean loopControl = false;
            while (!loopControl) {
                int userCurrentProgress = currentLearner.getCourseProgress(courseId)+1;
                double progressInPercent = (100.0 / currCourse.getNumberOfChapters()) * userCurrentProgress;
                System.out.println("+++++++" + currCourse.getCourseId() + "+++++++   [" + (int) progressInPercent + " %]");

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
                        if (userCurrentProgress < chapterIndex) currentLearner.updateCourseProgress(courseId);
                        break;
                    } else if (courseControl.equals("2")) {
                        loopControl = true;
                        break;
                    } else {
                        System.out.println("Invalid input!!");
                    }
                }
            }
        }else{
            System.out.println("\nThis Course has no Content to show.\n");
        }
    }
    private void openCommentPage(String courseId){
        Scanner sc = new Scanner(System.in);
        boolean closeCommentPage = false;
        while (!closeCommentPage) {
            boolean isCurrentUserCommented = false;

            ArrayList<Comment> courseComments = dataManager.getComments(courseId);
            for (Comment comment : courseComments) {
                if (comment.getCommentor().equals(currentLearner.getUserId())) {
                    isCurrentUserCommented = true;
                    break;
                }
            }
            System.out.println("          Comment Page");
            System.out.println("-------------------------------");
            if (courseComments.size() == 0) {
                System.out.println("No comments were posted.");
            }
                if (isCurrentUserCommented) {
                    System.out.println("++++++++++Your Comment+++++++++");
                    for (Comment comment : courseComments) {
                        if (comment.getCommentor().equals(currentLearner.getUserId())) {
                            System.out.println(" * " + comment.getComment());
                        }
                    }
                }
                System.out.println("-------------------------------");
                for (Comment comment : courseComments) {
                    if (!comment.getCommentor().equals(currentLearner.getUserId())) {
                        System.out.println(dataManager.getLearnerName(comment.getCommentor()) + " : " + comment.getComment());
                    }
                }
                System.out.println();
                System.out.println("1. Add Comment    0. Back");
                boolean isValidCommentPageOption = false;
                while (!isValidCommentPageOption) {
                    String commentPageOption = sc.nextLine();
                    if (commentPageOption.equals("1")) {
                        System.out.println("Enter Your Comment");
                        String comment = sc.nextLine();
                        currentLearner.addComment(comment, courseId);
                        isValidCommentPageOption = true;
                    } else if (commentPageOption.equals("0")) {
                        isValidCommentPageOption = true;
                        closeCommentPage = true;
                    } else {
                        System.out.println("Invalid Option!!!");
                    }
                }
        }
    }
    private void rateCourse(String courseId,String courseName){
        System.out.println("****Rate the Course - "+courseName+" ****");
        System.out.println("1.Poor 2.Bad 3.Good 4.Very Good 5.Excellent");
        int rating = CustomScanner.getIntegerInput(1, 5);
        currentLearner.rateCourse(courseId,rating);
    }

    //********* Enroll New Course *************************************************************************
    public void enrollNewCourse(){
        String selectedCourseId = showCategoriesToEnroll();
        if(selectedCourseId == null) return;
        if(!selectedCourseId.equals("No_Courses_Available")) {
            Course selectedCourse = dataManager.getCourseDetails(selectedCourseId);
            System.out.println("+++++++" + selectedCourse.getCourseId() + "+++++++");
            System.out.println("Course Name :" + selectedCourse.getCourseName());
            System.out.println("Creator :" + dataManager.getCreatorName(selectedCourse.getCreatorId()));
            System.out.println("Rating :" + selectedCourse.getRating());
            System.out.println("-What You'll Learn-");
            ArrayList<String> learnings = dataManager.getCourseLearnings(selectedCourseId);
            if (learnings.size() > 0) {
                for (int i = 1; i <= learnings.size(); i++) {
                    System.out.println("[" + i + "] " + learnings.get(i - 1));
                }
            }else {
                System.out.println("This Course Doesn't have any Content.\n");
            }
            System.out.println();
            System.out.println("\n1. Enroll  0. Back");
            String enrollOption = CustomScanner.getOptions("1","0");
            if(enrollOption.equals("1")) {
                currentLearner.enrollCourse(selectedCourseId);
                System.out.println("Course Enrolled Successfully!!");
            }
        }

    }
    private String showCategoriesToEnroll(){
        int categoryNumber = 0;
        ArrayList<String> courseCategories = dataManager.getCategories();

        if(courseCategories.size()==0){
            return showCoursesBasedOnCategory("General");
        }

        System.out.println("Select Course Category");
        for (String category : courseCategories){
            System.out.println("["+ (++categoryNumber)+"] "+category);
        }
        System.out.println();
        System.out.println("0. back");
        int categoryIndex = CustomScanner.getIntegerInput(0, courseCategories.size());
        if(categoryIndex==0) return null;
        return showCoursesBasedOnCategory(courseCategories.get(categoryIndex-1));
    }
    private String showCoursesBasedOnCategory(String category){
        ArrayList<Course> allCourses = dataManager.getCoursesBasedOnCategory(category);
        ArrayList<Course> userNotEnrolledCourses = new ArrayList<>();
        for(Course course:allCourses){
            if(!currentLearner.getEnrolledCourses().contains(course.getCourseId())){
                userNotEnrolledCourses.add(course);
            }
        }
        if(userNotEnrolledCourses.size() == 0){
            System.out.println("Sorry! No Courses Available for your Category!!");
        }else{
            System.out.println("****** "+category+" Courses *****");
            int courseNumber = 0;
            for(Course course:userNotEnrolledCourses){
                System.out.println("["+(++courseNumber)+"] "+course.getCourseName());
                System.out.println("Price: "+course.getPrice()+"      Author:"+ dataManager.getCreatorName(course.getCreatorId())+"      Rating: "+course.getRating());
                System.out.println();
            }
            System.out.println();
            System.out.println("0. back");
            int courseOption = CustomScanner.getIntegerInput(0, userNotEnrolledCourses.size());
            if(courseOption==0) return null;
            return userNotEnrolledCourses.get(courseOption-1).getCourseId();
        }
        return "No_Courses_Available";
    }


}
