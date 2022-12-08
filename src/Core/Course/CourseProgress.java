package Core.Course;

public class CourseProgress {
    private final String userId;
    private final String courseId;
    private double progress;

    //******* Constructor *******************************
    public CourseProgress(String userId, String courseId, double progress) {
        this.userId = userId;
        this.courseId = courseId;
        this.progress = progress;
    }

    //******* Getters and Setters *******************************
    //******* user id ******

    public String getUserId() {
        return userId;
    }

    //******* course id ******

    public String getCourseId() {
        return courseId;
    }

    //******* progress ******

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
