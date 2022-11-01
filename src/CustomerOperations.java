import java.util.ArrayList;
import java.util.Scanner;

public class CustomerOperations {
    void learnerOperation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----Learning never exhausts the mind----");
        System.out.println("1. View Enrolled Courses\n2. Enroll new Course\n3. View Certificates\n4. Switch to Creator\n0. Log Out");
        String learnerOperationOption = sc.next();
        boolean isValidLearnerOperationOption = false;
        while(!isValidLearnerOperationOption){
            if(learnerOperationOption.equals("1")){
                isValidLearnerOperationOption=true;
                viewEnrolledCourse();
            }else if(learnerOperationOption.equals("2")){
                isValidLearnerOperationOption=true;
            }else if(learnerOperationOption.equals("3")){
                isValidLearnerOperationOption=true;
            }else if(learnerOperationOption.equals("4")){
                isValidLearnerOperationOption=true;
                creatorOperations();
            }else if(learnerOperationOption.equals("0")){
                isValidLearnerOperationOption=true;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }
    void creatorOperations(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----To Teach is to Learn Twice Over----");
        System.out.println("1.View Created Courses\n2. Create New Course\n3. Switch to Learner\n0. Log Out");
        boolean isValidCreatorOperationOption = false;
        while(!isValidCreatorOperationOption){
            String creatorOperationOption = sc.next();
            if(creatorOperationOption.equals("1")){
                isValidCreatorOperationOption=true;
            }else if(creatorOperationOption.equals("2")){
                isValidCreatorOperationOption=true;
            }else if(creatorOperationOption.equals("3")){
                isValidCreatorOperationOption=true;
                learnerOperation();
            }else if(creatorOperationOption.equals("0")){
                isValidCreatorOperationOption=true;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }

    void viewEnrolledCourse(){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        if(ZLearn.currUser instanceof Learner) {
            if(((Learner) ZLearn.currUser).getMyCourses().size() != 0) {
                System.out.println("-----Enrolled Courses-----");
                int courseNumber = 1;
                for (String courseId : ((Learner) ZLearn.currUser).getMyCourses()) {
                    Course tempCourse = db.getCourseDetails(courseId);
                    System.out.println("["+courseNumber +"] "+tempCourse.getCourseName());
                    courseNumber++;
                }
                System.out.println("0. Back");
                boolean isValidCourseOption = false;
                while (!isValidCourseOption){
                    String courseOption = sc.next();
                    if(courseOption.equals("0")) isValidCourseOption=true;
                    else if (Integer.parseInt(courseOption) <= ((Learner) ZLearn.currUser).getMyCourses().size()) {
                        int courseIndex = Integer.parseInt(courseOption)-1;
                        openCourse(((Learner) ZLearn.currUser).getMyCourses().get(courseIndex));
                        isValidCourseOption = true;
                    }else{
                        System.out.println("Invalid Option");
                    }
                }

            }else{
                System.out.println("You havent enrolled any course Yet!\n 1. To enroll new Course");
            }
        }
    }

    void openCourse(String courseId){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        Course currCourse = db.getCourseDetails(courseId);
        System.out.println("+++++++"+currCourse.getCourseId()+"+++++++");
        System.out.println("1. Start Learning\n2. Course Details\n3. Comment Page\n4. Unenroll Course\n0. Back");
        String courseOperation = sc.next();
        boolean isValidCourseOperation = false;
        while (!isValidCourseOperation){
            if(courseOperation.equals("1")){
                isValidCourseOperation=true;
                startLearning(courseId);
                db.getCourseDetails(courseId);
            } else if(courseOperation.equals("2")){
                isValidCourseOperation=true;
                System.out.println("+++++++"+currCourse.getCourseId()+"+++++++");
                System.out.println("Course Name :"+ currCourse.getCourseName());
                System.out.println("Creator :"+ db.getCreatorName(currCourse.getCreatorId()));
                System.out.println("Rating :"+currCourse.getRating());
                System.out.println("-What You'll Learn-");
                ArrayList<String> learnings = currCourse.getCourseLearnings();
                for(int i=1;i<learnings.size();i++){
                    System.out.println("["+i+"] "+learnings.get(i-1));
                }
            } else if (courseOperation.equals("3")) {
                isValidCourseOperation=true;
                openCommentPage(courseId);
            } else if (courseOperation.equals("4")) {
                isValidCourseOperation=true;
            } else if (courseOperation.equals("0")) {
                isValidCourseOperation=true;
            } else {
                System.out.println("Invalid Option");
            }
        }
    }

    void startLearning(String courseId){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        Course currCourse = db.getCourseDetails(courseId);
        db.updateUserProgress(courseId,ZLearn.currUser.getUserId(), currCourse.getCourseProgressStepValue());
        double userCurrentProgress = db.getUserCurrentProgress(courseId,ZLearn.currUser.getUserId());
        int contentLength = currCourse.getContentLength();
        int chapterIndex = (int) Math.round(contentLength / (100.0 / userCurrentProgress)) - 1;
        boolean loopContol = false;
        while (!loopContol) {
            userCurrentProgress = db.getUserCurrentProgress(courseId,ZLearn.currUser.getUserId());
            System.out.println("+++++++" + currCourse.getCourseId() + "+++++++   ["+(int)userCurrentProgress+" %]");
            Chapter lesson = currCourse.getChapter(chapterIndex);
            System.out.println("Chapter : "+lesson.getChapterName());
            System.out.println("Lesson: "+lesson.getLesson());
            if(chapterIndex>0 && chapterIndex<currCourse.getContentLength()-1)
                System.out.println("0. back  1. next  2. Exit");
            else if (chapterIndex == 0) {
                System.out.println("1. next 2. Exit");
            } else if(chapterIndex == currCourse.getContentLength()-1){
                System.out.println("0. back 2. Exit");
            }
            while (true) {
                String courseControl = sc.next();
                if (courseControl.equals("0") && chapterIndex>0) {
                    chapterIndex-=1;
                    break;
                } else if (courseControl.equals("1") && chapterIndex< currCourse.getContentLength()-1) {
                    chapterIndex+=1;
                    db.updateUserProgress(courseId,ZLearn.currUser.getUserId(), currCourse.getCourseProgressStepValue());
                    break;
                } else if (courseControl.equals("2")) {
                    loopContol=true;
                    break;
                } else {
                    System.out.println("Invalid input!!");
                }
            }
        }
    }

    void openCommentPage(String courseId){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        Course currCourse = db.getCourseDetails(courseId);
        boolean closeCommentPage = false;
        while (!closeCommentPage) {
            boolean isCurrentUserCommented = false;
            for (Comment comment : currCourse.getComments()) {
                if (comment.getCommenter() == ZLearn.currUser.getUserId()) {
                    isCurrentUserCommented = true;
                    break;
                }
            }
            System.out.println("          Comment Page");
            System.out.println("-------------------------------");
            System.out.println("| 1. Add Comment      0. Back |");
            if (isCurrentUserCommented) {
                System.out.println("++++++++++Your Comment+++++++++");
                for (Comment comment : currCourse.getComments()) {
                    if (comment.getCommenter() == ZLearn.currUser.getUserId()) {
                        System.out.println(" * " + comment.getComment());
                    }
                }
            }
            System.out.println("-------------------------------");
            for (Comment comment : currCourse.getComments()) {
                if (comment.getCommenter() != ZLearn.currUser.getUserId())
                    System.out.println(db.getLearnerName(comment.getCommenter()) + " : " + comment.getComment());
            }

            boolean isValidCommentPageOption = false;
            while (!isValidCommentPageOption) {
                String commentPageOption = sc.nextLine();
                if (commentPageOption.equals("1")) {
                    System.out.println("Enter Your Comment");
                    String comment = sc.nextLine();
                    db.addComment(currCourse.getCourseId(),comment,ZLearn.currUser.getUserId());
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
}
