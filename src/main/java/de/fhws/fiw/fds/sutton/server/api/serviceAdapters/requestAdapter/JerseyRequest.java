package de.fhws.fiw.fds.sutton.server.api.serviceAdapters.requestAdapter;

import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;

public class JerseyRequest implements SuttonRequest {

    private final Request request;

    public JerseyRequest(Request request) {
        this.request = request;
    }

    @Override
    public boolean clientKnowsCurrentModel(String entityTag) {
        return this.request.evaluatePreconditions(new EntityTag(entityTag)) != null;
    }
}
