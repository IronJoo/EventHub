package controllers;

import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class EventController extends Controller {

    private final FormFactory formFactory;
    @Inject
    public EventController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result event(Http.Request request, Long id){
        Event event = Event.getEventById(id);
        List<Section> sections = Section.getSectionByEventId(id);
        if(event == null){
            return notFound();
        }
        return ok(views.html.event.render(event, sections, request));
    }

    public Result event_no_id(Http.Request request){
        return notFound();
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
//        List<String> filters = Event.getAppliedFilters(title, location, dateBetween, dateAnd, category, company);
        return ok(views.html.search_results.render(events, categories));
    }

    public Result purchaseTicket(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        Long sectionId = Long.parseLong(dynamicForm.get("section_id"));
        Section section = Section.getSectionById(sectionId);
        User user = User.getUserById(2L);
        if (user.getBalance() - section.getPrice() >= 0){
            user.setBalance(user.getBalance() - section.getPrice());
            Ticket ticket = new Ticket(Ticket.generateRandomId(), section, user);
            user.save();
            user.refresh();
            ticket.save();
            ticket.refresh();
        }
        return redirect(routes.HomeController.home());
    }

    public Result createEvent(Http.Request request){
        List<Category> categories = Category.getCategoryList();
        List<Venue> venues = Venue.getVenueList();
        LocalDate minDate = LocalDate.now();
        return ok(views.html.create_event.render(request, categories, venues , minDate));
    }

    public Result createEventProcess(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        String title = dynamicForm.get("title");
        String categoryName = dynamicForm.get("category");
        String venueName = dynamicForm.get("venue");
        String startDateString = dynamicForm.get("startDate");
        String startTimeString = dynamicForm.get("startTime");
        String endDateString = dynamicForm.get("endDate");
        String endTimeString = dynamicForm.get("endTime");
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        String description = dynamicForm.get("description");
        String sectionName = dynamicForm.get("sectionName");
        String sectionDescription = dynamicForm.get("sectionDescription");
        String quantity = dynamicForm.get("quantity");
        String price = dynamicForm.get("price");

        //Assigning undefined company until eventmanager role is working, change later!!!
        //TODO
        Company company = Company.getCompanyById(7L);
        Category category = Category.getCategoryByName(categoryName);
        Venue venue = Venue.getVenueByName(venueName);
        Event event = new Event(title, description, startDateTime, endDateTime, company, category, venue);
        event.save();
        event.refresh();
        Section section = new Section(event, sectionName, sectionDescription, Integer.parseInt(quantity), Float.parseFloat(price));
        section.save();
        section.refresh();
        return redirect(routes.EventController.event(event.getId()));
    }
}
