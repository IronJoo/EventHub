package controllers;

import models.*;
import org.mindrot.jbcrypt.BCrypt;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

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
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request); //obtains user inputs
        String email = dynamicForm.get("email");
        String password = dynamicForm.get("password");
        if (allInputsProvided(email, password)){ //checks if all inputs were filled in
            User user = User.getUserByEmail(email);
            if (user != null){ //checks if email is registered
                if (User.passwordIsRight(email, password)){ //checks if password matches password in database; I don't know why I made this to be a User static function, could have implemented it here
                    if(user.getStatus() == Status.Approved){ //if user status is Approved, they are logged in
                        return redirect(routes.HomeController.home())
                                .addingToSession(request, EMAIL, email)
                                .addingToSession(request, ID, user.getId().toString())
                                .addingToSession(request, ROLE_ID, Role.getLowestRoleIdOfUser(user).toString());
                    } else if (user.getStatus() == Status.Pending){ //if user status is Pending, log in is halted and message is flashed
                        return redirect(routes.AuthenticationController.signIn()).flashing("error", "Your account is pending verification by our staff. Please wait a few moments before signing in.");
                    } else if (user.getStatus() == Status.Rejected){ //if user status is Rejected, they cannot log in and error is flashed
                        return redirect(routes.AuthenticationController.signIn()).flashing("error", "Your account has been rejected. Please contact support.");
                    }
                }
            }
        }
        return redirect(routes.AuthenticationController.signIn()).flashing("error", "Invalid email or password"); //flashes invalid email or password if not all of the above conditions are met
    }

    //Render Sign Up for Participant
    public Result signUp(Http.Request request){
        return ok(views.html.sign_up.render(request));
    }

    //Sign Up Process for Participant
    public Result signUpProcess(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request); //obtains user inputs
        String email = dynamicForm.get("email");
        String password = BCrypt.hashpw(dynamicForm.get("password"), BCrypt.gensalt()); //creates password hash
        String firstName = dynamicForm.get("firstName");
        String lastName = dynamicForm.get("lastName");
        String city = dynamicForm.get("city");
        if (allInputsProvided(email, password, firstName, lastName, city)){ //checks if all inputs were filled in
            if (!emailExists(email)){ //checks if email is available in database
                User user = new User(firstName, lastName, email, password, city);
                user.save();
                user.refresh();
                UserRole userRole = new UserRole(user, Role.getRoleByName(PARTICIPANT), false); //boolean is_active is present but not being used for now
                userRole.save();
//                userRole.refresh();
                return redirect(routes.AuthenticationController.signIn()).flashing("error", "Your account is pending verification by our staff. Please wait a few moments before signing in.");
            }
        }
        return redirect(routes.AuthenticationController.signUp()).flashing("error", "Email already taken"); //flashes error if email is already registered
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
                return redirect(routes.AuthenticationController.signIn()).flashing("error", "Your account is pending verification by our staff. Please wait a few moments before signing in.");
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
                return redirect(routes.AuthenticationController.signIn()).flashing("error", "Your account is pending verification by our staff. Please wait a few moments before signing in.");
            }
        }
        return redirect(routes.AuthenticationController.signUpAdmin()).flashing("error", "Email already taken");
    }
    //End of functions with duplicated code fragments

    public Result logoutProcess(Http.Request request){
        return redirect(routes.HomeController.home()).withNewSession(); //creates new session when User logs out
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
