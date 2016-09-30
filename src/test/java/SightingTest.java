import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Sighting_instantiatesCorrectly_true() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void getLocation_instantiatesWithZone_Zone_A() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    assertEquals("Zone A", testSighting.getLocation());
  }

  @Test
  public void getRanger_instantiatesWithRanger_Dave() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    assertEquals("Dave", testSighting.getRanger());
  }

  // @Test
  // public void getSightingTime_instantiatesWithSightTime() {
  //   Sighting testSighting = new Sighting("Zone A", "Dave", 1);
  //   Timestamp rightNow = testSighting.getSightingTime();
  //   assertEquals(rightNow.getHour(), testSighting.getSightingTime().getHour());
  // }

  @Test
  public void getAnimalId_instantiatesWithAnimalId_1() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    assertEquals(1, testSighting.getAnimalId());
  }

  @Test
  public void equals_firstSightingEqualsSecondSighting_true() {
    Sighting firstSighting = new Sighting("Zone A", "Dave", 1);
    Sighting secondSighting = new Sighting("Zone A", "Dave", 1);
    assertTrue(firstSighting.equals(secondSighting));
  }

  @Test
  public void save_savesSighitngIntoDatabase_true() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    testSighting.save();
    assertTrue(Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    Sighting firstSighting = new Sighting("Zone A", "Dave", 1);
    firstSighting.save();
    Sighting secondSighting = new Sighting("Zone B", "Brad", 2);
    secondSighting.save();
    assertTrue(Sighting.all().get(0).equals(firstSighting));
    assertTrue(Sighting.all().get(1).equals(secondSighting));
  }

}
