/*
 * Copyright 2019 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.fhws.fiw.fds.sutton.server.api.states.get;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingContext;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.Status;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.util.Collection;

/**
 * <p>The AbstractGetCollectionState extends the {@link AbstractState} and provides the required methods and
 * properties to request a collection of resources.</p>
 *
 * <p>Each extending state class has to define a builder class, which must extend
 * {@link AbstractGetCollectionState.AbstractGetCollectionStateBuilder}.</p>
 */
public abstract class AbstractGetCollectionState<T extends AbstractModel, R> extends AbstractState<R, Collection<T>> {

    /**
     * The header name {@link String} of the total number of results found in the database to be sent in
     * the response to the client
     */
    public static final String HEADER_TOTALNUMBEROFRESULTS = "X-totalnumberofresults";

    /**
     * The header name {@link String} for the number of the results in the current response page
     */
    public static final String HEADER_NUMBEROFRESULTS = "X-numberofresults";

    /**
     * The query {@link AbstractQuery} to be used to fetch the resources from the database and to set the paging
     * behavior
     */
    protected AbstractQuery<T, R> query;

    /**
     * the collection {@link CollectionModelResult} of the requested resources to be sent to the client
     */
    protected CollectionModelResult<T> result;

    protected AbstractGetCollectionState(final AbstractGetCollectionStateBuilder<T, R> builder) {
        super(builder);
        this.query = builder.query;
    }

    @Override
    protected R buildInternal() {
        configureState();

        authorizeRequest();

        this.result = loadModels();

        if (this.result.hasError()) {
            return this.suttonResponse.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return createResponse();
    }

    /**
     * This method should be used to prove if the user is allowed to request a model
     */
    protected abstract void authorizeRequest();

    /**
     * Extending classes should use this method to implement the loading of the requested resources
     * from the database
     */
    protected final CollectionModelResult<T> loadModels() {
        return this.query.startQuery();
    }

    protected R createResponse() {
        defineHttpHeaderTotalNumberOfResults();

        defineHttpHeaderNumberOfResults();

        defineHttpResponseBody();

        defineSelfLink();

        definePagingLinks();

        defineTransitionLinks();

        return this.suttonResponse.build();
    }

    protected void defineHttpHeaderTotalNumberOfResults() {
        this.suttonResponse.header(getHeaderForTotalNumberOfResults(), this.result.getTotalNumberOfResult());
    }

    protected String getHeaderForTotalNumberOfResults() {
        return HEADER_TOTALNUMBEROFRESULTS;
    }

    /**
     * Extending classes should use this method to set the body of the response.
     */
    protected abstract void defineHttpResponseBody();

    protected void defineHttpHeaderNumberOfResults() {
        this.suttonResponse.header(getHeaderForNumberOfResults(), this.result.getResult().size());
    }

    protected String getHeaderForNumberOfResults() {
        return HEADER_NUMBEROFRESULTS;
    }

    /**
     * This method is used to define all transition links based on the idea of a REST system as
     * a finite state machine.
     */
    protected abstract void defineTransitionLinks();

    protected void definePagingLinks() {
        final PagingContext<R, Collection<T>> pagingContext = createPagingContext();

        this.query.addPrevPageLink(pagingContext);
        this.query.addNextPageLink(pagingContext);
    }

    protected void defineSelfLink() {
        this.query.addSelfLink(createPagingContext());
    }

    private PagingContext<R, Collection<T>> createPagingContext() {
        return new PagingContext<>(this.uriInfo, this.suttonResponse, getAcceptRequestHeader());
    }

    public static abstract class AbstractGetCollectionStateBuilder<T extends AbstractModel, R>
            extends AbstractStateBuilder<R, Collection<T>> {
        protected AbstractQuery<T, R> query;

        public AbstractGetCollectionStateBuilder<T, R> setQuery(final AbstractQuery<T, R> query) {
            this.query = query;
            return this;
        }
    }

}
