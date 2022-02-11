package webserver;

import obg.core.AppContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Spark;
import webserver.firewall.Firewall;
import webserver.user.User;
import webserver.user.UserAdministrator;

import static spark.Spark.*;

public class Server {
    private static final Logger logger = LogManager.getLogger();

    public Server(int port, UserAdministrator userAdmin, AppContext context) {
        Spark.port(port);
        before("/*", (req, res) -> logger.info("Received call: " + req.pathInfo()));
        setupFirewall(userAdmin);
        // Normal routes
        get("/", (req, res) -> new Handler(req, res, userAdmin, context).handleIndex());
        get("/login", (req, res) -> new Handler(req, res, userAdmin, context).showLoginScreen());
        post("/login", (req, res) -> new Handler(req, res, userAdmin, context).handleLoginRequest());
        post("/logout", (req, res) -> new Handler(req, res, userAdmin, context).handleLogout());
        get("/instructor/:username", (req, res) -> new Handler(req, res, userAdmin, context).getInstructorIndexPage());
        get("/student/:username", (req, res) -> new Handler(req, res, userAdmin, context).getStudentIndexPage());
        get("/instructor/:username/course/:courseid", (req, res) -> "Hello");
        logger.info("Server started, serving at localhost:" + Spark.port());
    }

    private static void setupFirewall(UserAdministrator userAdmin) {
        new Firewall<>(userAdmin)
                .addRule("/login", (User u) -> true)
                .addRule("/instructor", (User u) -> u.canActAs(User.Role.Instructor))
                .addRule("/student", (User u) -> u.canActAs(User.Role.Student))
                .addRule("/admin", (User u) -> u.canActAs(User.Role.Admin))
                .addRule("/", User::isAuthenticated)
                .setup();
        exception(Firewall.UnauthorizedAccess.class,
                  (e, req, res) -> res.redirect("/login"));
    }

}
