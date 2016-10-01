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
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk");
    assertTrue(testAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getName_instantiesWithName_Roosevelt_Elk() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk");
    assertEquals("Roosevelt Elk", testAnimal.getName());
  }

  @Test
  public void getType_instantiatesWithEndangered_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk");
    assertEquals(true, testAnimal.getEndangered());
  }

  @Test
  public void equals_firstAnimalSameAsSecondAnimal_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Roosevelt Elk");
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Roosevelt Elk");
    assertTrue(firstAnimal.equals(secondAnimal));
  }

  @Test
  public void save_savesAnimalIntoDatabase_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk");
    testAnimal.save();
    assertTrue(EndangeredAnimal.all().get(0).equals(testAnimal));
  }

  @Test
  public void all_returnsAllInstancesOfEndnageredAnimal_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Roosevelt Elk");
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Gray Wolf");
    secondAnimal.save();
    assertTrue(firstAnimal.equals(EndangeredAnimal.all().get(0)));
    assertTrue(secondAnimal.equals(EndangeredAnimal.all().get(1)));
  }

  @Test
    public void find_returnsEndangeredAnimalWithSameId() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk");
    testAnimal.save();
    assertTrue(EndangeredAnimal.find(testAnimal.getId()).equals(testAnimal));
  }

  @Test
  public void update_updatesEndangeredAnimal_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Roosevelt Elk");
    testAnimal.save();
    testAnimal.update("Gray Wolf");
    assertEquals("Gray Wolf", testAnimal.getName());
  }

  @Test
  public void delete_deletesASingleEndangeredAnimal_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Roosevelt Elk");
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Panda Bear");
    secondAnimal.save();
    secondAnimal.delete();
    assertEquals(firstAnimal, EndangeredAnimal.find(firstAnimal.getId()));
    assertEquals(null, EndangeredAnimal.find(secondAnimal.getId()));
  }

}
