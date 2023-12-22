package de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter;

import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.Map;

public class JerseyUriInfoAdapter implements SuttonUriInfo {

    private final UriInfo uriInfo;


    public JerseyUriInfoAdapter(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @Override
    public String getUriTemplate(final String path) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.replacePath(beforeQuestionMark(path));
        builder.replaceQuery(afterQuestionMark(path));
        return builder.toTemplate();
    }

    @Override
    public URI getURI() {
        return this.uriInfo.getAbsolutePath();
    }

    @Override
    public String createURIWithQueryParamTemplates(String... queryParams) {
        UriBuilder uriBuilder = this.uriInfo.getRequestUriBuilder();
        for(String param : queryParams) {
            uriBuilder.replaceQueryParam(param, getQueryParamAsTemplate(param));
        }
        return uriBuilder.toTemplate();
    }

    @Override
    public URI getURI(String URI, Map<String, ?> queryParams) {
        UriBuilder uriBuilder = UriBuilder.fromUri(URI);
        queryParams.forEach(uriBuilder::replaceQueryParam);
        return uriBuilder.build();
    }

    @Override
    public URI appendIdToPath(final long id) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        return builder.path(Long.toString(id)).build();
    }

    @Override
    public String getBaseUri() {
        return this.uriInfo.getBaseUri().toASCIIString();
    }

    @Override
    public String appendToBaseUri(String uri) {
        UriBuilder builder = this.uriInfo.getBaseUriBuilder();
        builder.path(beforeQuestionMark(uri));
        builder.replaceQuery(afterQuestionMark(uri));
        return builder.toTemplate();
    }

    @Override
    public String appendToBaseUriWithoutSchemePortHost(String uri) {
        return UriBuilder.newInstance()
                .path(this.uriInfo.getBaseUri().getPath())
                .path(beforeQuestionMark(uri))
                .replaceQuery(afterQuestionMark(uri))
                .toTemplate();
    }
}
