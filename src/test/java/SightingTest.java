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

  @Test
  public void getSightingTime_instantiatesWithSightingTime() {
    
  }

  @Test
  public void getAnimalId_instantiatesWithAnimalId_1() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    assertEquals(1, testSighting.getAnimalId());
  }

}
