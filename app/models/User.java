package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User extends Model {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private String status;

    public User(String name, String email, String password, String status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }
}
