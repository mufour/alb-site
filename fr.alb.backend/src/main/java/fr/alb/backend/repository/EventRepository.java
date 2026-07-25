package fr.alb.backend.repository;

import fr.alb.backend.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByPublishedTrueOrderByStartDateDesc();
    Optional<Event> findByTitle(String title);
}