package de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter;

import java.net.URI;

public interface SuttonResponse<R, T> {

    R build();

    SuttonResponse<R, T> location(final URI location);

    SuttonResponse<R, T> link(final URI uri, final String rel);

    SuttonResponse<R, T> entity(final T entity);

    SuttonResponse<R, T> status(final Status status);

    SuttonResponse<R, T> header(final String headerName, final Object headerValue);
}
