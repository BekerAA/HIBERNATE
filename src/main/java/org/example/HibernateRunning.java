package org.example;

import org.example.Entity.Role;
import org.example.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunning {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            System.out.println("-".repeat(100) + "\nПодкючение к БД: УСПЕШНО!\n" + "-".repeat(100));

            session.beginTransaction();
            User user = User.builder()
                    .name("Igori")
                    .lastname("Gidulinov")
                    .birthDate(LocalDate.of(2003, 7, 23))
                    .age(LocalDate.now().getYear() - LocalDate.of(2003, 7, 23).getYear())
                    .role(Role.ADMIN)
                    .build();

            session.save(user);
            session.getTransaction().commit();
        }
    }
}
