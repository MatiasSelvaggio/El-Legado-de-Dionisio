package com.api.repository;

import com.api.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
    Optional<Attendance> findByEventIdEventAndUserIdUser(Long idEvent, Long idUser);

    List<Attendance> findByEventIdEvent(Long idEvent);
}
