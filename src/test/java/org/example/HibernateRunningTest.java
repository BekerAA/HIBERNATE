package org.example;

import lombok.Cleanup;
import org.example.Entity.Company;
import org.example.Entity.NoEntity.Birthday;
import org.example.Entity.NoEntity.Role;
import org.example.Entity.PersonalInfo;
import org.example.Entity.User;
import org.example.utils.HibernateUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

class HibernateRunningTest {

    @Test
    public void saveUserDB(){

        User userTest = User.builder()
                .username("Igor Gidulinov")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Igor")
                        .lastname("Gidulinov")
                        .birthday(new Birthday(LocalDate.of(2003,7,23)))
                        .build())
                .role(Role.USER)
                .company(null)
                .build();

        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(userTest);
        session.getTransaction().commit();
    }


    @Test
    public void saveCompanyDB(){

        Company companyTest = Company.builder().name("RIDAN").build();

        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(companyTest);
        session.getTransaction().commit();
    }

    @Test
    public void saveUserAndCompanyDB(){

        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        User userTest = User.builder()
                .username("Igor Gidulinov")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Igor")
                        .lastname("Gidulinov")
                        .birthday(new Birthday(LocalDate.of(2003,7,23)))
                        .build())
                .role(Role.USER)
                .company(session.get(Company.class, 1))
                .build();
        session.persist(userTest);
        session.getTransaction().commit();
    }

    @Test
    public void getUserDB(){
        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        User user = session.get(User.class, 1);
        session.getTransaction().commit();
    }



    @Test
    public void checkOneToMany(){
        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        Company companyTest = session.get(Company.class, 1);
        System.out.println(companyTest.getUsers());

        session.getTransaction().commit();
    }

    @Test
    public void addCompanyAndUserDB(){

        User userTest = User.builder()
                .username("Mark Mashinin")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Mark")
                        .lastname("Mashinin")
                        .birthday(new Birthday(LocalDate.of(2015,7,4)))
                        .build())
                .role(Role.USER)
                .build();

        Company companyTest = Company.builder().name("GlowByte").build();

        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        companyTest.addUser(userTest);
        session.persist(companyTest);

        session.getTransaction().commit();


    }

    @Test
    public void checkOrphanRemoval(){

        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        Company companyTest = session.get(Company.class,  1);
        companyTest.getUsers().removeIf(user -> user.getId().equals(6));

        session.getTransaction().commit();


    }

}