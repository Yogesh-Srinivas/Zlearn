package UI;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Users.Learner;
import Managers.UIManager;
import Utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;

public class LearnerOperations {
    private final Learner currentLearner;
    private final UIManager uiManager = new UIManager();
    public LearnerOperations(Learner learner){
        this.currentLearner = learner;
    }
    public void learnerDashBoard(){
        while (true) {
            System.out.println("----Learning never exhausts the mind----");
            System.out.println("1. View Enrolled Courses\n2. Enroll new Course\n0. Log Out");
            String learnerOperationOption = CustomScanner.getOptions("120");
            if (learnerOperationOption.equals("1")) viewEnrolledCourse();
            if (learnerOperationOption.equals("2")) enrollNewCourse();
            if (learnerOperationOption.equals("0")) {
                System.out.println("Logged Out!!");
                break;
            }
        }
    }
    
    private void viewEnrolledCourse() {
        Scanner sc = new Scanner(System.in);
        if(currentLearner.getEnrolledCourses().size() != 0) {
            System.out.println("-----Enrolled Courses-----");
            int courseNumber = 1;
            for (String courseId : currentLearner.getEnrolledCourses()) {
                Course tempCourse = uiManager.getCourseDetails(courseId);
                System.out.println("["+courseNumber +"] "+tempCourse.getCourseName());
                courseNumber++;
            }
            System.out.println("0. Back");
            boolean isValidCourseOption = false;
            while (!isValidCourseOption){
                String courseOption = sc.next();
                if(courseOption.equals("0")) isValidCourseOption=true;
                else if (Integer.parseInt(courseOption) <= currentLearner.getEnrolledCourses().size()) {
                    int courseIndex = Integer.parseInt(courseOption)-1;
                    openCourse(currentLearner.getEnrolledCourses().get(courseIndex));
                    isValidCourseOption = true;
                }else{
                    System.out.println("Invalid Option");
                }
            }

        }else{
            //need to add back to this
            System.out.println("You have not enrolled any course Yet!");
        }
    }

    private void openCourse(String courseId) {
        while (true) {
            Course currCourse = uiManager.getCourseDetails(courseId);
            System.out.println("+++++++" + currCourse.getCourseName() + "+++++++");
            boolean isUserRated = currCourse.isRatedBy(currentLearner.getUserId());
            String courseOperation;
            if (isUserRated) {
                System.out.println("1. Start Learning\n2. Course Details\n3. Comment Page\n4. Unenroll Course\n0. Back");
                courseOperation = CustomScanner.getOptions("12340");
            } else {
                System.out.println(
                        "1. Start Learning\n2. Course Details\n3. Comment Page\n4. Unenroll Course\n5. Rate Course\n0. Back");
                courseOperation = CustomScanner.getOptions("123450");
            }

            if (courseOperation.equals("1")) {
                startLearning(courseId);
            }
            if (courseOperation.equals("2")) {
                System.out.println("+++++++" + currCourse.getCourseId() + "+++++++");
                System.out.println("Course Name :" + currCourse.getCourseName());
                System.out.println("Creator :" + uiManager.getCreatorName(currCourse.getCreatorId()));
                System.out.println("Rating :" + currCourse.getRating());
                System.out.println("-What You'll Learn-");
                ArrayList<String> learnings = currCourse.getCourseLearnings();
                for (int i = 1; i < learnings.size(); i++) {
                    System.out.println("[" + i + "] " + learnings.get(i - 1));
                }
            }
            if (courseOperation.equals("3")) {
                openCommentPage(courseId);
            }
            if (courseOperation.equals("4")) {
                currentLearner.unenrollCourse(courseId);
                System.out.println("Course Unenrolled!!");
                break;
            }
            if (courseOperation.equals("5")) {
                rateCourse(courseId, currCourse.getCourseName());
                System.out.println("Rated Successfully!!");
            }
            if (courseOperation.equals("0")) {
                break;
            }
        }


    }
    private void startLearning(String courseId) {
        Scanner sc = new Scanner(System.in);
        Course currCourse = uiManager.getCourseDetails(courseId);
        currentLearner.updateCourseProgress(courseId,currCourse.getCourseProgressStepValue());
        double userCurrentProgress = currentLearner.getCourseProgress(courseId);
        int contentLength = currCourse.getContentLength();
        int chapterIndex = (int) Math.round(contentLength / (100.0 / userCurrentProgress)) - 1;
        boolean loopControl = false;
        while (!loopControl) {
            userCurrentProgress = currentLearner.getCourseProgress(courseId);
            System.out.println("+++++++" + currCourse.getCourseId() + "+++++++   [" + (int) userCurrentProgress + " %]");
            Chapter lesson = currCourse.getChapter(chapterIndex);
            System.out.println("Chapter : " + lesson.getChapterName());
            System.out.println("Lesson: " + lesson.getLesson());
            if (chapterIndex > 0 && chapterIndex < currCourse.getContentLength() - 1)
                System.out.println("0. back  1. next  2. Exit");
            else if (chapterIndex == 0 && currCourse.getContentLength() > 1) {
                System.out.println("1. next 2. Exit");
            }else if (chapterIndex == 0 && currCourse.getContentLength() == 1){
                System.out.println("2. Exit");
            } else if (chapterIndex == currCourse.getContentLength() - 1) {
                System.out.println("0. back 2. Exit");
            }
            while (true) {
                String courseControl = sc.next();
                if (courseControl.equals("0") && chapterIndex > 0) {
                    chapterIndex -= 1;
                    break;
                } else if (courseControl.equals("1") && chapterIndex < currCourse.getContentLength() - 1 && currCourse.getContentLength() > 1) {
                    chapterIndex += 1;
                    currentLearner.updateCourseProgress(courseId,currCourse.getCourseProgressStepValue());
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
    private void rateCourse(String courseId,String courseName){
        System.out.println("****Rate the Course - "+courseName+" ****");
        System.out.println("1.Poor 2.Bad 3.Good 4.Very Good 5.Excellent");
        int rating = CustomScanner.getIntegetInput(1,5);
        currentLearner.rateCourse(courseId,rating);
    }
    private void openCommentPage(String courseId){
        Scanner sc = new Scanner(System.in);
        Course currCourse = uiManager.getCourseDetails(courseId);
        boolean closeCommentPage = false;
        while (!closeCommentPage) {
            boolean isCurrentUserCommented = false;
            for (Comment comment : currCourse.getComments()) {
                if (comment.getCommentor().equals(currentLearner.getUserId())) {
                    isCurrentUserCommented = true;
                    break;
                }
            }
            System.out.println("          Comment Page");
            System.out.println("-------------------------------");
            if (isCurrentUserCommented) {
                System.out.println("++++++++++Your Comment+++++++++");
                for (Comment comment : currCourse.getComments()) {
                    if (comment.getCommentor().equals(currentLearner.getUserId())){
                        System.out.println(" * " + comment.getComment());
                    }
                }
            }
            System.out.println("-------------------------------");
            for (Comment comment : currCourse.getComments()) {
                if (!comment.getCommentor().equals(currentLearner.getUserId())) {
                    System.out.println(uiManager.getLearnerName(comment.getCommentor()) + " : " + comment.getComment());
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
                    currentLearner.addComment(comment,currCourse.getCourseId());
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
    public void enrollNewCourse(){
        Scanner sc = new Scanner(System.in);
        String selectedCourseId = showCategoriesToEnroll();
        if(!selectedCourseId.equals("No_Courses_Available")) {
            Course selectedCourse = uiManager.getCourseDetails(selectedCourseId);
            System.out.println("+++++++" + selectedCourse.getCourseId() + "+++++++");
            System.out.println("Course Name :" + selectedCourse.getCourseName());
            System.out.println("Creator :" + uiManager.getCreatorName(selectedCourse.getCreatorId()));
            System.out.println("Rating :" + selectedCourse.getRating());
            System.out.println("-What You'll Learn-");
            ArrayList<String> learnings = selectedCourse.getCourseLearnings();
            for (int i = 1; i < learnings.size(); i++) {
                System.out.println("[" + i + "] " + learnings.get(i - 1));
            }
            System.out.println("\n1. Enroll  0.Back");
            boolean isValidEnrollOption = false;
            while (!isValidEnrollOption) {
                String enrollOption = sc.next();
                if (enrollOption.equals("1")) {
                    currentLearner.enrollNewCourse(selectedCourseId);
                    System.out.println("Course Enrolled Successfully!!");
                    isValidEnrollOption = true;
                } else if (enrollOption.equals("0")) {
                    isValidEnrollOption = true;
                } else {
                    System.out.println("Invalid Option!!");
                }
            }
        }

    }
    private String showCategoriesToEnroll(){
        int categoryNumber = 0;
        System.out.println("Select Course Category");
        ArrayList<String> courseCategories = uiManager.getCategories();
        for (String category : courseCategories){
            System.out.println("["+ (++categoryNumber)+"] "+category);
        }

        int categoryIndex = CustomScanner.getIntegetInput(1,courseCategories.size());
        return showCoursesBasedOnCategory(courseCategories.get(categoryIndex-1));
    }
    private String showCoursesBasedOnCategory(String category){
        ArrayList<Course> allCourses = uiManager.getCoursesBasedOnCategory(category);
        ArrayList<Course> userNotEnrolledCourses = new ArrayList<>();
        for(Course course:allCourses){
            if(!currentLearner.getEnrolledCourses().contains(course.getCourseId())){
                userNotEnrolledCourses.add(course);
            }
        }
        if(userNotEnrolledCourses.size() == 0){
            System.out.println("Sorry! No Courses Available for your Category!!");
        }else{
            System.out.println("******"+category+" Courses*****");
            int courseNumber = 0;
            for(Course course:userNotEnrolledCourses){
                System.out.println("["+(++courseNumber)+"] "+course.getCourseName());
                System.out.println("Price: "+course.getPrice()+"      Author:"+uiManager.getCreatorName(course.getCreatorId())+"      Rating: "+course.getRating());
                System.out.println();
            }
            int courseOption = CustomScanner.getIntegetInput(1,userNotEnrolledCourses.size());
            return userNotEnrolledCourses.get(courseOption-1).getCourseId();
        }
        return "No_Courses_Available";
    }




}
