package core.course;

public final class CourseCategory {
    private final String courseId;
    private final String category;

    //******* Constructor *******************************

    public CourseCategory(String courseId, String category) {
        this.courseId = courseId;
        this.category = category;
    }

    //******* Getters and Setters *******************************
    //******* coursed id ******

    public String getCourseId() {
        return courseId;
    }

    //******* category ******

    public String getCategory() {
        return category;
    }
}
