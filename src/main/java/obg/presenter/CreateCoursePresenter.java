package obg.presenter;

import obg.core.entity.Course;

public interface CreateCoursePresenter {
    void reportError(String errorMessage);
    void reportCourseCreated(Course savedCourse);
}
