package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Section extends Model {
    @Id
    private Long id;
    @ManyToOne
    private Event event;
    private String name;
    private String description;
    private Integer capacity;
    private Float price;
    private static final Finder<Long, Section> finder = new Finder<>(Section.class);

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public static List<Section> getSectionByEventId(Long id) {
        return finder.query().where().eq("event_id", id).findList();
    }

    public static Section getSectionById(Long id) {
        return finder.query().where().eq("id", id).setMaxRows(1).findOne();
    }
}
