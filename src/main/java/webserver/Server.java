package webserver;

import obg.Course;
import obg.Gateway;
import obg.Instructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Spark;
import webserver.firewall.Firewall;

import java.util.UUID;

import static spark.Spark.*;

public class Server {
    private static Logger logger = LogManager.getLogger();
    private static Gateway gateway = makeAndPrepareGateway();

    private static UserAdministrator userAdmin = new UserAdministrator(gateway);

    public static void main(String[] args) {
        startServer(3006);
    }

    public static void startServer(int port) {
        Spark.port(port);
        before("/*", (req, res) -> logger.info("Received call: " + req.pathInfo()));
        setupFirewall();
        // Normal routes
        get("/", (req, res) -> new Handler(req, res, userAdmin).handleIndex());
        get("/login", (req, res) -> new Handler(req, res, userAdmin).showLoginScreen());
        post("/login", (req, res) -> new Handler(req, res, userAdmin).handleLoginRequest());
        post("/logout", (req, res) -> new Handler(req, res, userAdmin).handleLogout());
        logger.info("Server started, serving at localhost:" + Spark.port());
    }

    private static void setupFirewall() {
        new Firewall<User>(userAdmin)
                .addRule("/login", (User u) -> true)
                .addRule("/instructor", (User u) -> u.canActAs(User.Role.Instructor))
                .addRule("/student", (User u) -> u.canActAs(User.Role.Student))
                .addRule("/admin", (User u) -> u.canActAs(User.Role.Admin))
                .addRule("/", User::isAuthenticated)
                .setup();
        exception(Firewall.UnauthorizedAccess.class,
                  (e, req, res) -> res.redirect("/login"));
    }

    private static InMemoryGateway makeAndPrepareGateway() {
        InMemoryGateway gateway = new InMemoryGateway();
        Instructor instructor = new Instructor("skiadas", "Haris", "Skiadas");
        Course course = new Course(UUID.randomUUID(), "course1");
        gateway.instructors.put(instructor.getInstructorId(), instructor);
        gateway.courses.put(course.courseID, course);
        gateway.assignCourseInstructor(course, instructor);
        return gateway;
    }
}
