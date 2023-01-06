package controllers;

import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

public class AuthenticationController extends Controller {
    private final FormFactory formFactory;

    @Inject
    public AuthenticationController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    public Result signIn(Http.Request request){
        return ok(views.html.sign_in.render());
    }

    public Result signUp(Http.Request request){
        return ok(views.html.sign_up.render(request));
    }
    public Result signUpManager(Http.Request request){
        return ok(views.html.sign_up_manager.render());
    }

    public Result passwordReset(Http.Request request){
        return ok(views.html.password_reset.render());
    }
}
