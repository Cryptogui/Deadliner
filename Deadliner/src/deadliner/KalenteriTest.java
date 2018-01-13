package deadliner;
// Generated by ComTest BEGIN
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import deadliner.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2017.03.30 03:56:22 // Generated by ComTest
 *
 */
public class KalenteriTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa47 
   * @throws Poikkeus when error
   */
  @Test
  public void testLisaa47() throws Poikkeus {    // Kalenteri: 47
    Prioriteetit prioriteetit = new Prioriteetit(); 
    Prioriteetti yksi = new Prioriteetti(), kaksi = new Prioriteetti(); 
    assertEquals("From: Kalenteri line: 51", 0, prioriteetit.getLkm()); 
    prioriteetit.lisaa(yksi); assertEquals("From: Kalenteri line: 52", 1, prioriteetit.getLkm()); 
    prioriteetit.lisaa(kaksi); assertEquals("From: Kalenteri line: 53", 2, prioriteetit.getLkm()); 
    prioriteetit.lisaa(yksi); assertEquals("From: Kalenteri line: 54", 3, prioriteetit.getLkm()); 
    assertEquals("From: Kalenteri line: 55", yksi, prioriteetit.viittaa(0)); 
    assertEquals("From: Kalenteri line: 56", kaksi, prioriteetit.viittaa(1)); 
    assertEquals("From: Kalenteri line: 57", yksi, prioriteetit.viittaa(2)); 
    assertEquals("From: Kalenteri line: 58", false, prioriteetit.viittaa(1) == yksi); 
    assertEquals("From: Kalenteri line: 59", true, prioriteetit.viittaa(1) == kaksi); 
    try {
    assertEquals("From: Kalenteri line: 60", yksi, prioriteetit.viittaa(3)); 
    fail("Kalenteri: 60 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    prioriteetit.lisaa(yksi); assertEquals("From: Kalenteri line: 61", 4, prioriteetit.getLkm()); 
    prioriteetit.lisaa(yksi); assertEquals("From: Kalenteri line: 62", 5, prioriteetit.getLkm()); 
    try {
    prioriteetit.lisaa(yksi); 
    fail("Kalenteri: 63 Did not throw Poikkeus");
    } catch(Poikkeus _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testHaeTehtavat83 */
  @Test
  public void testHaeTehtavat83() {    // Kalenteri: 83
    Kalenteri kalenteri = new Kalenteri(); 
    Prioriteetti yksi = new Prioriteetti(), kaksi = new Prioriteetti(), kolme = new Prioriteetti(); 
    yksi.luoId(); kaksi.luoId(); kolme.luoId(); 
    int id1 = yksi.getId(); 
    int id2 = kaksi.getId(); 
    Tehtava yksiT = new Tehtava(1); kalenteri.lisaa(yksiT); 
    Tehtava kaksiT = new Tehtava(3); kalenteri.lisaa(kaksiT); 
    Tehtava kolmeT = new Tehtava(2); kalenteri.lisaa(kolmeT); 
    Tehtava neljaT = new Tehtava(1); kalenteri.lisaa(neljaT); 
    Tehtava viisiT = new Tehtava(3); kalenteri.lisaa(viisiT); 
    List<Tehtava> loydetyt; 
    loydetyt = kalenteri.haeTehtavat(kolme); 
    assertEquals("From: Kalenteri line: 98", 2, loydetyt.size()); 
    loydetyt = kalenteri.haeTehtavat(yksi); 
    assertEquals("From: Kalenteri line: 100", 2, loydetyt.size()); 
    assertEquals("From: Kalenteri line: 101", false, loydetyt.get(0) == kaksiT); 
    assertEquals("From: Kalenteri line: 102", false, loydetyt.get(1) == viisiT); 
  } // Generated by ComTest END
}