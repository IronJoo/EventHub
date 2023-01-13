package controllers;

import models.Category;
import models.Event;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class EventController extends Controller {

    private final FormFactory formFactory;
    @Inject
    public EventController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result event(Http.Request request, Long id){
        Event event = Event.getEventById(id);
        if(event == null){
            return notFound();
        }
        return ok(views.html.event.render(event));
    }

    public Result search(Http.Request request) {
        List<Category> categories = Category.getCategoryList();
        return ok(views.html.search.render(categories));
    }

    public Result filter(Http.Request request, String title, String location, String dateBetween, String dateAnd, String category, String company){
        List<Event> events = Event.filter(title, location, dateBetween, dateAnd, category, company);
        List<Category> categories = Category.getCategoryList();
        return ok(views.html.searchResults.render(events, categories));
    }
}
