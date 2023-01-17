package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Random;

@Entity
public class Ticket extends Model {
    @Id
    private Long id;
//    @ManyToOne
//    private Event event;
    @ManyToOne
    private Section section;
    @ManyToOne
    private User user;

    private static final Finder<Long, Ticket> finder = new Finder<>(Ticket.class);

    public Ticket(Long id, Section section, User user) {
        this.id = id;
        this.section = section;
    }

    public List<Ticket> getTickets(){
        return finder.all();
    }

    public static Long generateRandomId() {
        Random random = new Random();
        Long number = (long)(random.nextDouble()*1000000000L);
        return number;
    }
}
