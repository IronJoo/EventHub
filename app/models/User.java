package models;

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
    private String status;
    private Float balance;
    @ManyToMany
    private List<Role> roles;
    @OneToMany
    private List<Ticket> tickets;
}
