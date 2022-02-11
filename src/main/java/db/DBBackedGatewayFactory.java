package db;

import obg.gateway.Gateway;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class DBBackedGatewayFactory {
    private static final DBBackedGatewayFactory instance = new DBBackedGatewayFactory();

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

    public static DBBackedGatewayFactory getInstance() {
         return instance;
    }

    private DBBackedGatewayFactory() {}

    DBBackedGateway getGateway() {
        EntityManager em = factory.createEntityManager();
        return new DBBackedGateway(em);
    }

    void doWithGateway(Consumer<Gateway> gatewayConsumer) {
        DBBackedGateway gateway = getGateway();
        try {
            gateway.beginTransaction();
            gatewayConsumer.accept(gateway);
            gateway.commitTransaction();
        } catch (Exception e) {
            gateway.rollbackTransaction();
            throw e;
        }
    }
}
