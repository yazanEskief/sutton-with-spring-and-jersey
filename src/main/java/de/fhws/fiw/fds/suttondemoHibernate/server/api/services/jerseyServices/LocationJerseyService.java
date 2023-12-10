package de.fhws.fiw.fds.suttondemoHibernate.server.api.services.jerseyServices;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.ServletRequestAdapter.JerseyServletRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.requestAdapter.JerseyRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter.JerseyUriInfoAdapter;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.states.locations.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Component
@Path( "locations" )
public class LocationJerseyService extends AbstractJerseyService
{
	@GET
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response getAllLocations( )
	{
		return new GetAllLocations.Builder<Response>( )
			.setQuery( new GetAllLocations.AllLocations<>( ) )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setSuttonRequest( new JerseyRequest(this.request) )
			.setSuttonServletRequest( new JerseyServletRequest(this.httpServletRequest))
				.setSuttonResponse(new JerseyResponse<>())
			.build( )
			.execute( );
	}

	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response getSingleLocation( @PathParam( "id" ) final long id )
	{
		return new GetSingleLocation.Builder<Response>( )
			.setRequestedId( id )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setSuttonRequest( new JerseyRequest(this.request) )
			.setSuttonServletRequest( new JerseyServletRequest(this.httpServletRequest) )
				.setSuttonResponse(new JerseyResponse<>())
			.build( )
			.execute( );
	}

	@POST
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response createSingleLocation( final Location locationModel )
	{
		return new PostNewLocation.Builder<Response>( )
			.setModelToCreate( locationModel )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setSuttonRequest( new JerseyRequest(this.request) )
			.setSuttonServletRequest( new JerseyServletRequest(this.httpServletRequest) )
				.setSuttonResponse(new JerseyResponse<>())
			.build( )
			.execute( );
	}

	@PUT
	@Path( "{id: \\d+}" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response updateSingleLocation( @PathParam( "id" ) final long id, final Location locationModel )
	{
		return new PutSingleLocation.Builder<Response>( )
			.setRequestedId( id )
			.setModelToUpdate( locationModel )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setSuttonRequest( new JerseyRequest(this.request) )
			.setSuttonServletRequest( new JerseyServletRequest(this.httpServletRequest) )
				.setSuttonResponse(new JerseyResponse<>())
			.build( )
			.execute( );
	}

	@DELETE
	@Path( "{id: \\d+}" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response deleteSingleLocation( @PathParam( "id" ) final long id )
	{
		return new DeleteSingleLocation.Builder<Response>( )
			.setRequestedId( id )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setSuttonRequest( new JerseyRequest(this.request) )
			.setSuttonServletRequest( new JerseyServletRequest(this.httpServletRequest) )
				.setSuttonResponse(new JerseyResponse<>())
			.build( )
			.execute( );
	}

}
