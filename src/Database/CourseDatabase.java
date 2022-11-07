package Database;

import Core.Course.Course;

import java.util.ArrayList;

public class CourseDatabase {
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<String> allCategories = new ArrayList<>();

    //***********************************************************************************
    private CourseDatabase(){}
    private static CourseDatabase instance = null;
    public static CourseDatabase getInstance(){
        if(instance == null){
            instance= new CourseDatabase();
        }
        return instance;
    }
    //******** Getters ***************************************************************************
    public Course getCourse(String courseId){
        Course course = null;
        for(Course c:this.courses){
            if(c.getCourseId().equals(courses)){
                course=c;
            }
        }
        return course;
    }

    public ArrayList<String> getAllCategories(){
        ArrayList<String> categories = new ArrayList<>();
        for(String category:this.allCategories){
            categories.add(category);
        }
        return categories;
    }

    private int getCourseIndex(String courseId){
        int courseIndex = 0;
        for(Course course:this.courses){
            if(course.getCourseId().equals(courseId)) courseIndex=this.courses.indexOf(course);
        }
        return courseIndex;
    }
    //********* Setters ************************************************************************

    public void addCourse(Course course){
        this.courses.add(course);
    }

    public void deleteCourse(String courseId){
        for(Course course : this.courses){
            if(course.getCourseId().equals(courseId)){
                this.courses.remove(course);
            }
        }
    }

    public void addToAllCategories(String category){
        this.allCategories.add(category);
    }

    public void removeFromAllCategories(String category){
        this.allCategories.remove(category);
    }

    public void addComment(String comment, String courseId, String userId){
        int courseIndex = getCourseIndex(courseId);
        this.courses.get(courseIndex).addComment(comment,userId);
    }

    public void rateCourse(String courseId, int rating, String userId) {
        int courseIndex = getCourseIndex(courseId);
        this.courses.get(courseIndex).updateRating(rating,userId);
    }
}
