package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ticket extends Model {
    @Id
    private Long id;
    @ManyToOne
    private Event event;
    @ManyToOne
    private Section section;
    private User user;
}
