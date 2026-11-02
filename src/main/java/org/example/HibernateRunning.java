package org.example;

import org.example.Entity.Birthday;
import org.example.Entity.Role;
import org.example.Entity.User;
import org.example.converter.BirthdayConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunning {
    static void main() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            User user = User.builder()
                    .name("Igori")
                    .lastname("Gidulinov")
                    .birthday( new Birthday(LocalDate.of(2003, 7, 23)))
                    .role(Role.ADMIN)
                    .build();

            session.save(user);
            session.getTransaction().commit();
        }
    }
}
