package webserver.firewall;

import spark.Spark;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Firewall<U> {
    private final List<Rule<U>> rules = new ArrayList<>();
    private final UserProvider<U> userAdmin;

    public Firewall(UserProvider<U> userAdmin) {
        this.userAdmin = userAdmin;
    }


    public Firewall<U> addRule(String route, Function<U, Boolean> condition) {
        rules.add(new Rule<U>(route, condition));
        return this;
    }

    public void setup() {
        for (Rule<U> rule : rules) {
            Spark.before(rule.route, (req, res) -> {
                if (!rule.appliesTo(userAdmin.retrieveUser(req))) {
                    throw new UnauthorizedAccess();
                }
            });
        }
    }

    public static class Rule<U> {
        private final String route;
        private final Function<U, Boolean> condition;

        public Rule(String route, Function<U, Boolean> condition) {
            this.route = route;
            this.condition = condition;
        }

        private Boolean appliesTo(U u) {
            return condition.apply(u);
        }
    }

    public static class UnauthorizedAccess extends RuntimeException {
    }
}
