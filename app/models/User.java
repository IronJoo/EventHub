package models;

import io.ebean.Finder;
import io.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;

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
    private String city;
    private static final Finder<Long, User> finder = new Finder<>(User.class);

    public User(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public User(String firstName, String lastName, String email, String password, String city) { //EventParticipant
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.balance = 1000F; //awarding 1000 to all EventParticipant for testing purposes
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public Company getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public static User getUserByEmail(String email) {
        return finder.query().where().eq("email", email).setMaxRows(1).findOne();
    }

    public static Boolean userExists(String email){
        return finder.query().where().eq("email", email).findCount() > 0;
    }

    public static Boolean passwordIsRight(String email, String password) {
        User user = getUserByEmail(email);
        return BCrypt.checkpw(password, user.getPassword());

    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public static List<User> getUserList(){
        return finder.all();
    }

    public static List<User> getUserByStatus(Status status){
        return finder.query().where().eq("status", status).findList();
    }

    public static User getUserById(Long id){
        return finder.byId(id);
    }
}
