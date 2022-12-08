package Database;


import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;

import java.util.ArrayList;

public class UserDatabase implements UserDBOperations{
    private final ArrayList<Learner> learners = new ArrayList<>();
    private final ArrayList<Creator> creators = new ArrayList<>();
    private final ArrayList<Admin> admins = new ArrayList<>();

    //********* Constructor *******************************************************************
    private UserDatabase(){}
    private static UserDatabase instance = null;
    public static UserDatabase getInstance(){
        if(instance == null){
            instance= new UserDatabase();
        }
        return instance;
    }
    //*********** Getters and Setters ************************************************************

    //*** Learner *************************************************************************
    public Learner getLearner(String userName) {
        Learner learner = null;
        for(Learner l:this.learners){
            if(l.getUserName().equals(userName)){
                learner = l;
                break;
            }
        }
        return learner;
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

    public String getLearnerName(String commentor) {
        int learnerIndex = getLearnerIndex(commentor);
        if(learnerIndex==-1) return "NULL";
        return this.learners.get(learnerIndex).getFirstName();
    }
    public void addLearner(Learner learner) {
        this.learners.add(learner);
    }

    public boolean removeLearner(String userName){
        boolean isUserRemoved = false;
        for(Learner l:this.learners){
            if(l.getUserName().equals(userName)){
                this.learners.remove(l);
                isUserRemoved = true;
                break;
            }
        }
        return isUserRemoved;
    }

    public boolean changeLearnerPassword(String userName,String newPassword){
        boolean isPasswordChanged = false;
        for(Learner l:this.learners){
            if(l.getUserName().equals(userName)){
                this.learners.get(this.learners.indexOf(l)).changePassword(newPassword);
                isPasswordChanged = true;
                break;
            }
        }
        return isPasswordChanged;
    }
    public boolean isLearnerExist(String userName) {
        boolean isUserExist = false;
        for(Learner learner:this.learners){
            if(learner.getUserName().equals(userName)){
                isUserExist = true;
                break;
            }
        }
        return isUserExist;
    }


    //*** Creator *************************************************************************
    public Creator getCreator(String userName) {
        Creator creator = null;
        for(Creator c:this.creators){
            if(c.getUserName().equals(userName)){
                creator = c;
                break;
            }
        }
        return creator;
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

    public String getCreatorName(String userId){
        if(userId.contains("Adm")) return "ZLearn";
        int creatorIndex = getCreatorIndex(userId);
        if(creatorIndex==-1) return "NULL";
        return this.creators.get(creatorIndex).getFirstName();
    }

    public void addCreator(Creator creator) {
        this.creators.add(creator);
    }

    public boolean removeCreator(String userName){
        boolean isUserRemoved = false;
        for(Creator c:this.creators){
            if(c.getUserName().equals(userName)){
                this.creators.remove(c);
                isUserRemoved=true;
                break;
            }
        }
        return isUserRemoved;
    }

    public boolean changeCreatorPassword(String userName,String newPassword){
        boolean isPasswordChanged = false;
        for(Creator c:this.creators){
            if(c.getUserName().equals(userName)){
                this.creators.get(this.creators.indexOf(c)).changePassword(newPassword);
                isPasswordChanged=true;
                break;
            }
        }
        return isPasswordChanged;
    }
    public boolean isCreatorExist(String userName) {
        boolean isUserExist = false;
        for(Creator creator:this.creators){
            if(creator.getUserName().equals(userName)){
                isUserExist = true;
                break;
            }
        }
        return isUserExist;
    }

    //*** Admin *************************************************************************
    public Admin getAdmin(String adminId) {
        Admin admin = null;
        for(Admin a:this.admins){
            if(a.getUserId().equals(adminId)){
                admin = a;
                break;
            }
        }
        return admin;
    }

    public void addAdmin(Admin admin) {
        this.admins.add(admin);
    }

    public void removeAdmin(String adminId){
        for(Admin a:this.admins){
            if(a.getUserId().equals(adminId)){
                this.admins.remove(a);
                break;
            }
        }
    }




}
