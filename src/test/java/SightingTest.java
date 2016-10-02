import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

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
  public void getSightingTime_recordsSightingTimeOnCreation() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    testSighting.save();
    Timestamp savedSightingTime = Sighting.find(testSighting.getId()).getSightingTime();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(rightNow.getMinutes(), savedSightingTime.getMinutes());
  }

  @Test
  public void getHealth_instantiatesWithHealth_Fair() {
    Sighting testSighting = new Sighting("Zone A", "Dave", Sighting.HEALTH_2, Sighting.AGE_1, 1);
    assertEquals("Fair", testSighting.getHealth());
  }

  @Test
  public void getAge_instantiatesWithAge_Adult() {
    Sighting testSighting = new Sighting("Zone A", "Dave", Sighting.HEALTH_2, Sighting.AGE_1, 1);
    assertEquals("Adult", testSighting.getAge());
  }

  @Test
  public void getAnimalId_instantiatesWithAnimalId_1() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    assertEquals(1, testSighting.getAnimalId());
  }

  @Test
  public void animalEquals_firstSightingEqualsSecondSighting_true() {
    Sighting firstSighting = new Sighting("Zone A", "Dave", Sighting.HEALTH_2, Sighting.AGE_1, 1);
    Sighting secondSighting = new Sighting("Zone A", "Dave", Sighting.HEALTH_2, Sighting.AGE_1, 1);
    assertTrue(firstSighting.equals(secondSighting));
  }

  @Test
  public void save_savesSightingIntoDatabase_true() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    testSighting.save();
    assertTrue(Sighting.all().get(0).animalEquals(testSighting));
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    Sighting firstSighting = new Sighting("Zone A", "Dave", 1);
    firstSighting.save();
    Sighting secondSighting = new Sighting("Zone B", "Brad", 2);
    secondSighting.save();
    assertTrue(Sighting.all().get(0).animalEquals(firstSighting));
    assertTrue(Sighting.all().get(1).animalEquals(secondSighting));
  }

  @Test
  public void find_returnsSightingWithSameId() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    testSighting.save();
    assertTrue(Sighting.find(testSighting.getId()).animalEquals(testSighting));
  }

  @Test
  public void update_updatesSighting_true() {
    Sighting testSighting = new Sighting("Zone A", "Dave", 1);
    testSighting.save();
    testSighting.update("Zone B", "Brad");
    assertEquals("Zone B", testSighting.getLocation());
    assertEquals("Brad", testSighting.getRanger());
  }

  @Test
  public void delete_deletesSingleSighting_null() {
    Sighting firstSighting = new Sighting("Zone A", "Dave", Sighting.HEALTH_2, Sighting.AGE_1, 1);
    firstSighting.save();
    Sighting secondSighting = new Sighting("Zone B", "Brad", Sighting.HEALTH_2, Sighting.AGE_1, 2);
    secondSighting.save();
    secondSighting.delete();
    assertEquals(firstSighting, Sighting.find(firstSighting.getId()));
    assertEquals(null, Sighting.find(secondSighting.getId()));
  }

  @Test
  public void sighting_cannotBeInstantiedWithEmptyRanger_null() {
    Sighting firstSighting = new Sighting("Zone A", "", Sighting.HEALTH_2, Sighting.AGE_1, 1);
    assertEquals(null, Sighting.find(firstSighting.getId()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void sighting_throwsExceptionIfRangerIsEmpty() {
    Sighting firstSighting = new Sighting("Zone A", "", Sighting.HEALTH_2, Sighting.AGE_1, 1);
    firstSighting.save();
  }

}
