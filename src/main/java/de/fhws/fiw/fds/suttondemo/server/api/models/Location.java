package de.fhws.fiw.fds.suttondemo.server.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.fhws.fiw.fds.sutton.server.api.converter.JsonLinkConverter;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.glassfish.jersey.linking.InjectLink;


import java.time.LocalDate;

@JsonRootName("location")
@JsonIgnoreProperties(value = {"selfLinkOnSecond", "selfLinkPrimary"}, allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class Location extends AbstractModel {

    private String cityName;

    private double longitude;

    private double latitude;

    private LocalDate visitedOn;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/persons/${instance.primaryId}/locations/${instance.id}",
            rel = "self",
            title = "self",
            type = "application/json",
            condition = "${instance.primaryId != 0}"
    )
    private Link selfLinkOnSecond;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/locations/${instance.id}",
            rel = "self",
            title = "self",
            type = "application/json",
            condition = "${instance.primaryId == 0}"
    )
    private Link selfLinkPrimary;

    public Location() {
        // make JPA happy
    }

    public Location(final String cityName, final double longitude, final double latitude, final LocalDate visitedOn) {
        this.cityName = cityName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.visitedOn = visitedOn;
    }

    @JsonSerialize(using = JsonLinkConverter.class)
    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(final Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    @JsonSerialize(using = JsonLinkConverter.class)
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
