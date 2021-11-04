package webserver.firewall;

import spark.Request;

public interface UserProvider<T> {
    T retrieveUser(Request req);
}
