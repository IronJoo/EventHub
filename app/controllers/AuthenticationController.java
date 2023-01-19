package controllers;

import models.Event;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class AuthenticationController extends Controller {
    private final FormFactory formFactory;
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String ROLE_ID = "role_id";

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
                    Http.Session new_session = request.session().adding(EMAIL, email).adding(ID, user.getId().toString());

                    //TODO isto é assim???????? vvv
                    List<Event> events = Event.getEventList();
                    return ok(views.html.home.render(events, request)).withSession(new_session);
                }
            }
        }
        return redirect(routes.AuthenticationController.signIn());
    }

    public Result signUp(Http.Request request){
        return ok(views.html.sign_up.render(request));
    }

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
            }
        }
        return redirect(routes.HomeController.home());
    }

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


    public Result signUpManager(Http.Request request){
        return ok(views.html.sign_up_manager.render());
    }

    public Result passwordReset(Http.Request request){
        return ok(views.html.password_reset.render());
    }
}
