package de.fhws.fiw.fds.suttondemoHibernate.server.api.services.springServices;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.ServletRequestAdapter.SpringServletRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.requestAdapter.SpringRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.SpringResponse;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter.SpringUriInfoAdapter;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractSpringService;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.states.dispatcher.GetDispatcher;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.utils.InitializeDatabase;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.utils.ResetDatabase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring")
public class DispatcherController extends AbstractSpringService {

    public DispatcherController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> getDispatcher() {
        return new GetDispatcher.Builder<ResponseEntity<Void>>()
                .setUriInfo(new SpringUriInfoAdapter(getUtiComponentsBuilder()))
                .setSuttonResponse(new SpringResponse<>())
                .setSuttonRequest(new SpringRequest())
                .setSuttonServletRequest(new SpringServletRequest(httpServletRequest))
                .build()
                .execute();
    }

    @GetMapping(value = "/filldatabase", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> resetDatabase() {
        System.out.println("RESET DATABASE");

        ResetDatabase.resetAll();

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/resetdatabase", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fillDatabase() {
        System.out.println("FILL DATABASE");

        InitializeDatabase.initializeDBWithRelations();

        return ResponseEntity.ok().build();
    }
}
