package obg.core;

import obg.core.Interactor;
import obg.core.Request;
import obg.core.Response;

public interface AppContext {
    Request getInstructorCourseListRequest(String instructorId);
    Request getRequestAttemptRequest(String studentId, String courseId, String objective);

    Interactor getInteractorFor(Request request);

    <T> Response<T> handle(Request request);
}
