package obg;

import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.ViewUnmetRequirementTargetGradeGateway;
import obg.interactor.ViewTargetGradeUnmetRequirementsInteractor;
import obg.request.ViewUnmetRequirementTargetGradeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class ViewUnmetRequirementTargetGradeInteractorTests {
    private ViewTargetGradeUnmetRequirementsInteractor interactor;
    private Presenter presenter;

    private Attempt basicAttempt;
    private Attempt coreAttempt;
    private Attempt extraAttempt;

    private ViewUnmetRequirementTargetGradeRequest request;

    @Before
    public void setUp() {
        presenter = mock(Presenter.class);

        Course course = new Course(randomUUID(), "courseName");
        course.addObjective(ObjectiveGroup.BASIC, "basic");
        course.addObjective(ObjectiveGroup.CORE, "core");
        course.addObjective(ObjectiveGroup.EXTRA, "extra");

        Student student = new Student(randomUUID(), "userName");
        Enrollment enrollment = new Enrollment(course, student, "12-15-2020", false);

        basicAttempt = new Attempt("basic", 1, enrollment);
        coreAttempt = new Attempt("core", 1, enrollment);
        extraAttempt = new Attempt("extra", 1, enrollment);

        enrollment.addAttempt(basicAttempt);
        enrollment.addAttempt(coreAttempt);
        enrollment.addAttempt(extraAttempt);

        ViewUnmetRequirementTargetGradeGateway gateway = mock(ViewUnmetRequirementTargetGradeGateway.class);
        request = new ViewUnmetRequirementTargetGradeRequest(student.getStudentId().toString(), course.getCourseId(), "A");
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enrollment);
        interactor = new ViewTargetGradeUnmetRequirementsInteractor(gateway);
    }

    @Test
    public void noUnmetRequirementsForTargetGrade() {
        basicAttempt.assignScore(4);
        coreAttempt.assignScore(4);
        extraAttempt.assignScore(3);
        interactor.handle(request, presenter);
        verify(presenter).presentTargetGradeUnmetRequirements(new ArrayList<>());
    }

    @Test
    public void basicUnmetRequirementForTargetGrade() {
        basicAttempt.assignScore(3);
        coreAttempt.assignScore(4);
        extraAttempt.assignScore(3);
        interactor.handle(request, presenter);
        verify(presenter).presentTargetGradeUnmetRequirements(List.of(ObjectiveGroup.BASIC));
    }

    @Test
    public void coreUnmetRequirementForTargetGrade() {
        basicAttempt.assignScore(4);
        coreAttempt.assignScore(3);
        extraAttempt.assignScore(3);
        interactor.handle(request, presenter);
        verify(presenter).presentTargetGradeUnmetRequirements(List.of(ObjectiveGroup.CORE));
    }

    @Test
    public void extraUnmetRequirementForTargetGrade() {
        basicAttempt.assignScore(4);
        coreAttempt.assignScore(4);
        extraAttempt.assignScore(2);
        interactor.handle(request, presenter);
        verify(presenter).presentTargetGradeUnmetRequirements(List.of(ObjectiveGroup.EXTRA));
    }

    @Test
    public void noRequirementsMetForTargetGrade() {
        basicAttempt.assignScore(3);
        coreAttempt.assignScore(3);
        extraAttempt.assignScore(2);
        interactor.handle(request, presenter);
        verify(presenter).presentTargetGradeUnmetRequirements(List.of(ObjectiveGroup.BASIC, ObjectiveGroup.CORE, ObjectiveGroup.EXTRA));
    }
}
