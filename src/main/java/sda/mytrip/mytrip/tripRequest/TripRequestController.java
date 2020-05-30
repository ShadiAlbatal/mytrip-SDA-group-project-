package sda.mytrip.mytrip.tripRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("requests")
public class TripRequestController {
    @Autowired
    private TripRequestService service;
    @GetMapping("")
    public List<TripRequest> getAllRequest(@RequestParam(required = false) Long requestId){
        return service.getAll();
    }
    @GetMapping("/{tripId}")
    public List<TripRequest> getAllRequestTrip(@PathVariable Long tripId){
        return service.getAllByTripId(tripId);
    }
    @GetMapping("/user/{userId}")
    public List<TripRequest> getAllUserIdRequest(@PathVariable Long userId){
      return service.getAllByUserId(userId);
    }
    @PostMapping("")
    public TripRequest createRequest(@RequestBody TripRequest request){
        return service.create(request);
    }
    @PutMapping("/approve")
    public TripRequest updateRequest(@RequestBody TripRequest updateRequest){
        return service.approveRequest(updateRequest);
    }
    @PutMapping("/reject")
    public TripRequest rejectRequest(@RequestBody TripRequest updateRequest){
        return service.rejectRequest(updateRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteRequestById(@PathVariable Long id){
        service.deleteById(id);
    }

}
