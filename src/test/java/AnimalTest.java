import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;


public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Animal_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Black Tailed Deer");
    assertTrue(testAnimal instanceof Animal);
  }

  @Test
  public void getName_instantiesWithName_Black_Tailed_Deer() {
    Animal testAnimal = new Animal("Black Tailed Deer");
    assertEquals("Black Tailed Deer", testAnimal.getName());
  }

  @Test
  public void getEndangered_instantiateWithEndangeredStatus_false() {
    Animal testAnimal = new Animal("Black Tailed Deer");
    assertEquals(false, testAnimal.getEndangered());
  }

  @Test
  public void equals_firstAnimalSameAsSecondAnimal_true() {
    Animal firstAnimal = new Animal("Black Tailed Deer");
    Animal secondAnimal = new Animal("Black Tailed Deer");
    assertTrue(firstAnimal.equals(secondAnimal));
  }

  @Test
  public void save_animalSavesToDatabase_testAnimal() {
    Animal testAnimal = new Animal("Black Tailed Deer");
    testAnimal.save();
    assertTrue(Animal.all().get(0).equals(testAnimal));
  }

  @Test
  public void all_returnsAllInstancesOfAnimal_true() {
    Animal firstAnimal = new Animal("Black Tailed Deer");
    firstAnimal.save();
    Animal secondAnimal = new Animal("Roosevelt Elk");
    secondAnimal.save();
    assertTrue(Animal.all().get(0).equals(firstAnimal));
    assertTrue(Animal.all().get(1).equals(secondAnimal));
  }
}
