package com.example.springapp.controller;

import com.example.springapp.model.Theater;
import com.example.springapp.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theaters")
@CrossOrigin(origins = "*")
@Tag(name = "Theater Management", description = "APIs for managing theaters")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @Operation(summary = "Get all theaters", description = "Retrieve a list of all theaters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved theaters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {
        try {
            List<Theater> theaters = theaterService.getAllTheaters();
            return ResponseEntity.ok(theaters);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get theater by ID", description = "Retrieve a specific theater by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Theater found"),
        @ApiResponse(responseCode = "404", description = "Theater not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        Optional<Theater> theater = theaterService.getTheaterById(id);
        return theater.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get theaters by city", description = "Retrieve theaters in a specific city")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Theaters found"),
        @ApiResponse(responseCode = "404", description = "No theaters found in this city")
    })
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Theater>> getTheatersByCity(@PathVariable String city) {
        List<Theater> theaters = theaterService.getTheatersByCity(city);
        if (theaters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(theaters);
    }

    @Operation(summary = "Search theaters by name", description = "Search theaters by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Theaters found"),
        @ApiResponse(responseCode = "404", description = "No theaters found with this name")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Theater>> searchTheatersByName(@RequestParam String name) {
        List<Theater> theaters = theaterService.searchTheatersByName(name);
        if (theaters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(theaters);
    }

    @Operation(summary = "Create a new theater", description = "Add a new theater to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Theater created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid theater data")
    })
    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        try {
            Theater createdTheater = theaterService.createTheater(theater);
            return ResponseEntity.ok(createdTheater);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update theater", description = "Update an existing theater's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Theater updated successfully"),
        @ApiResponse(responseCode = "404", description = "Theater not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody Theater theaterDetails) {
        Theater updatedTheater = theaterService.updateTheater(id, theaterDetails);
        if (updatedTheater != null) {
            return ResponseEntity.ok(updatedTheater);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete theater", description = "Delete a theater by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Theater deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Theater not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        boolean deleted = theaterService.deleteTheater(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}