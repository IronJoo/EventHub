package controllers;

import models.Role;
import models.User;
import models.UserRole;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

public class RoleController extends Controller {
    private final FormFactory formFactory;
    private static final String ID = "id";
    private static final String ROLE_ID = "role_id";

    @Inject
    public RoleController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    public Result addRoleParticipant(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get()));
        Role role = Role.getRoleByName("Participant");
        if(!UserRole.userHasRole(user, role)){
            UserRole userRole = new UserRole(user, role, true);
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString());
        }
        return redirect(routes.AuthenticationController.switchProfile()).flashing("error", "You already have a Participant profile.");
    }

    public Result addRoleManager(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get()));
        Role role = Role.getRoleByName("Manager");
        if(!UserRole.userHasRole(user, role)){
            UserRole userRole = new UserRole(user, role, true);
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString());
        }
        return redirect(routes.AuthenticationController.switchProfile()).flashing("error", "You already have a Manager profile.");
    }

    public Result addRoleAdmin(Http.Request request){
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get()));
        Role role = Role.getRoleByName("Admin");
        if(!UserRole.userHasRole(user, role)){
            UserRole userRole = new UserRole(user, role, true);
            userRole.save();
            return redirect(routes.HomeController.home()).removingFromSession(request, ROLE_ID).addingToSession(request, ROLE_ID, role.getId().toString());
        }
        return redirect(routes.AuthenticationController.switchProfile()).flashing("error", "You already have an Admin profile.");
    }
}


