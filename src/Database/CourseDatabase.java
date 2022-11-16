package Database;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;

import java.util.ArrayList;

public class CourseDatabase implements CourseDBOperations{
    private final ArrayList<Course> courses = new ArrayList<>();
    private final ArrayList<String> allCategories = new ArrayList<>();

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

    public void addCourse(Course course){
        this.courses.add(course);
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

    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        ArrayList<Course> filteredCourses = new ArrayList<>();
        for(Course course : this.courses){
            if(course.getCourseCategories().contains(category))
                filteredCourses.add(course);
        }
        return filteredCourses;
    }

    public void addCourseCategory(String category, String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            this.courses.get(courseIndex).addCourseCategory(category);
        }
    }

    public void removeCourseCategory(String category, String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId)) {
            this.courses.get(courseIndex).removeCourseCategory(category);
        }
    }

    //*************************************************************

    public ArrayList<Comment> getCourseComments(String courseId, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && this.courses.get(courseIndex).getCreatorId().equals(userId))
            return new ArrayList<>(this.courses.get(courseIndex).getComments());
        return new ArrayList<>();
    }

    public void addComment(String comment, String courseId, String userId){
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1)
            this.courses.get(courseIndex).addComment(comment,userId);
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
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId))
            this.courses.get(courseIndex).addContent(courseChapter);
    }

    public void deleteCourseContent(String courseId, int contentIndex, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId))
            this.courses.get(courseIndex).deleteContent(contentIndex);

    }

    public void changeCourseChapterName(String newChapterName, String courseId, int chapterIndex, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId))
            this.courses.get(courseIndex).changeChapterName(newChapterName,chapterIndex);
    }

    public void changeCourseLesson(String newLesson, String courseId, int chapterIndex, String userId) {
        int courseIndex = getCourseIndex(courseId);
        if(courseIndex!=-1 && (userId.contains("Adm") && courseId.contains("ZCourse")) || this.courses.get(courseIndex).getCreatorId().equals(userId))
            this.courses.get(courseIndex).changeLesson(newLesson,chapterIndex);
    }

}
