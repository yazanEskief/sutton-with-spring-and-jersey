package de.fhws.fiw.fds.sutton.server.api.states;


import jakarta.ws.rs.core.Response;

public class DemoStateReturns200Ok extends AbstractState {
    public DemoStateReturns200Ok() {
        super(new AbstractStateBuilder() {
            @Override
            public AbstractState build() {
                return null;
            }
        });
    }

    @Override
    protected Response buildInternal() {
        return Response.ok().build();
    }
}
