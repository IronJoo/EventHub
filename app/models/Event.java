package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Event extends Model {
    @Id
    private Long id;
    private String title;
    @Column(length = 1500)
    private String description;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Venue venue;
    private static final Finder<Long, Event> finder = new Finder<>(Event.class);

    public Event(Long id, String title, String description, Date startTime, Date endTime, Company company, Category category, Venue venue) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.company = company;
        this.category = category;
        this.venue = venue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public static List<Event> getEventList(){
        return finder.all();
    }

    public static Event getEventById(Long id) {
        return finder.byId(id);
    }
}
