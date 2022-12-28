package Database;

import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;
import Core.Users.User;

interface UserDBOperations {

    //*** User *************************************************************************
     boolean isUserExist(String userName);
     User getUser(String userName);
    //*** Learner *************************************************************************
     String getLearnerName(String commentor);
     void addLearner(Learner learner);
     boolean removeLearner(String userName);
     boolean changeLearnerPassword(String userName,String newPassword);
    //*** Creator *************************************************************************
     String getCreatorName(String userId);
     void addCreator(Creator creator);
     boolean removeCreator(String userName);
     boolean changeCreatorPassword(String userName,String newPassword);
    //*** Admin *************************************************************************
     void addAdmin(Admin admin);
     void removeAdmin(String adminId);

}
