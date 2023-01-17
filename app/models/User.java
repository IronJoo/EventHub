package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User extends Model {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 50, columnDefinition = "VARCHAR(50) default 'Pending'")
    private Status status = Status.Pending;
    private Company company;
    private Float balance;
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
    private static final Finder<Long, User> finder = new Finder<>(User.class);

    public User(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public static List<User> getUserList(){
        return finder.all();
    }

    public static List<User> getUserByStatus(Status status){
        return finder.query().where().eq("status", status).findList();
    }
}
