package de.fhws.fiw.fds.suttondemo.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.*;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@JsonRootName("location")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class Location extends AbstractModel {

    private String cityName;

    private double longitude;

    private double latitude;

    private LocalDate visitedOn;

    @SuttonLink(
            style = SuttonLink.Style.ABSOLUTE,
            value = "/persons/${primaryId}/locations/${id}",
            rel = "self",
            type = SuttonLink.MediaType.APPLICATION_JSON,
            condition = @Condition(field = "primaryId", operation = Condition.Operation.NOT_EQUAL, value = "0")
    )
    private transient Link selfLinkOnSecond;

    @SuttonLink(
            style = SuttonLink.Style.ABSOLUTE,
            value = "/locations/${id}",
            rel = "self",
            type = SuttonLink.MediaType.APPLICATION_JSON,
            condition = @Condition(field = "primaryId", operation = Condition.Operation.EQUAL, value = "0")
    )
    private transient Link selfLinkPrimary;

    public Location() {
        // make JPA happy
    }

    public Location(final String cityName, final double longitude, final double latitude, final LocalDate visitedOn) {
        this.cityName = cityName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.visitedOn = visitedOn;
    }

    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(final Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    public Link getSelfLinkPrimary() {
        return selfLinkPrimary;
    }

    public void setSelfLinkPrimary(final Link selfLinkPrimary) {
        this.selfLinkPrimary = selfLinkPrimary;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public LocalDate getVisitedOn() {
        return visitedOn;
    }

    public void setVisitedOn(final LocalDate visitedOn) {
        this.visitedOn = visitedOn;
    }
}
