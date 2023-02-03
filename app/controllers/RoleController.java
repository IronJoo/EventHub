package controllers;

import models.Role;
import models.User;
import models.UserRole;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class RoleController extends Controller {
    private final FormFactory formFactory;
    private static final String ID = "id";
    private static final String ROLE_ID = "role_id";

    @Inject
    public RoleController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    //Provides the User with a selection of Roles they possess
    public Result switchProfile(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get())); //obtains User that is logged in
        List<Role> roles = Role.getRoles();
        List<UserRole> userRoles = UserRole.getListOfRolesOfUser(user); //obtains Roles that that User possesses
        return ok(views.html.switch_profile.render(request, roles, userRoles));
    }

    //Switches Role of User to selected Role
    public Result switchProfileProcess(Http.Request request, Long roleId){
        String userId = request.session().get("id").orElse(null);
        if (userId != null) {
            User user = User.getUserById(Long.parseLong(userId));
            Role role = Role.getRoleById(roleId);
            if (UserRole.userHasRole(user, role)){ //checks if User possesses selected Role
                return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, roleId.toString()); //removes previous Role from session and adds new Role
            }
        }
        return notFound();
    }

    //Associates a Participant Role to a User
    public Result addRoleParticipant(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get())); //obtains User that is logged in
        Role role = Role.getRoleByName("Participant");
        if(!UserRole.userHasRole(user, role)){ //checks if User does not already have that Role
            UserRole userRole = new UserRole(user, role, true);
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString());
        }
        return redirect(routes.RoleController.switchProfile()).flashing("error", "You already have a Participant profile."); //flashes error if User already Participant selected Role
    }

    //Associates a Manager Role to a User
    public Result addRoleManager(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get())); //obtains User that is logged in
        Role role = Role.getRoleByName("Manager");
        if(!UserRole.userHasRole(user, role)){ //checks if User does not already have that Role
            UserRole userRole = new UserRole(user, role, true); //creates new association between User and Role
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString()); //removes previous Role from session and adds new one
        }
        return redirect(routes.RoleController.switchProfile()).flashing("error", "You already have a Manager profile."); //flashes error if User already possesses Manager Role
    }

    //Associates an Admin Role to a User
    public Result addRoleAdmin(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get())); //obtains User that is logged in
        Role role = Role.getRoleByName("Admin");
        if(!UserRole.userHasRole(user, role)){ //checks if User does not already have that Role
            UserRole userRole = new UserRole(user, role, true); //creates new association between User and Role
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString()); //removes previous Role from session and adds new one
        }
        return redirect(routes.RoleController.switchProfile()).flashing("error", "You already have an Admin profile."); //flashes error if User already possesses Manager Role
    }
}


