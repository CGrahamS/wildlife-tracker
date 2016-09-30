import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;
import java.util.List;


public class EndangeredAnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void EndangeredAnimal_instantiatesCorrectly_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk", HEALTH_FAIR, AGE_ADULT);
    assertTrue(testAnimal instancof EndangeredAnimal);
  }

}
