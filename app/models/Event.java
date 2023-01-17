package models;

import io.ebean.ExpressionList;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
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
    private static final Finder<Long, Event> finder = new Finder<>(Event.class);

//    public static List<String> getAppliedFilters(String title, String location, String dateBetween, String dateAnd, String category, String company) {
// this functions is intended to show the user which filters were being applied to his search
//    }


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

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
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

    public String getStartDate(){
        String dayOfTheWeek = startDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String month = startDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String day = Integer.toString(startDateTime.getDayOfMonth());
        return dayOfTheWeek + ", " + month + " " + day;
    }

    public String getStartTime(){
        String hour = Integer.toString(startDateTime.getHour());
        String minute = Integer.toString(startDateTime.getMinute());
        if (startDateTime.getMinute() < 10){
            return hour + ":0" + minute;
        }
        return hour + ":" + minute;
    }

    public String getEndDate(){
        String dayOfTheWeek = endDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String month = endDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String day = Integer.toString(endDateTime.getDayOfMonth());
        return dayOfTheWeek + ", " + month + " " + day;
    }

    public String getEndTime(){
        String hour = Integer.toString(endDateTime.getHour());
        String minute = Integer.toString(endDateTime.getMinute());
        if (endDateTime.getMinute() < 10){
            return hour + ":0" + minute;
        }
        return hour + ":" + minute;
    }

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
                .like("venue.name", "%" + location + "%")
                .like("venue.city", "%" + location + "%")
                .endOr()
                .and()
                .like("category.name", "%" + category + "%")
                .and()
                .like("company.name", "%" + company + "%");
        if ((!dateBetween.equals("")) && dateAnd.equals("")) { //if dateBetween is filled and dateAnd is not
            query.and().like("start_date_time", "%" + dateBetween + "%");
        } else if (dateBetween.equals("") && !dateAnd.equals("")) { //if dateBetween is not filled and dateAnd is
                query.and().like("start_date_time", "%" + dateAnd + "%");
        } else if (!(dateBetween.equals("") && dateAnd.equals(""))) {
            dateAnd = plusOneDay(dateAnd); //it is needed to add one day, because the between() function looks at "below" this date
            query.and().between("start_date_time", dateBetween, dateAnd);
        }
//
//                .and()
//                .like("start_date_time", "%" + dateAnd + "%")

        return query.findList();
    }

    private static String plusOneDay(String stringDate) throws ParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateDate = LocalDate.parse(stringDate, format);
        dateDate = dateDate.plusDays(1);
        stringDate = dateDate.format(format);
        return stringDate;
    }

//        return getEventsByStringCompany(company);


//        List<Company> companies = Company.getCompanies();
//        Long company_id = null;
//        for (Company c : companies){
//            if (c.getName().equals(company)){
//                company_id = c.getId();
//                break;
//            }
//        }
//        List<Event> events = finder.query().where().eq("company_id", Long.toString(company_id)).findList();
//        return events;

//        List<Event> events = finder.query().where().eq("company", company).findList();
//        return events;


//        if(!title.isEmpty()){
//            queryList.eq("company", company);
//        }
//        List<Event> events = 

//            ExpressionList<Event> queryList = finder.query().where().ilike("company", company);




//        List<Event> events = finder.query().where().or()
//                .eq("company_id", company)
//                .ilike("company_id", company)
//                .endOr().findList();

//        List<Event> events = finder.query().where().like("title", "%" + title + "%").findList();
//        return events;
}
