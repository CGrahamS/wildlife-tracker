import java.util.List;
import org.sql2o.*;

public class EndangeredAnimal implements DatabaseManagement {
  private int id;
  private String name;
  private String type;
  private String health;
  private String age;

  public static final String DATABASE_TYPE = "Endangered";

  public static final String HEALTH_HEALTHY = "Healthy";
  public static final String HEALTH_FAIR = "Fair";
  public static final String HEALTH_ILL = "Ill";

  public static final String AGE_ADULT = "Adult";
  public static final String AGE_YOUNG = "Young";
  public static final String AGE_NEWBORN = "Newborn";

  public EndangeredAnimal(String name, String health, String age) {
    this.name = name;
    type = DATABASE_TYPE;
    this.health = health;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
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
             this.type.equals(newEndangeredAnimal.getType()) &&
             this.health.equals(newEndangeredAnimal.getHealth()) &&
             this.age.equals(newEndangeredAnimal.getAge());
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String saveEndangeredAnimalQuery = "INSERT INTO animals (name, type, health, age) VALUES (:name, :type, :health, :age)";
      this.id = (int) con.createQuery(saveEndangeredAnimalQuery, true)
                         .addParameter("name", this.name)
                         .addParameter("type", this.type)
                         .addParameter("health", this.health)
                         .addParameter("age", this.age)
                         .executeUpdate()
                         .getKey();
    }
  }

  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String allEndangeredAnimalQuery = "SELECT * FROM animals WHERE type = :type";
      return con.createQuery(allEndangeredAnimalQuery)
                .addParameter("type", EndangeredAnimal.DATABASE_TYPE)
                .executeAndFetch(EndangeredAnimal.class);
    }
  }
}
