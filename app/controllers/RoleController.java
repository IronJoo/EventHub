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

    public Result switchProfile(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get()));
        List<Role> roles = Role.getRoles();
        List<UserRole> userRoles = UserRole.getListOfRolesOfUser(user);
//        if(UserRole.userHasRole(user,))
        if(userRoles.contains( Role.getRoleByName("Participant"))){
            System.out.println("if(!userRoles.contains(Role.getRoleByName(\"Participant\")))");
        }
        if(userRoles.contains(Role.getRoleByName("Manager"))){
            System.out.println("if(!userRoles.contains(Role.getRoleByName(\"Manager\")))");
        }
        if(userRoles.contains(Role.getRoleByName("Admin"))){
            System.out.println("if(!userRoles.contains(Role.getRoleByName(\"Admin\")))");
        }
        return ok(views.html.switch_profile.render(request, roles, userRoles));
    }
    public Result switchProfileProcess(Http.Request request, Long roleId){
        String userId = request.session().get("id").orElse(null);
        if (userId != null) {
            User user = User.getUserById(Long.parseLong(userId));
            Role role = Role.getRoleById(roleId);
            if (UserRole.userHasRole(user, role)){
                return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, roleId.toString());
            }
        }
        return notFound();
    }

    public Result addRoleParticipant(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get()));
        Role role = Role.getRoleByName("Participant");
        if(!UserRole.userHasRole(user, role)){
            UserRole userRole = new UserRole(user, role, true);
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString());
        }
        return redirect(routes.RoleController.switchProfile()).flashing("error", "You already have a Participant profile.");
    }

    public Result addRoleManager(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get()));
        Role role = Role.getRoleByName("Manager");
        if(!UserRole.userHasRole(user, role)){
            UserRole userRole = new UserRole(user, role, true);
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString());
        }
        return redirect(routes.RoleController.switchProfile()).flashing("error", "You already have a Manager profile.");
    }

    public Result addRoleAdmin(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get()));
        Role role = Role.getRoleByName("Admin");
        if(!UserRole.userHasRole(user, role)){
            UserRole userRole = new UserRole(user, role, true);
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString());
        }
        return redirect(routes.RoleController.switchProfile()).flashing("error", "You already have an Admin profile.");
    }
}


