package az.spring.bookstore.repository;

import az.spring.bookstore.domain.Student;
import az.spring.bookstore.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s.student FROM Subscription s WHERE s.author.id = :authorId")
    List<Student> findByAuthorId(Long authorId);

}