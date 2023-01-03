package Core.Course;

public class Comment {
    private final String courseId;
    private final String comment;
    private final String commentor;

    //******* Constructor *******************************
    public Comment(String comment, String commentor,String courseId) {
        this.comment = comment;
        this.commentor = commentor;
        this.courseId = courseId;
    }

    //Copy Constructor
    public Comment(Comment comment){
        this.comment = comment.comment;
        this.commentor = comment.commentor;
        this.courseId = comment.courseId;
    }
    //******* Getters and Setters *******************************

    public String getCourseId(){return courseId;}

    public String getComment() {
        return comment;
    }

    public String getCommentor() {
        return commentor;
    }
}
