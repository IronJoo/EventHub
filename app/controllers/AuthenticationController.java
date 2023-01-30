package controllers;

import models.Event;
import models.Role;
import models.User;
import models.UserRole;
import org.mindrot.jbcrypt.BCrypt;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationController extends Controller {
    private final FormFactory formFactory;
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String ROLE_ID = "role_id";
    private static final String ADMIN = "Admin";
    private static final String MANAGER = "Manager";
    private static final String PARTICIPANT = "Participant";

    @Inject
    public AuthenticationController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    public Result signIn(Http.Request request){
        return ok(views.html.sign_in.render(request));
    }

    public Result signInProcess(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        String email = dynamicForm.get("email");
        String password = dynamicForm.get("password");
        if (allInputsProvided(email, password)){
            User user = User.getUserByEmail(email);
            if (user != null){
                if (User.passwordIsRight(email, password)){
                    return redirect(routes.HomeController.home())
                            .addingToSession(request, EMAIL, email)
                            .addingToSession(request, ID, user.getId().toString())
                            .addingToSession(request, ROLE_ID, Role.getLowestRoleIdOfUser(user).toString());
                }
            }
        }
        return redirect(routes.AuthenticationController.signIn()).flashing("error", "Invalid username or password");
    }

    //Render Sign Up for Participant
    public Result signUp(Http.Request request){
        return ok(views.html.sign_up.render(request));
    }

    //Sign Up Process for Participant
    public Result signUpProcess(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        String email = dynamicForm.get("email");
        String password = BCrypt.hashpw(dynamicForm.get("password"), BCrypt.gensalt());
        String firstName = dynamicForm.get("firstName");
        String lastName = dynamicForm.get("lastName");
        String city = dynamicForm.get("city");
        if (allInputsProvided(email, password, firstName, lastName, city)){
            if (!emailExists(email)){
                User user = new User(firstName, lastName, email, password, city);
                user.save();
                user.refresh();
                UserRole userRole = new UserRole(user, Role.getRoleByName(PARTICIPANT), false);
                userRole.save();
//                userRole.refresh();
                return redirect(routes.HomeController.home());
            }
        }
        return redirect(routes.AuthenticationController.signUp()).flashing("error", "Email already taken");
    }

    public Result signUpManager(Http.Request request){
        return ok(views.html.sign_up_manager.render(request));
    }
    //The following functions use duplicated code fragments because I'm running out of time to do things properly! Copy and paste is faster
    public Result signUpManagerProcess(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        String email = dynamicForm.get("email");
        String password = BCrypt.hashpw(dynamicForm.get("password"), BCrypt.gensalt());
        String firstName = dynamicForm.get("firstName");
        String lastName = dynamicForm.get("lastName");
        if (allInputsProvided(email, password, firstName, lastName)){
            if (!emailExists(email)){
                User user = new User(firstName, lastName, email, password);
                user.save();
                user.refresh();
                UserRole userRole = new UserRole(user, Role.getRoleByName(MANAGER), false);
                userRole.save();
//                userRole.refresh();
                return redirect(routes.HomeController.home());
            }
        }
        return redirect(routes.AuthenticationController.signUpManager()).flashing("error", "Email already taken");
    }
    public Result signUpAdminProcess(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        String email = dynamicForm.get("email");
        String password = BCrypt.hashpw(dynamicForm.get("password"), BCrypt.gensalt());
        String firstName = dynamicForm.get("firstName");
        String lastName = dynamicForm.get("lastName");
        if (allInputsProvided(email, password, firstName, lastName)){
            if (!emailExists(email)){
                User user = new User(firstName, lastName, email, password);
                user.save();
                user.refresh();
                UserRole userRole = new UserRole(user, Role.getRoleByName(ADMIN), false);
                userRole.save();
//                userRole.refresh();
                return redirect(routes.HomeController.home());
            }
        }
        return redirect(routes.AuthenticationController.signUpAdmin()).flashing("error", "Email already taken");
    }
    //End of functions with duplicated code fragments

    public Result logoutProcess(Http.Request request){
        return redirect(routes.HomeController.home())
                .removingFromSession(request, ID)
                .removingFromSession(request, EMAIL)
                .removingFromSession(request, ROLE_ID);
    }

    public Result signUpAdmin(Http.Request request){
        return ok(views.html.sign_up_admin.render(request));
    }


    public Result passwordReset(Http.Request request){
        return ok(views.html.password_reset.render());
    }

    //Private methods
    private boolean emailExists(String email) {
        User user = User.getUserByEmail(email);
        if (user != null){
            return true;
        }
        return false;
    }

    private boolean allInputsProvided(String email, String password) {
        if (email != null && password != null && !email.isEmpty() && !password.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean allInputsProvided(String email, String password, String firstName, String lastName, String city) {
        if (email != null && password != null && firstName != null && lastName != null && city != null &&
                !email.isEmpty() && !password.isEmpty() && !firstName.isEmpty() &&
                !lastName.isEmpty() && !city.isEmpty()) {
            return true;
        }
        return false;
    }
    private boolean allInputsProvided(String email, String password, String firstName, String lastName) {
        if (email != null && password != null && firstName != null && lastName != null &&
                !email.isEmpty() && !password.isEmpty() && !firstName.isEmpty() &&
                !lastName.isEmpty()) {
            return true;
        }
        return false;
    }
}
