package org.example;

import org.example.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunning {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            System.out.println("-".repeat(100) + "\nПодкючение к БД: УСПЕШНО!\n" + "-".repeat(100));

            session.beginTransaction();
            User user = User.builder()
                    .name("Andrey")
                    .lastname("Pushkov")
                    .birthDate(LocalDate.of(2003, 8, 1))
                    .age((int) LocalDate.now().getYear() - LocalDate.of(2003, 8, 1).getYear())
                    .build();

            session.save(user);
            session.getTransaction().commit();
        }
    }
}
