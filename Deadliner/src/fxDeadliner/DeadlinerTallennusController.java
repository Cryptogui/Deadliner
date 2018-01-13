package fxDeadliner;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Max
 * @version 17.2.2017
 *
 */
public class DeadlinerTallennusController implements ModalControllerInterface<String> {

    @FXML
    private Button poistuNappi;
    
    @FXML void suljeIkkuna() {
        ModalController.closeStage(poistuNappi);
    }

    @FXML void tallenna() {
        Dialogs.showMessageDialog("Ei voi tallentaa, Ei toimi vielä");
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

}
