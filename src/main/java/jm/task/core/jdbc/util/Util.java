package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest?Timezone=Europe/Moscow";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private final static String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static SessionFactory sessionFactory=null;

    public static SessionFactory getConnection() {

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", HOST)
                    .setProperty("hibernate.connection.username", LOGIN)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", DIALECT)
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static void closeConnection() {
        if (sessionFactory != null)
            sessionFactory.close();
    }

}
//    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=Europe/Moscow";
//    private static final String LOGIN = "root";
//    private static final String PASSWORD = "root";
//
//    public static Connection getConnection() {
//        Connection conn = null;
//        try {
//            Class.forName(DRIVER);
//            conn = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//}


