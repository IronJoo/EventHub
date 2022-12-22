package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Section extends Model {
    @Id
    private Long id;
    @ManyToOne
    private Event event;
    private String name;
    private Integer capacity;
    private Float price;
}
