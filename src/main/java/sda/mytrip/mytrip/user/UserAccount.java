package sda.mytrip.mytrip.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;
import sda.mytrip.mytrip.trip.Trip;
import sda.mytrip.mytrip.tripRequest.TripRequest;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="account")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email(message = "Invalid email address! Please provide a valid email address")
    @NotEmpty(message = "Please provide an email address")
    @Column(name = "email", unique = true)
    private String email;


    @Length(min = 5, max=100, message = "Password length most be between 5-100 characters")
    @Column(name = "password")
    private String password;

    @Length(min = 3, max=100, message = "Name must be between 3-100 characters")
    @Column(name = "firstName")
    private String firstName;

    @Length(min = 3, max=100, message = "Name must be between 3-100 characters")
    @Column(name = "lastName")
    private String lastName;

    @NotEmpty(message = "Please provide a city")
    @Column(name = "city")
    private String city;

    @Pattern(regexp = "0\\d{9}$" , message = "Password provide a valid phone number")
    @Column(name = "phone")
    private String phone;

    @NotEmpty(message = "Please choose you gender")
    @Column(name = "gender")
    private String gender;


   @OneToOne
   @NotFound(action= NotFoundAction.IGNORE)
    private Trip trip;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    //@JsonManagedReference
    @JsonIgnore
    private List<TripRequest> tripRequests;
    public UserAccount() {}

    public UserAccount(
            @Email(message = "Invalid email address! Please provide a valid email address")
            @NotEmpty(message = "Please provide an email address") String email,
            @Length(min = 5, max = 100, message = "Password length most be between 5-100 characters") String password,
            @Length(min = 3, max = 100, message = "Name must be between 3-100 characters") String firstName,
            @Length(min = 3, max = 100, message = "Name must be between 3-100 characters") String lastName,
            @NotEmpty(message = "Please provide a city") String city,
            @Pattern(regexp = "0\\d{9}$" , message = "Password provide a valid phone number") String phone,
            @NotEmpty(message = "Please choose you gender") String gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.phone = phone;
        this.gender = gender;


        //this.trip = trip;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
