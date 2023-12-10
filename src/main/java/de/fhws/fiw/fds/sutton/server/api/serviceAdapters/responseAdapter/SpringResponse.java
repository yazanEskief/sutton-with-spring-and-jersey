package de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class SpringResponse<T> implements SuttonResponse<ResponseEntity<T>, T> {

    private Status status;

    private T body;

    private final HttpHeaders headers;

    public SpringResponse() {
        this.body = null;
        this.status = Status.OK;
        this.headers = new HttpHeaders();
    }

    @Override
    public ResponseEntity<T> build() {
        return ResponseEntity.status(this.status.getCode())
                .headers(this.headers)
                .body(this.body);
    }

    @Override
    public SpringResponse<T> location(final URI location) {
        this.headers.setLocation(location);
        return this;
    }

    @Override
    public SpringResponse<T> link(final URI uri, final String rel) {
        System.out.println("-".repeat(10) + uri.toASCIIString() + "-".repeat(10));
        this.headers.add("Link", linkHeader(uri.toASCIIString(), rel));
        return this;
    }

    @Override
    public SpringResponse<T> entity(final T entity) {
        this.body = entity;
        return this;
    }

    @Override
    public SpringResponse<T> status(final Status status) {
        this.status = status;
        return this;
    }

    @Override
    public SpringResponse<T> header(final String headerName, final Object headerValue) {
        this.headers.add(headerName, headerValue.toString());
        return null;
    }

    private static String linkHeader(final String uri, final String rel) {
        final StringBuilder sb = new StringBuilder();
        sb.append('<').append(uri).append(">;");
        sb.append("rel").append("=\"").append(rel).append("\"");
        sb.append(";");
        sb.append("type=\"*\"");

        return sb.toString();
    }
}
