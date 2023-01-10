package models;

import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role extends Model {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
}
