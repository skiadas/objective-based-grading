package webserver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Spark;

import static spark.Spark.*;

public class Server {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        before("/*", (req, res) -> logger.info("Received call: " + req.pathInfo()));
        get("/", (req, res) -> "Hello there!");
        logger.info("Server started, serving at localhost:" + Spark.port());
    }
}
