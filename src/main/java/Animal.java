import java.util.Arrays;
import java.util.List;
import org.sql2o.*;

public class Animal implements DatabaseManagement {
  private int id;
  private String name;
  private String type;

  public static final String DATABASE_TYPE = "Non-Endangered";

  public Animal(String name) {
    this.name = name;
    type = DATABASE_TYPE;
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

  @Override
  public boolean equals(Object otherAnimal) {
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.id == newAnimal.getId() &&
             this.name.equals(newAnimal.getName());
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String saveAnimalQuery = "INSERT INTO animals (name) VALUES (:name)";
      this.id = (int) con.createQuery(saveAnimalQuery, true)
                         .addParameter("name", this.name)
                         .executeUpdate()
                         .getKey();
    }
  }

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      String allAnimalQuery = "SELECT * FROM animals";
      return con.createQuery(allAnimalQuery)
                .throwOnMappingFailure(false)
                .executeAndFetch(Animal.class);
    }
  }

}
