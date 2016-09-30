import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;


public class Sighting {
  private int id;
  private String location;
  private String ranger;
  private Timestamp sighting_time;
  private int animal_id;

  public Sighting(String location, String ranger, int animal_id) {
    this.location = location;
    this.ranger = ranger;
    this.animal_id = animal_id;
  }

  public String getLocation() {
    return location;
  }

  public String getRanger() {
    return ranger;
  }

  public int getAnimalId() {
    return anim;
  }

}
