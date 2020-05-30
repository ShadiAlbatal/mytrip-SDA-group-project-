package sda.mytrip.mytrip.tripRequest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import sda.mytrip.mytrip.trip.Trip;
import sda.mytrip.mytrip.user.UserAccount;

import javax.persistence.*;

@Entity
@Table(name = "requests")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TripRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "")
    private Status status;
    @ManyToOne
    //@JsonBackReference
    private Trip trip;

    @ManyToOne
    //@JsonBackReference
    @NotFound(action= NotFoundAction.IGNORE)
    private UserAccount userAccount;

    public TripRequest() {

    }

    public TripRequest(Long id, Status status, Trip trip, UserAccount userAccount) {
        this.id = id;
        this.status = status;
        this.trip = trip;
        this.userAccount = userAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
