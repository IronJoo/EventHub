package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Event extends Model {
    @Id
    private Long id;
    private String title;
    @Column(length = 500)
    private String description;
    @OneToOne
    private Image image;
    private LocalDate startTime;
    private LocalDate endTime;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Venue venue;
    private static final Finder<Long, Event> finder = new Finder<>(Event.class);

    public Event(Long id, String title, String description, LocalDate startTime, LocalDate endTime, Company company, Category category, Venue venue) {
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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
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
