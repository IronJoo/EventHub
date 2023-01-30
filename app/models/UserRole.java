package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class UserRole extends Model {
    @ManyToOne
    @Column(nullable = false)
    private User user;

    @ManyToOne
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean is_active = false;

    private static final Finder<User, UserRole> finder = new Finder<>(UserRole.class);

    public UserRole(User user, Role role, Boolean is_active) {
        this.user = user;
        this.role = role;
        this.is_active = is_active;
    }

    public Role getRole() {
        return role;
    }

    public static List<UserRole> getListOfRolesOfUser(User user){
        return finder.query().where().eq("user_id", user.getId()).findList();
    }

}
