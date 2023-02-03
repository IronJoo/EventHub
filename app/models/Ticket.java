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

    //Begin attributes getters and setters
    public User getUser() {
        return user;
    }
    public Section getSection() {
        return section;
    }

    //End attributes getters and setters

    //Begin queries
    public static Ticket getTicketFromUserForEvent(User user, Event event) {
        return finder.query().where()
                .eq("user_id", user.getId())
                .eq("section.event.id", event.getId()).findOne();
    }

    public static List<Ticket> getTicketsOfUser(User user) {
        return finder.query().where().eq("user_id", user.getId()).findList();
    }

    public List<Ticket> getTickets(){
        return finder.all();
    }

    public Ticket getTicketById(Long id){
        return finder.byId(id);
    }
    //End queries

    private static Boolean ticketExists(Long id){
        return finder.query().where().eq("id", id).findCount() > 0;
    }

    //Generates random ticket number between 1 and 999999999
    public static Long generateRandomId() {
        Random random = new Random();
        Long id = (Long.valueOf(random.nextInt((999999999 - 1) + 1)) +1);

        while(ticketExists(id)) { //Repeats generation if number already exists
            id = (Long.valueOf(random.nextInt((999999999 - 1) + 1)) +1);

        }
        return id;
    }
}
