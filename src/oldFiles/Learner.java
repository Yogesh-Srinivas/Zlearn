package oldFiles;

import java.util.ArrayList;
import java.util.Scanner;

public class Learner extends Customer {
    private ArrayList<String> myCourses = new ArrayList<>();

    public Learner(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
        Database db = Database.getInstance();
        if(db.isLearnerACreator(mobileNumber)){
            super.setRole(role.LEARNER_AND_CREATOR.toString());
            db.changeCreatorRole(role.LEARNER_AND_CREATOR.toString(),mobileNumber);
        }
        else super.setRole(role.LEARNER_ONLY.toString());

    }
    //need to remove after
    public void addMyCourse(String courseId){
        this.myCourses.add(courseId);
    }
    private void unenrollCourse(String courseId){
        this.myCourses.remove(courseId);
    }

    public void viewEnrolledCourse(){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        if(this.getMyCourses().size() != 0) {
            System.out.println("-----Enrolled Courses-----");
            int courseNumber = 1;
            for (String courseId : this.getMyCourses()) {
                Course tempCourse = db.getCourseDetails(courseId);
                System.out.println("["+courseNumber +"] "+tempCourse.getCourseName());
                courseNumber++;
            }
            System.out.println("0. Back");
            boolean isValidCourseOption = false;
            while (!isValidCourseOption){
                String courseOption = sc.next();
                if(courseOption.equals("0")) isValidCourseOption=true;
                else if (Integer.parseInt(courseOption) <= this.getMyCourses().size()) {
                    int courseIndex = Integer.parseInt(courseOption)-1;
                    openCourse(this.getMyCourses().get(courseIndex));
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
    private void openCourse(String courseId){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        Course currCourse = db.getCourseDetails(courseId);
        System.out.println("+++++++"+currCourse.getCourseId()+"+++++++");
        boolean isUserRated = currCourse.isUserRated(super.getUserId());
        if(isUserRated){
            System.out.println("1. Start Learning\n2. oldFiles.Course Details\n3. oldFiles.Comment Page\n4. Unenroll oldFiles.Course\n0. Back");
        }else {
            System.out.println("1. Start Learning\n2. oldFiles.Course Details\n3. oldFiles.Comment Page\n4. Unenroll oldFiles.Course\n5. Rate oldFiles.Course\n0. Back");
        }
        String courseOperation = sc.next();
        boolean isValidCourseOperation = false;
        while (!isValidCourseOperation){
            if(courseOperation.equals("1")){
                isValidCourseOperation=true;
                this.startLearning(courseId);
                db.getCourseDetails(courseId);
            } else if(courseOperation.equals("2")){
                isValidCourseOperation=true;
                System.out.println("+++++++"+currCourse.getCourseId()+"+++++++");
                System.out.println("oldFiles.Course Name :"+ currCourse.getCourseName());
                System.out.println("oldFiles.Creator :"+ db.getCreatorName(currCourse.getCreatorId()));
                System.out.println("Rating :"+currCourse.getRating());
                System.out.println("-What You'll Learn-");
                ArrayList<String> learnings = currCourse.getCourseLearnings();
                for(int i=1;i<learnings.size();i++){
                    System.out.println("["+i+"] "+learnings.get(i-1));
                }
            } else if (courseOperation.equals("3")) {
                isValidCourseOperation=true;
                this.openCommentPage(courseId);
            } else if (courseOperation.equals("4")) {
                isValidCourseOperation=true;
                this.unenrollCourse(courseId);
                db.unenrollCourse(courseId,super.getUserId());
                System.out.println("oldFiles.Course Unenrolled!!");
            } else if (courseOperation.equals("0")) {
                isValidCourseOperation=true;
            } else if (!isUserRated && courseOperation.equals("5")) {
                isValidCourseOperation=true;
                this.rateCourse(courseId);
                System.out.println("Rated Successfully!!");
            } else {
                System.out.println("Invalid Option");
            }
        }
    }
    private void startLearning(String courseId) {
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        Course currCourse = db.getCourseDetails(courseId);
        db.updateUserProgress(courseId, super.getUserId(), currCourse.getCourseProgressStepValue());
        double userCurrentProgress = db.getUserCurrentProgress(courseId, super.getUserId());
        int contentLength = currCourse.getContentLength();
        int chapterIndex = (int) Math.round(contentLength / (100.0 / userCurrentProgress)) - 1;
        boolean loopControl = false;
        while (!loopControl) {
            userCurrentProgress = db.getUserCurrentProgress(courseId, super.getUserId());
            System.out.println("+++++++" + currCourse.getCourseId() + "+++++++   [" + (int) userCurrentProgress + " %]");
            Chapter lesson = currCourse.getChapter(chapterIndex);
            System.out.println("oldFiles.Chapter : " + lesson.getChapterName());
            System.out.println("Lesson: " + lesson.getLesson());
            if (chapterIndex > 0 && chapterIndex < currCourse.getContentLength() - 1)
                System.out.println("0. back  1. next  2. Exit");
            else if (chapterIndex == 0) {
                System.out.println("1. next 2. Exit");
            } else if (chapterIndex == currCourse.getContentLength() - 1) {
                System.out.println("0. back 2. Exit");
            }
            while (true) {
                String courseControl = sc.next();
                if (courseControl.equals("0") && chapterIndex > 0) {
                    chapterIndex -= 1;
                    break;
                } else if (courseControl.equals("1") && chapterIndex < currCourse.getContentLength() - 1) {
                    chapterIndex += 1;
                    db.updateUserProgress(courseId, super.getUserId(), currCourse.getCourseProgressStepValue());
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
    public void enrollNewCourse(){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        String selectedCourseId = showCategoriesToEnroll();
        if(selectedCourseId.equals("0")){
            System.out.println("Back");
        }else{
            Course selectedCourse = db.getCourseDetails(selectedCourseId);
            System.out.println("+++++++"+selectedCourse.getCourseId()+"+++++++");
            System.out.println("oldFiles.Course Name :"+ selectedCourse.getCourseName());
            System.out.println("oldFiles.Creator :"+ db.getCreatorName(selectedCourse.getCreatorId()));
            System.out.println("Rating :"+selectedCourse.getRating());
            System.out.println("-What You'll Learn-");
            ArrayList<String> learnings = selectedCourse.getCourseLearnings();
            for(int i=1;i<learnings.size();i++){
                System.out.println("["+i+"] "+learnings.get(i-1));
            }
            System.out.println("\n1. Enroll  0.Back");
            boolean isValidEnrollOption = false;
            while(!isValidEnrollOption){
                String enrollOption = sc.next();
                if(enrollOption.equals("1")){
                    this.myCourses.add(selectedCourseId);
                    db.enrollCourse(selectedCourseId,super.getUserId());
                    System.out.println("oldFiles.Course Enrolled Successfully!!");
                    isValidEnrollOption=true;
                } else if (enrollOption.equals("0")) {
                    isValidEnrollOption=true;
                }else {
                    System.out.println("Invalid Option!!");
                }
            }
        }
    }
    private String showCategoriesToEnroll(){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        int categoryNumber = 0;
        System.out.println("Select oldFiles.Course Category");
        ArrayList<String> courseCategories = db.getCategories();
        for (String category : courseCategories){
            System.out.println("["+ (++categoryNumber)+"] "+category);
        }
        boolean isValidCategoryOption = false;
        while (!isValidCategoryOption){
            String categoryOption = sc.next();
            if(categoryOption.equals("0")) isValidCategoryOption=true;
            else if (Integer.parseInt(categoryOption) <= courseCategories.size()) {
                isValidCategoryOption = true;
                int categoryIndex = Integer.parseInt(categoryOption)-1;
                return showCoursesBasedOnCategory(courseCategories.get(categoryIndex));
            }else{
                System.out.println("Invalid Input!!");
            }
        }
        return "0";
    }
    private String showCoursesBasedOnCategory(String category){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> allCourses = db.getCoursesBasedOnCategory(category);
        ArrayList<Course> userNotEnrolledCourses = new ArrayList<>();
        for(Course course:allCourses){
            if(!this.myCourses.contains(course.getCourseId())){
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
                System.out.println("Price: "+course.getPrice()+"      Author:"+db.getCreatorName(course.getCreatorId())+"      Rating: "+course.getRating());
                System.out.println();
            }
            boolean isValidCourseOption = false;
            while (!isValidCourseOption){
                String courseOption = sc.next();
                if(courseOption.equals("0")) return "0";
                if(Integer.parseInt(courseOption) <= userNotEnrolledCourses.size()){
                    int courseIndex = Integer.parseInt(courseOption)-1;
                    return userNotEnrolledCourses.get(courseIndex).getCourseId();
                }
            }
        }
        return "0";
}
    private void openCommentPage(String courseId){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        Course currCourse = db.getCourseDetails(courseId);
        boolean closeCommentPage = false;
        while (!closeCommentPage) {
            boolean isCurrentUserCommented = false;
            for (Comment comment : currCourse.getComments()) {
                if (comment.getCommenter() == super.getUserId()) {
                    isCurrentUserCommented = true;
                    break;
                }
            }
            System.out.println("          oldFiles.Comment Page");
            System.out.println("-------------------------------");
            System.out.println("| 1. Add oldFiles.Comment      0. Back |");
            if (isCurrentUserCommented) {
                System.out.println("++++++++++Your oldFiles.Comment+++++++++");
                for (Comment comment : currCourse.getComments()) {
                    if (comment.getCommenter() == super.getUserId()) {
                        System.out.println(" * " + comment.getComment());
                    }
                }
            }
            System.out.println("-------------------------------");
            for (Comment comment : currCourse.getComments()) {
                if (comment.getCommenter() != super.getUserId())
                    System.out.println(db.getLearnerName(comment.getCommenter()) + " : " + comment.getComment());
            }

            boolean isValidCommentPageOption = false;
            while (!isValidCommentPageOption) {
                String commentPageOption = sc.nextLine();
                if (commentPageOption.equals("1")) {
                    System.out.println("Enter Your oldFiles.Comment");
                    String comment = sc.nextLine();
                    db.addComment(currCourse.getCourseId(),comment,super.getUserId());
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

    private ArrayList<String> getMyCourses() {
        return myCourses;
    }
    private void rateCourse(String courseId){
        Database db = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        System.out.println("****Rate the oldFiles.Course - "+courseId+" ****");
        System.out.println("1.Poor 2.Bad 3.Good 4.Very Good 5.Excellent");
        boolean isValidRatingOption = false;
        int rating=0;
        while (!isValidRatingOption){
            String ratingOption = sc.next();
            if(ratingOption.equals("1")){
                isValidRatingOption=true;
                rating=1;
            }else if(ratingOption.equals("2")){
                isValidRatingOption=true;
                rating=2;
            } else if (ratingOption.equals("3")) {
                isValidRatingOption=true;
                rating=3;
            }else if (ratingOption.equals("4")) {
                isValidRatingOption=true;
                rating=4;
            } else if (ratingOption.equals("5")) {
                isValidRatingOption=true;
                rating=5;
            } else {
                System.out.println("Invalid Option!!");
            }
            db.rateCourse(courseId,super.getUserId(),rating);
        }
    }

}