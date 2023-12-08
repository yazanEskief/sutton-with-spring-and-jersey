package de.fhws.fiw.fds.suttondemoHibernate.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter.JerseyUriInfoAdapter;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.states.locations.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Component
@Path( "locations" )
public class LocationService extends AbstractService
{
	@GET
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response getAllLocations( )
	{
		return new GetAllLocations.Builder( )
			.setQuery( new GetAllLocations.AllLocations( ) )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response getSingleLocation( @PathParam( "id" ) final long id )
	{
		return new GetSingleLocation.Builder( )
			.setRequestedId( id )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@POST
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response createSingleLocation( final Location locationModel )
	{
		return new PostNewLocation.Builder( )
			.setModelToCreate( locationModel )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@PUT
	@Path( "{id: \\d+}" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response updateSingleLocation( @PathParam( "id" ) final long id, final Location locationModel )
	{
		return new PutSingleLocation.Builder( )
			.setRequestedId( id )
			.setModelToUpdate( locationModel )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@DELETE
	@Path( "{id: \\d+}" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response deleteSingleLocation( @PathParam( "id" ) final long id )
	{
		return new DeleteSingleLocation.Builder( )
			.setRequestedId( id )
			.setUriInfo( new JerseyUriInfoAdapter(this.uriInfo) )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

}
