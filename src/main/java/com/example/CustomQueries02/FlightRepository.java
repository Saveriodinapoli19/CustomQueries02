package com.example.CustomQueries02;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT * FROM Flight ORDER BY Flight.fromAirport ASC", nativeQuery = true)
    Page<Flight> getAllFlight(Pageable pageable);

    @Query(value = "SELECT * FROM FLIGHT WHERE FLIGHT.status = :p1 OR FLIGHT.status = :p2", nativeQuery = true)
    List<Flight> findByStatus(String p1, String p2);
    }

