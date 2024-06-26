package com.example.Middleware_Es4.repositories;

import com.example.Middleware_Es4.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.status = :status1 OR f.status = :status2")
    List<Flight> findByStatusIn(@Param("status1") Flight.Status status1, @Param("status2") Flight.Status status2);
}
