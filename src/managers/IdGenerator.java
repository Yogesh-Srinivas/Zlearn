package managers;

public final class IdGenerator {
    private static int currentLearnerId = 1000;
    private static int currentCreatorId = 1000;
    private static int currentAdminId = 0;
    private static int currentGeneralCourseId = 100;
    private static int currentZlearnCourseId = 100;

    //***** getters ******************

    //******* users ******

    public static String getNewLearnerId(){
        return "Lrn_"+(++currentLearnerId);
    }

    public static String getNewCreatorId(){
        return "Ctr_"+(++currentCreatorId);
    }

    public static String getNewAdminId(){
        return "Adm_"+(++currentAdminId);
    }


    //******* course ******
    public static String getNewGeneralCourseId(){
        return "Course_"+(++currentGeneralCourseId);
    }
    public static String getNewZlearnCourseId(){
        return "ZCourse_"+(++currentZlearnCourseId);
    }

}
