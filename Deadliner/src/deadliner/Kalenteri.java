package deadliner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Max
 * @version 28.3.2017
 *
 */
public class Kalenteri {
    
    private final Tehtavat tehtavat = new Tehtavat();
    private final Prioriteetit prioriteetit = new Prioriteetit();
    
    /**Palauttaa tehtävien määrän
     * @return ^
     */
    public int getTehtavia() {
        return tehtavat.getLkm();
    }
    
    /**Palauttaa prioriteettien määrän
     * @return ^
     */
    public int getPrioriteetteja() {
        return prioriteetit.getLkm();
    }
    
    /**Palauttaa tiedostosta luetut prioriteetit
     * @return prioriteetit ArrayList muodossa
     */
    public ArrayList<Prioriteetti> getPrioriteetit() {
        ArrayList<Prioriteetti> lista = new ArrayList<Prioriteetti>();
        for(Prioriteetti x : prioriteetit) {
            lista.add(x);
        }
        return lista;
    }
    
    /**Poistaa syötetyn tehtävän
     * @param tehtava poistettava tehtävä
     */
    public void poistaTehtava(Tehtava tehtava) {
        tehtavat.poista(tehtava);
    }
    
    /**Lisää uuden tehtävän
     * @param tehtava lisättävä tehtävä
     */
    public void lisaa(Tehtava tehtava) {
        tehtavat.lisaa(tehtava);
    }
    
    /** Lisää uuden prioriteetin
     * @param prioriteetti liätäävä prioriteetti
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
        prioriteetit.lisaa(prioriteetti);
    }
    
    /**Palauttaa viitteen i:nteen prioriteettiin
     * @param i indeksi
     * @return viite i:nteen prioriteettiin
     * @throws IndexOutOfBoundsException jos i on ulkopuollella
     */
    public Prioriteetti palautaPrioriteetti(int i) throws IndexOutOfBoundsException {
        return prioriteetit.viittaa(i);
    }
    
    /**Hakee kaikki tiettyä prioriteettia olevat tehtävät
     * @param prioriteetti jolle tehtäviä haetaan
     * @return palauttaa tietorakenteen jossa on viitteet löydettyihin tehtäviin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Kalenteri kalenteri = new Kalenteri();
     * Prioriteetti yksi = new Prioriteetti(), kaksi = new Prioriteetti(), kolme = new Prioriteetti();
     * yksi.luoId(); kaksi.luoId(); kolme.luoId();
     * int id1 = yksi.getId();
     * int id2 = kaksi.getId();
     * Tehtava yksiT = new Tehtava(1); kalenteri.lisaa(yksiT);
     * Tehtava kaksiT = new Tehtava(3); kalenteri.lisaa(kaksiT);
     * Tehtava kolmeT = new Tehtava(2); kalenteri.lisaa(kolmeT);
     * Tehtava neljaT = new Tehtava(1); kalenteri.lisaa(neljaT);
     * Tehtava viisiT = new Tehtava(3); kalenteri.lisaa(viisiT);
     * 
     * List<Tehtava> loydetyt;
     * loydetyt = kalenteri.haeTehtavat(kolme);
     * loydetyt.size() === 2;
     * loydetyt = kalenteri.haeTehtavat(yksi);
     * loydetyt.size() === 2;
     * loydetyt.get(0) == kaksiT === false;
     * loydetyt.get(1) == viisiT === true;
     * </pre>
     */
    public List<Tehtava> haeTehtavat(Prioriteetti prioriteetti) {
        if (prioriteetti == null) return tehtavat.haeTehtavat(0);
        return tehtavat.haeTehtavat(prioriteetti.getId());
    }
    
    /**
     * @param hakusana hakuehto
     * @param k //
     * @return tietorakenteen löytyneistä tehtävistä
     * @throws Poikkeus poikkeuksen sattuessa
     */
    public Collection<Tehtava> haeTehtavat(String hakusana, int k) throws Poikkeus {
        return tehtavat.haeTehtavat(hakusana, k);
    }
    
    /**Palauttaa prio:a vastaavat tehtävät
     * @param prio prioriteettiId
     * @return palauttaa vastaavat tethtavat Collection<Tehtava> muodossa
     */
    public Collection<Tehtava> lataa(int prio) {
        return tehtavat.lataaTehtavat(prio);
    }
    
    /**
     * @param hakusana //
     * @return //
     */
    public Collection<Tehtava> tehtavaHaku(String hakusana) {
        return tehtavat.haeTehtavat(hakusana);
    }
    
    /**Lukee tiedot tiedostosta
     * @param nimi tiedoston nimi
     * @throws Poikkeus jos lukeminen ei toimi
     */
    public void lueTiedostosta(String nimi) throws Poikkeus {
        if (nimi == "tehtavat") tehtavat.lueTiedostosta(nimi);
        else if (nimi == "prioriteetit") prioriteetit.lueTiedostosta(nimi);
    }
    
    /**Tallenna tiedot tiedostoon
     * @throws Poikkeus jos tallentaminen ei toimi
     */
    public void tallennus() throws Poikkeus {
        String virhenimi = "";
        try {
            tehtavat.tallenna();
        } catch (Poikkeus e) {
            virhenimi = e.getMessage();
        }
//        
//        try {
//            prioriteetit.tallenna();
//        } catch (Poikkeus e) {
//            virhenimi += e.getMessage();
//        }
        if ("".equals(virhenimi) == false) throw new Poikkeus(virhenimi);
    }
    
    /** Testipääohjelma
     * @param args //
     */
    public static void main(String[] args) {
        Kalenteri kalenteri = new Kalenteri();
        
        try {
            Prioriteetti yksi = new Prioriteetti(), kaksi = new Prioriteetti();
            yksi.luoId();
            yksi.lisaaTestiarvotP();
            kaksi.luoId();
            kaksi.lisaaTestiarvotP();
            
            kalenteri.lisaa(yksi);
            kalenteri.lisaa(kaksi);
            int id1 = yksi.getId();
            int id2 = kaksi.getId();
            Tehtava yksiT = new Tehtava(id1); yksiT.lisaaTestiarvot(id1); kalenteri.lisaa(yksiT);
            Tehtava kaksiT = new Tehtava(id1); kaksiT.lisaaTestiarvot(id1); kalenteri.lisaa(kaksiT);
            Tehtava kolmeT = new Tehtava(id2); kolmeT.lisaaTestiarvot(id2); kalenteri.lisaa(kolmeT);
            Tehtava neljaT = new Tehtava(id2); neljaT.lisaaTestiarvot(id2); kalenteri.lisaa(neljaT);
            Tehtava viisiT = new Tehtava(id2); viisiT.lisaaTestiarvot(id2); kalenteri.lisaa(viisiT);
            
            for (int i = 0; i < kalenteri.getPrioriteetteja(); i++) {
                Prioriteetti prioriteetti = kalenteri.palautaPrioriteetti(i);
                System.out.println("Prioriteetti paikassa: " + i);
                prioriteetti.tulosta();
                List<Tehtava> loydetyt = kalenteri.haeTehtavat(prioriteetti);
                for (Tehtava tehtava : loydetyt)
                    tehtava.tulosta();
            }
            
        } catch (Poikkeus e) {
            System.out.println(e.getMessage());
        }

    }

}
