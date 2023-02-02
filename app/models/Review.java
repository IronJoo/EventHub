package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review extends Model {
    @Id
    private Long id;
    @OneToOne
    private Ticket ticket;
    private Integer rate;
    @Enumerated(EnumType.STRING)
    private Privacy privacy;
    private String title;
    @Column(length = 500)
    private String comment;
    private LocalDateTime date;
    private static final Finder<Long, Review> finder = new Finder<>(Review.class);

    public Review(Integer rate, Ticket ticket, Privacy privacy, String title, String comment) {
        this.rate = rate;
        this.ticket = ticket;
        this.privacy = privacy;
        this.title = title;
        this.comment = comment;
        date = LocalDateTime.now();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public Integer getRate() {
        return rate;
    }

    public static List<Review> getReviewsByEvent(Event event) {
        return finder.query().where().eq("ticket.section.event.id", event.getId()).findList();
    }

}
