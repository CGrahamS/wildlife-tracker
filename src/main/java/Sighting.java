import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;
import java.text.DateFormat;


public class Sighting implements DatabaseManagement {
  private int id;
  private Timestamp sighting_time;
  private String location;
  private String ranger;
  private String health;
  private String age;
  private int animal_id;

  public static final String HEALTH_1 = "Healthy";
  public static final String HEALTH_2 = "Fair";
  public static final String HEALTH_3 = "Poorly";

  public static final String AGE_1 = "Adult";
  public static final String AGE_2 = "Immature";
  public static final String AGE_3 = "Newborn";

  public Sighting(String location, String ranger, int animal_id) {
    this.location = location;
    this.ranger = ranger;
    this.animal_id = animal_id;
  }

  public Sighting(String location, String ranger, String health, String age, int animal_id) {
    this.location = location;
    this.ranger = ranger;
    this.health = health;
    this.age = age;
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

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
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
             this.health.equals(newSighting.getHealth()) &&
             this.age.equals(newSighting.getAge()) &&
             this.animal_id == newSighting.getAnimalId();
    }
  }

  public boolean animalEquals(Object otherSighting) {
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
      String saveSightingQuery = "INSERT INTO sightings (sighting_time, location, ranger, health, age, animal_id) VALUES (now(), :location, :ranger, :health, :age, :animal_id)";
      this.id = (int) con.createQuery(saveSightingQuery, true)
                         .addParameter("location", this.location)
                         .addParameter("ranger", this.ranger)
                         .addParameter("health", this.health)
                         .addParameter("age", this.age)
                         .addParameter("animal_id", this.animal_id)
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

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String findSightingQuery = "SELECT * FROM sightings WHERE id = :id";
      return con.createQuery(findSightingQuery)
                .addParameter("id", id)
                .executeAndFetchFirst(Sighting.class);
    }
  }

  public void update(String location, String ranger) {
    try(Connection con = DB.sql2o.open()) {
      this.location = location;
      this.ranger = ranger;
      String updateSightingQuery = "UPDATE sightings SET location = :location, ranger = :ranger, health = :health, age = :age WHERE id =:id";
      con.createQuery(updateSightingQuery)
         .addParameter("location", location)
         .addParameter("ranger", ranger)
         .addParameter("health", health)
         .addParameter("age", age)
         .addParameter("id", id)
         .executeUpdate();
    }
  }

  @Override
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteSightingQuery = "DELETE FROM sightings WHERE id = :id";
      con.createQuery(deleteSightingQuery)
         .addParameter("id", id)
         .executeUpdate();
    }
  }

}
