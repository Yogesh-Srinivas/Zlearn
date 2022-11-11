package Core.Course;

public class Comment {
    private final String comment;
    private final String commentor;

    public Comment(String comment, String commentor) {
        this.comment = comment;
        this.commentor = commentor;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentor() {
        return commentor;
    }
}
