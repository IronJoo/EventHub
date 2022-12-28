package controllers;

import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;

public class HomeController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result home(){
        System.out.println();
        return ok(views.html.home.render());

    }
    public Result tutorial(){
        return ok(views.html.tutorial.render());
    }

}
            
