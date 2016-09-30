import java.util.List;
import org.sql2o.*;

public class EndangeredAnimal extends Animal {
  private String health;
  private String age;

  public final String HEALTH_HEALTHY = "Healthy";
  public final String HEALTH_FAIR = "Fair";
  public final String HEALTH_ILL = "Ill";

  public final String AGE_ADULT = "Adult";
  public final String AGE_YOUNG = "Young";
  public final String AGE_NEWBORN = "Newborn";

  public final boolean ENDANGERED = true;

  public EndangeredAnimal(String name, String health, String age) {
    this.name = name;
    this.health = health;
    this.age = age;
  }
}
