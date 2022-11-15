package Database;

import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;

import java.util.ArrayList;

 public interface UserDBOperations {
    //*** Learner *************************************************************************
     Learner getLearner(String userName);
     String getLearnerName(String commentor);
     double getLearnerCurrentProgress(String courseId,String userId);
     void addLearner(Learner learner);
     void removeLearner(String userName);
     void changeLearnerPassword(String userName,String newPassword);
     void addCourseToLearner(String userId,String courseId);
     boolean isLearnerExist(String userName);
     void unenrollCourse(String courseId, String userId);
     void updateUserProgress(String courseId, String userId, double currentProgress);
     ArrayList<String> getCourseLearners(String courseId);
    //*** Creator *************************************************************************
     Creator getCreator(String userName);
     Admin getAdmin(String adminId);
     String getCreatorName(String userId);
     void addCreator(Creator creator);
     void removeCreator(String userName);
     void changeCreatorPassword(String userName,String newPassword);
     boolean isCreatorExist(String userName);
    //*** Admin *************************************************************************
     void addAdmin(Admin admin);
     void removeAdmin(String adminId);
}
