package com.example.springapp.controller;

import com.example.springapp.model.Theater;
import com.example.springapp.service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theaters")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular/React frontend access
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    /**
     * ✅ Get all theaters
     */
    @GetMapping
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }

    /**
     * ✅ Get a specific theater by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        Optional<Theater> theater = theaterService.getTheaterById(id);
        return theater.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * ✅ Create a new theater
     */
    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater newTheater = theaterService.createTheater(theater);
        return ResponseEntity.ok(newTheater);
    }

    /**
     * ✅ Update a theater
     */
    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody Theater updatedTheater) {
        Optional<Theater> existingTheater = theaterService.getTheaterById(id);

        if (existingTheater.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedTheater.setId(id);
        Theater savedTheater = theaterService.updateTheater(updatedTheater);
        return ResponseEntity.ok(savedTheater);
    }

    /**
     * ✅ Delete a theater
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        Optional<Theater> existingTheater = theaterService.getTheaterById(id);

        if (existingTheater.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ JPQL: Get theaters by location
    @GetMapping("/location")
    public List<Theater> getTheatersByLocation(@RequestParam String location) {
        return theaterService.findTheatersByLocation(location);
    }
}
