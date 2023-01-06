package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class EventController extends Controller {
    public EventController() {
    }

    public Result event(Http.Request request){
        return ok(views.html.event.render());
    }

    public Result search(Http.Request request) {
        return ok(views.html.search.render());
    }
}
