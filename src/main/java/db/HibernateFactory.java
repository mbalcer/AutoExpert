package db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(entity.Car.class);
        configuration.addAnnotatedClass(entity.Question.class);
        StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
        return sessionFactory;
    }
}