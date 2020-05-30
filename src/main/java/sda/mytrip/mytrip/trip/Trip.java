package sda.mytrip.mytrip.trip;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import sda.mytrip.mytrip.tripRequest.TripRequest;
import sda.mytrip.mytrip.user.UserAccount;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trip")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
   @Column(name = "locationFrom", nullable = false)
    private String locationFrom;
   @Column(name = "locationTo", nullable = false)
    private String locationTo;
   //@Temporal(TemporalType.DATE)
   @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private LocalDate date;

   @JsonFormat(pattern = "hh:mm")
    @Column(name = "time", nullable = false)
    private String time;

    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id")

    private UserAccount userAccount;

    @OneToMany(mappedBy = "trip")
    private List<TripRequest> tripRequests;

    @Column(name = "seats", nullable = false)
    private int seats;
    public Trip() {

    }

    public Trip(Long id, String locationFrom, String locationTo, LocalDate date, String time, int seats, UserAccount userAccount,List<TripRequest> tripRequests) {
        this.id = id;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.date = date;
        this.seats = seats;
        this.userAccount = userAccount;
        this.tripRequests= tripRequests;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(String locationFrom) {
        this.locationFrom = locationFrom;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public List<TripRequest> getTripRequests() {
        return tripRequests;
    }

    public void setTripRequests(List<TripRequest> tripRequests) {
        this.tripRequests = tripRequests;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
