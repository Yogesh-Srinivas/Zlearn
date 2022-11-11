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
                creatorIndex = this.creators.indexOf(c);
            }
        }
        return creatorIndex;
    }
    public String getLearnerName(String commentor) {
        int learnerIndex = getLearnerIndex(commentor);
        return this.learners.get(learnerIndex).getFirstName();
    }
    public String getCreatorName(String userId){
        int creatorIndex = getCreatorIndex(userId);
        return this.creators.get(creatorIndex).getFirstName();
    }

    public double getLearnerCurrentProgress(String courseId,String userId){
        int learnerIndex = getLearnerIndex(userId);
        return this.learners.get(learnerIndex).getCourseProgress(courseId);
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
    public void removeLearner(String userName){
        for(Learner l:this.learners){
            if(l.getUserName().equals(userName)){
                this.learners.remove(l);
                break;
            }
        }
    }

    public void removeCreator(String userName){
        for(Creator c:this.creators){
            if(c.getUserName().equals(userName)){
                this.creators.remove(c);
                break;
            }
        }
    }

    public void removeAdmin(String adminId){
        for(Admin a:this.admins){
            if(a.getAdminId().equals(adminId)){
                this.admins.remove(a);
                break;
            }
        }
    }

    //****************************************************************************
    public void changeLearnerPassword(String userName,String newPassword){
        for(Learner l:this.learners){
            if(l.getUserName().equals(userName)){
                this.learners.get(this.learners.indexOf(l)).changePassword(newPassword);
                break;
            }
        }
    }

    public void changeCreatorPassword(String userName,String newPassword){
        for(Creator c:this.creators){
            if(c.getUserName().equals(userName)){
                this.creators.get(this.creators.indexOf(c)).changePassword(newPassword);
                break;
            }
        }
    }
    //****************************************************************************
    public void addCourseToLearner(String userId,String courseId){
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).addEnrolledCourse(courseId);
    }
    public void unenrollCourse(String courseId, String userId) {
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).removeCourse(courseId);
    }
    public void updateUserProgress(String courseId, String userId, double currentProgress){
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).updateProgress(courseId,currentProgress);
    }

    public ArrayList<String> getCourseLearners(String courseId) {
        ArrayList<String> courseLearners = new ArrayList<>();
        for(Learner learner:this.learners){
            if(learner.isEnrolled(courseId)){
                courseLearners.add(learner.getUserId());
            }
        }
        return courseLearners;
    }
}
