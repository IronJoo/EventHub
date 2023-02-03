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
    private static final String ID = "id";

    private final FormFactory formFactory;
    @Inject
    public EventController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    //Displays event page according to provided Event id
    public Result event(Http.Request request, Long id){
        Event event = Event.getEventById(id);
        if(event == null){
            return notFound();
        }
        List<Section> sections = Section.getSectionByEventId(id);
        LocalDateTime currentDate = LocalDateTime.now();
        List<Review> reviews = Review.getReviewsByEvent(event);
        return ok(views.html.event.render(event, sections, currentDate, request, reviews));
    }

    //returns notFound() if User enters event page without providing an id
    public Result event_no_id(Http.Request request){
        return notFound();
    }

    public Result search(Http.Request request) {
        List<Category> categories = Category.getCategoryList();
        return ok(views.html.search.render(categories, request));
    }

    //Filters search according to User input
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
        return ok(views.html.search_results.render(events, categories, request));
    }

    //Participant purchases a Ticket
    public Result purchaseTicket(Http.Request request){
        Long userId = Long.parseLong(request.session().get(ID).get()); //checks who is logged in to associate ticket purchase to logged User
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        Long sectionId = Long.parseLong(dynamicForm.get("section_id"));
        Section section = Section.getSectionById(sectionId); //obtains Section being purchased by User
        User user = User.getUserById(userId);
        if (user.getBalance() - section.getPrice() >= 0){ //if User has enough balance to make purchase
            user.setBalance(user.getBalance() - section.getPrice()); //reduces User balance
            Ticket ticket = new Ticket(Ticket.generateRandomId(), section, user); //generate new Ticket with random number id
            section.setCapacity(section.getCapacity() - 1); //reduces ticket availability by 1
            section.save();
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
        List<String> cities = Venue.getCities();
        LocalDate minDate = LocalDate.now(); //variable used to force Managers to create Events for upcoming dates only
        return ok(views.html.create_event.render(request, categories, venues , cities, minDate));
    }

    //Creates new Event with Section
    public Result createEventProcess(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request); //obtains User inputs
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

        //Assigning a manually chosen Company until Manager association to a Company has been implemented! TODO
        Company company = Company.getCompanyById(4L);
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

    public Result addVenue(Http.Request request){
        return ok(views.html.add_venue.render(request));
    }

    //Create a new Venue
    public Result addVenueProcess(Http.Request request){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        String name = dynamicForm.get("venueName");
        String address = dynamicForm.get("address");
        String city = dynamicForm.get("city");
        Venue venue = new Venue(name, address, city);
        venue.save();
        venue.refresh();
        return redirect(routes.EventController.createEvent());
    }

    public Result writeReview(Http.Request request, Long id){
        Event event = Event.getEventById(id);
        return ok(views.html.write_review.render(request, event));
    }

    //Creates new Review
    public Result writeReviewProcess(Http.Request request, Long id){
        DynamicForm dynamicForm = this.formFactory.form().bindFromRequest(request);
        Integer rating = Integer.parseInt(dynamicForm.get("rating"));
        String title = dynamicForm.get("title");
        String description = dynamicForm.get("description");
        Privacy privacy = Privacy.PUBLIC; //Privacy set to public as default
        User user = User.getUserById(Long.parseLong(request.session().get(ID).get())); //obtains User who is logged in to associate them to Review
        Event event = Event.getEventById(id);
        Ticket ticket = Ticket.getTicketFromUserForEvent(user, event); //obtains Ticket purchased by User
        if(dynamicForm.get("privacy") == "semiPrivate"){ //Privacy is set to semi private if so is requested by User
            privacy = Privacy.SEMIPRIVATE;
        }
        Review review = new Review(rating, ticket, privacy, title, description);
        review.save();
        review.refresh();
        return redirect(routes.EventController.event(id));
    }
}
