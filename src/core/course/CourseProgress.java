package core.course;

public final class CourseProgress {
    private final String userId;
    private final String courseId;
    private int completedChapterCount;

    //******* Constructor *******************************
    public CourseProgress(String userId, String courseId, int completedChapterCount) {
        this.userId = userId;
        this.courseId = courseId;
        this.completedChapterCount = completedChapterCount;
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

    public int getProgress() {
        return completedChapterCount;
    }

    public void setProgress(int progress) {
            this.completedChapterCount = progress;
    }
}
