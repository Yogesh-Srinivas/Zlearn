package Core.Course;

public class CourseCategory {
    private final String courseId;
    private final String category;


    public CourseCategory(String courseId, String category) {
        this.courseId = courseId;
        this.category = category;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCategory() {
        return category;
    }
}
