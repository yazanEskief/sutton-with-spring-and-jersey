package de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringUriInfoAdapter implements SuttonUriInfo {

    private final UriComponentsBuilder uriComponentsBuilder;

    public SpringUriInfoAdapter(UriComponentsBuilder uriComponentsBuilder) {
        this.uriComponentsBuilder = uriComponentsBuilder;
    }

    @Override
    public String getUriTemplate(final String path) {
        UriComponentsBuilder clone = uriComponentsBuilder.cloneBuilder();
        clone.path(beforeQuestionMark(path));
        clone.replaceQuery(afterQuestionMark(path));
        return clone.build().toUriString();
    }

    @Override
    public URI getURI() {
        System.out.println("-".repeat(20) + this.uriComponentsBuilder.toUriString());
        return this.uriComponentsBuilder.build().toUri();
    }

    @Override
    public String createURIWithQueryParamTemplates(String... queryParams) {
        String queryString = UriComponentsBuilder.newInstance()
                .queryParams(createQueryParamsMap(queryParams))
                .build().toUriString();
        UriComponentsBuilder clone = uriComponentsBuilder.cloneBuilder();
        clone.replaceQueryParam(queryString);
        return clone.build().toUriString();
    }

    @Override
    public URI getURI(final String URI, Map<String, ? extends Object> queryParams) {
        var queryParamsMultiMap = new LinkedMultiValueMap<String, String>();
        queryParams.forEach((k, v) -> {
            queryParamsMultiMap.put(k, List.of(v.toString()));
        });

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URI);
        builder.replaceQueryParams(queryParamsMultiMap);
        return builder.build().toUri();
    }

    @Override
    public URI appendIdToPath(long id) {
        UriComponentsBuilder clone = uriComponentsBuilder.cloneBuilder();
        return clone.path(Long.toString(id)).build().toUri();
    }

    private MultiValueMap<String, String> createQueryParamsMap(String... queryParams) {
        Map<String, List<String>> queriesMap = new HashMap<>();
        for (String param : queryParams) {
            queriesMap.put(param, List.of(getQueryParamAsTemplate(param)));
        }
        return new LinkedMultiValueMap<>(queriesMap);
    }
}
