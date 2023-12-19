package de.fhws.fiw.fds.suttondemo.server.api.states.users;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.security.AuthenticationProvider;


public class GetAllUsers<R> extends AbstractGetCollectionState<User, R> {

    public GetAllUsers(Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationProvider.accessControl(this.suttonServletRequest, "ADMIN", "SUPERVISOR", "USER");
    }

    @Override
    protected void defineHttpResponseBody() {
        this.suttonResponse.entity(this.result.getResult());
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UserUri.REL_PATH, UserRelTypes.CREATE_USER, getAcceptRequestHeader());
    }

    public static class AllUsers<R> extends AbstractQuery<User, R> {

        @Override
        protected CollectionModelResult<User> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
            return DaoFactory.getInstance().getUserDao().readAll();
        }
    }

    public static class Builder<R> extends AbstractGetCollectionStateBuilder<User, R> {

        @Override
        public GetAllUsers<R> build() {
            return new GetAllUsers<>(this);
        }
    }
}
