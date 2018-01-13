package deadliner;

import java.util.concurrent.ThreadLocalRandom;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Max
 * @version 28.3.2017
 *
 */
public class Prioriteetti {
    private int     id;
    private String  nimi    = "";
    
    private static int seuraavaId = 1;
    
    /**
     * Oletusmuodostaja
     */
    public Prioriteetti() {
        //
    }
    
    /**Antaa seuraavan id:n uutta prioriteettiä varten
     * @return palauttaa sauraavan id:n
     * @example
     * <pre name="test">
     *  Prioriteetti yksi = new Prioriteetti();
     *  yksi.getId() === 0;
     *  yksi.luoId();
     *  Prioriteetti kaksi = new Prioriteetti();
     *  kaksi.luoId();
     *  int n1 = yksi.getId();
     *  int n2 = kaksi.getId();
     *  n1 === n2-1;
     * </pre>
     */
    public int luoId() {
        id = seuraavaId;
        seuraavaId += 1;
        return id;
    }
    
    /**
     * @param numero //
     */
    public void setId(int numero) {
        id = numero;
        if (id >= seuraavaId) seuraavaId = id + 1;
    }
    
    /**Palauttaa prioriteetin id:n
     * @return ^
     */
    public int getId() {
        return id;
    }
    
    /**Palauttaa prioriteetin nimen
     * @return ^
     */
    public String getNimi() {
        return nimi;
    }
    
    @Override
    public String toString() {
        return "" + getId() + "|" + nimi;
    }
    
    /**
     * @param rivi //
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setId(Mjonot.erota(sb, '|', getId()));
        nimi = Mjonot.erota(sb, '|', nimi);
    }
    
    @Override
    public boolean equals(Object prioriteetti) {
        if (prioriteetti == null) return false;
        return this.toString().equals(prioriteetti.toString());
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
    //VV---Testaus tästä alaspäin----VV

    /**
     * @param args //
     */
    public static void main(String[] args) {
        Prioriteetti yks = new Prioriteetti(), kaks = new Prioriteetti();
        yks.luoId();
        kaks.luoId();
        yks.tulosta();
        kaks.tulosta();
        
        yks.lisaaTestiarvotP();
        yks.tulosta();
        
        kaks.lisaaTestiarvotP();
        kaks.tulosta();

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
    
    /**
     * Täyttää testiarvot prioriteetille
     */
    public void lisaaTestiarvotP() {
        nimi = "Prioriteetti " + rand( 1, 5);
    }
    
    /**
     * Tulostaa Prioriteetin tiedot
     */
    public void tulosta() {
        System.out.println("Id: " + id);
        System.out.println("Nimi: " + nimi);
    }

}
