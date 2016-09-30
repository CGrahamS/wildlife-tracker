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
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    assertTrue(testAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getName_instantiesWithName_Roosevelt_Elk() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    assertEquals("Roosevelt Elk", testAnimal.getName());
  }

  @Test
  public void getType_instantiatesWithEndangered_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    assertEquals("Endangered", testAnimal.getType());
  }

  @Test
  public void getHealth_instantiatesWithHealth_Fair() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    assertEquals("Fair", testAnimal.getHealth());
  }

  @Test
  public void getAge_instantiatesWithAge_Fair() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    assertEquals("Adult", testAnimal.getAge());
  }

  @Test
  public void equals_firstAnimalSameAsSecondAnimal_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    assertTrue(firstAnimal.equals(secondAnimal));
  }

  @Test
  public void save_savesAnimalIntoDatabase_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    testAnimal.save();
    assertTrue(EndangeredAnimal.all().get(0).equals(testAnimal));
  }

  @Test
  public void all_returnsAllInstancesOfEndnageredAnimal_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Roosevelt Elk", EndangeredAnimal.HEALTH_FAIR, EndangeredAnimal.AGE_ADULT);
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Gray Wolf", EndangeredAnimal.HEALTH_HEALTHY, EndangeredAnimal.AGE_YOUNG);
    secondAnimal.save();
    assertTrue(firstAnimal.equals(EndangeredAnimal.all().get(0)));
    assertTrue(secondAnimal.equals(EndangeredAnimal.all().get(1)));
  }

}
