package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Venue extends Model {
    @Id
    private Long id;
    private String name;
    private String address;
    private String city;
    private static final Finder<Long, Venue> finder = new Finder<>(Venue.class);

    public Venue(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public static Venue getVenueByName(String venueName) {
        return finder.query().where().eq("name", venueName).setMaxRows(1).findOne();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static List<Venue> getVenueList(){
        return finder.all();
    }
}
