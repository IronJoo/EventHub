package models;

import io.ebean.Model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review extends Model {
    @Id
    private Long id;
    @OneToOne
    private Ticket ticket;
    private Integer rate;
    private Privacy privacy;
    private String title;
    @Column(length = 500)
    private String comment;
    private LocalDateTime date;

    public Review(Integer rate, Privacy privacy, String title, String comment) {
        this.rate = rate;
        this.privacy = privacy;
        this.title = title;
        this.comment = comment;
        date = LocalDateTime.now();
    }
}
