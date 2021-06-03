package com.example.shop.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {

    public static <T> T doInTransactionWithResult(SessionFactory sessionFactory, Function<Session, T> action) {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                final T result = action.apply(session);
                session.getTransaction().commit();
                return result;
            } catch (Exception exception) {
                session.getTransaction().rollback();
                throw exception;
            }
        }
    }

    public static <T> void doInTransaction(SessionFactory sessionFactory, Consumer<Session> action) {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                action.accept(session);
                session.getTransaction().commit();
            } catch (Exception exception) {
                session.getTransaction().rollback();
                throw exception;
            }
        }
    }

}