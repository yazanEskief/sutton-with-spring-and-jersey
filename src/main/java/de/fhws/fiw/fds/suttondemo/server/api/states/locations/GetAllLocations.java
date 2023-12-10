package de.fhws.fiw.fds.suttondemo.server.api.states.locations;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

import java.util.Collection;

public class GetAllLocations<R> extends AbstractGetCollectionState<Location, R> {
    public GetAllLocations(final Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    protected void defineHttpResponseBody() {
        this.suttonResponse.entity(this.result.getResult());
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(LocationUri.REL_PATH, LocationRelTypes.CREATE_LOCATION, getAcceptRequestHeader());
    }

    public static class AllLocations<R> extends AbstractQuery<Location, R> {
        @Override
        protected CollectionModelResult<Location> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
            return DaoFactory.getInstance().getLocationDao().readAll();
        }
    }

    public static class Builder<R> extends AbstractGetCollectionStateBuilder<Location, R> {
        @Override
        public AbstractState<R, Collection<Location>> build() {
            return new GetAllLocations<>(this);
        }
    }
}
