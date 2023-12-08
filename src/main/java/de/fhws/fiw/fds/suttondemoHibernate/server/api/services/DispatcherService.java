package de.fhws.fiw.fds.suttondemoHibernate.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.ServletRequestAdapter.JerseyServletRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.requestAdapter.JerseyRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter.JerseyUriInfoAdapter;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.states.dispatcher.GetDispatcher;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.utils.InitializeDatabase;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.utils.ResetDatabase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;


@Component
@Path("")
public class DispatcherService extends AbstractService {

    @GET
    public Response getDispatcher() {
        return new GetDispatcher.Builder().setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                .setSuttonRequest(new JerseyRequest(this.request))
                .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                .build()
                .execute();
    }


    @GET
    @Path("resetdatabase")
    @Produces({MediaType.APPLICATION_JSON})
    public Response resetDatabase() {
        System.out.println("RESET DATABASE");

        ResetDatabase.resetAll();

        return Response.ok().build();
    }

    @GET
    @Path("filldatabase")
    @Produces({MediaType.APPLICATION_JSON})
    public Response fillDatabase() {
        System.out.println("FILL DATABASE");

        InitializeDatabase.initializeDBWithRelations();

        return Response.ok().build();
    }
}
