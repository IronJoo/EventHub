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
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String ROLE_ID = "role_id";

    private final FormFactory formFactory;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result home(Http.Request request){
        List<Event> events = Event.getEventList();
        System.out.println(request.session().get(ID));
        System.out.println(request.session().get(EMAIL));
        System.out.println(request.session().get(ROLE_ID));
        return ok(views.html.home.render(events, request));

    }

    public Result teste(Http.Request request){
        return ok(views.html.teste.render());
    }
    public Result tutorial(Http.Request request){
        return ok(views.html.tutorial.render());
    }

    public Result styles(Http.Request request){
        return ok(views.html.styles.render(request));
    }

}
            
