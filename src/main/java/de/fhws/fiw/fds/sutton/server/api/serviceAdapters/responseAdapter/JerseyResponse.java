package de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter;

import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.Collection;

public class JerseyResponse<T> implements SuttonResponse<Response, T> {

    private final Response.ResponseBuilder builder;

    public JerseyResponse() {
        this.builder = Response.ok();
    }

    @Override
    public Response build() {
        return this.builder.build();
    }

    @Override
    public JerseyResponse<T> location(final URI location) {
        this.builder.location(location);
        return this;
    }

    @Override
    public JerseyResponse<T> link(final URI uri, final String rel) {
        this.builder.link(uri, rel);
        return this;
    }

    @Override
    public JerseyResponse<T> entity(final T entity) {
        if (entity instanceof Collection<?> entityCollection) {
            this.builder.entity(new GenericEntity<Collection<?>>(entityCollection) {
            });
        } else {
            this.builder.entity(entity);
        }
        return this;
    }

    @Override
    public JerseyResponse<T> status(final Status status) {
        final int code = status.getCode();
        Response.Status jerseyStatus = Response.Status.fromStatusCode(code);
        this.builder.status(jerseyStatus);
        return this;
    }

    @Override
    public JerseyResponse<T> header(final String headerName, final Object headerValue) {
        this.builder.header(headerName, headerValue);
        return this;
    }
}
