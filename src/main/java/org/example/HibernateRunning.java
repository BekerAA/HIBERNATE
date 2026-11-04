package org.example;

import org.example.Entity.Birthday;
import org.example.Entity.Role;
import org.example.Entity.User;
import org.example.converter.BirthdayConverter;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunning {
    static void main() {

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {

            User user = User.builder()
                    .name("Andrey")
                    .lastname("Pushkov")
                    .birthday( new Birthday(LocalDate.of(2003, 8, 1)))
                    .role(Role.ADMIN)
                    .build();

            session.beginTransaction();

            //Операция сохранения
            //session.save(user);

            //Операция обновления
            //session.saveOrUpdate(user);

            //Операция удаления
            //session.delete(user);

            //Операция получения данных
            //User user1 = session.get(User.class, 1);

            session.getTransaction().commit();
        }
    }
}
