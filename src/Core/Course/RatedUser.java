package Core.Course;

public class RatedUser {
    private final String courseId;
    private final String userId;


    //******* Constructor *******************************
    public RatedUser(String courseId, String userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    //******* Getters and Setters *******************************
    //******* course id ******

    public String getCourseId() {
        return courseId;
    }

    //******* user id ******

    public String getUserId() {
        return userId;
    }
}
