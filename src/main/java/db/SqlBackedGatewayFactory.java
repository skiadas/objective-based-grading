package db;

import obg.GatewayFactory;
import obg.gateway.Gateway;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class SqlBackedGatewayFactory implements GatewayFactory {
    private static final SqlBackedGatewayFactory instance = new SqlBackedGatewayFactory();

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

    public static SqlBackedGatewayFactory getInstance() {
         return instance;
    }

    public SqlBackedGatewayFactory() {}

    public SqlBackedGateway getGateway() {
        EntityManager em = factory.createEntityManager();
        return new SqlBackedGateway(em);
    }

    void doWithGateway(Consumer<Gateway> gatewayConsumer) {
        SqlBackedGateway gateway = getGateway();
        try {
            gateway.beginTransaction();
            gatewayConsumer.accept(gateway);
            gateway.commitTransaction();
        } catch (Exception e) {
            gateway.rollbackTransaction();
            throw e;
        }
    }

    public Gateway acquireGateway() {
        return instance.acquireGateway();
    }
}
