package org.example.utils;

import lombok.NoArgsConstructor;
import org.example.converter.BirthdayConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor
public class HibernateUtil {


    public static SessionFactory getSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);

        return configuration.buildSessionFactory();
    }
}
