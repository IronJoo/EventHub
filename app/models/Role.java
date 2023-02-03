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

    //Begin attributes getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    //End attributes getters and setters

    //Begin queries
    public static List<Role> getRoles() {
        return finder.all();
    }

    public static Role getRoleByName(String name){
        return finder.query().where().eq("name", name).findOne();
    }

    public static Role getRoleById(Long id){
        return finder.byId(id);
    }
    //End queries

    //if User has more than one role, this method will return their least privileged role when they log in
    public static Long getLowestRoleIdOfUser(User user) {
        List<UserRole> userRoles = UserRole.getListOfRolesOfUser(user);
        Long lowestRoleId = 3L; //this Long needs to be adapted to the number of available Roles in the application (TODO: created a function to automatically count the number of Roles)
        for(UserRole userRole : userRoles){
            if (userRole.getRole().getId() < lowestRoleId){
                lowestRoleId = userRole.getRole().getId();
            }
        }
        return lowestRoleId;
    }
}
