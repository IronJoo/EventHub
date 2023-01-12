package models;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.List;

@Entity
public class Company extends Model {
    @Id
    private Long id;
    private String name;
    @OneToOne
    private Image image;
    @Column(length = 400)
    private String bio;
    private List<User> users;

    private static final Finder<Long, Company> finder = new Finder<>(Company.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static List<Company> getCompanies(){
        return finder.all();
    }


}
