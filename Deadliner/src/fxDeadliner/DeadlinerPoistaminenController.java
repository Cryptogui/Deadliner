package fxDeadliner;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Max
 * @version 17.2.2017
 *
 */
public class DeadlinerPoistaminenController implements ModalControllerInterface<String> {


    @FXML private Button poistuNappi;
    
    private String vastaus = "false";
    
    @FXML void poistaTehtavaNappi() {
        vastaus = "true";
        suljeIkkuna();
    }

    @FXML void suljeIkkuna() {
        ModalController.closeStage(poistuNappi);
    }
    
    @Override
    public String getResult() {
        return vastaus;
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
