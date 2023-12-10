package de.fhws.fiw.fds.suttondemo.server.api.states.locations;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;
import de.fhws.fiw.fds.suttondemo.server.api.states.persons.PersonUri;

public class GetSingleLocation<R> extends AbstractGetState<Location, R> {

    public GetSingleLocation( final Builder<R> builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Location> loadModel( )
    {
        return DaoFactory.getInstance( ).getLocationDao( ).readById( this.requestedId );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( LocationUri.REL_PATH_ID, LocationRelTypes.UPDATE_SINGLE_LOCATION, getAcceptRequestHeader( ),
                this.requestedId );
        addLink( PersonUri.REL_PATH_ID, LocationRelTypes.DELETE_SINGLE_LOCATION, getAcceptRequestHeader( ),
                this.requestedId );
    }

    public static class Builder<R> extends AbstractGetStateBuilder<R, Location>
    {
        @Override public AbstractState<R, Location> build( )
        {
            return new GetSingleLocation<>( this );
        }
    }

}
