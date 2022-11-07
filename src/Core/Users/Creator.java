package Core.Users;

import Core.Course.Course;
import Managers.CreatorManager;

import java.util.ArrayList;

public class Creator extends User{
    CreatorManager creatorManager;
    public Creator(String userId,String userName,String password,String firstName,ROLE role){
        super(userId,userName,password,firstName,role);
        this.creatorManager = creatorManager.initialize();
    }

    //Creator Operations
    public void addNewCourse(Course newCourse){
        creatorManager.addNewCourse(newCourse);
    }

    public void deleteCourse(String courseId){
        creatorManager.deleteCourse(courseId,this.getUserId());
    }

    public void changeCourseName(String newCourseName,String courseId){
        creatorManager.changeCourseName(newCourseName,courseId,this.getUserId());
    }

    public void changeCoursePrice(int newPrice,String courseId){
        creatorManager.changeCoursePrice(newPrice,courseId,this.getUserId());
    }

    public void addCourseCategory(String category,String courseId){
        creatorManager.addCourseCategory(category,courseId,this.getUserId());
    }

    public void removeCourseCategory(String category,String courseId){
        creatorManager.removeCourseCategory(category,courseId,this.getUserId());
    }

    public ArrayList<Course> getCreatedCourse(){
        return creatorManager.getCreatedCourse(this.getUserId());
    }
}
