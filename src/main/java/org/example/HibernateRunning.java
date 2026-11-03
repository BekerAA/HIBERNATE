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

            User user = User.builder()
                    .id(6)
                    .name("Stas")
                    .lastname("Karpov")
                    .birthday( new Birthday(LocalDate.of(1999, 1, 1)))
                    .role(Role.USER)
                    .build();

            session.beginTransaction();

            //Операция сохранения
            //session.save(user);

            //Операция обновления
            //session.saveOrUpdate(user);

            //Операция удаления
            //session.delete(user);

            User user1 = session.get(User.class, 1);
            System.out.println(user1.toString());
            session.getTransaction().commit();
        }
    }
}
