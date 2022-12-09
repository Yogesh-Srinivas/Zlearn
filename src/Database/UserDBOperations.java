package Database;

import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;

 public interface UserDBOperations {
    //*** Learner *************************************************************************
     Learner getLearner(String userName);
     String getLearnerName(String commentor);
     void addLearner(Learner learner);
     boolean removeLearner(String userName);
     boolean changeLearnerPassword(String userName,String newPassword);
     boolean isLearnerExist(String userName);
    //*** Creator *************************************************************************
     Creator getCreator(String userName);
     Admin getAdmin(String adminId);
     String getCreatorName(String userId);
     void addCreator(Creator creator);
     boolean removeCreator(String userName);
     boolean changeCreatorPassword(String userName,String newPassword);
     boolean isCreatorExist(String userName);
    //*** Admin *************************************************************************
     void addAdmin(Admin admin);
     void removeAdmin(String adminId);
}
