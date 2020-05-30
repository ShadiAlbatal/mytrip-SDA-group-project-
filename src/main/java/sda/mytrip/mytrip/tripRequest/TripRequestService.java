package sda.mytrip.mytrip.tripRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.mytrip.mytrip.trip.Trip;
import sda.mytrip.mytrip.trip.TripService;

import java.util.List;
import java.util.Optional;

@Service
public class TripRequestService {
    @Autowired
    private TripRequestRepository repository;
    @Autowired
    private TripService tripService;
    public List<TripRequest> getAll(){
       return repository.findAll();
    }
    public Optional<TripRequest> getAllById(Long requestId){
        return repository.findById(requestId);
    }
    public List<TripRequest> getAllByTripId(Long tripId){
    return repository.findAllByTripId(tripId);
    }
    public List<TripRequest> getAllByUserId(Long userId){
        return repository.findAllByUserAccountId(userId);
    }
    public TripRequest create(TripRequest newRequest){
        newRequest.setStatus(Status.WAITING);
        return repository.save(newRequest);
    }
    public TripRequest approveRequest(TripRequest updatedRequest){
        updatedRequest.setStatus(Status.APPROVED);
        Trip trip = updatedRequest.getTrip();
        int seats = trip.getSeats();
        seats = seats-1;

        trip.setSeats(seats);
        updatedRequest.setTrip(trip);
        TripRequest tripRequest = repository.save(updatedRequest);
        tripService.update(trip);
        return tripRequest;
    }
    public TripRequest rejectRequest(TripRequest updatedRequest){
        updatedRequest.setStatus(Status.DISAPPROVED);
        return repository.save(updatedRequest);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
