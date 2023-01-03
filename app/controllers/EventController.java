package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class EventController extends Controller {
    public EventController() {
    }

    public Result event(){
        return ok(views.html.event.render());
    }

    public Result search() {
        return ok(views.html.search.render());
    }
}
