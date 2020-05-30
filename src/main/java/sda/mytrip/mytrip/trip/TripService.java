package sda.mytrip.mytrip.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripService {
    @Autowired
    private TripRepository repository;
    public List<Trip> getAllTrips(){
        Date today = Calendar.getInstance().getTime();
        return repository.findAll();

               // .stream().filter(n->n.getDate().equals(today)).collect(Collectors.toList());
    }
    public Optional<Trip> getTripById(Long tripIp){
        return repository.findById(tripIp);
    }
    public Trip getTripByUserId(Long userId){
        return repository.findTripByUserAccountId(userId);
    }
    public List<Trip> getSearchTripDates(LocalDate date){
        return repository.findAll()
                .stream()
                .filter(n->n.getDate().equals(date))
                .collect(Collectors.toList());
    }
    public List<Trip> getSearched(LocalDate date, String from, String to){
        return repository.findAll()
                .stream()
                .filter(n->n.getDate().equals(date)&&n.getLocationFrom().toLowerCase().equals(from.toLowerCase())&&n.getLocationTo().toLowerCase().equals(to.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Trip> getSearchTripLocation(String from){
        return repository.findAll()
                .stream()
                .filter(n->n.getLocationFrom().equals(from))
                .collect(Collectors.toList());
    }
    public Trip create(Trip newTrip){
        return repository.save(newTrip);
    }
    public Optional<Trip> update(Trip updatedTrip){
        Optional<Trip> tripFromDb = repository.findById(updatedTrip.getId());
        if(tripFromDb==null)return null;
        Trip tripResponse = repository.save(updatedTrip);

        return Optional.ofNullable(tripResponse);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }


}
