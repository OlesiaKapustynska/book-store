package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't save book to DB", e);
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery(
                    "SELECT b FROM Book b", Book.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get books from DB", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        }
    }
}
