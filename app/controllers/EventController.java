package controllers;

import models.Category;
import models.Event;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.text.ParseException;
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

    public Result filter(Http.Request request) throws ParseException {
        List<Category> categories = Category.getCategoryList();
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        String title = dynamicForm.get("title");
        String location = dynamicForm.get("location");
        String dateBetween = dynamicForm.get("dateBetween");
        String dateAnd = dynamicForm.get("dateAnd");
        String category = dynamicForm.get("category");
        String company = dynamicForm.get("company");
        List<Event> events = Event.filter(title, location, dateBetween, dateAnd, category, company);
        return ok(views.html.searchResults.render(events, categories));
    }
}
