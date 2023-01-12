package controllers;

import models.Event;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class HomeController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result home(Http.Request request){
        List<Event> events = Event.getEventList();
        return ok(views.html.home.render(events));

    }
    public Result tutorial(Http.Request request){
        return ok(views.html.tutorial.render());
    }

    public Result styles(Http.Request request){
        return ok(views.html.styles.render());
    }
    public Result signIn(Http.Request request){
        return ok(views.html.sign_in.render());
    }

}
            
