package de.fhws.fiw.fds.sutton.server.api.serviceAdapters.ServletRequestAdapter;

import jakarta.servlet.http.HttpServletRequest;

public class JerseyServletRequest implements SuttonServletRequest {

    private final HttpServletRequest httpServletRequest;

    public JerseyServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String getHeader(final String headerName) {
        return this.httpServletRequest.getHeader(headerName);
    }
}
