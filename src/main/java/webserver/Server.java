package webserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Spark;

import static spark.Spark.*;

public class Server {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        startServer(3006);
    }

    public static void startServer(int port) {
        Spark.port(port);
        before("/*", (req, res) -> logger.info("Received call: " + req.pathInfo()));
        // TODO
        before("/*", "..... check user and if no user, and its not /login, send them there");
        get("/", (req, res) -> new Handler(req, res).handleIndex());
        get("/login", (req, res) -> new Handler(req, res).showLoginScreen());
        post("/login", (req, res) -> new Handler(req, res).handleLoginRequest());
        post("/logout", (req, res) -> new Handler(req, res).handleLogout());
        logger.info("Server started, serving at localhost:" + Spark.port());
    }
}
