package obg.core;

public interface AppContext {
    void instructorCourseListRequested(String instructorId, Presenter presenter);
    void attemptRequested(String studentId, String courseId, String objective, Presenter presenter);

}
