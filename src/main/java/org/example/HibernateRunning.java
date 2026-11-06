package org.example;

import org.example.Entity.NoEntity.Birthday;
import org.example.Entity.NoEntity.Role;
import org.example.Entity.PersonalInfo;
import org.example.Entity.User;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.time.LocalDate;

import static java.time.LocalDate.*;

public class HibernateRunning {
    static void main() {

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User userTest = User.builder()
                    .username("aPushkov")
                    .personalInfo(PersonalInfo.builder()
                            .firstname("Andrey")
                            .lastname("Pushkov")
                            .birthday(new Birthday(LocalDate.of(2003, 8,1)))
                            .build())
                    .role(Role.ADMIN)
                    .companyId(null)
                    .build();

            session.save(userTest);
            session.flush();
            User userGet = session.get(User.class, 1);
            System.out.println(userGet.toString());
            session.getTransaction().commit();
        }
    }
}
