package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venue extends Model {
    @Id
    private Long id;
    private String address;
    private String City;
}
