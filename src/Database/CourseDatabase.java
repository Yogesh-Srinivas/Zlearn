package Database;

import Core.Course.*;
import java.util.ArrayList;


class CourseDatabase implements CourseDBOperations{
    private final ArrayList<Course> courses = new ArrayList<>();
    private final ArrayList<String> allCategories = new ArrayList<>();
    private final ArrayList<Comment> comments = new ArrayList<>();
    private final ArrayList<Chapter> courseContents = new ArrayList<>();
    private final ArrayList<RatedUser> ratedBy = new ArrayList<>();
    private final ArrayList<CourseCategory> courseCategories = new ArrayList<>();
    private final ArrayList<CourseProgress> courseProgresses = new ArrayList<>();


    //******** Constructor *********************************************************************
    private CourseDatabase(){}
    private static CourseDatabase instance = null;
    public static CourseDatabase getInstance(){
        if(instance == null){
            instance= new CourseDatabase();
        }
        return instance;
    }
    //******** Getters  and Setters ***************************************************************************

    //******* utils ******
    private int getCourseIndex(String courseId){
        int courseIndex = 0;
        for(Course course:this.courses){
            if(course.getCourseId().equals(courseId)) courseIndex=this.courses.indexOf(course);
        }
        return courseIndex;
    }

    //******* courses ******
    public Course getCourse(String courseId){
        Course course = null;
        for(Course c:this.courses){
            if(c.getCourseId().equals(courseId)){
                course=c;
            }
        }
        return course;
    }
    public ArrayList<Course> getCreatedCourses(String userId){
        ArrayList<Course> courses = new ArrayList<>();
        for(Course course:this.courses){
            if(course.getCreatorId().equals(userId)){
                courses.add(course);
            }
        }
        return courses;
    }

    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(this.courses);
    }

    //******* edit courses ******

    public void addCourse(Course course,ArrayList<Chapter> content){
        this.courses.add(course);
        this.courseContents.addAll(content);
    }
    public boolean deleteCourse(String courseId,String userId){
        for(Course course : this.courses){
            if((userId.contains("Adm") || course.getCreatorId().equals(userId)) && course.getCourseId().equals(courseId)){
                return this.courses.remove(course);
            }
        }
        return false;
    }

    public void changeCourseName(String newCourseName,String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            this.courses.get(courseIndex).changeCourseName(newCourseName);
        }
    }

    public void changeCoursePrice(int newPrice, String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)){
            this.courses.get(courseIndex).changePrice(newPrice);
        }
    }

    public void changeCourseChapterName(String newChapterName, String courseId, int chapterIndex, String userId) {
        int courseIndex = getCourseIndex(courseId);
        int chIndex = getCourseContentIndex(courseId,chapterIndex);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)){
            if(chIndex!=-1) {
                this.courseContents.get(chIndex).changeChapterName(newChapterName);
            }
        }
    }

    //******* all categories ******

    public ArrayList<String> getAllCategories(){
        return new ArrayList<>(this.allCategories);
    }

    public void addToAllCategories(String category){
        this.allCategories.add(category);
    }

    public void removeFromAllCategories(String category){
        this.allCategories.remove(category);
    }

    //******* comments ******
    public ArrayList<Comment> getCourseComments(String courseId) {
        ArrayList<Comment> comments = new ArrayList<>();

        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1) {
            for (Comment comment : this.comments) {
                if (comment.getCourseId().equals(courseId)) comments.add(comment);
            }
        }
        return comments;
    }

    public void addComment(String comment, String courseId, String userId){
        this.comments.add(new Comment(comment,userId,courseId));
    }

    public void deleteCourseComments(String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            ArrayList<Comment> commentsToRemove = new ArrayList<>();
            for(Comment comment:this.comments){
                if(comment.getCourseId().equals(courseId)){
                    commentsToRemove.add(comment);
                }
            }
            this.comments.removeAll(commentsToRemove);
        }
    }

    //******* course content ******
    public ArrayList<Chapter> getCourseContent(String courseId) {
        ArrayList<Chapter> content = new ArrayList<>();
        for(Chapter ch : this.courseContents){
            if(ch.getCourseId().equals(courseId)){
                content.add(ch);
            }
        }
        return content;
    }

    private int getCourseContentIndex(String courseId,int lessonNo){
        int chapterIndex=-1;
        for(int i=0;i<this.courseContents.size();i++){
            if(this.courseContents.get(i).getCourseId().equals(courseId) && this.courseContents.get(i).getLessonNo() == lessonNo){
                chapterIndex=i;
            }
        }
        return chapterIndex;
    }

    public Chapter getChapter(String courseId,int lessonNo){
        Chapter chapter = null;
        int chIndex = getCourseContentIndex(courseId,lessonNo);
        if(chIndex!=-1){
            chapter = this.courseContents.get(chIndex);
        }
        return chapter;
    }
    public ArrayList<String> getCourseLearnings(String courseId){
        ArrayList<String> learnings = new ArrayList<>();
        int courseInd = getCourseIndex(courseId);
        if(courseInd!=-1) {
            int numberOfChapters = getCourseChapterCount(courseId);
            for(int i=0;i<numberOfChapters;i++) {
                for (Chapter ch : this.courseContents) {
                    if (ch.getCourseId().equals(courseId) && ch.getLessonNo() == i+1){
                        learnings.add(ch.getChapterName());
                        break;
                    }
                }
            }
        }
        return learnings;
    }
    public int getCourseChapterCount(String courseId){
        int numberOfChapters = 0;
        int courseInd = getCourseIndex(courseId);
        if(courseInd!=-1){
            numberOfChapters = this.courses.get(courseInd).getNumberOfChapters();
        }
        return numberOfChapters;
    }

    public void addCourseContent(String courseId, Chapter courseChapter, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            int currentNumber = getCourseChapterCount(courseId);
            System.out.println("ch number "+courseChapter.getLessonNo());
            this.courseContents.add(courseChapter);
            updateNumberOfChapters(courseId,currentNumber+1);
        }
    }
    public void deleteCourseContent(String courseId, int lessonNo, String userId) {
        int courseIndex = getCourseIndex(courseId);
        int chapterIndex = getCourseContentIndex(courseId,lessonNo);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            int currentNumber = getCourseChapterCount(courseId);
            if (chapterIndex != -1) {
                this.courseContents.remove(chapterIndex);
                updateChapterIndexAfterDelete(courseId,lessonNo);
                updateNumberOfChapters(courseId,currentNumber-1);
            }
        }
    }
    @Override
    public void deleteCourseContent(String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            ArrayList<Chapter> chaptersToRemove = new ArrayList<>();
            for(Chapter ch:this.courseContents){
                if(ch.getCourseId().equals(courseId)){
                    chaptersToRemove.add(ch);
                }
            }
            this.courseContents.removeAll(chaptersToRemove);
        }
    }
    public void changeCourseLesson(String newLesson, String courseId, int chapterIndex, String userId) {
        int courseIndex = getCourseIndex(courseId);
        int chIndex = getCourseContentIndex(courseId,chapterIndex);

        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)){
            if(chIndex!=-1) {
                this.courseContents.get(chIndex).changeLesson(newLesson);
            }
        }
    }
    private void updateChapterIndexAfterDelete(String courseId,int lessonNo){
        //updating chapter indexes
        for(Chapter chapter:this.courseContents){
            if(chapter.getCourseId().equals(courseId)) {
                int lessonNumber = chapter.getLessonNo();
                if (lessonNumber > lessonNo) {
                    chapter.setLessonNo(lessonNumber-1);
                }
            }
        }
    }

    private void updateNumberOfChapters(String courseId,int newNumber){
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1){
            this.courses.get(courseIndex).setNumberOfChapters(newNumber);
        }
    }

    //******* rated by ******
    private int getRatedUserCount(String courseId){
        int ratedCount=0;
        for(RatedUser ratedUser:this.ratedBy){
            if(ratedUser.getCourseId().equals(courseId)){
                ratedCount++;
            }
        }
        return ratedCount;
    }

    public boolean isRatedBy(String userId,String courseId){
        for(RatedUser ratedUser:this.ratedBy){
            if(ratedUser.getUserId().equals(userId) && ratedUser.getCourseId().equals(courseId)){
                return true;
            }
        }
        return false;
    }

    public void rateCourse(String courseId, int rating, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1){
            if(!isRatedBy(userId,courseId)){
                this.ratedBy.add(new RatedUser(courseId,userId));
                int ratedUserCount = getRatedUserCount(courseId);
                double courseCurrRating = this.courses.get(courseIndex).getRating();
                double newRating =  ((courseCurrRating*(ratedUserCount-1)) + rating) / (ratedUserCount);
                newRating = Double.parseDouble(String.format("%.1f",newRating));
                this.courses.get(courseIndex).setRating(newRating);
            }
        }
    }
    public void deleteCourseRatedBy(String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            ArrayList<RatedUser> ratedUsersToRemove = new ArrayList<>();
            for(RatedUser ratedUser:this.ratedBy){
                if(ratedUser.getCourseId().equals(courseId)){
                    ratedUsersToRemove.add(ratedUser);
                }
            }
            this.ratedBy.removeAll(ratedUsersToRemove);
        }
    }

    //******* categories ******

    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        ArrayList<Course> filteredCourses = new ArrayList<>();
        for(CourseCategory courseCategory : this.courseCategories){
            if(courseCategory.getCategory().equals(category))
                filteredCourses.add(getCourse(courseCategory.getCourseId()));
        }
        return filteredCourses;
    }
    public ArrayList<String> getCourseCategories(String courseId) {
        ArrayList<String> courseCategories = new ArrayList<>();
        for(CourseCategory category:this.courseCategories){
            if(category.getCourseId().equals(courseId)){
                courseCategories.add(category.getCategory());
            }
        }
        return courseCategories;
    }

    public void addCourseCategory(String category,String courseId,String userId){
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            this.courseCategories.add(new CourseCategory(courseId,category));
        }
    }

    public void removeCourseCategory(String category,String courseId,String userId){
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            int categoryIndex = -1;
            for (int i = 0; i < this.courseCategories.size(); i++) {
                if (this.courseCategories.get(i).getCourseId().equals(courseId) && this.courseCategories.get(
                        i).getCategory().equals(category)) {
                    categoryIndex = i;
                    break;
                }
            }
            this.courseCategories.remove(categoryIndex);
        }
    }

    public void deleteCourseCategory(String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            ArrayList<CourseCategory> categoriesToRemove = new ArrayList<>();
            for(CourseCategory courseCategory:this.courseCategories){
                if(courseCategory.getCourseId().equals(courseId)){
                    categoriesToRemove.add(courseCategory);
                }
            }
            this.courseCategories.removeAll(categoriesToRemove);
        }
    }

    //******* course progress / Enrollment ******

    public ArrayList<String> getEnrolledCourses(String userId){
        ArrayList<String> enrolledCourses = new ArrayList<>();
        for(CourseProgress courseProgress : this.courseProgresses){
            if(courseProgress.getUserId().equals(userId)){
                enrolledCourses.add(courseProgress.getCourseId());
            }
        }

        return enrolledCourses;
    }

    public ArrayList<String> getCourseLearners(String courseId) {
        ArrayList<String> courseLearners = new ArrayList<>();
        for(CourseProgress courseProgress:this.courseProgresses){
            if(courseProgress.getCourseId().equals(courseId)){
                courseLearners.add(courseProgress.getUserId());
            }
        }
        return courseLearners;
    }

    public double getCourseProgress(String courseId,String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1){
            for(CourseProgress courseProgress:this.courseProgresses){
                if(courseProgress.getCourseId().equals(courseId) && courseProgress.getUserId().equals(userId)){
                    return courseProgress.getProgress();
                }
            }
        }
        return -1;
    }

    private int getCourseProgressIndex(String userId,String courseId){
        for(int i=0;i<this.courseProgresses.size();i++){
            if(this.courseProgresses.get(i).getCourseId().equals(courseId) && this.courseProgresses.get(i).getUserId().equals(userId)){
                return i;
            }
        }
        return -1;
    }

    public boolean isEnrolled(String courseId,String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1){
            for(CourseProgress courseProgress:this.courseProgresses){
                if(courseProgress.getCourseId().equals(courseId) && courseProgress.getUserId().equals(userId)){
                    return true;
                }
            }
        }
        return false;
    }

    public void enrollCourse(CourseProgress courseProgress){
        this.courseProgresses.add(courseProgress);
    }

    public void updateProgress(String courseId,String userId, double currentProgress){
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1){
            int progressIndex = getCourseProgressIndex(userId,courseId);
            this.courseProgresses.get(progressIndex).setProgress(currentProgress);
        }
    }

    public void unenrollCourse(String courseId,String userId){
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1) {
            int progressIndex = getCourseProgressIndex(userId, courseId);
            this.courseProgresses.remove(progressIndex);
        }
    }

}
