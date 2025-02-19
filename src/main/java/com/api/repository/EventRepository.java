package com.api.repository;

import com.api.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
    Optional<Event> findByIdEventAndDeletedIsNull(Long idEvent);
}
