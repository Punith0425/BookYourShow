package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Theater;
import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByLocationContainingIgnoreCase(String location);
    List<Theater> findByCity(String city);
    List<Theater> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT t FROM Theater t WHERE t.city = :city AND t.location LIKE %:area%")
    List<Theater> findByCityAndArea(@Param("city") String city, @Param("area") String area);
}