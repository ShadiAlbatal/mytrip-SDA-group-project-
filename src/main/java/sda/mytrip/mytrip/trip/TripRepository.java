package sda.mytrip.mytrip.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    Trip findTripByUserAccountId(Long id);
}
