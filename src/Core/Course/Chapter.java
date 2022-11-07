package Core.Course;

public class Chapter {
    private String chapterName;
    private String lesson;

    public String getChapterName() {
        return chapterName;
    }

    public void changeChapterName(String newChapterName) {
        this.chapterName = newChapterName;
    }

    public String getLesson() {
        return lesson;
    }

    public void changeLesson(String lesson) {
        this.lesson = lesson;
    }
}
