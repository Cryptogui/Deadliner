package fxDeadliner;

import java.util.Collection;
import java.util.stream.Stream;
import fi.jyu.mit.fxgui.*;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import deadliner.Tehtava;
import deadliner.Kalenteri;
import deadliner.Poikkeus;
import deadliner.Prioriteetti;

/**
 * @author Max
 * @version 17.2.2017
 *
 */
public class DeadlinerGUIController {

    /**
     * Paaikkuna
     */
    @FXML private TextField hakuLaatikko;
    @FXML private ListChooser<Tehtava> hakuTulokset;
    @FXML private MenuItem suljeNappi;
    @FXML private CheckBox suodMyohassa;
    @FXML private CheckBox suodTarkeat;
    @FXML private CheckBox suodNormaalit;
    @FXML private CheckBox suodTeeJosEhdit;
    @FXML private CheckBox suodEipakollinen;
    @FXML private TextArea infoKentta;
    @FXML private DatePicker pvmLaatikko;
    @FXML private TextField nimiLaatikko;
    @FXML private ChoiceBox<Prioriteetti> prioriValikko;
    @FXML private ListChooser<Tehtava> tehtavaLista;
    
    /**
     * avaa tietonakyman
     */
    @FXML void avaaAloitus() {
        avaa();
        ModalController.showModal(DeadlinerGUIController.class.getResource("DeadlinerStartUp.fxml"), "Aloitus", null, "");
    }
    
    @FXML private void haku() {
        String hakusana = hakuLaatikko.getText();
        hae(hakusana);
    }
    
    /**
     * luo uuden tyhjan tehtavan
     */
    @FXML private void luoTehtava() {
        luoUusiTehtava();
    }
    
    /**
     * poistaa valitun tehtavan
     */
    @FXML private void poistaTehtava() {
        String poisto = ModalController.showModal(DeadlinerGUIController.class.getResource("DeadlinerPoistaminen.fxml"), "Poistaminen", null, "false");
        if (poisto == "true") poistaminen();
        }
    
    /**
     * sulkee ohjelman
     */
    @FXML private void suljeOhjelma() {
        tallennus();
        Platform.exit();
    }
    
    /**
     * tallentaa muutokset
     */
    @FXML private void tallenna() {
        tallennus();
    }
    
    @FXML void tehtavaValittu() {
        taytaLaatikot();
    }
    
    @FXML void tehtavaValittuHT() {
        taytaLaatikotHT();
    }
    
    /**
     * 
     */
    public void initialize() {
        //avaa();
    }

    /**
     * 
     */
    public DeadlinerGUIController() { //Suoritetaan ennen @FXML merkittyjä
        // avaa();
        // ModalController.showModal(DeadlinerGUIController.class.getResource("DeadlinerStartUp.fxml"), "Aloitus", null, "");
    }
    
    //-------------------------------------------------------------------
    
    private Kalenteri kalenteri;
    private Tehtava tehtava;
    private boolean prioHaettu = false;
    
    /**
     * @param kalenteri //
     */
    public void setKalenteri(Kalenteri kalenteri) {
        this.kalenteri = kalenteri;
    }
    
    /**Hakee tehtävät listaan
     * 
     */
    public void avaa() {
        suodMyohassa.setIndeterminate(false);
        suodTarkeat.setIndeterminate(false);
        suodNormaalit.setIndeterminate(false);
        suodTeeJosEhdit.setIndeterminate(false);
        suodEipakollinen.setIndeterminate(false);
        tehtavaLista.clear();
        lueTiedosto("tehtavat");
        lueTiedosto("prioriteetit");
        Stream<Tehtava> a = tarkistaNapit();
        if (prioHaettu == false) prioriValikko.getItems().addAll(kalenteri.getPrioriteetit());
        prioHaettu = true;
    }
    
    /**
     * @param nimi //
     * @return //
     */
    protected String lueTiedosto(String nimi) {
        try {
            kalenteri.lueTiedostosta(nimi);
            if (nimi == "tehtavat") naytaTehtavat(0);
            return null;
        }catch (Poikkeus e) {
            if (nimi == "tehtavat") naytaTehtavat(0);
            String virhe = e.getMessage();
            if (virhe != null) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
    }
    
    /**
     * 
     */
    public void poistaminen() {
        Tehtava poistettava = tehtavaLista.getSelectedObject();
        if (poistettava == null) return;
        kalenteri.poistaTehtava(poistettava);
        tallennus();
        avaa();
        
    }
    /**
     * Tallentaa tiedot
     * @return virheteksti
     */
    public String tallennus() {
        try {
            asetaArvot();
            kalenteri.tallennus();
            avaa();
            return null;
        } catch (Poikkeus e) {
            Dialogs.showMessageDialog("Tallennusongelma: " + e.getMessage());
            return e.getMessage();
        }
    }
    
    /**
     * @param tNro //
     * 
     */
    public void naytaTehtavat(int tNro) {
        tehtavaLista.clear();
        int i = 0;
        int index = 0;
        Collection<Tehtava> tehtavat;
        try {
            tehtavat = kalenteri.haeTehtavat("", 0);
            for(Tehtava x : tehtavat) {
                if (x.getId() == tNro) index = i;
                tehtavaLista.add(x.getNimi(), x);
                i++;
            }
        } catch (Poikkeus e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tehtavaLista.setSelectedIndex(index);
    }
    
    /**Syöttää tiedot muokkauslaatikoihin
     * aktivoituu tehtävälistasta
     */
    public void taytaLaatikot() {
        nimiLaatikko.clear();
        infoKentta.clear();
        Tehtava valittu = tehtavaLista.getSelectedObject();
        nimiLaatikko.insertText(0, valittu.getNimi());
        infoKentta.insertText(0, valittu.getTiedot());
        pvmLaatikko.setValue(valittu.getDeadline());
        int prioNro = valittu.getPrioriteettiId();
        prioriValikko.setValue(kalenteri.palautaPrioriteetti(prioNro));
    }
    
    /**Syöttää tiedot muokkauslaatikoihin
     * aktivoituu hakutulosten kautta
     */
    public void taytaLaatikotHT() {
        nimiLaatikko.clear();
        infoKentta.clear();
        Tehtava valittu = hakuTulokset.getSelectedObject();
        nimiLaatikko.insertText(0, valittu.getNimi());
        infoKentta.insertText(0, valittu.getTiedot());
        pvmLaatikko.setValue(valittu.getDeadline());
        int prioNro = valittu.getPrioriteettiId();
        prioriValikko.setValue(kalenteri.palautaPrioriteetti(prioNro));
    }
    
    /**
     * Vie kenttien arvot tehtävän tietoihin
     */
    public void asetaArvot() {
        Tehtava valittu = tehtavaLista.getSelectedObject();
        valittu.setNimi(nimiLaatikko.getText());
        valittu.setDeadline(pvmLaatikko.getValue());
        valittu.setTiedot(infoKentta.getText());
        Prioriteetti nyt = prioriValikko.getValue();
        valittu.setPrioriteettiId(nyt.getId());
    }
    
    /**
     * @param hakusana sana jolla haetaan
     */
    public void hae(String hakusana) {
        hakuTulokset.clear();
        int index = 0;
        Collection<Tehtava> tehtavat;
        tehtavat = kalenteri.tehtavaHaku(hakusana);
        for(Tehtava x : tehtavat) {
            hakuTulokset.add(x.getNimi(), x);
        }

        hakuTulokset.setSelectedIndex(index);
    }
    
    /**
     * Tarkistaa suodatinnappien tilan
     * @return palauttaa collectionin tehtävistä suodatinnappien tilan mukaan
     */
    public Stream<Tehtava> tarkistaNapit() {
        BooleanProperty myoh = suodMyohassa.selectedProperty();
        boolean myohassa = myoh.get();
        Collection<Tehtava> a = null;
        BooleanProperty tark = suodTarkeat.selectedProperty();
        boolean tarkea = tark.get();
        Collection<Tehtava> b = null;
        BooleanProperty norm = suodNormaalit.selectedProperty();
        boolean normaali = norm.get();
        Collection<Tehtava> c = null;
        BooleanProperty tje = suodTeeJosEhdit.selectedProperty();
        boolean teejosehdit = tje.get();
        Collection<Tehtava> d = null;
        BooleanProperty eipakk = suodEipakollinen.selectedProperty();
        boolean eipakoll = eipakk.get();
        Collection<Tehtava> e = null;
        
        if (myohassa == true) {
            a = kalenteri.lataa(0);
        }
        if (tarkea == true) {
            b = kalenteri.lataa(1);
        }
        if (normaali == true) {
            c = kalenteri.lataa(2);
        }
        if (teejosehdit == true) {
            d = kalenteri.lataa(3);
        }
        if (eipakoll == true) {
            e = kalenteri.lataa(4);
        }
        
        return Stream.of(a,b,c,d,e).flatMap(Collection::stream);
        
    }
    
    /**
     * Luo uuden tehtävän jossa on mallidataa
     */
    public void luoUusiTehtava() {
        if (tehtava != null ) return;
        Tehtava uusi = new Tehtava();
        uusi.luoId();
        uusi.lisaaTestiarvot(0);
        kalenteri.lisaa(uusi);
        naytaTehtavat(0);
    }
}
