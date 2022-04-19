package db;

import obg.core.entity.*;
import obg.gateway.Gateway;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SqlBackedGateway implements Gateway {
    private final EntityManager em;

    public SqlBackedGateway(EntityManager em) {
        this.em = em;
    }

    public boolean getStudentIsEnrolled(String userName, UUID courseId) {
        TypedQuery<Long> q = em.createQuery("SELECT count(e) FROM Enrollment e WHERE (e.student.userName =:userName) AND (e.course.courseId =: courseId)", Long.class);
        q.setParameter("userName",  userName);
        q.setParameter("courseId", courseId);
        List e = q.getResultList();
        System.out.println(e.get(0));
        return (Long) (e).get(0) != 0;
    }

    @Override
    public int getAttemptNumber() {
        return 0;
    }


    public Enrollment getEnrollment(UUID courseId, String studentId) {
        TypedQuery<Enrollment> q = em.createQuery("SELECT e FROM Enrollment e WHERE (e.student.studentId =:studentId) AND (e.course.courseId =: courseId)", Enrollment.class);
        q.setParameter("studentId",  UUID.fromString(studentId));
        q.setParameter("courseId", courseId);
        return q.getSingleResult();
    }

    public Student getStudent(String username) {
        TypedQuery<Student> q = em.createQuery("SELECT s FROM Student s WHERE s.userName = :username", Student.class);
        q.setParameter("username", username);
        return q.getSingleResult();
    }

    public List<Course> getStudentCourses(String userName) {
        return em.createQuery("SELECT c.course FROM Enrollment c WHERE c.student.userName = :student", Course.class)
                .setParameter("student", userName)
                .getResultList();
    }

    public void saveInstructor(Instructor instructor) {
        save(instructor);
    }

    public void saveAttempt(Attempt attempt) {
        save(attempt);
    }

    public void removeAttempt(Long longId) {
        Attempt removeAttempt = em.find(Attempt.class, longId);
        em.remove(removeAttempt);
    }

    @Override
    public Attempt getAttempt(UUID id) {
        TypedQuery<Attempt> q = em.createQuery("SELECT a FROM Attempt a WHERE a.attemptId = :attemptId", Attempt.class);
        q.setParameter("attemptId", id);
        return q.getSingleResult();
    }

    public Instructor getInstructor(String instructorId) {
        TypedQuery<Instructor> q = em.createQuery("SELECT e FROM Instructor e WHERE e.instructorId = :instructorId", Instructor.class);
        q.setParameter("instructorId", instructorId);
        return q.getSingleResult();
    }

    public List<Attempt> getAttempts(Course course) {
        return null;
    }

    public List<Course> getCoursesTaughtBy(Instructor instructor) {
        return em.createQuery("SELECT e FROM Course e WHERE e.instructor = :instructor", Course.class)
                .setParameter("instructor", instructor)
                .getResultList();
    }

    public Course getCourse(UUID courseId) {
        return em.createQuery("SELECT c FROM Course c WHERE c.courseId = :courseId",
                Course.class)
                .setParameter("courseId", courseId)
                .getSingleResult();
    }

    @Override
    public Instructor getInstructor(UUID instructorId) {
        return null;
    }

    @Override
    public Student getStudent(UUID studentId) {
        TypedQuery<Student> q = em.createQuery("SELECT s FROM Student s WHERE s.studentId = :studentId", Student.class);
        q.setParameter("studentId", studentId);
        return q.getSingleResult();
    }

    @Override
    public void saveEnrollment(Enrollment enrollment) {
        save(enrollment);
    }

    EntityManager getEntityManager() {
        return em;
    }

    void beginTransaction() {
        em.getTransaction().begin();
    }

    void commitTransaction() {
        em.getTransaction().commit();
    }

    public <E> void save(E o) {
        getEntityManager().persist(o);
    }

    @Override
    public void removeStudent(Enrollment enrollment1) {
        long enrollment_Id = enrollment1.getLongId();
        Enrollment removeEnroll = em.find(Enrollment.class, enrollment_Id);
        em.remove(removeEnroll);
    }

    @Override
    public void saveCourse(Course course) {
        save(course);
    }

    @Override
    public void assignCourseInstructor(Course course, Instructor instructor) {
        // stubbed, will fix later.
    }

    @Override
    public void addCourse(Course course) {

    }

    void rollbackTransaction() {
        getEntityManager().getTransaction().rollback();
    }

    @Override
    public Enrollment getEnrollment(Enrollment enrollment) {
        return null;
    }

    @Override
    public void addStudent(Student username) {
        save(username);
    }

    @Override
    public Student getStudentUsername(String username) {
        TypedQuery<Student> q = em.createQuery("SELECT s FROM Student s WHERE s.userName = :userName", Student.class);
        q.setParameter("userName", username);
        return q.getSingleResult();
    }
}
