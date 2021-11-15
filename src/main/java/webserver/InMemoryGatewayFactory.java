package webserver;

import obg.gateway.Gateway;
import obg.GatewayFactory;

public class InMemoryGatewayFactory implements GatewayFactory {
    public Gateway acquireGateway() {
        return new InMemoryGateway();
    }
}
