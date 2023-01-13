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

    public Result filter(Http.Request request){
        List<Category> categories = Category.getCategoryList();
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        System.out.println(dynamicForm.get("title"));
        System.out.println(dynamicForm.get("location"));
//        System.out.println(dynamicForm.get("dateBetween"));
        String date = dynamicForm.get("dateBetween");
        System.out.println(date);
        System.out.println(dynamicForm.get("dateAnd"));
        System.out.println(dynamicForm.get("category"));
        System.out.println(dynamicForm.get("company"));
//        List<Event> events = Event.filter(title, location, dateBetween, dateAnd, category, company);
        List<Event> events = Event.getEventList();

        return ok(views.html.searchResults.render(events, categories));
    }
}
