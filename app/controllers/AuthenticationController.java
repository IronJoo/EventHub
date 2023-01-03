package controllers;

import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class AuthenticationController extends Controller {
    private final FormFactory formFactory;

    @Inject
    public AuthenticationController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    public Result signIn(){
        return ok(views.html.sign_in.render());
    }

    public Result signUp(){
        return ok(views.html.sign_up.render());
    }
    public Result signUpManager(){
        return ok(views.html.sign_up_manager.render());
    }

    public Result passwordReset(){
        return ok(views.html.password_reset.render());
    }
}
