package models;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Company extends Model {
    @Id
    private Long id;
    private String name;
    private String bio;

}
