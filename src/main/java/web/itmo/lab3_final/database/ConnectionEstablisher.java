package web.itmo.lab3_final.database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Properties;

public class ConnectionEstablisher  {

    private static final EntityManagerFactory factory;

    public static EntityManagerFactory getFactory() {
        return factory;
    }

    static {
        try {
            Properties properties = new Properties();
            properties.load(ConnectionEstablisher.class.getClassLoader()
                    .getResourceAsStream("/database.cfg"));
            factory = Persistence.createEntityManagerFactory("default", properties);
        } catch (Exception e) {
            System.err.println("Something went wrong while establishing connection with EclipseLink: " + e);
            throw new ExceptionInInitializerError();
        }
    }

}
