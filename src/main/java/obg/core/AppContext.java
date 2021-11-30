package obg.core;

import webserver.ResultProducer;

public interface AppContext {
    void instructorCourseListRequested(String instructorId, Presenter presenter);
    void studentCourseListRequested(String studentId, Presenter presenter);

    void attemptRequested(String studentId, String courseId, String objective, Presenter presenter);
}
