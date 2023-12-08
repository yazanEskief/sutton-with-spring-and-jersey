package de.fhws.fiw.fds.sutton.server.api.serviceAdapters.requestAdapter;

import jakarta.ws.rs.core.Request;

public class JerseyRequest implements SuttonRequest {

    private final Request request;

    public JerseyRequest(Request request) {
        this.request = request;
    }
}
