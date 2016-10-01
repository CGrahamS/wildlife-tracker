import java.util.Arrays;
import java.util.List;
import org.sql2o.*;

public class Animal implements DatabaseManagement {
  private int id;
  private String name;
  private static boolean endangered;

  public static final boolean ENDANGERED_STATUS = false;

  public Animal(String name) {
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
  public boolean equals(Object otherAnimal) {
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.id == newAnimal.getId() &&
             this.name.equals(newAnimal.getName()) &&
             this.endangered == newAnimal.getEndangered();
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String saveAnimalQuery = "INSERT INTO animals (name, endangered) VALUES (:name, :endangered)";
      this.id = (int) con.createQuery(saveAnimalQuery, true)
                         .addParameter("name", this.name)
                         .addParameter("endangered", this.endangered)
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

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String findAnimalQuery = "SELECT * FROM animals WHERE id = :id";
      return con.createQuery(findAnimalQuery)
                .addParameter("id", id)
                .throwOnMappingFailure(false)
                .executeAndFetchFirst(Animal.class);
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      this.name = name;
      String updateAnimalQuery = "UPDATE animals SET name = :name WHERE id = :id";
      con.createQuery(updateAnimalQuery)
         .addParameter("name", name)
         .addParameter("id", id)
         .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteAnimalQuery = "DELETE FROM animals WHERE endangered = :endangered AND id = :id";
      con.createQuery(deleteAnimalQuery)
         .addParameter("endangered", this.endangered)
         .addParameter("id", this.id)
         .executeUpdate();
    }
  }

}
