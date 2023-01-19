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
        this.user = user;
    }

    public List<Ticket> getTickets(){
        return finder.all();
    }


    public Ticket getTicketById(Long id){
        return finder.byId(id);
    }
    private static Ticket getStaticTicketById(Long id){
        return finder.byId(id);
    }

    private static Boolean ticketExists(Long id){
        return finder.query().where().eq("id", id).findCount() > 0;
    }

    public static Long generateRandomId() {
        Random random = new Random();
        Long id = (Long.valueOf(random.nextInt((999999999 - 1) + 1)) +1);

        while(ticketExists(id)) {
            id = (Long.valueOf(random.nextInt((999999999 - 1) + 1)) +1);

        }
        return id;
    }
}
