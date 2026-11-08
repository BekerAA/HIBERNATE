package org.example;

import org.example.Entity.Company;
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
            session.getTransaction().commit();
        }
    }
}
