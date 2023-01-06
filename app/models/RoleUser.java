package models;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RoleUser extends Model {
    @ManyToOne
    @Column(nullable = false)
    private User user;

    @ManyToOne
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean is_active = false;
}
