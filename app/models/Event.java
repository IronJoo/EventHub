package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Event extends Model {
    @Id
    private Long id;
    private String title;
    private Date dateTime;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Venue venue;
}
