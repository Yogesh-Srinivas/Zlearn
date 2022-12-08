package Core.Course;

public class CourseProgress {
    private final String userId;
    private final String courseId;
    private double progress;


    public CourseProgress(String userId, String courseId, double progress) {
        this.userId = userId;
        this.courseId = courseId;
        this.progress = progress;
    }

    public String getUserId() {
        return userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
