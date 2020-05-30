package sda.mytrip.mytrip.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService service;
    
    @GetMapping("")
    public List<Trip> getAllTrips(@RequestParam(required = false) String date,
                                  @RequestParam(required = false) String from,
                                  @RequestParam(required = false) String to){
          //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(date!=null) {
            LocalDate dateObj = LocalDate.parse(date);
            return service.getSearched(dateObj,from,to);
        }
        return service.getAllTrips();
    }
    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id){
        return service.getTripById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }
    @GetMapping("/user/{id}")
    public Trip getTripByUserId(@PathVariable Long id){
        return service.getTripByUserId(id);

    }
    @GetMapping("/search/{from}")
    public List<Trip> getSearchedTripDestination(@PathVariable String from){
        return service.getSearchTripLocation(from);
    }
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Trip postTrip(@RequestBody Trip newTrip){
        return service.create(newTrip);
    }
    @PutMapping("/update")
    public Trip updateTrip(@RequestBody Trip trip){
        return service.update(trip)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public void deleteTripById(@PathVariable Long id){
        service.delete(id);
    }





}
