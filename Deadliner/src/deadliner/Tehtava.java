package deadliner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Max
 * @version 28.3.2017
 *
 */
public class Tehtava {
    private int     id;
    private String  nimi        = "";
    private String  deadline    = "";
    private String  lisatiedot  = "";
    private int     prioritId;
    
    private static int seuraavaId = 1;
    
    /**
     * Alustus
     */
    public Tehtava() {
        //
    }
    
    /**Alustetaan tietyn prioriteetin tehtävä
     * @param id prioriteetin viitenro
     */
    public Tehtava(int id) {
        this.id = id;
    }

    /**Hakee tehtävän nimen
     * @return tehtävän nimi
     * @example
     * <pre name="test">
     * Tehtava yksi = new Tehtava();
     * yksi.lisaaTestiarvot(1);
     * yksi.getNimi() =R= "HT Vaihe.*";
     * </pre>
     */
    public String getNimi() {
        return nimi;
    }
    
    /**Asettaa syötetyn String:in tehtävän nimeksi
     * @param stringNimi syötetty String muotoinen nimi
     */
    public void setNimi(String stringNimi) {
        this.nimi = stringNimi;
    }
    
    /**Hakee tehtävän lisätiedot
     * @return lisätiedot String muodossa
     */
    public String getTiedot() {
        return lisatiedot;
    }
    
    /**Asettaa syötetyn string:in lisatiedot kohdan arvoksi
     * @param teksti syötetty string
     */
    public void setTiedot(String teksti) {
        this.lisatiedot = teksti;
    }
    
    /**Palauttaa deadlinen
     * @return deadline
     */
    public LocalDate getDeadline() {
        return pvmMuunnos(deadline);
    }
    
    /**Asettaa syötetyn LocalDate muotoisen päivämäärän deadline kohdan arvoksi
     * @param pvm päivämäärä LocalDate muodossa
     */
    public void setDeadline(LocalDate pvm) {
        this.deadline = pvmTakaisin(pvm);
    }
    
    /**muuntaa string muodossa olevan pvm:n LocalDate muotoon
     * @param pvmString string muodossa syötetty pvm "dd-mm-yyyy"
     * @return pvm LocalDate muodossa
     */
    public static LocalDate pvmMuunnos(String pvmString) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate pvm = LocalDate.parse(pvmString, f);
        return pvm;
    }
    
    /**Muuntaa syötetyn DateTime päivämäärän String muotoon
     * @param pvm DateTime muodossa syötetty pvm
     * @return String muotoinen pvm (dd-MM-yyyy)
     */
    public static String pvmTakaisin(LocalDate pvm) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String pvmString = pvm.format(f);
        return pvmString;
    }
    
    /**Antaa seuraavan id:n uutta tehtävää varten
     * @return palautaa seuraavan id:n
     * @example
     * <pre name="test">
     *  Tehtava yksi = new Tehtava();
     *  yksi.getId() === 0;
     *  yksi.luoId();
     *  Tehtava kaksi = new Tehtava();
     *  kaksi.luoId();
     *  int n1 = yksi.getId();
     *  int n2 = kaksi.getId();
     *  n1 === n2 - 1;
     * </pre>
     */
    public int luoId() {
        this.id = seuraavaId;
        seuraavaId += 1;
        return id;
    }
    
    /**Palauttaa tehtävän id:n
     * @return tehtävän id
     */
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return getId() + "|" + nimi + "|" + deadline + "|"+ lisatiedot +"|" + prioritId;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
    /**Palauttaa Tehtävän prioriteetin
     * @return prioriteetin tunnus
     */
    public int getPrioriteettiId() {
        return prioritId;
    }
    
    /**Asettaa tehtävän prioriteetin
     * @param pId syötetty int muotoinen prioriteetin id
     */
    public void setPrioriteettiId(int pId) {
        this.prioritId = pId - 1;
    }
    
    private void setId(int numero) {
        id = numero;
        if (id >= seuraavaId) seuraavaId = id + 1;
    }
    
    /**
     * @param rivi //
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setId(Mjonot.erota(sb, '|', getId()));
        nimi = Mjonot.erota(sb, '|', nimi);
        deadline = Mjonot.erota(sb, '|', nimi);
        lisatiedot = Mjonot.erota(sb, '|', lisatiedot);
        prioritId = Mjonot.erota(sb, '|', prioritId);
    }
    
    
    
    //VV---Testaus tästä alaspäin----VV
    
    /**Testipääohjelma
     * @param args //
     */
    public static void main(String args[]) {
        Tehtava yksi = new Tehtava(), kaksi = new Tehtava();
        yksi.luoId();
        kaksi.luoId();
        yksi.tulosta();
        yksi.lisaaTestiarvot(1);
        yksi.tulosta();
        
        kaksi.lisaaTestiarvot(2);
        kaksi.tulosta();
    }
    
    /**Palauttaa satunnaisen kokonaisluvun min ja max arvon väliltä (min ja max mukaanlukien)
     * @param min alaraja
     * @param max yläraja
     * @return satunnainen kokonaisluku
     */
    public int rand(int min, int max) {
        int numero = ThreadLocalRandom.current().nextInt(min, max + 1);
        return numero;
    }
    
    /**Täyttää testiarvot Tehtävälle
     * @param nro viite tehtävän prioriteettiin
     * 
     */
    public void lisaaTestiarvot(int nro) {
        nimi = "HT vaihe " + rand(1, 10);
        deadline = "30-03-2017";
        lisatiedot = "jotain lisätietoa";
        prioritId = nro;
    }
    
    
    /**
     * Tulostaa Tehtävän tiedot
     */
    public void tulosta() {
        System.out.println("nimi: " + nimi);
        System.out.println("Deadline: " + deadline);
        System.out.println("Lisätiedot: " + lisatiedot);
        System.out.println("Prioriteetti: " + prioritId);
    }
}
