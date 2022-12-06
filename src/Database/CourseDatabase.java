package Database;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Course.CourseCategory;

import java.util.ArrayList;
import java.util.Locale;

public class CourseDatabase implements CourseDBOperations{
    private final ArrayList<Course> courses = new ArrayList<>();
    private final ArrayList<String> allCategories = new ArrayList<>();
    private final ArrayList<Comment> comments = new ArrayList<>();
    private final ArrayList<Chapter> courseContents = new ArrayList<>();

    private final ArrayList<CourseCategory> courseCategories = new ArrayList<CourseCategory>();


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
    public Course getCourse(String courseId){
        Course course = null;
        for(Course c:this.courses){
            if(c.getCourseId().equals(courseId)){
                course=c;
            }
        }
        return course;
    }

    public void addCourse(Course course,ArrayList<Chapter> content){
        this.courses.add(course);
        for(Chapter ch:content)
            courseContents.add(ch);
    }

    public boolean deleteCourse(String courseId,String userId){
        boolean isCourseDeleted = false;
        for(Course course : this.courses){
            if((userId.contains("Adm") || course.getCreatorId().equals(userId)) && course.getCourseId().equals(courseId)){
                this.courses.remove(course);
                isCourseDeleted=true;
                break;
            }
        }
        return isCourseDeleted;
    }

    //*************************************************************
    public ArrayList<String> getAllCategories(){
        return new ArrayList<>(this.allCategories);
    }

    public void addToAllCategories(String category){
        this.allCategories.add(category);
    }

    public void removeFromAllCategories(String category){
        this.allCategories.remove(category);
    }

    //*************************************************************

    //*************************************************************

    public ArrayList<Comment> getCourseComments(String courseId, String userId) {
        ArrayList<Comment> comments = new ArrayList<>();

        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            for (Comment comment : this.comments) {
                if (comment.getCourseId().equals(courseId)) comments.add(comment);
            }
        }
        return comments;
    }

    public void addComment(String comment, String courseId, String userId){
        this.comments.add(new Comment(comment,userId,courseId));
    }


    //*************************************************************

    private int getCourseIndex(String courseId){
        int courseIndex = 0;
        for(Course course:this.courses){
            if(course.getCourseId().equals(courseId)) courseIndex=this.courses.indexOf(course);
        }
        return courseIndex;
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

    //*************************************************************
    public void rateCourse(String courseId, int rating, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1)
            this.courses.get(courseIndex).updateRating(rating,userId);
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

    public void addCourseContent(String courseId, Chapter courseChapter, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            int currentNumber = this.courses.get(courseIndex).getNumberOfChapters();
            this.courseContents.add(courseChapter);
            updateNumberOfChapers(courseId,currentNumber+1);
        }
    }

    public void deleteCourseContent(String courseId, int contentIndex, String userId) {
        int courseIndex = getCourseIndex(courseId);
        int chapterIndex = getCourseContentIndex(courseId,contentIndex);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            int currentNumber = this.courses.get(courseIndex).getNumberOfChapters();
            if (chapterIndex != -1) {
                this.courseContents.remove(chapterIndex);
                updateChapterIndexAfterDelete(courseId,contentIndex);
                updateNumberOfChapers(courseId,currentNumber-1);
            }
        }
    }

    private void updateChapterIndexAfterDelete(String courseId,int contentIndex){
        //updating chapter indexes
        for(Chapter chapter:this.courseContents){
            if(chapter.getCourseId().equals(courseId)) {
                int lessonNumber = chapter.getLessonNo();
                if (lessonNumber > contentIndex + 1) {
                    chapter.setLessonNo(lessonNumber-1);
                }
            }
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

    public void changeCourseLesson(String newLesson, String courseId, int chapterIndex, String userId) {
        int courseIndex = getCourseIndex(courseId);
        int chIndex = getCourseContentIndex(courseId,chapterIndex);

        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)){
            if(chIndex!=-1) {
                this.courseContents.get(chIndex).changeLesson(newLesson);
            }
        }
    }

    @Override
    public ArrayList<Chapter> getCourseContent(String courseId) {
        ArrayList<Chapter> content = new ArrayList<>();
        for(Chapter ch : this.courseContents){
            if(ch.getCourseId().equals(courseId)){
                content.add(ch);
            }
        }
        return content;
    }

    private int getCourseContentIndex(String courseId,int chIndex){
        int chapterIndex=-1;
        for(int i=0;i<this.courseContents.size();i++){
            if(this.courseContents.get(i).getCourseId().equals(courseId) && this.courseContents.get(i).getLessonNo() == chIndex+1){
                chapterIndex=i;
            }
        }
        return chapterIndex;
    }

    public Chapter getChapter(String courseId,int chapterIndex){
        Chapter chapter = null;
        int chIndex = getCourseContentIndex(courseId,chapterIndex);
        if(chIndex!=-1){
            chapter = this.courseContents.get(chIndex);
        }
        return chapter;
    }

    public ArrayList<String> getCourseLearnings(String courseId){
        ArrayList<String> learnings = new ArrayList<>();
        int courseInd = getCourseIndex(courseId);
        if(courseInd!=-1) {
            int numberOfChapters = this.courses.get(courseInd).getNumberOfChapters();
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

    private void updateNumberOfChapers(String courseId,int newNumber){
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1){
            this.courses.get(courseIndex).setNumberOfChapters(newNumber);
        }
    }

    //**** Course Categories *******************
    public ArrayList<String> getCourseCategories(String courseId) {
        ArrayList<String> courseCategories = new ArrayList<>();
        for(CourseCategory category:this.courseCategories){
            if(category.getCourseId().equals(courseId)){
                courseCategories.add(category.getCategory());
            }
        }
        return new ArrayList<>(courseCategories);
    }

    public void addCourseCategory(CourseCategory category,String courseId,String userId){
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            this.courseCategories.add(category);
        }
    }

    public void removeCourseCategory(String category,String courseId){
        int categoryIndex = -1;
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            int courseIndex = getCourseIndex(courseId);
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
    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        ArrayList<Course> filteredCourses = new ArrayList<>();
        for(CourseCategory courseCategory : this.courseCategories){
            if(courseCategory.getCategory().equals(category))
                filteredCourses.add(getCourse(courseCategory.getCourseId()));
        }
        return filteredCourses;
    }

    public void removeCourseCategory(String category, String courseId, String userId) {

            this.courses.get(courseIndex).removeCourseCategory(category);
        }
    }




}
