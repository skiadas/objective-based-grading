package main;

import db.SqlBackedGateway;
import db.SqlBackedGatewayFactory;
import obg.ConcreteAppContext;
import webserver.Server;
import webserver.user.UserAdministrator;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements Observer<Integer> {
    private boolean debug = false;
    private ConcreteAppContext context;
    private LoggingContext loggingContext;
    private AppContextWrapper contextWrapper;
    private final Clock clock = Clock.getInstance();

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        SqlBackedGatewayFactory gatewayFactory = new SqlBackedGatewayFactory();
        SqlBackedGateway gateway = (SqlBackedGateway) gatewayFactory.acquireGateway();
        new SqlBackedSampleDataGenerator(gateway).populateWithData();

        context = new ConcreteAppContext(gatewayFactory);
        loggingContext = new LoggingContext(context);
        contextWrapper = new AppContextWrapper(context);
        new Server(3006, new UserAdministrator(gateway), contextWrapper);
        clock.addObserver(this);
    }

    public void update(Integer times) {
        if (times % 3 == 0) {
            Map<String, String> settings = getSettings();
            boolean newDebug = settings.getOrDefault("DEBUG", "0").equals("1");
            if (debug != newDebug) {
                debug = newDebug;
                System.out.println("Changing debug setting");
                contextWrapper.setContext(this.debug ? loggingContext : context);
            }
        }
    }

    private static Map<String, String> getSettings() {
        Map<String, String> settings = new HashMap<>();
        InputStream stream = ClassLoader.getSystemResourceAsStream("settings.txt");
        if (stream == null) return settings;

        Scanner scanner = new Scanner(stream);
        while (scanner.hasNext()) {
            String s = scanner.next();
            String[] parts = s.split("=");
            settings.put(parts[0], parts[1]);
        }
        return settings;
    }

}
