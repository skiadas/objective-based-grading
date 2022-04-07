package obg;

import obg.gateway.Gateway;

public interface GatewayFactory {
    Gateway acquireGateway();
}