package models;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Role extends Model {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany
    private List<User> users;
}
