package com.example.springapp.service;

import com.example.springapp.model.Theater;
import com.example.springapp.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public Optional<Theater> getTheaterById(Long id) {
        return theaterRepository.findById(id);
    }

    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    public Theater updateTheater(Long id, Theater theaterDetails) {
        Optional<Theater> theaterOptional = theaterRepository.findById(id);
        if (theaterOptional.isPresent()) {
            Theater theater = theaterOptional.get();
            theater.setName(theaterDetails.getName());
            theater.setLocation(theaterDetails.getLocation());
            theater.setCity(theaterDetails.getCity());
            theater.setState(theaterDetails.getState());
            theater.setPincode(theaterDetails.getPincode());
            theater.setTotalScreens(theaterDetails.getTotalScreens());
            return theaterRepository.save(theater);
        }
        return null;
    }

    public boolean deleteTheater(Long id) {
        if (theaterRepository.existsById(id)) {
            theaterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Theater> searchTheatersByName(String name) {
        return theaterRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Theater> getTheatersByLocation(String location) {
        return theaterRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Theater> getTheatersByCity(String city) {
        return theaterRepository.findByCity(city);
    }

    public List<Theater> getTheatersByCityAndArea(String city, String area) {
        return theaterRepository.findByCityAndArea(city, area);
    }
}