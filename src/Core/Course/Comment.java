package Core.Course;

public class Comment {
    private final String courseId;
    private final String comment;
    private final String commentor;

    public Comment(String comment, String commentor,String courseId) {
        this.comment = comment;
        this.commentor = commentor;
        this.courseId = courseId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentor() {
        return commentor;
    }

    public String getCourseId(){return courseId;}
}
