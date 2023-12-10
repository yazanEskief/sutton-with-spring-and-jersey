package de.fhws.fiw.fds.suttondemo.server;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

public interface PersonLocationDao extends IDatabaseRelationAccessObject<Location> {

    CollectionModelResult<Location> readByCityName(long primaryId, String cityName, SearchParameter searchParameter);

    void initializeDatabase();

    void resetDatabase();

}
