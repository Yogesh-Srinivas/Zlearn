package Core.Course;

public class RatedUser {
    private final String courseId;
    private final String userId;

    public RatedUser(String courseId, String userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getUserId() {
        return userId;
    }
}
