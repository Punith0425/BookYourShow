package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springapp.model.Theater;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

    // Find theaters by location (case-insensitive search)
    @Query("SELECT t FROM Theater t WHERE LOWER(t.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Theater> findTheatersByLocation(@Param("location") String location);
}
