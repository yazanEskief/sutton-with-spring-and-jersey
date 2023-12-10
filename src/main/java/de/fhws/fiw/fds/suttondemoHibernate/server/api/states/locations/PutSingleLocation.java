package de.fhws.fiw.fds.suttondemoHibernate.server.api.states.locations;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.DaoFactory;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;

public class PutSingleLocation<R> extends AbstractPutState<Location, R> {

    public PutSingleLocation( final Builder<R> builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Location> loadModel( )
    {
        return DaoFactory.getInstance( ).getLocationDao( ).readById( this.modelToUpdate.getId( ) );
    }

    @Override protected NoContentResult updateModel( )
    {
        return DaoFactory.getInstance( ).getLocationDao( ).update( this.modelToUpdate );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( LocationUri.REL_PATH_ID, LocationRelTypes.GET_SINGLE_LOCATION, getAcceptRequestHeader( ),
                this.modelToUpdate.getId( ) );
    }

    public static class Builder<R> extends AbstractPutStateBuilder<Location, R>
    {
        @Override public AbstractState<R, Void> build( )
        {
            return new PutSingleLocation<>( this );
        }
    }

}
