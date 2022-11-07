package Database;


import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;

import java.util.ArrayList;

public class UserDatabase {
    private ArrayList<Learner> learners = new ArrayList<>();
    private ArrayList<Creator> creators = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();

    //****************************************************************************
    private UserDatabase(){}
    private static UserDatabase instance = null;
    public static UserDatabase getInstance(){
        if(instance == null){
            instance= new UserDatabase();
        }
        return instance;
    }
    //****************************************************************************
    public Learner getLearner(String userId) {
        Learner learner = null;
        for(Learner l:this.learners){
            if(l.getUserId().equals(userId)){
                learner = l;
                break;
            }
        }
        return learner;
    }

    public Creator getCreator(String userId) {
        Creator creator = null;
        for(Creator c:this.creators){
            if(c.getUserId().equals(userId)){
                creator = c;
                break;
            }
        }
        return creator;
    }

    public Admin getAdmin(String adminId) {
        Admin admin = null;
        for(Admin a:this.admins){
            if(a.getAdminId().equals(adminId)){
                admin = a;
                break;
            }
        }
        return admin;
    }

    private int getLearnerIndex(String userId){
        int learnerIndex = -1;
        for(Learner l : this.learners){
            if(l.getUserId().equals(userId)){
                learnerIndex = this.learners.indexOf(l);
            }
        }
        return learnerIndex;
    }

    private int getCreatorIndex(String userId){
        int creatorIndex = -1;
        for(Creator c : this.creators){
            if(c.getUserId().equals(userId)){
                creatorIndex = this.learners.indexOf(c);
            }
        }
        return creatorIndex;
    }
    //****************************************************************************
    public void addLearner(Learner learner) {
        this.learners.add(learner);
    }
    public void addCreator(Creator creator) {
        this.creators.add(creator);
    }
    public void addAdmin(Admin admin) {
        this.admins.add(admin);
    }

    //****************************************************************************
    public void removeLearner(String userId){
        for(Learner l:this.learners){
            if(l.getUserId().equals(userId)){
                this.learners.remove(l);
                break;
            }
        }
    }

    public void removeCreator(String userId){
        for(Creator c:this.creators){
            if(c.getUserId().equals(userId)){
                this.creators.remove(c);
                break;
            }
        }
    }

    public void removeAdmin(String userId){
        for(Admin a:this.admins){
            if(a.getAdminId().equals(userId)){
                this.admins.remove(a);
                break;
            }
        }
    }

    //****************************************************************************
    public void changeLearnerPassword(String userId,String newPassword){
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).changePassword(newPassword);
    }

    public void changeUserPassword(String userId,String newPassword){
        int creatorIndex = getCreatorIndex(userId);
        this.creators.get(creatorIndex).changePassword(newPassword);
    }
    //****************************************************************************
    public void addCourseToLearner(String userId,String courseId){
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).addEnrolledCourse(courseId);
    }

    public void updateUserProgress(String userId,double currentProgress,String courseId){
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).updateProgress(courseId, currentProgress);
    }

    public void unenrollCourse(String courseId, String userId) {
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).removeCourse(courseId);
    }

}
