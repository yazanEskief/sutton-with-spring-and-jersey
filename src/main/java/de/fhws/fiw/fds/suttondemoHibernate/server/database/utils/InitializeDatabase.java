package de.fhws.fiw.fds.suttondemoHibernate.server.database.utils;

import de.fhws.fiw.fds.suttondemoHibernate.server.DaoFactory;

public class InitializeDatabase {

    public static void initializeDBWithRelations() {
        DaoFactory.getInstance().getPersonLocationDao().initializeDatabase();
    }
}
