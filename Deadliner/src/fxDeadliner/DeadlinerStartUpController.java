package fxDeadliner;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import deadliner.Tehtava;
import deadliner.Kalenteri;

/**
 * @author Max
 * @version 17.2.2017
 *
 */
public class DeadlinerStartUpController implements ModalControllerInterface<String> {

    @FXML private Button aloitusUusiTehtava;
    @FXML private Button suljeNappi;
    @FXML private CheckBox noStartScreen;

    @FXML void aloitusUusiTehtava() {
        luoUusiTehtava();
    }

    @FXML
    void suljeIkkuna() {
        ModalController.closeStage(suljeNappi);
    }
    
    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    
    private Tehtava tehtava;
    private Kalenteri kalenteri;
    
    /**
     * 
     */
    public void luoUusiTehtava() {
        if (tehtava != null ) return;
        Tehtava uusi = new Tehtava();
        uusi.luoId();
        uusi.lisaaTestiarvot(0);
        kalenteri.lisaa(uusi);
        // naytaTehtavat(0);
        // Dialogs.showMessageDialog("toimii");
        
    }

}
