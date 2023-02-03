package controllers;

import models.Event;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class HomeController extends Controller {
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String ROLE_ID = "role_id";

    private final FormFactory formFactory;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result home(Http.Request request){
        List<Event> events = Event.getTopEventsList();
        return ok(views.html.home.render(events, request));
    }

    //This view was used for testing/learning purposes
    public Result teste(Http.Request request){
        return ok(views.html.teste.render());
    }

    //This view was used for following various tutorials
    public Result tutorial(Http.Request request){
        return ok(views.html.tutorial.render());
    }

    //This view showcases the system styling (left unfinished)
    public Result styles(Http.Request request){
        return ok(views.html.styles.render(request));
    }

}
            
