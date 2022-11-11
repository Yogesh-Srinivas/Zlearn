package Managers;

public class IdGenerator {
    private static int currentLearnerId = 1000;
    private static int currentCreatorId = 1000;
    private static int currentAdminId = 0;
    private static int currentCourseId = 100;
    public static String getNewLearnerId(){
        return "Lrn_"+(++currentLearnerId);
    }
    public static String getNewCreatorId(){
        return "Ctr_"+(++currentCreatorId);
    }
    public static String getNewAdminId(){
        return "Adm_"+(++currentAdminId);
    }
    public static String getNewCourseId(){
        return "Course_"+(++currentCourseId);
    }

}
