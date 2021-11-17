package main;

import obg.ConcreteAppContext;
import obg.core.AppContext;
import obg.core.Presenter;

public class AppContextWrapper implements AppContext {
    private AppContext context;

    public AppContextWrapper(AppContext context) {
        this.context = context;
    }

    public void setContext(AppContext newContext) {
        context = newContext;
    }

    public void instructorCourseListRequested(String instructorId, Presenter presenter) {
        context.instructorCourseListRequested(instructorId, presenter);
    }

    public void attemptRequested(String studentId, String courseId, String objective, Presenter presenter) {
        context.attemptRequested(studentId, courseId, objective, presenter);
    }
}
