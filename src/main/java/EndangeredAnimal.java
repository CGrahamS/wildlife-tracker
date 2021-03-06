import java.util.List;
import org.sql2o.*;

public class EndangeredAnimal implements DatabaseManagement {
  private int id;
  private String name;
  private static boolean endangered;

  public static final boolean ENDANGERED_STATUS = true;

  public EndangeredAnimal(String name) {
    this.name = name;
    endangered = ENDANGERED_STATUS;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean getEndangered() {
    return endangered;
  }

  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
      return this.id == newEndangeredAnimal.getId() &&
             this.name.equals(newEndangeredAnimal.getName()) &&
             this.endangered == newEndangeredAnimal.getEndangered();
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String saveEndangeredAnimalQuery = "INSERT INTO animals (name, endangered) VALUES (:name, :endangered)";
      this.id = (int) con.createQuery(saveEndangeredAnimalQuery, true)
                         .addParameter("name", this.name)
                         .addParameter("endangered", this.endangered)
                         .executeUpdate()
                         .getKey();
    }
  }

  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String allEndangeredAnimalQuery = "SELECT * FROM animals WHERE endangered = :endangered";
      return con.createQuery(allEndangeredAnimalQuery)
                .addParameter("endangered", EndangeredAnimal.ENDANGERED_STATUS)
                .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(String name, int id) {
    try(Connection con = DB.sql2o.open()) {
      String findEndangeredAnimalQuery = "SELECT * FROM animals WHERE name = :name AND id = :id";
      return con.createQuery(findEndangeredAnimalQuery)
                .addParameter("name", name)
                .addParameter("id", id)
                .executeAndFetchFirst(EndangeredAnimal.class);
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      this.name = name;
      String updateEndangeredAnimalQuery = "UPDATE animals SET name = :name WHERE id = :id";
      con.createQuery(updateEndangeredAnimalQuery)
         .addParameter("name", name)
         .addParameter("id", id)
         .executeUpdate();
    }
  }

  @Override
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteEndangeredAnimalQuery = "DELETE FROM animals WHERE endangered = :endangered AND id = :id";
      con.createQuery(deleteEndangeredAnimalQuery)
         .addParameter("endangered", endangered)
         .addParameter("id", id)
         .executeUpdate();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String getSightingsQuery = "SELECT * FROM sightings WHERE animal_id = :id ORDER BY sighting_time";
      return con.createQuery(getSightingsQuery)
                .addParameter("id", id)
                .executeAndFetch(Sighting.class);
    }
  }
}
