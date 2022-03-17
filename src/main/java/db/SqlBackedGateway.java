package db;

import obg.core.entity.*;
import obg.gateway.Gateway;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SqlBackedGateway implements Gateway {
    private final EntityManager em;

    public SqlBackedGateway(EntityManager em) {
        this.em = em;
    }

    public boolean objectiveInCourse(String objective, UUID courseId) {
        return false;
    }

    public boolean getStudentIsEnrolled(String userName, UUID courseId) {
        return false;
    }

    public int getAttemptNumber() {
        return 0;
    }

    public HashMap getObjMap(String studentName, UUID courseId) {
        return null;
    }

    public Enrollment getEnrollment(UUID courseId, String studentId) {
        return null;
    }

    public Enrollment getEnrolledStudent() {
        return null;
    }

    public Course getEnrolledCourse() {
        return null;
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

    public void addInstructor(Instructor instructor) {

    }

    public void addAttempt(Attempt attempt) {

    }

    public void removeAttempt(Attempt attempt) {

    }

    public void clearAttempts() {

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

    @Override
    public Instructor getInstructor(UUID instructorId) {
        return null;
    }

    public Course getCourse(UUID courseId) {
        return em.createQuery("SELECT c FROM Course c WHERE c.courseId = :courseId",
                              Course.class)
                .setParameter("courseId", courseId)
                .getSingleResult();
    }

    @Override
    public Student getStudent(UUID studentId) {
        return null;
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

    void rollbackTransaction() {
        getEntityManager().getTransaction().rollback();
    }

}
