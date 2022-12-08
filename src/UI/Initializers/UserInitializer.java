package UI.Initializers;

import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;
import Database.UserDatabase;
import Managers.IdGenerator;

public class UserInitializer {
    public static void initiateUsers(){
        UserDatabase userdb = UserDatabase.getInstance();
        Learner learner1 = new Learner(IdGenerator.getNewLearnerId(), "Yogi", "123", "Yogesh");
        Learner learner2 = new Learner(IdGenerator.getNewLearnerId(), "Logan", "123", "Loganathan");
        Learner learner3 = new Learner(IdGenerator.getNewLearnerId(), "Avinash", "123", "Avinash");


        Creator creator1 = new Creator(IdGenerator.getNewCreatorId(), "Sivanesh", "123", "Shivaneshwaran");
        Creator creator2 = new Creator(IdGenerator.getNewCreatorId(),"Sathya","123","Sathyanarayanan");

        Admin admin1 = new Admin(IdGenerator.getNewAdminId(), "123","Rahul");

        userdb.addLearner(learner1);
        userdb.addLearner(learner2);
        userdb.addLearner(learner3);
        userdb.addCreator(creator1);
        userdb.addCreator(creator2);
        userdb.addAdmin(admin1);
        learner1.enrollCourse("Course_101");
        learner1.enrollCourse("ZCourse_101");
    }
}
