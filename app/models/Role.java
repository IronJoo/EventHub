package models;

import io.ebean.Finder;
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
    private static final Finder<Long, Role> finder = new Finder<>(Role.class);

    public Long getId() {
        return id;
    }

    public static Role getRoleByName(String name){
        return finder.query().where().eq("name", name).findOne();
    }

    public static Role getRoleById(Long id){
        return finder.byId(id);
    }
    public static Long getLowestRoleIdOfUser(User user) {
        List<UserRole> userRoles = UserRole.getListOfRolesOfUser(user);
        Long lowestRoleId = 1L;
        for(UserRole userRole : userRoles){
            if (userRole.getRole().getId() > lowestRoleId){
                lowestRoleId = userRole.getRole().getId();
            }
        }
        return lowestRoleId;
    }
}
