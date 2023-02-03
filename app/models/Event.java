package models;

import io.ebean.ExpressionList;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
public class Event extends Model {
    @Id
    private Long id;
    private String title;
    @Column(length = 1500)
    private String description;
    @OneToOne
    private Image image;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Venue venue;
    @OneToMany
    private List<Section> sections;
    private static final Finder<Long, Event> finder = new Finder<>(Event.class);

    public Event(String title, String description, LocalDateTime startDateTime, LocalDateTime endDateTime, Company company, Category category, Venue venue) {
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.company = company;
        this.category = category;
        this.venue = venue;
    }

    //Begin attributes getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }


    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
    //End attributes getters and setters

    public static List<Event> getUpcomingEventsOfUser(User user) {
        List<Ticket> tickets = Ticket.getTicketsOfUser(user);
        List<Event> upcomingEvents = new ArrayList<>();
        for(Ticket ticket : tickets){
            LocalDateTime eventDate = ticket.getSection().getEvent().getStartDateTime();
            if(eventDate.isAfter(LocalDateTime.now())){
                upcomingEvents.add(ticket.getSection().getEvent());
            }
        }
        return upcomingEvents;
    }

    public static List<Event> getPastEventsOfUser(User user) {
        List<Ticket> tickets = Ticket.getTicketsOfUser(user);
        List<Event> pastEvents = new ArrayList<>();
        for(Ticket ticket : tickets){
            LocalDateTime eventDate = ticket.getSection().getEvent().getStartDateTime();
            if(eventDate.isBefore(LocalDateTime.now())){
                pastEvents.add(ticket.getSection().getEvent());
            }
        }
        return pastEvents;
    }

    //Obtain the three most sold events (not fully developed) to be displayed in the home view under "Popular Events"
    //Only shows upcoming events and orders by startDateTime
    public static List<Event> getTopEventsList() {
        LocalDateTime currentDate = LocalDateTime.now();
        return finder.query().where().gt("start_date_time", currentDate).setMaxRows(3).orderBy("start_date_time").findList();
    }

    //Obtain ONLY the date in String from the startDateTime attribute to be displayed as e.g. "fri, Feb 03"
    public String getStartDate(){
        String dayOfTheWeek = startDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String month = startDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String day = Integer.toString(startDateTime.getDayOfMonth());
        return dayOfTheWeek + ", " + month + " " + day;
    }

    //Obtain ONLY the time in String from the startDateTime attribute to be displayed as e.g. "12:05"
    public String getStartTime(){
        String hour = Integer.toString(startDateTime.getHour());
        String minute = Integer.toString(startDateTime.getMinute());
        if (startDateTime.getMinute() < 10){    //prevents minutes below 10 to be displayed as a single digit
            return hour + ":0" + minute;
        }
        return hour + ":" + minute;
    }

    //Obtain ONLY the date in String from the endDateTime attribute to be displayed as e.g. "fri, Feb 03"
    public String getEndDate(){
        String dayOfTheWeek = endDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String month = endDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String day = Integer.toString(endDateTime.getDayOfMonth());
        return dayOfTheWeek + ", " + month + " " + day;
    }

    //Obtain ONLY the time in String from the endDateTime attribute to be displayed as e.g. "12:05"
    public String getEndTime(){
        String hour = Integer.toString(endDateTime.getHour());
        String minute = Integer.toString(endDateTime.getMinute());
        if (endDateTime.getMinute() < 10){  //prevents minutes below 10 to be displayed as a single digit
            return hour + ":0" + minute;
        }
        return hour + ":" + minute;
    }

    //Begin queries
    public static List<Event> getEventList(){
        return finder.all();
    }

    public static Event getEventById(Long id) {
        return finder.byId(id);
    }

    public static List<Event> getEventsByStringCompany(String company){
        return finder.query().where().like("company.name", company).findList();
    }
    public static List<Event> filter(String title, String location, String dateBetween, String dateAnd, String category, String company) throws ParseException {
        ExpressionList<Event> query = finder.query().where()
                .like("title", "%" + title + "%")
                .and()
                .or()
                .like("venue.name", "%" + location + "%") //the location input will search for both name of venue and city where venue is located
                .like("venue.city", "%" + location + "%")
                .endOr()
                .and()
                .like("category.name", "%" + category + "%")
                .and()
                .like("company.name", "%" + company + "%")
                .orderBy("start_date_time");
        if ((!dateBetween.equals("")) && dateAnd.equals("")) { //if dateBetween is filled and dateAnd is not
            query.and().like("start_date_time", "%" + dateBetween + "%"); //obtains events for specified day ONLY
        } else if (dateBetween.equals("") && !dateAnd.equals("")) { //if dateBetween is not filled and dateAnd is
                query.and().like("start_date_time", "%" + dateAnd + "%"); //obtains events for specified day ONLY
        } else if (!(dateBetween.equals("") && dateAnd.equals(""))) { //if both dateBetween and dateAnd are filled
            dateAnd = plusOneDay(dateAnd); //it is needed to add one day, because the between() function (in next line of code) looks at BEFORE the provided end date
            query.and().between("start_date_time", dateBetween, dateAnd); //obtains events in interval of days
        }
        return query.findList();
    }
    //End queries

    private static String plusOneDay(String stringDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateDate = LocalDate.parse(stringDate, format);
        dateDate = dateDate.plusDays(1);
        stringDate = dateDate.format(format);
        return stringDate;
    }
}
