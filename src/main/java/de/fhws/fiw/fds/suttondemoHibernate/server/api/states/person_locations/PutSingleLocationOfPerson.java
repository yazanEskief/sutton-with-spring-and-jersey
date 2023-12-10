package de.fhws.fiw.fds.suttondemoHibernate.server.api.states.person_locations;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.DaoFactory;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;

public class PutSingleLocationOfPerson<R> extends AbstractPutRelationState<Location, R> {

    public PutSingleLocationOfPerson(final Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Location> loadModel() {
        return DaoFactory.getInstance().getLocationDao().readById(this.requestedId);
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getPersonLocationDao().update(this.primaryId, this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PersonLocationUri.REL_PATH_ID,
                PersonLocationRelTypes.GET_SINGLE_LOCATION,
                getAcceptRequestHeader(),
                this.primaryId, this.requestedId);
    }

    public static class Builder<R> extends AbstractPutRelationStateBuilder<Location, R> {
        @Override
        public AbstractState<R, Void> build() {
            return new PutSingleLocationOfPerson<>(this);
        }
    }

}
