package deadliner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Max
 * @version 28.3.2017
 *
 */
public class Prioriteetit implements Iterable<Prioriteetti>{
    private static final int    MAX_LKM     = 5;
    private int                 lkm         = 0;
    private Prioriteetti  alkiot[]    = new Prioriteetti[MAX_LKM];
    private String              tiedPNimi   = "prioriteetit";
    
    /**
     * Oletusmuodostaja
     */
    public Prioriteetit() {
        //
    }
    
    /**Palauttaa tehtävien lukumäärän
     * @return ^
     */
    public int getLkm() {
        return lkm;
    }
    
    /**Lisää prioriteetin
     * @param prioriteetti lisättävä prioriteetti
     * @throws Poikkeus //
     * @example
     * <pre name="test">
     * #THROWS Poikkeus
     * Prioriteetit prioriteetit = new Prioriteetit();
     * Prioriteetti yksi = new Prioriteetti(), kaksi = new Prioriteetti();
     * prioriteetit.getLkm() === 0;
     * prioriteetit.lisaa(yksi); prioriteetit.getLkm() === 1;
     * prioriteetit.lisaa(kaksi); prioriteetit.getLkm() === 2;
     * prioriteetit.lisaa(yksi); prioriteetit.getLkm() === 3;
     * prioriteetit.viittaa(0) === yksi;
     * prioriteetit.viittaa(1) === kaksi;
     * prioriteetit.viittaa(2) === yksi;
     * prioriteetit.viittaa(1) == yksi === false;
     * prioriteetit.viittaa(1) == kaksi === true;
     * prioriteetit.viittaa(3) === yksi; #THROWS IndexOutOfBoundsException
     * prioriteetit.lisaa(yksi); prioriteetit.getLkm() === 4;
     * prioriteetit.lisaa(yksi); prioriteetit.getLkm() === 5;
     * prioriteetit.lisaa(yksi);    #THROWS Poikkeus
     * </pre>
     */
    public void lisaa(Prioriteetti prioriteetti) throws Poikkeus {
        if (lkm >= MAX_LKM) throw new Poikkeus("liikaa alkioita");
        alkiot[lkm] = prioriteetti;
        lkm += 1;
    }
    
    /**Palauttaa viitteen prioriteettiin jolla on id "nro"
     * @param nro prioriteetin id
     * @return prioriteetti
     */
    public Prioriteetti getViite(int nro) {
        return alkiot[nro];
    }
    
    /**Palauttaa viitteen i:nteen prioriteettiin
     * @param i monesko prioriteetti
     * @return viite prioriteettiin indeksissä i
     * @throws IndexOutOfBoundsException jos i on ulkopuolella
     */
    public Prioriteetti viittaa(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    /**Lukee tiedot hakemistosta
     * @param hakemisto luettavan tiedoston hakemisto
     * @throws Poikkeus //
     */
    public void lueTiedostosta(String hakemisto) throws Poikkeus {
        if (alkiot[0] == null) {
            setTiedPNimi(hakemisto);
            try (BufferedReader br = new BufferedReader(new FileReader(getTiedNimi()))) {
                String rivi = null;
                while ((rivi = br.readLine()) != null ) {
                    rivi = rivi.trim();
                    if ( "".equals(rivi) || rivi.charAt(0) == ';') continue;
                    Prioriteetti prioriteetti = new Prioriteetti();
                    prioriteetti.parse(rivi);
                    lisaa(prioriteetti);
                }
            }catch ( FileNotFoundException e) {
                throw new Poikkeus("Tiedosto " + getTiedNimi() + "ei aukea");
            }catch ( IOException e ) {
                throw new Poikkeus("Tiedosto ongelma: " + e.getMessage());
            }
        }
    }
    
    /**Tallentaa tiedot tiedostoon
     * @throws Poikkeus //
     */
    public void tallenna() throws Poikkeus {
        File tied = new File(getTiedNimi());
        try (PrintWriter pw = new PrintWriter(new FileWriter(tied.getCanonicalPath()))) {
            pw.println(alkiot.length);
            for (Prioriteetti prioriteetti : this) {
                pw.println(prioriteetti.toString());
            }
        } catch ( FileNotFoundException ex) {
            throw new Poikkeus("Tiedoston " + tied.getName() + " ei aukea.");
        } catch ( IOException ex) {
            throw new Poikkeus("Tiedoston " + tied.getName() + " kirjoitusongelma.");
        }
    }

    /**
     * @return //
     */
    public String getTiedPNimi() {
        return tiedPNimi;
    }
    
    /**
     * @param tiednimi //
     */
    public void setTiedPNimi(String tiednimi) {
        tiedPNimi = tiednimi;
    }
    
    /**
     * @return //
     */
    public String getTiedNimi() {
        return getTiedPNimi() + ".dat";
    }
    
    /**
     * @author Max
     * @version 7.4.2017
     *
     */
    public class PrioritIterator implements Iterator<Prioriteetti> {
        private int i = 0;
        
        /**Tarkistaa onko seuraavaa prioriteettiä olemassa
         * @return true jos on, false jos ei ole
         */
        @Override
        public boolean hasNext() {
            return i < getLkm();
        }
        
        
        /**Palauttaa seuraavan prioriteetin
         * @return seuraavan prioriteetin viite
         * 
         */
        @Override
        public Prioriteetti next() throws NoSuchElementException {
            if (hasNext() == false) throw new NoSuchElementException("ei ole");
            return viittaa(i++);
        }   
    }
    
    /**Palauttaa prioriteettien iteraattorin
     * @return prioriteetti iteraattori
     */
    @Override
    public Iterator<Prioriteetti> iterator() {
        return new PrioritIterator();
    }
}
