package az.spring.bookstore.repository;

import az.spring.bookstore.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {



}