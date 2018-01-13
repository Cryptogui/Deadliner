package deadliner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Max
 * @version 28.3.2017
 *
 */
public class Tehtavat implements Iterable<Tehtava> {

    private String              tiedNimi  = "";
    private final Collection<Tehtava> alkiot = new ArrayList<Tehtava>();
    private String              tiedPNimi = "tehtavat";
    /**
     * Alustaminen
     */
    public Tehtavat() {
        //
    }
    
    /**Palauttaa tehtävien lukumäärän
     * @return ^
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**Lisää tehtävän
     * @param tehtava lisättävä tehtävä
     */
    public void lisaa(Tehtava tehtava) {
        alkiot.add(tehtava);
    }
    
    /**Poistaa valitun tehtävän
     * @param tehtava poistettava tehtävä
     */
    public void poista(Tehtava tehtava) {
        alkiot.remove(tehtava);
    }
    
    /**Lukee tiedot tiedostosta
     * @param hakemisto luettavan tidoston hakemisto
     * @throws Poikkeus //
     */
    public void lueTiedostosta(String hakemisto) throws Poikkeus {
        alkiot.clear();
        tiedNimi = hakemisto + ".har";
        try ( BufferedReader ti = new BufferedReader(new FileReader(getTiedNimi()))) {
            String rivi;
            while ((rivi = ti.readLine()) != null) {
                rivi.trim();
                if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
                Tehtava teh = new Tehtava();
                teh.parse(rivi);
                lisaa(teh);
            }
        }catch ( FileNotFoundException e) {
            throw new Poikkeus("Tiedosto " + getTiedNimi() + " ei aukea.");
        }catch ( IOException e) {
            throw new Poikkeus("Tiedostovika");
        }
        
    }
    
    
    /**Palauttaa tallennukseen käytettävän tiedostonimen
     * @return tiedoston nimi
     */
    public String getTiedPNimi() {
        return tiedPNimi;
    }
    
    /**Asettaa PNimen ilman tarkenninta
     * @param nimi tiedoston PNimi
     */
    public void setTiedPNImi(String nimi) {
        tiedPNimi = nimi;
    }
    
    /**Palauttaa tallennukseen käytettävän tiedoston nimen
     * @return tiedoston nimi
     */
    public String getTiedNimi() {
        return getTiedPNimi() + ".dat";
    }
    
    /**Tallentaa tiedot tiedostoon
     * @throws Poikkeus //
     */
    public void tallenna() throws Poikkeus {
        
        File tied = new File(getTiedNimi());
        try (PrintWriter wr = new PrintWriter(tied.getCanonicalPath())) {
            for (Tehtava teh : this) {
                wr.println(teh.toString());
            }
        }catch ( FileNotFoundException e) {
            throw new Poikkeus("Tiedosto " + tied.getName() + " ei aukea.");
        }catch ( IOException e) {
            throw new Poikkeus("Tiedoston " + tied.getName() + " kirjoitusongelma.");
        }
    }

    /**Iteraattori kaikkien tehtävien läpikäymiseen
     * @return tehtavaiteraattori
     * 
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     * Tehtavat teet = new Tehtavat();
     * Tehtava yksi = new Tehtava(1); teet.lisaa(yksi);
     * Tehtava kaksi = new Tehtava(3); teet.lisaa(kaksi);
     * Tehtava kolme = new Tehtava(2); teet.lisaa(kolme);
     * Tehtava nelja = new Tehtava(1); teet.lisaa(nelja);
     * Tehtava viisi = new Tehtava(3); teet.lisaa(viisi);
     * 
     * Iterator<Tehtava> i2 = teet.iterator();
     * i2.next() === yksi;
     * i2.next() === kaksi;
     * i2.next() === kolme;
     * i2.next() === nelja;
     * i2.next() === viisi;
     * i2.next() === nelja; #THROWS NoSuchElementException
     * 
     * int n = 0;
     * int tnrot[] = {1,3,2,1,3};
     * 
     * for ( Tehtava teh : teet ) {
     *  teh.getId() === tnrot[n]; n++;
     * }
     * 
     * n === 5;
     * </pre>
     */
    @Override
    public Iterator<Tehtava> iterator() {
        return alkiot.iterator();
    }
    
    /**Hakee kaikki tiettyä prioriteettia olevat tehtävät
     * @param id prioiriteetin tunnus
     * @return palauttaa tietorakenteen jossa on viitteet löydettyihin tehtäviin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Tehtavat teet = new Tehtavat();
     * Tehtava yksi = new Tehtava(1); teet.lisaa(yksi);
     * Tehtava kaksi = new Tehtava(3); teet.lisaa(kaksi);
     * Tehtava kolme = new Tehtava(2); teet.lisaa(kolme);
     * Tehtava nelja = new Tehtava(1); teet.lisaa(nelja);
     * Tehtava viisi = new Tehtava(3); teet.lisaa(viisi);
     * 
     * List<Tehtava> loydetyt;
     * loydetyt = teet.haeTehtavat(0);
     * loydetyt.size() === 0;
     * loydetyt = teet.haeTehtavat(3);
     * loydetyt.size() === 2;
     * loydetyt.get(0) == kaksi === true;
     * loydetyt.get(1) == viisi === true;
     * </pre>
     */
    public List<Tehtava> haeTehtavat(int id) {
        List<Tehtava> loytyneet = new ArrayList<Tehtava>();
        for (Tehtava tehtava : alkiot)
            if (tehtava.getId() == id) loytyneet.add(tehtava);
        return loytyneet;
    }
    
    /**Tekstihaku
     * @param hakusana hakusana
     * @param k haettava indeksi (tarpeeton?)
     * @return tietorakenteen löydetyistä tehtävistä
     */
    public Collection<Tehtava> haeTehtavat(String hakusana, int k) {
        Collection<Tehtava> loydetyt = new ArrayList<Tehtava>();
        for (Tehtava tehtava : this) {
            loydetyt.add(tehtava);
        }
        return loydetyt;
    }
    
    /**Palauttaa kaikki tehtävät joilla on prio:a vastaava prioriteettiId
     * @param prio etsittävä prioriteettiId
     * @return prio:a vastaavat tehtävät Collection<Tehtava> muodossa
     */
    public Collection<Tehtava> lataaTehtavat(int prio) {
        Collection<Tehtava> vastaavat = new ArrayList<Tehtava>();
        for (Tehtava tehtava : alkiot)
            if (tehtava.getPrioriteettiId() == prio) vastaavat.add(tehtava);
        return vastaavat;
    }
    
    /**Tekstihaku
     * @param hakusana hakusana
     * @return tietorakenteen löydetyistä tehtävistä
     */
    public Collection<Tehtava> haeTehtavat(String hakusana) {
        Collection<Tehtava> loydetyt = new ArrayList<Tehtava>();
        for (Tehtava tehtava : alkiot) {
            String nimi = tehtava.getNimi();
            String hs = hakusana.toLowerCase();
            String nm = nimi.toLowerCase();
            char[] hakukirjaimet = hs.toCharArray();
            char[] kirjaimet = nm.toCharArray();
            boolean vastaa = true;
            for (int i = 0; i < hakukirjaimet.length; i++) {
                if (hakukirjaimet[i] == kirjaimet[i]) continue;
                vastaa = false;
            }
            if (vastaa == true) loydetyt.add(tehtava);
        }
        return loydetyt;
    }
    
    /**Testipääohjelma
     * @param args //
     */
    public static void main(String[] args) {
        Tehtavat teet = new Tehtavat();
        Tehtava yksi = new Tehtava();
        yksi.lisaaTestiarvot(2);
        Tehtava kaksi = new Tehtava();
        kaksi.lisaaTestiarvot(1);
        Tehtava kolme = new Tehtava();
        kolme.lisaaTestiarvot(2);
        
        teet.lisaa(yksi);
        teet.lisaa(kaksi);
        teet.lisaa(kolme);
        teet.lisaa(kaksi);
        
        System.out.println("");
        
        List<Tehtava> tehtavat2 = teet.haeTehtavat(2);
        
        for (Tehtava teh : tehtavat2) {
            System.out.println(teh.getId() + " ");
            teh.tulosta();
        }
    }
}
