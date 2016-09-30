import java.util.List;
import org.sql2o.*;

public class EndangeredAnimal implements DatabaseManagement {
  private int id;
  private String name;
  private boolean endangered = ENDANGERED;
  private String health;
  private String age;

  public static final boolean ENDANGERED = true;

  public static final String HEALTH_HEALTHY = "Healthy";
  public static final String HEALTH_FAIR = "Fair";
  public static final String HEALTH_ILL = "Ill";

  public static final String AGE_ADULT = "Adult";
  public static final String AGE_YOUNG = "Young";
  public static final String AGE_NEWBORN = "Newborn";

  public EndangeredAnimal(String name, String health, String age) {
    this.name = name;
    this.health = health;
    this.age = age;
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
      return this.name.equals(newEndangeredAnimal.getName()) &&
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
                         .addParameter("name", name)
                         .addParameter("endangered", endangered)
                         .addParameter("age", age)
                         .addParameter("health", health)
                         .executeUpdate()
                         .getKey();
    }
  }

  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String allEndangeredAnimalQuery = "SELECT * FROM animals WHERE endangered = true";
      return con.createQuery(allEndangeredAnimalQuery)
                .addParameter("endangered", EndangeredAnimal.ENDANGERED)
                .executeAndFetch(EndangeredAnimal.class);
    }
  }
}
