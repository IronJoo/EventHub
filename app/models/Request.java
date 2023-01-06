package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Request extends Model {
    @OneToOne
    private User user;
    @OneToOne
    private Company company;
    private Status status;
}
