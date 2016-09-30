import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;


public class Sighting implements DatabaseManagement {
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

  public int getId() {
    return id;
  }

  public String getLocation() {
    return location;
  }

  public String getRanger() {
    return ranger;
  }

  public Timestamp getSightingTime() {
    return sighting_time;
  }

  public int getAnimalId() {
    return animal_id;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.id == newSighting.getId() &&
             this.location.equals(newSighting.getLocation()) &&
             this.ranger.equals(newSighting.getRanger()) &&
             this.animal_id == newSighting.getAnimalId();
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String saveSightingQuery = "INSERT INTO sightings (location, ranger, sighting_time, animal_id) VALUES (:location, :ranger, now(), :animal_id)";
      this.id = (int) con.createQuery(saveSightingQuery, true)
                         .addParameter("location", this.location)
                         .addParameter("ranger", this.ranger)
                         .addParameter("animal_id", animal_id)
                         .executeUpdate()
                         .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String allSightingQuery = "SELECT * FROM sightings";
      return con.createQuery(allSightingQuery)
                .executeAndFetch(Sighting.class);
    }
  }

}
