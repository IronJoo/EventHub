package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Category extends Model {
    @Id
    private Long id;
    private String name;
    private static final Finder<Long, Category> finder = new Finder<>(Category.class);

    public String getName() {
        return name;
    }

    public static List<Category> getCategoryList(){
        return finder.all();
    }
}
