package de.fhws.fiw.fds.sutton.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.ServletRequestAdapter.SuttonServletRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.requestAdapter.SuttonRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.SuttonResponse;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter.SuttonUriInfo;

public class ServiceAdapter {

    private SuttonResponse suttonResponse;

    private SuttonRequest suttonRequest;

    private SuttonServletRequest suttonServletRequest;

    private SuttonUriInfo uriInfo;

    public ServiceAdapter(SuttonResponse suttonResponse, SuttonRequest suttonRequest,
                          SuttonServletRequest suttonServletRequest, SuttonUriInfo uriInfo) {
        this.suttonResponse = suttonResponse;
        this.suttonRequest = suttonRequest;
        this.suttonServletRequest = suttonServletRequest;
        this.uriInfo = uriInfo;
    }

    public ServiceAdapter() {
    }

    public SuttonResponse getSuttonResponse() {
        return suttonResponse;
    }

    public void setSuttonResponse(SuttonResponse suttonResponse) {
        this.suttonResponse = suttonResponse;
    }

    public SuttonRequest getSuttonRequest() {
        return suttonRequest;
    }

    public void setSuttonRequest(SuttonRequest suttonRequest) {
        this.suttonRequest = suttonRequest;
    }

    public SuttonServletRequest getSuttonServletRequest() {
        return suttonServletRequest;
    }

    public void setSuttonServletRequest(SuttonServletRequest suttonServletRequest) {
        this.suttonServletRequest = suttonServletRequest;
    }

    public SuttonUriInfo getUriInfo() {
        return uriInfo;
    }

    public void setUriInfo(SuttonUriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }
}
