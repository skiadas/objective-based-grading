package webserver;

import obg.ConcreteAppContext;
import obg.gateway.Gateway;
import obg.GatewayFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Spark;
import webserver.firewall.Firewall;
import webserver.user.User;
import webserver.user.UserAdministrator;

import static spark.Spark.*;

public class Server {
    private static final Logger logger = LogManager.getLogger();
    private static final Gateway gateway = makeAndPrepareGateway();

    private static final UserAdministrator userAdmin = new UserAdministrator(gateway);
    private static final GatewayFactory gatewayFactory = new InMemoryGatewayFactory();
    private static final ConcreteAppContext context = new ConcreteAppContext(gatewayFactory);

    public static void main(String[] args) {
        startServer(3006);
    }

    public static void startServer(int port) {
        Spark.port(port);
        before("/*", (req, res) -> logger.info("Received call: " + req.pathInfo()));
        setupFirewall();
        // Normal routes
        get("/", (req, res) -> new Handler(req, res, userAdmin, context).handleIndex());
        get("/login", (req, res) -> new Handler(req, res, userAdmin, context).showLoginScreen());
        post("/login", (req, res) -> new Handler(req, res, userAdmin, context).handleLoginRequest());
        post("/logout", (req, res) -> new Handler(req, res, userAdmin, context).handleLogout());
        get("/instructor/:username", (req, res) -> new Handler(req, res, userAdmin, context).getInstructorIndexPage());

        logger.info("Server started, serving at localhost:" + Spark.port());
    }

    private static void setupFirewall() {
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

    private static InMemoryGateway makeAndPrepareGateway() {
        InMemoryGateway gateway = new InMemoryGateway();
        new SampleDataGenerator(gateway).populateWithData();
        return gateway;
    }

}
