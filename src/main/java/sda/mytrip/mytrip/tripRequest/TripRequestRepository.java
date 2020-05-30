package sda.mytrip.mytrip.tripRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRequestRepository extends JpaRepository<TripRequest,Long> {
    List<TripRequest> findAllByTripId(Long tripId);
   List<TripRequest> findAllByUserAccountId(Long id);
}
