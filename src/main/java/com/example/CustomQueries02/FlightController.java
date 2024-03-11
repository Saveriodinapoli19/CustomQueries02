package com.example.CustomQueries02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightRepository flightRepository;

    @PostMapping("/random")
    public List<Flight> createRandomFlight (@RequestParam(required = false) Integer number) {
        if(number == null){
            number = 100;
        }
        List<Flight> flightList = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i<number; i++){
            Flight flight = new Flight();
            flight.setDescription("description" + random.nextInt(55));
            flight.setFromAirport("airport" + random.nextInt(55));
            flight.setToAirport("airport" + random.nextInt(55));
            flight.setStatusType(StatusType.ON_TIME);
            flightList.add(flight);
        } return flightRepository.saveAll(flightList);
    }

    @GetMapping("/allFlight")
    public Page<Flight> AllFlight(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
       Pageable pageable = PageRequest.of(page, size);
        return flightRepository.getAllFlight(pageable);
    }
    @GetMapping("/flightsOnYime")
    public List<Flight> getAllOnTimeStatus(){
        List<Flight> flightList = flightRepository.findAll();
        List<Flight> onTimeList = new ArrayList<>();
        for(Flight flight : flightList){
            if(flight.getStatusType().equals(StatusType.ON_TIME)){
                onTimeList.add(flight);
            }
        }
        return onTimeList;
    }
    @GetMapping("/getStatus")
    public List<Flight> findStatus(@RequestParam(name = "p1") String p1, @RequestParam(name = "p2") String p2){
       List<Flight> flights = flightRepository.findByStatus(p1,p2);
       return flights;
    }
}
