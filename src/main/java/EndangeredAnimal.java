import java.util.List;
import org.sql2o.*;

public class EndangeredAnimal implements DatabaseManagement {
  private int id;
  private String name;
  private static boolean endangered;
  private String health;
  private String age;

  public static final boolean ENDANGERED_STATUS = true;

  public static final String HEALTH_HEALTHY = "Healthy";
  public static final String HEALTH_FAIR = "Fair";
  public static final String HEALTH_ILL = "Ill";

  public static final String AGE_ADULT = "Adult";
  public static final String AGE_YOUNG = "Young";
  public static final String AGE_NEWBORN = "Newborn";

  public EndangeredAnimal(String name, String health, String age) {
    this.name = name;
    endangered = ENDANGERED_STATUS;
    this.health = health;
    this.age = age;
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

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
      return this.id == newEndangeredAnimal.getId() &&
             this.name.equals(newEndangeredAnimal.getName()) &&
             this.endangered == newEndangeredAnimal.getEndangered() &&
             this.health.equals(newEndangeredAnimal.getHealth()) &&
             this.age.equals(newEndangeredAnimal.getAge());
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String saveEndangeredAnimalQuery = "INSERT INTO animals (name, endangered, health, age) VALUES (:name, :endangered, :health, :age)";
      this.id = (int) con.createQuery(saveEndangeredAnimalQuery, true)
                         .addParameter("name", this.name)
                         .addParameter("endangered", this.endangered)
                         .addParameter("health", this.health)
                         .addParameter("age", this.age)
                         .executeUpdate()
                         .getKey();
    }
  }

  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String allEndangeredAnimalQuery = "SELECT * FROM animals WHERE endangered = :endangered";
      return con.createQuery(allEndangeredAnimalQuery)
                .addParameter("endangered", endangered)
                .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String findEndangeredAnimalQuery = "SELECT * FROM animals WHERE id = :id AND endangered = :endangered";
      return con.createQuery(findEndangeredAnimalQuery)
                .addParameter("id", id)
                .addParameter("endangered", endangered)
                .executeAndFetchFirst(EndangeredAnimal.class);
    }
  }

  public void update(String name, String health, String age) {
    try(Connection con = DB.sql2o.open()) {
      this.name = name;
      this.health = health;
      this.age = age;
      String updateEndangeredAnimalQuery = "UPDATE animals SET name = :name, health = :health, age = :age WHERE id = :id";
      con.createQuery(updateEndangeredAnimalQuery)
         .addParameter("name", name)
         .addParameter("health", health)
         .addParameter("age", age)
         .addParameter("id", id)
         .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteEndangeredAnimalQuery = "DELETE FROM animals WHERE endangered = :endangered AND id = :id";
      con.createQuery(deleteEndangeredAnimalQuery)
         .addParameter("endangered", endangered)
         .addParameter("id", id)
         .executeUpdate();
    }
  }
}
