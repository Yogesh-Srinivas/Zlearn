public class Creator extends Customer{
    public Creator(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
        Database db = Database.getInstance();
        if(db.isCreatorALearner(mobileNumber)){
            super.setRole(role.LEARNER_AND_CREATOR.toString());
            db.changeLearnerRole(role.LEARNER_AND_CREATOR.toString(),mobileNumber);
        }
        else super.setRole(role.CREATOR_ONLY.toString());
    }
    void createCourse(){

    }
    void deleteCourse(){

    }
    void editCourse(){

    }
    void viewCreatedCourse(){

    }
}