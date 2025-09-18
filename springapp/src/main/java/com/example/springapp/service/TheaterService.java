package com.example.springapp.service;

import com.example.springapp.model.Theater;
import com.example.springapp.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    /**
     * ✅ Get all theaters
     */
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    /**
     * ✅ Get a theater by ID
     */
    public Optional<Theater> getTheaterById(Long id) {
        return theaterRepository.findById(id);
    }

    /**
     * ✅ Create a new theater
     */
    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    /**
     * ✅ Update an existing theater
     */
    public Theater updateTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    /**
     * ✅ Delete a theater by ID
     */
    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }

    // ✅ JPQL: Find theaters by location (case-insensitive)
    public List<Theater> findTheatersByLocation(String location) {
        return theaterRepository.findTheatersByLocation(location);
    }
}
